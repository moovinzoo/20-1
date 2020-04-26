import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Parsing {
    
    
    public static Queue<Element> parseString(String input) {
        // TODO: 2020/04/26 This method takes input and parse this string into element
        Queue<Element> resultQueue = new LinkedList<>();
        
        
        return resultQueue;
    }
    
    public static Stack<Element> infixToPostfix(String input) {
        try {
            checkInputContainsWrong(input);
        }
        Stack<Element> resultStack = new Stack<>();
        Queue<Element> parsedString = parseString(input);

        
        return resultStack;
    }

    public static boolean checkInputContainsWrong(String input) extends Exception {
        if(!input.matches("[a-zA-Z0-9,.;:_'\\s-]*")) {
            throw
        }
    }
}
