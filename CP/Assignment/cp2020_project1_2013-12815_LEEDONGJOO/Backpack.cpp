#include "Backpack.h"
#include <iostream>
using namespace std;

//TODO: ver.1
Backpack::Backpack() {
    //FIXME: Is it needed outside of the scope?
    // I think the answer is YES
    // But it is already decleared as 'new' in class::StoreInventory
    // So no need to instantiated as 'new' keyword in this Constructor

    // StoreInventory *si = new StoreInventory();
    this->setItems(StoreInventory().item_list);

    // zone 수만 고정, 각각의 item 수는 미정 -> 동적할당
    // 임의로 지정가능?
    // [5]로 fix 가능?
    //FIXME: new로 선언할 필요가 있나? 어차피 tmp_zones에 할당될텐데.
    Item **tmp_zones = new Item*[5];
    this->setZones(tmp_zones);

}

//TODO: ver.1
void Backpack::assignMeals(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp;
    Weight meal_weight = customerRequirement.getPreferredMealWeight;

    // Set local variables 'days', 'nights' by comparing enum data types.
    int days;
    int nights;
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

    // Assign member variable 'meal_length'.
    this->meal_length = (days * 2) + (nights * 2);

    // Set local variable 'arr_meals' that is going to assign member variable 'meals', later.
    Meal arr_meals[this->getMealLength()];

    //FIXME: Is it needed to sort these Meals in some order?
    // Put day-related Meals in 'arr_meals'
    for (int i = 0; i < (days * 2); i += 2) {
        arr_meals[i] = Meal(LUNCH, meal_weight);
        arr_meals[i+1] = Meal(SNACK, meal_weight);
    }

    // Put night-related Meals in 'arr_meals'
    for (int i = (days * 2); i < getMealLength(); i += 2) {
        arr_meals[i] = Meal(BREAKFAST, meal_weight);
        arr_meals[i+1] = Meal(DINNER, meal_weight);
    }

    // Assign local variable 'arr_meals' to member variable 'meals'
    this->setMeals(arr_meals);
}

//TODO: ver.1
void Backpack::assignItem(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'item_weight', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp;
    Weight item_weight = customerRequirement.getPreferredItemWeight;
    Weight meal_weight = customerRequirement.getPreferredMealWeight;

    // Set local variables by comparing enum data types.
    int cnt_fish_camping;
    int cnt_overnight_camping;
    int cnt_overnight_cooking_camping;
    if (days_on_camp == ONE) {
        cnt_fish_camping = 1;
        cnt_overnight_camping = 0;
        cnt_overnight_cooking_camping = 0;
    } else if ((days_on_camp == TWO) && (meal_weight == HIGH)) {
        cnt_fish_camping = 2;
        cnt_overnight_camping = 0;
        cnt_overnight_cooking_camping = 1;
    } else if ((days_on_camp == TWO) && (meal_weight != HIGH)) {
        cnt_fish_camping = 2;
        cnt_overnight_camping = 1;
        cnt_overnight_cooking_camping = 0;
    } else if ((days_on_camp == THREE) && (meal_weight == HIGH)) {
        cnt_fish_camping = 3;
        cnt_overnight_camping = 0;
        cnt_overnight_cooking_camping = 2;
    } else if ((days_on_camp == THREE) && (meal_weight != HIGH)) {
        cnt_fish_camping = 3;
        cnt_overnight_camping = 2;
        cnt_overnight_cooking_camping = 0;
    } else {
        cout << "COUNTING CAMPING FAILED" << endl;
    }

    // Assign member variable 'item_length'.
    //FIXME: cnt_overnight_cooking_camping 관련 확정해야 한다. 질문해둠 ; 일단, 포함으로 해둠.
    this->item_length = (cnt_fish_camping * 4) + (cnt_overnight_camping * 2) + (cnt_overnight_cooking_camping * 3);

    // Set local variable 'arr_meals' that is going to assign member variable 'meals', later.
    Item arr_items[this->getItemLength()];

    //TODO: from here.

    // Set local variable 'arr_items' that is going to assign member variable 'items', later.
    // Cannot 
    // Meal *arr_items;

    //FIXME: Is it needed to sort these Meals in some order?
    // Put day-related Meals in 'arr_meals'
    for (int i = 0; i < (days * 2); i += 2) {
        arr_meals[i] = Meal(LUNCH, meal_weight);
        arr_meals[i+1] = Meal(SNACK, meal_weight);
    }

    // Put night-related Meals in 'arr_meals'
    for (int i = (days * 2); i < getMealLength(); i += 2) {
        arr_meals[i] = Meal(BREAKFAST, meal_weight);
        arr_meals[i+1] = Meal(DINNER, meal_weight);
    }

    // Assign local variable 'arr_meals' to member variable 'meals'
    this->setMeals(arr_meals);
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
