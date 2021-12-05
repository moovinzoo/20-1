#include <iostream>
using namespace std;

class A {
    public:
    virtual void pr() {
        cout << "base" << endl;
    }
};

class B : public A {
    // int tmp = 1; 
    // ㄴ 멤버 있으면 에러남
    // ㄴ Base class랑 Derived class 관한 예 찾아보기
    public:
    void pr() {
        cout << "derived" << endl;
        // ERROR로는 안나고 오버라이드됨
    }
};

int main () {
    B b;
    A a = b; // b라는 오브젝트로 어사인.
    // what if A &a = b;
    // ㄴ a의 레퍼런스를 A로 한다는 문법.
    // ㄴ = b를 하게되면, b의 별명을 a라고 지어주는 것이라 이해하면 된다. 따라서 derived가 나온다.
    // ㄴㄴ virtual 없이는 A &a = b; 해도 base 나온다.
    // ㄴ A *pA = &b; 가 포인터 문법이고 다른 것.
    A *pA = &b; // pA라는 포인터는 b를 가리키게 함.
    a.pr(); // a에 대해서 pr을 부르면 어떤 값이 보이나?
    pA->pr(); // pA에 대해서 pr을 부르면 어떤 값이 보이나?
}


// 답1 : virtual 없을 때 base, base.
// ㄴ 둘 다, polymorphism이 적용이 안됨.
//  ㄴ Handle의 type에 의해 어떤 Func이 굴려질지가 결정됨.
// 답2 : virtual 쓰면 base, derived.
// ㄴ pA는 handle의 type에 의해 결정되는게 아니고, 실질적으로 뭘 가리키느냐, 가리키는 대상의 type(class(B))에 의해 결정된다. 그러므로, B에 대해 찍히게 됨.


// Java에선 virtual이 없다.
/*
1. A라는 것은 reference를 의미하게 됨
2. 암튼 중요함. 무조건 셤에 나온대. 
*/