#include "Printing.h"
#include <iostream>

using namespace std;

Printing::Printing(int a, int b, int c) {
    this->p1 = a;
    this->p2 = b;
    this->p3 = c;
}

void Printing::print() {
    cout << this->p1 << endl;
    cout << this->p2 << endl;
    cout << this->p3 << endl;
}
