#include <iostream>
using namespace std;

enum Weight {
  LOW, MEDIUM, HIGH
};

enum ItemType {
    SLEEPING_BAG,
    TENT,
    LURE,
    CLOTHING,
    FISHING_ROD,
    COOKING,
    WATER
};

class Item {
    public:
        Item(ItemType itemType, Weight weight) {
            this->item_type = itemType;
            this->weight = weight;
        }

    private:
        Itemtype item_type;
        Weight weight;
    
};

class Person {
    private:
        Item* item_list;
};

void main() {

}
