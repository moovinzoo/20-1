import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Parsing {
    // FIXME: 2020/04/26 "이렇게하면 throw 되나?"
    public static Stack<Element> processInput(String input) throws Exception {
        // For the first step of Parsing, check if input string contains NOT-allowed characters.
        try {
            inputContainsWrong(input);
        } catch (Exception e) {
            throw e;
        }

        /* Now, it's clear that input string is pure(only contains allowed characters). */

        // Plent input strings into queue chunk by chunk, with some process.
        Queue<Element> infixQueue = parseString(input);

        /* Now, input string processed chunks-level and stored as Element in parsedString(queue). */

        // Convert infix expression to postfix expression.
        Stack<Element> postfixStack = infixToPostfix(infixQueue);

        /* Now, input string processed chunks-level and stored as Element in parsedString(queue). */

        // Return converted stack as result.
        return postfixStack;
    }


    // FIXME: 2020/04/27 "if문 throw new~ 관련하여 Exception class 하나 따로 만들어서 handling 할 것."
    // For the first step of Parsing, check if input string contains NOT-allowed characters.
    public static void inputContainsWrong(String input) throws Exception {
        // If input contains NOT-allowed characters, throws.
        // FIXME: 2020/04/27 "지금 \t, (  )이 중복으로 들어가 있다. 클리어해지면 둘 중 하나 제거
        if (!input.matches("[0-9\t( )(   )/%^+*)(-]*")) throw new Exception("ERROR");
        // Only contains allowed characters.
    }


    // Plent input strings into queue chunk by chunk, with some process.
    public static Queue<Element> parseString(String input) {
        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */
        Queue<Element> resultQueue = new LinkedList<>();
        // By using FIFO property of queue, it is possible to maintain origin order of chunks.

        return resultQueue;
    }


    // TODO: 2020/04/27  
    // Convert infix expression to postfix expression.
    public static Stack<Element> infixToPostfix(Queue<Element> infixQueue) throws Exception {
        return new Stack<Element>();
    }
}
