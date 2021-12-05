#include <iostream>

#include "GameCharacter.h"
#include "Thief.h"
#include "Warrior.h"
#include "Bowman.h"
#include "Magician.h"

using namespace std;

int main() {
    //declaring pointer array of type GameCharacter class of size 4
    GameCharacter* gcArray[4];

    //assign each of index with different types of child class instances!
    //with "new" keyword new instance is created in dynamic memory (heap)
    gcArray[0] = new Warrior(27, 40);
    gcArray[1] = new Bowman(25, 37);
    gcArray[2] = new Thief(24, 41);
    gcArray[3] = new Magician(29, 35);

    cout << "Let's play game! All my characters start attacking monster" << endl << endl;
    
    //because of polymorphism, gcArray[i]->attack() executes different codes depending on its class
    for (int i = 0; i < 4; i++) {
        gcArray[i]->attack();
    }

    return 0;

    //when you try to build program using all these files on the terminal
    //1. go to the directory where all these files reside by using cd command
    //2. type g++ -o (exe name) (all cpp files)
    //3. ./(exe name) then you will see the result of code on the terminal
    //Good luck!


}

