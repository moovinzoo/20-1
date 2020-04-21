#include "InterestChecking.h"
#include <iostream>
using namespace std;

InterestChecking::InterestChecking(int num, float bal, float cmin, float imin, float chg, float rate, float monchg) : Checking(num, bal, cmin, chg) {
    this->minint = imin;
    this->intrate = rate;
    this->moncharge = monchg;
}

void InterestChecking::interest() {
    if (getBalance() < (this->minint)) {
        this->bal -= (this->moncharge);
        // if (getBalance() > cmin) {

        // } else {

        // }

    } else {
        this->bal += getBalance() * (this->intrate) / 1200;

    }
}

void InterestChecking::print() {
    cout << "Interest Checking Account: " << this->getAcctnum() << endl;
    cout << "\tBalance: " << this->getBalance() << endl;
    cout << "\tMinimum to Avoid Charges: " << this->minimum << endl;
    cout << "\tCharge per Check: " << this->charge << endl;
    cout << "\tMinimum balance for getting interest and No Monthly Fee: "<< this->minint << endl;
    cout << "\tInterest: " << this->intrate << "%" << endl;
    cout << "\tMonthly Fee: " << this->moncharge << endl << endl;

}