#ifndef WARRIOR_H
#define WARRIOR_H

#include "GameCharacter.h"

class Warrior : public GameCharacter {
    public:
        Warrior(double v1 = 10, double v2 = 30);
        void attack();
        double damage();
};

#endif