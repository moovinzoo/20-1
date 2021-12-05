//it is known as include gaurd that prevents multiple declaration of the class in this header file.
#ifndef GAMECHARACTER_H // <- if GAMECHARACTER_H is not defined
#define GAMECHARACTER_H // <- define GAMECHARACTER_H
//If it is already defined then it skips the class declaration part and goes to #endif

#include <iostream>
using namespace std;

class GameCharacter {
    //declaration of double type private variables for GameCharacter (parent, super, or base class)
    private:
        double strength;
        double intelligence;
        double dexterity;
        double luck;
        double power;

    //declaration of methods (aka member function)
    public:
        GameCharacter(); //constructor with no parameter
        GameCharacter(double v1, double v2, double v3, double v4, double v5); //constructor with 5 parameters

        //setter for all 5 member variables
        void setStrength(double v);
        void setIntelligence(double v);
        void setDexterity(double v);
        void setLuck(double v);
        void setPower(double v);

        //getter for all 5 member variables
        double getStrength();
        double getIntelligence();
        double getDexterity();
        double getLuck();
        double getPower();
        //setter and getters for member variables are essential since child class cannot directly access those variables!

        //virtual method that is going to be overriden in child classes!
        virtual void attack(){ //you can define virtual method either in header file or cpp file. Your choice!
            cout << "attack!" << endl;
        }

        virtual double damage() = 0; //= 0; means it is pure virtual method (no definition in parent class, only for overriding!)
};

#endif