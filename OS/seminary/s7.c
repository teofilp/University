#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void work(int reader, int writer, char *name) {
	int current;
	
	read(reader, &current, sizeof(int));
	while(current >= 0) {
		int sub = rand() % 8 + 2;
		current -= sub;
                if (current < 0) {
                        break;
                }
		printf("%s: %d\n", name, current);
		write(writer, &current, sizeof(int));
		read(reader, &current, sizeof(int));
	}
	write(writer, &current, sizeof(int));
	printf("Done with %s\n", name);
}
void init(int fd) {
	int nr = rand() % 900 + 10;
	write(fd, &nr, sizeof(int));
}

int main(int argc, char* argv[]) {
	int a, b, c;
	int a2b[2], b2c[2], c2a[2];
	srand(getpid());
	pipe(a2b);
	pipe(b2c);
	pipe(c2a);

	a = fork();
	if (a == 0) {
		close(b2c[0]);
		close(b2c[1]);
		close(c2a[1]);
		close(a2b[0]);
		init(a2b[1]);
		work(c2a[0], a2b[1], "A");
		close(c2a[0]);
		close(a2b[1]);
	} else if (a > 0) {
		b = fork();
		if (b == 0) {
			close(a2b[1]);
			close(b2c[0]);
			close(c2a[0]);
			close(c2a[1]);
			work(a2b[0], b2c[1], "B");
			close(a2b[0]);
			close(b2c[1]);
		} else if (b > 0) {
			c = fork();
			if (c == 0) {
				close(b2c[1]);
				close(c2a[0]);
				close(a2b[0]);
				close(a2b[1]);
				work(b2c[0], c2a[1], "C");
				close(b2c[0]);
				close(c2a[1]);
			}
			
		}

	}
	waitpid(a, NULL, 0);
	waitpid(b, NULL, 0);
	waitpid(c, NULL, 0);
	return 0;
}
