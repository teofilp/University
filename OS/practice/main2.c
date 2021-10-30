#include<stdio.h>
#include<string.h>
#include<pthread.h>
#include<stdlib.h>
#include<unistd.h>
#include <time.h>

#define M 5
#define T 6

pthread_t tid[T];
pthread_mutex_t locks[M];
int thread_ids[T];

void checkpoint(checkpointId, threadId) {
	pthread_mutex_lock(&locks[checkpointId]);
	srand(time(NULL));
	int sleepTime = rand() % 100001 + 100000; 
	usleep(sleepTime);
	printf("Checkpoint: %d; Thread: %d \n", checkpointId, threadId);
	pthread_mutex_unlock(&locks[checkpointId]);
}

void* doSomething(void* tid) {
	int id = *((int*)tid);
	for (int i=0; i<M; i++) {
		checkpoint(i, id);
	}	
	return NULL;
}

int main() {

	for (int i=0; i<M; i++) {
		pthread_mutex_init(&locks[i], NULL);
	}
	
	for (int i=0; i<T; i++) {
		thread_ids[i] = i;
		pthread_create(&tid[i], NULL, doSomething, &thread_ids[i]);
	}
	
	for (int i=0; i<T; i++) {
		pthread_join(tid[i], NULL);
	}

	for (int i=0; i<M; i++) {
		pthread_mutex_destroy(&locks[i]);
	}
}
