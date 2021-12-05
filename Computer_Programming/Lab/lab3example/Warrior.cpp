#include "Warrior.h"
using namespace std;

Warrior::Warrior(double v1, double v2) {
    setStrength(v1);
    setPower(v2);
    setIntelligence(v1 - 10);
    setDexterity(v1 - 10);
    setLuck(v1 - 10);
}

void Warrior::attack() {
    cout << "Warrior: attack!! clang clang" << endl;
    cout << "Damage: " << damage() << endl << endl;
}

double Warrior::damage() {
    return (getStrength()*4 + getDexterity() + getLuck()*0.1 + getIntelligence() *0.1) * getPower();
}