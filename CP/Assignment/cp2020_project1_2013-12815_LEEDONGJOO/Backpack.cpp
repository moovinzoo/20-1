#include "Backpack.h"
#include <iostream>
using namespace std;

/* TODO:
    1. Initialize the storeInventory member v.
    - by using the StoreInventory member v. with the inventory created by using the StoreInventory class.
    2. Instantiate the zones array.
    - two-dimentional Item array that represents the zones of the backpack in which corresponding types of tiems should be packed.
    - first dimension of the array should have as many elements as there are available zones in the picture.
    - second dimension should be an array that is capable of holding zero to many items of Item, depending on the number of items that need to be put in a specific zone of a backpack.
*/
Backpack::Backpack() {
    // FIXME: Is it needed outside of the scope?
    // I think the answer is YES
    // But it is already decleared as 'new' in class::StoreInventory
    // So no need to instantiated as 'new' keyword in this Constructor

    // StoreInventory *si = new StoreInventory();
    this->setItems(StoreInventory().item_list);

    // 임의로 지정가능?
    // [5]로 fix 가능?
    //FIXME: new로 선언할 필요가 있나? 어차피 tmp_zones에 할당될텐데.
    Item **tmp_zones = new Item*[5];
    this->setZones(tmp_zones);

}

//TODO:
void Backpack::assignMeals(CustomerRequirement customerRequirement) {
    // Declare member variables to store parameter's data
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp;
    Weight meal_weight = customerRequirement.getPreferredMealWeight;

    // Set days & nights by comparing enum data types
    int days, nights;
    if (days_on_camp == ONE) {
        days = 1;
        nights = 0;
    } else if (days_on_camp == TWO) {
        days = 2;
        nights = 1;
    } else {
        days = 3;
        nights = 2;
    }

    // Set member variable meal_length
    this->meal_length = (days * 2) + (nights * 2);

    // Assign meals by days
    Meal *arr_meals[this->getMealLength()];
    for (int i = 0; i < (days * 2); i += 2) {
        arr_meals[i] = new Meal(LUNCH, meal_weight);
        arr_meals[i+1] = new Meal(SNACK, meal_weight);
    }

    // Assign meals by nights
    for (int i = (days * 2); i < getMealLength(); i += 2) {
        arr_meals[i] = new Meal(BREAKFAST, meal_weight);
        arr_meals[i+1] = new Meal(DINNER, meal_weight);
    }
    


}

//TODO:
void Backpack::assignItem(CustomerRequirement customerRequirement) {

}

//TODO:
void Backpack::packBackpack() {

}

//TODO:
void Backpack::addItem(Item item) {

}

//TODO:
void Backpack::removeItem(int i) {

}

//TODO:
void Backpack::removeItem(Item item) {

}

//TODO:
void Backpack::print() {
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
