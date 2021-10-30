#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
	int a2b[2], b2c[2], c2a[2];
	pipe(a2b);
	pipe(b2c);
	pipe(c2a);
	
	int bPid = fork();

	if (bPid < 0) {
		perror("Error on fork\n");
		exit(1);
	} else if (bPid == 0) {
		// b
		close(c2a[0]);
		close(c2a[1]);
		close(a2b[1]);
		close(b2c[0]);
		srandom(time(0));
		int n;
		if (0 > read(a2b[0], &n, sizeof(int))) {
			perror("Error B");
			close(b2c[1]);
			close(a2b[0]);
			exit(1);
		}

		if (0 > write(b2c[1], &n, sizeof(int))){
			perror("error B");
			close(b2c[1]);
			close(a2b[0]);
			exit(1);
		}
		for (int i=0; i<n; i++) {
			int error = random() % 4 + 2;
			int k;
			if(0 > read(a2b[0], &k, sizeof(int))) {
				perror("error B");
				close(b2c[1]);
				close(a2b[0]);
				exit(1);
			}
			k += error;
			printf("B - k: %d\n", k);
			if (0 > write(b2c[1], &k, sizeof(int))) {
				perror("error B");
				close(b2c[1]);
				close(a2b[0]);
				exit(1);
			}
		}
		close(a2b[0]);
		close(b2c[1]);
	} else if (bPid > 0) {
		int cPid = fork();
		if (cPid < 0) {
			perror("Error on fork\n");
		} else if (cPid == 0) {
			// c
			close(a2b[0]);
			close(a2b[1]);
			close(c2a[0]);
			close(b2c[1]);
			int n;
			if(0 > read(b2c[0], &n, sizeof(int))) {
				perror("Error C");
				close(c2a[1]);
				close(b2c[0]);
				exit(1);
			}
			printf("C - n: %d\n", n);
			int sum = 0;
			for (int i=0; i<n; i++) {
				int k;
				if (0 > read(b2c[0], &k, sizeof(int))) {
					perror("Error C");
					close(c2a[1]);
					close(b2c[0]);
					exit(1);
				}
				printf("C - k: %d\n", k);
				sum += k;
			}

			if (0 > write(c2a[1], &sum, sizeof(int))){
				perror("Error C");
				close(c2a[1]);
				close(b2c[0]);
				exit(1);
			}
		} else if (cPid > 0) {
			// a
			close(a2b[0]);
			close(b2c[0]);
			close(b2c[1]);
			close(c2a[1]);
			int n;
			printf("Enter a number: ");
			scanf("%d", &n);
			if(0 > write(a2b[1], &n, sizeof(int))) {
				perror("Error A");
				close(a2b[1]);
				close(c2a[0]);
				exit(1);
			}
			for (int i=0; i<n; i++) {
				int k;
				scanf("%d", &k);
				printf("A - k: %d\n", k);
				if (0 > write(a2b[1], &k, sizeof(int))) {
					perror("Error A");
					close(a2b[1]);
	                                close(c2a[0]);
					exit(1);
				}
			}
			int sum;
			if (0 > read(c2a[0], &sum, sizeof(int))) {
				perror("Error A");
				close(a2b[1]);
				close(c2a[0]);
				exit(1);
			}
			printf("result: %d\n", sum);

			close(a2b[1]);
			close(c2a[0]);		
		}
	}
	return 0;
}
