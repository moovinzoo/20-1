#include "BankSystem.h"
#define FEE 10.0

void BankSystem::transaction(BankAccount* from, BankAccount* to, float amount) {
    if (from->withdraw(amount + FEE)) {
        to->deposit(amount);
    }
}

void BankSystem::deposit(BankAccount* b, float amount) {
    b->deposit(amount);
}

void BankSystem::withdraw(BankAccount* b, float amount) {
    b->withdraw(amount);
}