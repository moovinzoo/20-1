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
    this->storeInventory = StoreInventory().item_list;
    this->zones = new Item*[5];

    //TODO: Initialize rest of the member variables to 0/NULL
    this->meals = NULL;
    this->meal_length = 0;
    this->items = NULL;
    this->item_length = NULL;
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

    // Gets items from store inventory

    // assignItem에 의하면 각 아이템은 하나씩 밖에 들어가지 않는데 removeItem에서는 중복된거는 하나만 삭제하라고 해서 충분히 헷갈릴만한 소지가 있었네요.
    // 저의 의도는 만약 addItem의 사용으로 중복된 Item들이 items array에 들어가있을수도 있는 상황에 대해 removeItem은 하나만 삭제하라고 얘기했습니다. 그에 따라 packBackpack의 설명이 많이 부족한 상태네요. 

    // backBackpack할 때는 만약 해당 zone에 들어가야될 Item이 하나가 아닌 다수가 있을 경우 그중 하나만 넣으라고 설명을 수정해놓겠습니다. (그러나 이러한 상황은 제가 드릴 테스트 코드에 포함이 되어있을 수도 안되어있을 수도 있습니다. 그에 따라 유동적으로 코딩을 해주시면 되겠습니다.
    //일상에 빗대어 말하자면 assignItem은 고객의 요청에 의해 필요한 최소한의 물품만 준비해준다라고 생각하면 좋겠고요. addItem은 고객이 추가적으로 구매한다? 정도로 생각하면 되겠습니다. 그에 따라 가방을 쌀때는 (packBackpack) 다 들고 갈 필요는 없으니 겹치는 장비중 하나만 가져간다 라고 생각하면 좋겠습니다. 감사합니다.

    // list = {clothing, fishing rod, lure, water, (sleeping bag, tent, (cooking equipment))}

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

    for (int i = 0; i < this->item_length; i++) {
        ItemType _type = possibly_needed_items[i]->getItemType();
        Weight _weight = possibly_needed_items[i]->getWeight();
        // Put Items from full list to member variable 'items' considering it's quantity.
        this->items[i].setItemType(_type);
        this->items[i].setWeight(_weight);
        // Get item, next (cause, it is guaranteed that Item shortage from the inventory is not happening)
        while()
    }


    for (int i = 0; i < this->item_length; i++) {

    }


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

    //TODO: deleting full list of Items.
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
