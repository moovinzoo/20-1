#include "Magician.h"
using namespace std;

Magician::Magician(double v1, double v2) {
    setIntelligence(v1);
    setPower(v2);
    setStrength(v1 - 10);
    setDexterity(v1 - 10);
    setLuck(v1 - 10);
}

void Magician::attack() {
    cout << "Magician: attack!! magic balt!" << endl;
    cout << "Damage: " << damage() << endl << endl;
}

double Magician::damage() {
    return (getIntelligence()*4 + getLuck() + getDexterity()*0.1 + getStrength() *0.1) * getPower();
}