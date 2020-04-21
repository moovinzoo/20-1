#include "Savings.h"
#include "iostream"
using namespace std;

Savings::Savings(int num, float bal, float rate) : BankAccount(num, bal) {
    this->intrate = rate;
}

void Savings::interest() {
    this->bal += getBalance() * (this->intrate) / 1200;
}

int Savings::withdraw(float amount) {
    if (getBalance() <= amount) {
        cout << "Cannot withdraw $" << amount << " on account #" << getAcctnum() << " because the balance is low." << endl;
        return 0;

    } else {
        this->bal -= amount;
        return 1;

    }
}

void Savings::print() {
    cout << "Savings Account: " << getAcctnum() << endl << "\tBalance: " << getBalance() << endl << "\tInterest: " << this->intrate << "%" << endl;

}