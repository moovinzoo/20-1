//#ifndef FISHBREAD_H
//#define FISHBREAD_H
#include <iostream>
#include <string>
using namespace std;

class Fishbread
{
    public:
        Fishbread(); Fishbread(int argCost, string argCtent);
        ~Fishbread();
        int getCost();
        void setCost(int c);

    private:
        int cost;
        string content;
};

//#endif
