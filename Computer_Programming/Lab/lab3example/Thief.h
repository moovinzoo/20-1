#ifndef THIEF_H
#define THIEF_H

#include "GameCharacter.h"

class Thief : public GameCharacter {
    public:
        Thief(double v1 = 10, double v2 = 30);
        void attack();
        double damage();
};

#endif
