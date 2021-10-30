#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <math.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

void make_fifo(char *name) {
	printf("%s\n", name);
	if (mkfifo(name, 0600) < 0) {
		perror("error on creating fifo");
		exit(1);
	} 
}

int main(int argc, char *argv[]) {
//	int p2c[2], c2p[2];
//	pipe(p2c);
//	pipe(c2p);
	char *p2cfifo = "./p2c";
	char *c2pfifo = "./c2p";
	make_fifo(p2cfifo);
	make_fifo(c2pfifo);

	int pid = fork();
	if (pid < 0) {
		perror("Error on fork");
		exit(1);
	
	} else if (pid == 0) {
		// child
//		close(p2c[1]);
//		close(c2p[0]);
		int c2p = open(c2pfifo, O_WRONLY);
		int p2c = open(c2pfifo, O_RDONLY);
		srandom(time(0));
		int current = random() % 901 + 100;
		while(1) {
			int next;
			if(0 > read(p2c, &next, sizeof(int))) {
				perror("Error");
//				close(p2c[0]);
//				close(c2p[1]);
				close(p2c);
				close(c2p);	
			}
			int diff = abs(current - next);
			printf("B received %d; different: %d\n", next, diff);
			int stop = diff < 50 ? 1 : 0;
			if (0 > write(c2p, &stop, sizeof(int))) {
				perror("Error");
//				close(p2c[0]);
//				close(c2p[1]);
				close(p2c);
				close(c2p);
			}
			if (stop == 1) {
				break;
			}

		}
//		close(p2c[0]);
//		close(c2p[1]);
		close(c2p);
		close(p2c);
	} else {
		// parent
//		close(p2c[0]);
//		close(c2p[1]);
		int c2p = open(c2pfifo, O_RDONLY);
		int p2c = open(p2cfifo, O_WRONLY);
		int generated = 0;
		srandom(time(0));
		while (1) {
			int stop, next;
			next = random() % 1001 + 50;
			generated++;
			if (0 > write(p2c, &next, sizeof(int))) {
				perror("Error");
//				close(p2c[1]);
//				close(c2p[0]);
				close(c2p);
				close(p2c);
				exit(1);
			}
			if (0 > read(c2p, &stop, sizeof(int))) {
				perror("Error");
//				close(p2c[1]);
//				close(c2p[0]);
				close(c2p);
				close(p2c);
				exit(1);
			}
			if (stop == 1) {
				break;
			}
		}
		printf("Process A has generated %d numbers", generated);
//		close(p2c[1]);
//		close(c2p[0]);
		close(c2p);
		close(p2c);
		unlink(p2cfifo);
		unlink(c2pfifo);
	
	}
	return 0;
}
