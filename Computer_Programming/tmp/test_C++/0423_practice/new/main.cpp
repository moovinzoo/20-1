#include <iostream>

#include "Fishbread.h"
using namespace std;

int main() {

    Fishbread fish1(500, "red bean");
    cout << "how much? " << fish1.getCost() << endl;
    fish1.setCost(800);
    cout << "how much? " << fish1.getCost() << endl;
   

    return 0;
}
