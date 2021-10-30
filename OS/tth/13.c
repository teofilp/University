#include <stdlib.h>
#include <pthread.h>
#include <stdio.h>
#include <time.h>

int n, *v, idx;
pthread_mutex_t mutex;
pthread_cond_t cond;
	
void* run1(void *arg) {
	pthread_mutex_lock(&mutex);
	while(idx < n) {
		int k = (random() % 50)*2;
		//printf("T1 generated %d...\n", k);
		v[idx++] = k;
		pthread_cond_signal(&cond);
		pthread_cond_wait(&cond, &mutex);
		//printf("T1 unlocked mutex\n");
	}
	pthread_cond_signal(&cond);
	pthread_mutex_unlock(&mutex);
	return NULL;
}

void* run2(void *arg) {
	pthread_mutex_lock(&mutex);
	pthread_cond_wait(&cond, &mutex);
	while (idx < n) {
		int k = (random() % 50)*2 + 1;
		v[idx++] = k;
		pthread_cond_signal(&cond);
		pthread_cond_wait(&cond, &mutex);
	}
	pthread_cond_signal(&cond);
	pthread_mutex_unlock(&mutex);
	return NULL;
}

int main() {
	srandom(time(NULL));
	pthread_t t1, t2;
	scanf("%d", &n);
	v = malloc(sizeof(int)*n);
	pthread_cond_init(&cond, NULL);	
	pthread_mutex_init(&mutex, NULL);
	pthread_create(&t2, NULL, run2, NULL);
	pthread_create(&t1, NULL, run1, NULL);

	pthread_join(t1, NULL);
	pthread_join(t2, NULL);
	pthread_mutex_destroy(&mutex);
	pthread_cond_destroy(&cond);
	
	for (int i=0; i<n; i++) {
		printf("%d\n", v[i]);
	}	

	return 0;
}
