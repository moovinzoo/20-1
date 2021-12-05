#ifndef SHAPE_H
#define SHAPE_H

class Shape {
    protected:
        int width, height;
    public:
        Shape(int a = 0, int b = 0) {
            width = a;
            height = b;
        }
        virtual int area() {
            std::cout << "Parent class area :" << std::endl;
            return 0;
        }
};

class Rectangle: public Shape {
    public:
        Rectangle(int a = 0, int b = 0) : Shape(a, b) {}
        int area();
};

class Triangle: public Shape {
    public:
        Triangle(int a = 0, int b = 0) :Shape(a, b) {}
        int area();
};

#endif
