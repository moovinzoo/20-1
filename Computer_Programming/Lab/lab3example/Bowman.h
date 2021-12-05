#ifndef BOWMAN_H
#define BOWMAN_H

// Bowman inherits from Gamecharacter, so gamecharacter.h needs to be incldued here.
#include "GameCharacter.h"

class Bowman : public GameCharacter {
    public:
        Bowman(double v1 = 10, double v2 = 30);
        void attack();
        double damage();
};

#endif