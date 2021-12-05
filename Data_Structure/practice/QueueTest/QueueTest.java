import java.io.*;
import java.util.*;

public class QueueTest {

    public static void main(String args[]) {
        Queue<Integer> newQueue = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            newQueue.add(i);
        }
        /*
        for (int i = 0; i < 10; i++) {
            System.out.println("peek :\t\t" + newQueue.peek());
            System.out.println("i :\t\t " + newQueue.poll());
        }
        */

        while (!newQueue.isEmpty()) {
            System.out.println("i :\t\t " + newQueue.poll());
            System.out.println("peek :\t\t" + newQueue.peek());
        }
    }
}
