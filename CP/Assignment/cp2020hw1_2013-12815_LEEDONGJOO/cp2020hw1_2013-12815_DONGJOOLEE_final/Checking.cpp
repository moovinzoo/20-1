#include "Checking.h"
#include <iostream>
using namespace std;


Checking::Checking(int num,float bal,float min,float chg) : BankAccount(num, bal) {
    this->minimum = min;
    this->charge = chg;
}

int Checking::withdraw(float amount) {
    if (getBalance() <= amount) {
        cout << "Cannot withdraw $" << amount << " on account #" << getAcctnum() << " because the balance is low." << endl;

        return 0;

    } else {
        if (getBalance() < (this->minimum)) {
            this->bal -= amount + (this->charge);

        } else {
            this->bal -= amount;

        }

        return 1;

    }
}

void Checking::print() {
    cout << "Checking Account: " << this->getAcctnum() << endl;
    cout << "\tBalance: " << this->getBalance() << endl;
    cout << "\tMinimum to Avoid Charges: " << this->minimum << endl;
    cout << "\tCharge per Check: " << this->charge << endl << endl;

}