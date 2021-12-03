#include <iostream>
#include "Shape2.h"

using namespace std;

int Rectangle::area() {
    cout << "Rectangle class area :" << endl;
    return (width * height);
}

int Triangle::area() {
    cout << "Triangle class area :" << endl;
    return (width * height / 2);
}
