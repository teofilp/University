#include <pthread.h>
#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>

void* function(void* ptr);



int main(){
        int n= 5;
        int i;

        pthread_t p[n];
        for(i =0; i<n;i++){
                int* pp = (int*)malloc(sizeof(int));
                *pp = i;
                if(pthread_create(&p[i], NULL, function, pp)!=0){
                        perror("Unable to create philosopher");
                        exit(1);
                }
        }
        for(i=0;i<n;i++){
                pthread_join(p[i], NULL);
        }


        return 0;
}

void* function(void* ptr){
        printf("Philosopher %d started\n", *((int*)ptr));
        free(ptr);
        return NULL;
}
