#include <iostream>
#include "Backpack.h"
#include <vector>
// #include <list>
#define INVENTORY_SIZE 42 // Hard-coded the length of inventory's items
#define SORT_OF_ITEMS 7 // Hard-coded the length of inventory's items
#define CNT_ZONES 5

using namespace std;

Backpack::Backpack() {
    // Initialize member variable storeInventory
    this->storeInventory = StoreInventory().item_list;

    // Assign member variable packing_zones
    this->zones = new Item*[CNT_ZONES];
    int number_of_partitions[CNT_ZONES] = {1, 1, 1, 2, 2};
    for (int i = 0; i < CNT_ZONES; i++) {
        this->zones[i] = new Item[number_of_partitions[i]];
    }
    /* Now, default Item:{SLEEPING BAG, LOW} filled in every partitions of each zones */
    
    // Initialize member variables of meal & item
    this->meals = NULL;
    this->meal_length = 0;
    this->items = NULL;
    this->item_length = 0;
}

void Backpack::assignMeals(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp();
    Weight meal_weight = customerRequirement.getPreferredMealWeight();

    // Set local variables 'days', 'nights' by comparing enum data types.
    int cnt_days = 1;
    int cnt_nights = 0;
    if (days_on_camp != ONE) {
        if (days_on_camp == TWO) {
            cnt_days += 1;
            cnt_nights += 1;
        } else {
            cnt_days += 2;
            cnt_nights += 2;
        }
    }

    // Assign member variable 'meal_length'.
    this->meal_length = (cnt_days * 2) + (cnt_nights * 2);

    // Set local variable 'arr_meals' that is going to assign member variable 'meals', later.
    this->meals = new Meal[this->meal_length];
    // Put day-related Meals in 'arr_meals'
    for (int i = 0; i < (cnt_days * 2); i += 2) {
        this->meals[i] = Meal(LUNCH, meal_weight);
        this->meals[i+1] = Meal(SNACK, meal_weight);
    }

    // Put night-related Meals in 'arr_meals'
    for (int i = (cnt_days * 2); i < this->meal_length; i += 2) {
        this->meals[i] = Meal(BREAKFAST, meal_weight);
        this->meals[i+1] = Meal(DINNER, meal_weight);
    }
}

void Backpack::assignItem(CustomerRequirement customerRequirement) {
    /* Instanciate or assign variables */

    // Set local variables 'days_on_camp', 'item_weight', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp();
    Weight item_weight = customerRequirement.getPreferredItemWeight();
    Weight meal_weight = customerRequirement.getPreferredMealWeight();

    // Set local variables 'cnt_fishing_item' & 'cnt_overnight_item'.
    int cnt_fishing_item = 4;
    int cnt_overnight_item = (days_on_camp != ONE)? 2 : 0;
    int cnt_cooking_item = (meal_weight == HIGH)? 1 : 0;
    
    // Assign member variable 'item_length'
    this->item_length = cnt_fishing_item + cnt_overnight_item + cnt_cooking_item;
    // Instantiate member variable 'items'
    this->items = new Item[this->item_length];

    /* Even if, finding Item is not in 'storeInventory', by default item had been inserted, could ignore them later. */

    // Instantiate local variable 'preffered_item_arr'.
    vector<Item> preferred_item_arr;
    preferred_item_arr.push_back(Item(CLOTHING, item_weight));
    preferred_item_arr.push_back(Item(FISHING_ROD, item_weight));
    preferred_item_arr.push_back(Item(LURE, item_weight));
    preferred_item_arr.push_back(Item(WATER, HIGH));//FIXED
    if (cnt_overnight_item > 0) {
        preferred_item_arr.push_back(Item(SLEEPING_BAG, MEDIUM));//FIXED
        preferred_item_arr.push_back(Item(TENT, item_weight));
    }// only customer having overnight camping
    if (cnt_cooking_item > 0) { // only if needed
        preferred_item_arr.push_back(Item(COOKING, item_weight));
    }// only customer prefer high-weight meal
    /* It's clear that length of preferred_item_arr is equal to member variable 'item_length'.*/


    /* Put preferred items to member variable 'items' by considering the member variable 'storeInventory' */

    // For each member of 'preffered_item_arr'
    for (int i = 0; i < this->item_length; i++) {
        // For Looking for the exactly matching preffered item in member variable 'storeInventory'.
        for (int j = 0; j < INVENTORY_SIZE; j++) {

            // If matches, then store it into member variable 'items'
            if (preferred_item_arr.at(i).equals(this->storeInventory[j])) {
                // Found matching Item.
                this->items[i].setItemType(preferred_item_arr.at(i).getItemType());
                this->items[i].setWeight(preferred_item_arr.at(i).getWeight());

                // Exit for-loop when you found it.
                break;
            }
        }
    }
    /* After preceding loop, preferred item that is not the 'storeInventory remained default(SLEEPING_BAG, LOW) */
}

void Backpack::packBackpack() {
    // In packing each item, there exist order and zones.
    // Local variables below are intended to act like they are mapped.
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

void Backpack::addItem(Item item) {
    // Member variable 'items' is null(empty).
    if (this->items == NULL) {
        this->items = new Item[1];
        items[0].setItemType(item.getItemType());
        items[0].setWeight(item.getWeight());

    } else {
        // Copy existing Items
        int curr_len = this->item_length;
        Item *curr_items = new Item[curr_len + 1];
        for (int i = 0; i < curr_len; i++) {
            curr_items[i].setItemType(this->items[i].getItemType());
            curr_items[i].setWeight(this->items[i].getWeight());
        }

        // re-assign 'items' by new length
        this->items = curr_items;
        this->items[curr_len].setItemType(item.getItemType());
        this->items[curr_len].setWeight(item.getWeight());
    }

    // Increase 'item_length'
    (this->item_length)++;
}

void Backpack::removeItem(int i) {
    int curr_len = this->item_length;

    // For extra ordinary cases.
    if (curr_len == 0) return;
    if (curr_len == 1) {
        this->items = NULL;
        return;
    }
    if (i >= curr_len) return;

    /* From here, ordinary cases */ 

    // Set local variable that has smaller length
    Item *new_items = new Item[curr_len - 1];

    for (int j = 0; j < i; j++) {
            new_items[j].setItemType(this->items[j].getItemType());
            new_items[j].setWeight(this->items[j].getWeight());
    }
    for (int j = i + 1; j < curr_len; j++) {
            new_items[j - 1].setItemType(this->items[j].getItemType());
            new_items[j - 1].setWeight(this->items[j].getWeight());
    }

    // Re-assign 'items' and 'item_length'
    (this->item_length)--;
    this->items = new Item[this->item_length];

    // Copying Items from 'new_items' to re-assigned 'items'
    for (int j = 0; j < this->item_length; j++)
    {
        this->items[j].setItemType(new_items[j].getItemType());
        this->items[j].setWeight(new_items[j].getWeight());
    }
}

void Backpack::removeItem(Item item) {
    int curr_len = this->item_length;

    // For extra ordinary cases.
    if (curr_len == 0) return;
    if (curr_len == 1) {
        this->items = NULL;
        return;
    }

    /* From here, ordinary cases */ 

    int removingItemIndex = -1;

    // Find if there is removing Item.
    for (int i = 0; i < curr_len; i++) {
        // This Item is to be removed.
        if (item.equals(this->items[i])) {
            // Store the index and get out of loop.
            removingItemIndex = i;
            break;
        }
    }

    // Using removingItemIndex(int).
    if (removingItemIndex > -1) {
        this->removeItem(removingItemIndex);
    }
}

void Backpack::print() {
    int number_of_partitions[CNT_ZONES] = {1, 1, 1, 2, 2};

    // At each zones,
    for (int i = 0; i < CNT_ZONES; i++) {
        // Print message which zone I am focussing
        cout << "Zone " << i << ":" << endl;
        // At each partition,
        for (int j = 0; j < number_of_partitions[i]; j++) {
            // Store current item as local variable
            Item curr_item = this->zones[i][j];
            // If it is not empty, print by using existing method.
            if (!curr_item.equals(Item())) {
                cout << "\t";
                curr_item.print();
            }
        }
    }
}

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
