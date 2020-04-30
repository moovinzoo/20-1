#include <iostream>
#include "Backpack.h"
// #include <vector>
// #include <list>
#define INVENTORY_SIZE 42 // Hard-coded the length of inventory's items
#define SORT_OF_ITEMS 7 // Hard-coded the length of inventory's items
#define CNT_ZONES 5

using namespace std;

//TODO: ver.2 ; for not using getter and setter
Backpack::Backpack() {
    //FIXME: Is it needed outside of the scope?
    // I think the answer is YES
    // But it is already decleared as 'new' in class::StoreInventory
    // So no need to instantiated as 'new' keyword in this Constructor

    // Assign member variable storeInventory
    // StoreInventory *si = new StoreInventory();

    this->storeInventory = StoreInventory().item_list;
    // Assign member variable packing_zones
    this->zones = new Item*[CNT_ZONES];
    int number_of_partitions[CNT_ZONES] = {1, 1, 1, 2, 2};
    for (int i = 0; i < CNT_ZONES; i++) {
        this->zones[i] = new Item[number_of_partitions[i]];
    }
    /* Now, {SLEEPING BAG, LOW} filled in every partitions of each zones */

    


    //TODO: 오브젝트 포인트 벡터에 오브젝트 포인터 할당하기.
    // 이렇게 하면, print()문제도 해결될 것 같다.
    // size Expand/Shrink도 용이핟.
    // 해결해야 할 점 ; new를 하지 않아도 되는지
    // 기타 벡터 특성 반영할 수 있도록
    //FIXME: for Refactoring ; by using std::vector
    // Item *tmpItemPtr = new Item[5];
    // new_vector.push_back(tmpItemPtr);
    // vector<Item *> new_vector;
    // zones = &new_vector[0];

    //TODO: 리스트버전이 더 나을지도.
    

    //TODO: Initialize rest of the member variables to 0/NULL
    this->meals = NULL;
    this->meal_length = 0;
    this->items = NULL;
    this->item_length = 0;
}
//TODO: ver.3
// ver.2 ; for not using get/set
// ver.3 ; from 'days' and 'nights' to 'cnt_days' and 'cnt_nights'
void Backpack::assignMeals(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp();
    Weight meal_weight = customerRequirement.getPreferredMealWeight();

    // Set local variables 'days', 'nights' by comparing enum data types.
    int cnt_days;
    int cnt_nights;
    if (days_on_camp == ONE) {
        cnt_days = 1;
        cnt_nights = 0;
    } else if (days_on_camp == TWO) {
        cnt_days = 2;
        cnt_nights = 1;
    } else {
        cnt_days = 3;
        cnt_nights = 2;
    }

    // Assign member variable 'meal_length'.
    this->meal_length = (cnt_days * 2) + (cnt_nights * 2);

    // Set local variable 'arr_meals' that is going to assign member variable 'meals', later.
    Meal arr_meals[this->meal_length];

    //FIXME: Is it needed to sort these Meals in some order?
    // Put day-related Meals in 'arr_meals'
    for (int i = 0; i < (cnt_days * 2); i += 2) {
        arr_meals[i] = Meal(LUNCH, meal_weight);
        arr_meals[i+1] = Meal(SNACK, meal_weight);
    }

    // Put night-related Meals in 'arr_meals'
    for (int i = (cnt_days * 2); i < this->meal_length; i += 2) {
        arr_meals[i] = Meal(BREAKFAST, meal_weight);
        arr_meals[i+1] = Meal(DINNER, meal_weight);
    }

    // Assign local variable 'arr_meals' to member variable 'meals'
    this->meals = arr_meals;
}

//TODO: ver.1 ; with not using get/set
//FIXME: add로 인해 items가 미리 채워져있는 경우도 생각해야하나?
void Backpack::assignItem(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'item_weight', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp();
    Weight item_weight = customerRequirement.getPreferredItemWeight();
    Weight meal_weight = customerRequirement.getPreferredMealWeight();

    // Set local variables 'cnt_days', 'cnt_nights' by comparing enum data types.
    int cnt_days;
    int cnt_nights;
    if (days_on_camp == ONE) {
        cnt_days = 1;
        cnt_nights = 0;
    } else if (days_on_camp == TWO) {
        cnt_days = 2;
        cnt_nights = 1;
    } else if (days_on_camp == THREE) {
        cnt_days = 3;
        cnt_nights = 2;
    } else {
        cout << "Failed to read days_on_camp by enum." << endl;
    }

    // Set local variables 'cnt_fishing_item' and 'cnt_overnight_item' by considering 'meal_weight'
    int cnt_fishing_item = 4;
    int cnt_overnight_item = 2;

    //FIXME: is_cooking_item_required 안필요하면 더 간단하게 정의해도 될 듯. boolean 따로 안쓰고 바로 overnight_item에 set해도 되니까.
    bool is_cooking_item_required = (meal_weight == HIGH);
    if (is_cooking_item_required) {
        cnt_overnight_item++;
    }
    
    // Assign member variable 'item_length'
    if (cnt_nights == 0) {
        this->item_length = cnt_fishing_item;
    } else {
        this->item_length = cnt_fishing_item + cnt_overnight_item;
    }

    // Instantiate member variable 'items'
    this->items = new Item[this->item_length];

    // Making Item's candidate(full) list, local variable 'possibly_needed_items'.
    Item *possibly_needed_items[] = {
        new Item(CLOTHING, item_weight),
        new Item(FISHING_ROD, item_weight),
        new Item(LURE, item_weight),
        new Item(WATER, HIGH),
        new Item(SLEEPING_BAG, item_weight),
        new Item(TENT, item_weight),
        new Item(COOKING, item_weight),
    };

    // Put Required Items to member variable 'items' by considering the member variable 'storeInventory'
    // For, every needed items 
    for (int i = 0; i < this->item_length; i++) {
        // Looking for the exactly matching Item from 'storeInventory'
        for (int j = 0; j < INVENTORY_SIZE; j++) {
            // If matches, then store it into member variable 'items'
            if (possibly_needed_items[i]->equals(this->storeInventory[j])) {
                this->items[i].setItemType(possibly_needed_items[i]->getItemType());
                this->items[i].setWeight(possibly_needed_items[i]->getWeight());
                break; // Exit for-loop when you found it
            }
            //FIXME: "At this point, Do nothing even if there is no matching Item in the inventory"
            //FIXME: "At this point, Not decreasing inventory even if there is matching Item"
        }
    }

    // Safely delete the local variable 'possibly_needed_items'
    for (int i = 0; i < SORT_OF_ITEMS; i++) {
        delete(possibly_needed_items[i]);
    }
}

//TODO: Move Items in 'items' into 'zones'
//FIXME: 하나 넣으면 하나 더 있어도 지금은 그냥 무시, 여럿 중 하나만 넣는 것은 충족하는데 items로부터 지운다던가 명시되지 않은 action은 발생 X
void Backpack::packBackpack() {

    // In packing each item, there exist order and zones.
    ItemType packing_order[SORT_OF_ITEMS] = {SLEEPING_BAG, TENT, COOKING, WATER, CLOTHING, FISHING_ROD, LURE};
    int packing_zones[SORT_OF_ITEMS] = {4, 4, 3, 3, 2, 1, 0};

    // By packing order, at each order of items,
    for (int i = 0; i < SORT_OF_ITEMS; i++) {
        // Set local variables not to re-visiting outside
        ItemType curr_packing_order = packing_order[i];
        int curr_packing_zone = packing_zones[i];

        // Search that Item in 'items'
        for (int j = 0; j < this->item_length; j++) {
            // if it is exact Item that was looking for
            if (this->items[j].getItemType() == curr_packing_order) {
                // If this zone is empty(default item is in)
                if (this->zones[curr_packing_zone][0].equals(Item())) {
                    this->zones[curr_packing_zone][0] = this->items[j];
                } else {
                    this->zones[curr_packing_zone][1] = this->items[j];
                }
                // Search ended, get out of for-loop
                break;
            }
        }
    }
}
/* previous ver. */
                // case 1 : this Item comes first in this zone
                // if (this->zones[curr_packing_zone] == NULL) {
                //     this->zones[curr_packing_zone] = new Item[1];
                //     this->zones[curr_packing_zone][0] = this->items[j];

                // case 2 : this Item comes second in this zone
                // else {
                //     // copying the existing(first) Item
                //     Item past_item(this->zones[curr_packing_zone][0]);
                //     // increasing size of zone
                //    this->zones[curr_packing_zone] = new Item[2];
                //    this->zones[curr_packing_zone][0] = past_item;
                //    this->zones[curr_packing_zone][1] = this->items[j];
                // }

//TODO: v.1
void Backpack::addItem(Item item) {
    // if assignItem() or addItem() never called before('items' is empty)
    if (this->items == NULL) {
        this->items = new Item[1];
        items[0] = item;
        this->item_length = 1;
    } else {
        // Copy existing Items
        Item curr_items[this->item_length + 1];
        for (int i = 0; i < this->item_length; i++) {
            curr_items[i] = this->items[i];
        }

        // re-assign 'items' by new length
        this->items = new Item[(this->item_length) + 1];
        
        // Move Items back
        for (int i = 0; i < this->item_length; i++) {
            this->items[i] = curr_items[i];
        }

        // Add new one in the last place
        this->items[this->item_length] = item;

        // Increase 'item_length'
        (this->item_length)++;
    }
}

//TODO: Copy 'items' except i-th Item
void Backpack::removeItem(int i) {
    // Set local variable that has smaller length
    Item new_items[(this->item_length) - 1];

    bool isAfterRemove = false;
    // At each Item in items(previous),
    for (int j = 0; j < this->item_length; j++) {
        // if current Item is to be removed one.
        if (j == i) {
            isAfterRemove = true;
            continue;
        }
        // Copying Items from 'items' to 'new_items'
        if (isAfterRemove) {
            new_items[j] = this->items[j-1];
        } else {
            new_items[j] = this->items[j];
        }
    }

    // Re-assign 'items' and 'item_length'
    this->items = new Item[item_length - 1];
    (this->item_length)--;

    // Copying Items from 'new_items' to re-assigned 'items'
    for (int j = 0; j < this->item_length; j++) {
        this->items[j] = new_items[j];
    }
}

//TODO: Copy 'items' except item
void Backpack::removeItem(Item item) {
    // Set local variable to copy 'items' except item
    Item new_items[(this->item_length) - 1];
    for (int j = 0; j < this->item_length; j++) {
        // Copying Items from 'items' to 'new_items'
        if (this->items[j].equals(item)) continue; // except item
        new_items[j] = this->items[j];
    }

    // Re-assign 'items' and 'item_length'
    this->items = new Item[item_length - 1];
    (this->item_length)--;

    // Copying Items from 'new_items' to re-assigned 'items'
    for (int j = 0; j < this->item_length; j++) {
        this->items[j] = new_items[j];
    }
}

/* Revised */
void Backpack::print() {
    int number_of_partitions[CNT_ZONES] = {1, 1, 1, 2, 2};

    // At each zones,
    for (int i = 0; i < CNT_ZONES; i++) {
        // At each partition,
        for (int j = 0; j < number_of_partitions[i]; j++) {
            // Print message which zone I am focussing
            cout << "Zone " << i << ":" << endl;

            // Store current item as local variable
            Item curr_item = this->zones[i][j];

            // If it is not empty, print by using existing method.
            if (curr_item.equals(Item())) curr_item.print();
        }
    }
}

/* previous ver. */
// void Backpack::print() {
//     // For each zones
//     int number_of_partitions[CNT_ZONES] = {1, 1, 1, 2, 2};
//     for (int i = 0; i < CNT_ZONES; i++) {
//         // Print message which zone I am focussing
//         cout << "Zone " << i << ":" << endl;
//         // Pass, if there's no Item inside
//         if (this->zones[i] == NULL) continue;
//         // If there's Item inside,
//         else {
//             for (int j = 0; j < max_partitions_of_zone; j++) {
//                 Item curr_item = this->zones[i][j];
//                 // If there's Item
//                 if (curr_item.getWeight() == LOW || curr_item.getWeight() == MEDIUM || curr_item.getWeight() == HIGH) {
//                     // Print by its regular form
//                     this->zones[i][j].print();
//                 }
//              }
//         }
//     }
// }

Meal* Backpack::getMeals() {
    return meals;
}

void Backpack::setMeals(Meal* m) {
    meals = m;
}

int Backpack::getMealLength() {
    return meal_length;
}

Item* Backpack::getItems() {
    return items;
}

void Backpack::setItems(Item* it) {
    items = it;
}

int Backpack::getItemLength() {
    return item_length;
}

Item** Backpack::getZones() {
    return zones;
}

void Backpack::setZones(Item** z) {
    zones = z;
}

Item* Backpack::getStoreInventory() {
    return storeInventory;
}

void Backpack::setStoreInventory(Item* s) {
    storeInventory = s;
}
