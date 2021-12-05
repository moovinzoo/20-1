#include "Bowman.h"
using namespace std;

//in constructor, use setter for variables inherited from parent 
//since Bowman cannot directly access them (not accessible from child class)
Bowman::Bowman(double v1, double v2) { 
    setDexterity(v1);
    setPower(v2);
    setIntelligence(v1 - 10);
    setStrength(v1 - 10);
    setLuck(v1 - 10);
}

//override virtual method attack() from parent class
void Bowman::attack() { //different implementation of the method!
    cout << "Bowman: attack!! zing zing" << endl;
    cout << "Damage: " << damage() << endl << endl;
}

//override virtual method damage()
double Bowman::damage() {
    return (getDexterity()*4 + getStrength() + getLuck()*0.1 + getIntelligence() *0.1) * getPower();
}