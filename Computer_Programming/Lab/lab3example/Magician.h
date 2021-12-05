#ifndef MAGICIAN_H
#define MAGICIAN_H

#include "GameCharacter.h"

class Magician : public GameCharacter {
    public:
        Magician(double v1 = 10, double v2 = 30);
        void attack();
        double damage();
};

#endif