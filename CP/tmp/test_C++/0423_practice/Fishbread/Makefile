CC = g++
CFLAGS = --std=c++17
TARGET = main
OBJECTS = Fishbread.o main.o

$(TARGET) : $(OBJECTS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJECTS)
clean :
	rm -f *.o
	rm -f $(TARGET)

Fishbread.o : Fishbread.cpp
	$(CC) $(CFLAGS) -c -o Fishbread.o Fishbread.cpp

main.o : main.cpp
	$(CC) -c -o main.o main.cpp
