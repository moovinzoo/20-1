#ifndef ENUM_H
#define ENUM_H

// Category of weight for item
// Applied to both Item & Meal objects
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

#endif