#include <iostream>
using namespace std;

int main(void) {
    char alphabet;
    bool out = false;

    char in;

    while(true) {
        cout << "Enter Capital or Small letter<0 for exit>:";
        cin >> in;
        if (in >= 'a' && in <= 'z') {
            cout << "input: " << in << " output: " << (char)(in - 'a' + 'A') << endl;
        } else if (in >= 'A' && in <= 'Z') {
            cout << "input: " << in << " output: " << (char)(in + 'a' - 'A') << endl;
        } else if (in == '0') {
            cout << "exiting..." << endl;
            break;
        } else {
            cout << "Enter character" << endl;
        }
    }

    return 0;
}
