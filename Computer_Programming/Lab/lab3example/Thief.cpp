#include "Thief.h"
using namespace std;

Thief::Thief(double v1, double v2) {
    setLuck(v1);
    setPower(v2);
    setIntelligence(v1 - 10);
    setDexterity(v1 - 10);
    setStrength(v1 - 10);
}

void Thief::attack() {
    cout << "Thief: attack!! ping ping" << endl;
    cout << "Damage: " << damage() << endl << endl;
}

double Thief::damage() {
    return (getLuck()*4 + getDexterity() + getStrength()*0.1 + getIntelligence() *0.1) * getPower();
}