// header file needs to be included in cpp file
#include "GameCharacter.h"

GameCharacter::GameCharacter() {
    strength = 0;
    intelligence = 0;
    luck = 0;
    dexterity = 0;
    power = 0;
}

GameCharacter::GameCharacter(double v1, double v2, double v3, double v4, double v5) {
    strength = v1;
    intelligence = v2;
    luck = v3;
    dexterity = v4;
    power = v5;
}

void GameCharacter::setStrength(double v) {
    strength = v;
}

void GameCharacter::setIntelligence(double v) {
    intelligence = v;
}

void GameCharacter::setDexterity(double v) {
    dexterity = v;
}

void GameCharacter::setLuck(double v) {
    luck = v;
}

void GameCharacter::setPower(double v) {
    power = v;
}

double GameCharacter::getStrength() {
    return strength;
}

double GameCharacter::getIntelligence() {
    return intelligence;
}

double GameCharacter::getDexterity() {
    return dexterity;
}

double GameCharacter::getLuck() {
    return luck;
}

double GameCharacter::getPower() {
    return power;
}

/* If you do not define attack() in header file
you can define it here like below

void GameCharacter::attack() {
    cout << "attack!" << endl;
}

however you do not put virtual keyword in front of void GameCharacter::attack()
You only use virtual keyword in header file (or class declaration)
*/