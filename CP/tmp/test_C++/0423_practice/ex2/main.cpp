#include <iostream>

using std::cout;
using std::endl;
using std::cin;

void swap(int *a, int *b);
void swap(char *a, char *b);

int main(void) {
    int i1 = 1;
    int i2 = 2;
    swap(&i1, &i2);
    cout << i1 << " " << i2 << endl;

    char c1 = 'a';
    char c2 = 'b';
    swap(&c1, &c2);
    cout << c1 << " " << c2 << endl;

}

void swap (int *a, int *b) {
    int c = *a;
    *a = *b;
    *b = c;
}

void swap (char *a, char *b) {
    char c = *a;
    *a = *b;
    *b = c;
}
