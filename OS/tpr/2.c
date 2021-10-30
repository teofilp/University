#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {	
	int p2c[2], c2p[2];
	pipe(p2c);
	pipe(c2p);
	
	int f = fork();
	if (f < 0) {
		perror("Error on fork");
	} else if (f == 0) {
		close(p2c[1]);
		close(c2p[0]);
		int n, sum = 0;
		read(p2c[0], &n, sizeof(int));
		printf("read from parent %d\n", n);
		for (int i=0; i<n; i++) {
			int k;
			read(p2c[0], &k, sizeof(int));
			printf("Got %d\n", k);
			sum += k;
		}

		float av = sum / (float) n;
		write(c2p[1], &av, sizeof(float));
		close(p2c[0]);
		close(c2p[1]);
		exit(0);
	} else {
		close(c2p[1]);
		close(p2c[0]);
		int n = atoi(argv[1]);
		write(p2c[1], &n, sizeof(int));
		for (int i=0; i<n; i++) {
			int k = random() % 150;
			write(p2c[1], &k, sizeof(int));			
		}
		float average;
		read(c2p[0], &average, sizeof(float));
		printf("Average is %f\n", average);
		close(p2c[1]);
		close(c2p[0]);
		wait(0);
		exit(0);
	}
	
	return 0;	
}
