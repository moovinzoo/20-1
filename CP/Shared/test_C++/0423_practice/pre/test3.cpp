#include <iostream>

class Fishbread
{
    private:
        int cost;
    public:
        void SetCost(int argCost) const;
};

void Fishbread::SetCost(int argCost) const
{
    cost = argCost;
}

using namespace std;

int main() {


    return 0;
}
