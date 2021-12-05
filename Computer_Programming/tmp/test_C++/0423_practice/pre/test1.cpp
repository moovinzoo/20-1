#include <iostream>

using namespace std;

int func(int a = 0) {
    return a+1;
}

int main() {

    cout << func(11) << endl;
    cout << func() << endl;


    return 0;
}
