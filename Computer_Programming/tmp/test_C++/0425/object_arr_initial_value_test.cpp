#include <iostream>
using namespace std;

enum Weight {
  LOW, MEDIUM, HIGH
};

// Types of items a customer can request
enum ItemType {
    SLEEPING_BAG,
    TENT,
    LURE,
    CLOTHING,
    FISHING_ROD,
    COOKING,
    WATER
};

// How many days you want to stay at fishing camp
enum DaysOnCamp {
    ONE, TWO, THREE
};

// All meal types
enum MealType {
    BREAKFAST, LUNCH, DINNER, SNACK
};

class Item {
    private:
        ItemType item_type;
        Weight weight;

    public:
        Item() {
            item_type = SLEEPING_BAG;
            weight = LOW;
        }

        Item(ItemType itemType, Weight weight) {
            item_type = itemType;
            this->weight = weight;
        }

        ItemType getItemType() {
            return item_type;
        }

        void setItemType(ItemType itemType) {
            item_type = itemType;
        }

        Weight getWeight() {
            return weight;
        }

        void setWeight(Weight weight) {
            this->weight = weight;
        }

        int compareTo(Item item) {
            if (item_type == item.getItemType()) return 0;
            if (item_type == SLEEPING_BAG) return 1;
            if (item.getItemType() == SLEEPING_BAG) return -1;
            if (item_type == TENT) return 1;
            if (item.getItemType() == TENT) return -1;
            if (item_type == LURE) return 1;
            if (item.getItemType() == LURE) return -1;
            if (item_type == CLOTHING) return 1;
            if (item.getItemType() == CLOTHING) return -1;
            if (item_type == FISHING_ROD) return 1;
            if (item.getItemType() == FISHING_ROD) return -1;
            if (item_type == COOKING) return 1;
            if (item.getItemType() == COOKING) return -1;
            if (item_type == WATER) return 1;
            if (item.getItemType() == WATER) return -1;
            return -1;
        }

        void print() {
            const char* it;
            const char* w;
            if(item_type == SLEEPING_BAG) {
                it = "SLEEPING_BAG";
            } else if(item_type == TENT) {
                it = "TENT";
            } else if(item_type == LURE) {
                it = "LURE";
            } else if(item_type == CLOTHING) {
                it = "CLOTHING";
            } else if(item_type == FISHING_ROD) {
                it = "FISHING_ROD";
            } else if(item_type == COOKING) {
                it = "COOKING";
            } else if(item_type == WATER) {
                it = "WATER";
            }

            if(weight == LOW) {
                w = "LOW";
            } else if(weight == MEDIUM) {
                w = "MEDIUM";
            } else if(weight == HIGH) {
                w = "HIGH";
            }

            cout << "Item{" <<
                "Item type: " << it << ", Weight: " << w << "}" << endl;
        }

        bool equals(Item item) {
            return getItemType() == item.getItemType() && getWeight() == item.getWeight();
        }
};

int main(void) {
    Item** zones = new Item*[5];
    zones[3] = new Item[1];
    zones[3][0].setItemType(LURE);
    zones[3][0].setWeight(LOW);
    for (int i = 0; i < 5; i++) {

        if (zones[i] == NULL) {
            cout << NULL << endl;
        } else {
            cout << zones[i] << endl;
        }
    }

    return 0;
}
