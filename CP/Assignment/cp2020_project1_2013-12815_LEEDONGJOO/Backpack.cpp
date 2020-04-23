#include "Backpack.h"
#include <iostream>
using namespace std;

//TODO: ver.2 ; for not using getter and setter
Backpack::Backpack() {
    //FIXME: Is it needed outside of the scope?
    // I think the answer is YES
    // But it is already decleared as 'new' in class::StoreInventory
    // So no need to instantiated as 'new' keyword in this Constructor

    // StoreInventory *si = new StoreInventory();
    // zone 수만 고정, 각각의 item 수는 미정 -> 동적할당
    // 임의로 지정가능?
    // [5]로 fix 가능?
    //FIXME: new로 선언할 필요가 있나? 어차피 tmp_zones에 할당될텐데.
    this->items = StoreInventory().item_list;
    this->zones = new Item*[5];
}

//TODO: ver.3
// ver.2 ; for not using get/set
// ver.3 ; from 'days' and 'nights' to 'cnt_days' and 'cnt_nights'
void Backpack::assignMeals(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp;
    Weight meal_weight = customerRequirement.getPreferredMealWeight;

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
void Backpack::assignItem(CustomerRequirement customerRequirement) {
    // Set local variables 'days_on_camp', 'item_weight', 'meal_weight' to store parameter's data to avoid re-visiting class::CustomerRequirement for performance.
    DaysOnCamp days_on_camp = customerRequirement.getDaysOnCamp;
    Weight item_weight = customerRequirement.getPreferredItemWeight;
    Weight meal_weight = customerRequirement.getPreferredMealWeight;

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
    //FIXME: from Q&A, "1박2일이던 2박3일이던 fish camping은 한 번 가는거죠. 제가 설명하고자 했던바는 낚시캠핑을 갈때 설명드린 4개의 아이템이 기본적으로 무조건 필요한것이고 그외에 숙박을 하냐 밥무게가 무겁냐에 따라 장비가 추가되는겁니다."
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




    // if (days_on_camp == ONE) {
    //     cnt_fish_camping = 1;
    //     cnt_overnight_camping = 0;
    //     cnt_overnight_cooking_camping = 0;
    // } else if ((days_on_camp == TWO) && (meal_weight == HIGH)) {
    //     cnt_fish_camping = 2;
    //     cnt_overnight_camping = 0;
    //     cnt_overnight_cooking_camping = 1;
    // } else if ((days_on_camp == TWO) && (meal_weight != HIGH)) {
    //     cnt_fish_camping = 2;
    //     cnt_overnight_camping = 1;
    //     cnt_overnight_cooking_camping = 0;
    // } else if ((days_on_camp == THREE) && (meal_weight == HIGH)) {
    //     cnt_fish_camping = 3;
    //     cnt_overnight_camping = 0;
    //     cnt_overnight_cooking_camping = 2;
    // } else if ((days_on_camp == THREE) && (meal_weight != HIGH)) {
    //     cnt_fish_camping = 3;
    //     cnt_overnight_camping = 2;
    //     cnt_overnight_cooking_camping = 0;
    // } else {
    //     cout << "COUNTING CAMPING FAILED" << endl;
    // }

    // Assign member variable 'item_length'.
    //FIXME: cnt_overnight_cooking_camping 관련 확정해야 한다. 질문해둠 ; 일단, 포함으로 해둠.
    this->item_length = (cnt_fish_camping * 4) + (cnt_overnight_camping * 2) + (cnt_overnight_cooking_camping * 3);

    // Set local variable 'arr_meals' that is going to assign member variable 'meals', later.
    Item arr_items[this->item_length];

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
