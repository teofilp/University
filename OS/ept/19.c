#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <pthread.h>
#include <string.h>

char *vowels;
int size, sum;

pthread_mutex_t vowels_mut, sum_mut;

void* addVowels(void *arg) {
	char *str = (char*)arg;
	for (int i=0; i<strlen(str); i++) {
		if(strchr("aeiou", str[i]) != NULL) {
			pthread_mutex_lock(&vowels_mut);
			vowels[size++] = str[i];
			pthread_mutex_unlock(&vowels_mut);
		}
	}
	//printf("%s", vowels);
	return NULL;
}

void* addDigits(void *arg) {
	char *str = (char*)arg;
	for (int i=0; i<strlen(str); i++) {
		if (str[i] >= '0' && str[i] <= '9') {
			pthread_mutex_lock(&sum_mut);
			sum += str[i] - '0';		
			pthread_mutex_unlock(&sum_mut);
		}
	}
	//printf("%d", sum);
	return NULL;
}

int main(int argc, char* argv[]) {

	vowels = malloc(sizeof(char)*1000);

	
	int a2p[2], b2p[2];
	pipe(a2p);
	pipe(b2p);
	int Apid = fork();
	if (Apid == 0) {
		// A
		close(b2p[0]);
		close(b2p[1]);
		close(a2p[0]);

		pthread_mutex_init(&vowels_mut, NULL);
		pthread_t threads[argc-1];
		for (int i=0; i<argc-1; i++) {
			pthread_create(&threads[i], NULL, addVowels, argv[i+1]);
		}		

		for (int i=0; i<argc-1; i++) {
			pthread_join(threads[i], NULL);
		}
		pthread_mutex_destroy(&vowels_mut);
		int vow_size = strlen(vowels) + 1;
		write(a2p[1], &vow_size, sizeof(int));
		write(a2p[1], vowels, vow_size);
		close(a2p[1]);
	} else {
		int Bpid = fork();
		if (Bpid == 0) {
			close(a2p[0]);
			close(a2p[1]);
			close(b2p[0]);

			pthread_mutex_init(&sum_mut, NULL);
			pthread_t threads[argc-1];
			for (int i=0; i<argc-1; i++) {
				pthread_create(&threads[i], NULL, addDigits, argv[i+1]);
			}

			for (int i=0; i<argc-1; i++) {
				pthread_join(threads[i], NULL);
			}
			pthread_mutex_destroy(&sum_mut);
		
			write(b2p[1], &sum, sizeof(int));
			close(b2p[1]);
		
		} else {
			close(a2p[1]);
			close(b2p[1]);
			// parent
			int mySum, mySize;
			char *vows = malloc(sizeof(char) * 1000);
		
			read(a2p[0], &mySize, sizeof(int));
			read(a2p[0], vows, mySize);
			read(b2p[0], &mySum, sizeof(int));
		
			printf("%s, %d", vows, mySum);
		}
		
	}
	return 0;
}
