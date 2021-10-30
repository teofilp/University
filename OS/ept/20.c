#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>

struct Payload {
	int *pipe;
	int id;
};

pthread_mutex_t mutex;

void* doWork(void* arg) {
	struct Payload *p = (struct Payload*) arg;
	
	int id = p->id;
	int *pipee = p->pipe;
	
	int n1 = random() % 101;
	int n2 = random() % 101;
	
	printf("ID: %d, n1: %d, n2: %d\n", id, n1, n2);	

	pthread_mutex_lock(&mutex);
	write(pipee[1], &id, sizeof(int));
	write(pipee[1], &n1, sizeof(int));
	write(pipee[1], &n2, sizeof(int));
	pthread_mutex_unlock(&mutex);

	free(p);
	return NULL;	
}

int main(int argc, char* argv[]) {
	int n = atoi(argv[1]);
	int t2c[2];
	pipe(t2c);
	pthread_mutex_init(&mutex, NULL);
	pthread_t threads[n];
	srandom(time(0));
	
	if (fork() == 0) {
		for (int i=0; i<n; i++) {
			int id, n1, n2;
			read(t2c[0], &id, sizeof(int));
			read(t2c[0], &n1, sizeof(int));
			read(t2c[0], &n2, sizeof(int));
			int average = (n1 + n2)/2;
			printf("Child process --- Thread Id: %d, Average: %d\n", id, average);
		}
		exit(0);
	}
	
	for (int i=0; i<n; i++) {

		struct Payload *p = malloc(sizeof(struct Payload));
		p->pipe = t2c;
		p->id = i;
		pthread_create(&threads[i], NULL, doWork, p);
	}
	for(int i=0; i<n; i++) {
		pthread_join(threads[i], NULL);
	}
	pthread_mutex_destroy(&mutex);

}
