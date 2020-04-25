#include <iostream>

using namespace std;

class Fishbread {
    public:
        Fishbread() {
            cout << "create: " << content << endl;
        }
        Fishbread(int argCost, string argCtent) {
            cost = argCost;
            content = argCtent;
            cout << "create: " << content << endl;
        }
        ~Fishbread()
        {
            cout << "we finish eating the <"+content+"> fishbread" << endl;
        }
        int getCost() { return cost; }
        void setCost(int c) { cost = c; }

    private:
        int cost;
        string content;
};



int main() {

    Fishbread fish1(500, "red bean");
    Fishbread *fish2 = new Fishbread(300, "cream");
    cout << "how much? " << fish1.getCost() << endl;
    fish2->setCost(1);
    cout << "how much? " << fish2->getCost() << endl;

    delete(fish2);
    return 0;
}
