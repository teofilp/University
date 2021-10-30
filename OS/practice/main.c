#include <stdlib.h>
#include <stdio.h>

void printMatrix(int **m, int rows, int cols, char name) {
    int i, j;
    printf("Matrix %c:\n",name);
    for (i = 0; i < rows; i++) {
        for (j = 0; j < cols; j++) {
            printf("%d ", m[i][j]);
        }
        printf("\n");
    }
}

void sum(int **x, int *y, int size) {
    int i;
    y[0] = 0;
    y[1] = 0;`
    for (i = 0; i < size; i++) {
        y[0] += x[i][i];
        y[1] += x[i][size-i-1];
    }
}

int main(int argc, char **argv) {
    int **x = NULL, i, j, n;
    FILE *f = NULL;

    if (argc != 2) {
        printf("Please provide one filename\n");
        exit(0);
    }
	f = fopen(argv[1], "r");
    fscanf(f, "%d", &n);
    x = (int**)malloc(sizeof(int*) * n);
    for (i = 0; i < n; i++) {
        x[i] = (int*)malloc(sizeof(int) * n);
    }
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            fscanf(f, "%d", &x[i][j]);
        }
    }

    fclose(f);

    printMatrix(x, n, n, 'x');

    int *y = malloc(sizeof(int) * 2);
    sum(x, y, n);
    printf("Pri: %d\nSec: %d\n", y[0], y[1]);

    for(i = 0; i < n; i++) {
        free(x[i]);
    }
	free(x);
    free(y);

    return 0;
}
