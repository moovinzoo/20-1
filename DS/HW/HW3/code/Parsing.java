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
        Queue<Element> resultQueue = new LinkedList<>();

        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */
        // By using FIFO property of queue, it is possible to maintain origin order of chunks.

        // TODO: 2020/04/27 "0을 삽입하는 Algorithm을 적용해서 unary -를 미리 parsing한다" 
        
        return resultQueue;
    }


    // TODO: 2020/04/27  
    // Convert infix expression to postfix expression.
    public static Stack<Element> infixToPostfix(Queue<Element> infixQueue) throws Exception {
        // TODO: 2020/04/27 "Operator Stack과 Return Stack을 만들어두고 index를 하나씩 오른쪽으로 옮기면서 Operand가 나오면 그냥 바로 return에 push하고, Operator가 나왔을 때, top에 있는 친구와 priority를 비교해서 큰 애가 먼저 들어가있는 경우 pop시켜서 return에 넣어주면 간단하게 해결된다."
        // TODO: 2020/04/27 "단지, 이 때에 top에 있는 애와 같은 priority를 가지는 경우 left-associative인지 right-associative인지가 중요한데 Element.compareTo()에서 0이 나오는 경우 자체에 대해 left/right에 맞게 아예 배정을 해버리면 편할 것 같다. 원칙적으로는 같을 때에 left면 꺼내고 right면 집어넣는 것인데 그것을 그냥 compareTo에 반영해버리는 것이 훨씬 간단할 듯, 단지 설명만 잘 써놓자". 
        
        return new Stack<Element>();
    }
}
