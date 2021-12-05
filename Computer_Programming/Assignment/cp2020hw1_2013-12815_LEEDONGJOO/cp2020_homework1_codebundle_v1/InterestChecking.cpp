#include "InterestChecking.h"
#include "iostream"
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
    cout << "Interest Checking Account: " << getAcctnum() << endl << "\tBalance: " << getBalance() << endl << "Minimum to Avoid Charges: " << this->minimum << endl << "Charge per Check: " << this->charge << endl << "Minimum balance for interest and No Monthly Fee: "<< this->minint << endl << "Interest: " << this->intrate << "%" << endl << "Monthly Fee: " << this->moncharge << endl;

}