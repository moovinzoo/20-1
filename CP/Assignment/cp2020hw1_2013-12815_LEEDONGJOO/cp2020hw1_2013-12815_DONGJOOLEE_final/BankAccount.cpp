#include "BankAccount.h"

    BankAccount::BankAccount(int num, float bal) {
        this->acctnum = num;
        this->bal = bal;
    }

    //money getting into account
    void BankAccount::deposit(float amount) {
        this->bal += amount;
    }

    int BankAccount::withdraw(float amount) {
        this->bal -= amount;
        return 0;
    }

    // getter function
    int BankAccount::getAcctnum() {
        return this->acctnum;
    }

    // getter function
    float BankAccount::getBalance() {
        return this->bal;
    };