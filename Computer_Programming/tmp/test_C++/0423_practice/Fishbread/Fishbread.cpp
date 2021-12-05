#include "Fishbread.h"
using namespace std;

Fishbread::Fishbread(){}

Fishbread::Fishbread(int argCost, string argCtent) {
    cost = argCost;
    content = argCtent;
}

Fishbread::~Fishbread() {
    cout << "we finish eating the <"+content+"> fishbread" << endl;
}

int Fishbread::getCost() {
    return cost;
}

void Fishbread::setCost(int c) {
    cost = c;
}
