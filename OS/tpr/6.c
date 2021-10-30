#include <stdlib.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

int main(int argc, char *argv[]) {
	int a2b[2], b2a[2];
	pipe(a2b);
	pipe(b2a);
	int aPid = fork();
	if (aPid == 0) {
		//a
		close(a2b[0]);
		close(b2a[1]);
		srandom(time(0));
		int k = random() % 151 + 50;
		while (k >= 5) {
			if (k % 2 == 1) {
				k++;
			}
			write(a2b[1], &k, sizeof(int));
			read(b2a[0], &k, sizeof(int));
			printf("Received from B: %d\n", k);
		} 
		close(a2b[1]);
		close(b2a[0]);
		exit(0);
	} else if (aPid > 0) {
		// parent;
		int bPid = fork();
		if (bPid == 0) {
			// b
			close(a2b[1]);
			close(b2a[0]);
			int k;
			while(1) {
				read(a2b[0], &k, sizeof(int));
				printf("Received from A: %d\n", k);
				k = k / 2;
				write(b2a[1], &k, sizeof(int));
				if (k < 5) {
					break;
				}
			}

			close(b2a[1]);
			close(a2b[0]);
			exit(0);
		}
	}	

}
