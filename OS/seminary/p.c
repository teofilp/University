#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char** argv){
        printf("argc = %d\n", argc);
        //exit(0);
        int c2p[2];
        int len = 255;
        if(pipe(c2p)<0)
        {
                perror("Unable to create pipe");
                exit(2);
        }
        for(int i = 1 ; i < argc ; i++){
                int pid = fork();
                if(pid < 0)
                {
                        perror("Failed to create process");
                        exit(1);
                }
                if(pid == 0)
                {
                        close(c2p[0]);
                        dup2(c2p[1], STDOUT_FILENO);
                        int failed = execl(argv[i], argv[i], NULL);
                        if(failed<0)
                        {
                                perror("Command could not be executed");
				printf("error\n");
				close(c2p[1]);
                                exit(1);
                        }

                }
                //close(c2p[1]);
                char* buff = (char*) malloc(sizeof(char)*len);
                printf("waiting before read\n");
		int b = read(c2p[0], buff, len-1);
		printf("after read b: %d\n", b);
                if (b < 0)
                {
                        perror("Unable to read");
                        exit(4);
                }
                buff[b]=0;
                printf("Received from child: %s", buff);
                //printf("Before wait\n");
                //wait(0);
                //printf("After wait\n");
                //waitpid(pid, 0, 0);

        }
        close(c2p[0]);
        close(c2p[1]);
        return 0;
}
