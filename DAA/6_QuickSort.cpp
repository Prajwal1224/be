#include <iostream>
#include <cstdlib>   // for rand()
#include <ctime>     // for seeding srand()

using namespace std;

int partition(int *arr, int p, int r) {
    int x = arr[r];  // pivot
    int i = p - 1;
    for (int j = p; j < r; j++) {
        if (arr[j] <= x) {
            i++;
            swap(arr[i], arr[j]);
        }
    }
    swap(arr[i + 1], arr[r]);
    return i + 1;
}

// Randomized Partition
int randomPartition(int *arr, int p, int r) {
    int randomIndex = p + rand() % (r - p + 1); // random index between p and r
    swap(arr[randomIndex], arr[r]);             // put random pivot at end
    return partition(arr, p, r);                // call normal partition
}

void randomizedQuickSort(int *arr, int p, int r) {
    if (p < r) {
        int q = randomPartition(arr, p, r);
        randomizedQuickSort(arr, p, q - 1);
        randomizedQuickSort(arr, q + 1, r);
    }
}

int main() {
    srand(time(0)); // seed random generator

    int A[] = {23, 34, 54, 123, 34, 56, 67676, 112};
    int n = sizeof(A) / sizeof(A[0]);

    randomizedQuickSort(A, 0, n - 1);

    cout << "Sorted array: ";
    for (int i : A) {
        cout << i << " ";
    }
    cout << '\n';

    return 0;
}
