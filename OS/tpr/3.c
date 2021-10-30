#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <time.h>

int main(int argc, char *argv[]) {
	int a2b[2], b2a[2];
	pipe(a2b);
	pipe(b2a);
	int aPid = fork();
	if (aPid > 0) {
		// parent
		int bPid = fork();
		if (bPid == 0) {
			// b
			srandom(time(0));
			close(b2a[0]);
			close(a2b[1]);
			while (1) {
				int k = random() % 10 + 1;
				int r;
				write(b2a[1], &k, sizeof(int));
				if (k == 10) {
					break;
				}
				read(a2b[0], &r, sizeof(int));
				printf("Received from A: %d\n", r);
				if (r == 10) {
					break;
				}
			}
			close(b2a[1]);
			close(a2b[0]);
			exit(0);
		}
	} else if (aPid == 0) {
		// a;
		srandom(time(0));
		close(a2b[0]);
		close(b2a[1]);
		while (1) {
			int k, r;
			read(b2a[0], &r, sizeof(int));
			printf("Received from B: %d\n", r);
			
			if (r == 10) {
				break;
			}

			k = random() % 10 + 1;
			write(a2b[1], &k, sizeof(int));
			
			if (k == 10) {
				break;
			}
		}
		close(a2b[1]);
		close(b2a[0]);
		exit(0);
	}
	return 0;
}
