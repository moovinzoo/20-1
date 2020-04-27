import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Parsing {

    // FIXME: 2020/04/26 "이렇게하면 throw 되나?"
    public static Stack<Element> processInput(String input) throws Exception {
        try {
            // For the first step of Parsing, check if input string contains NOT-allowed characters.
            inputContainsWrong(input);

            /* Now, it's clear that input string is pure(only contains allowed characters). */

            // Plent input strings into queue chunk by chunk, with some process.
            Queue<Element> infixQueue = parseString(input);

            /* Now, input string processed chunks-level and stored as Element in parsedString(queue). */

            // Convert infix expression to postfix expression.
            Stack<Element> postfixStack = infixToPostfix(infixQueue);

            /* Now, input string processed chunks-level and stored as Element in parsedString(queue). */

            // Return converted stack as result.
            return postfixStack;

        } catch (Exception e) {
            throw e;
        }
    }


    // FIXME: 2020/04/27 "if문 throw new~ 관련하여 Exception class 하나 따로 만들어서 handling 할 것."
    // For the first step of Parsing, check if input string contains NOT-allowed characters.
    public static void inputContainsWrong(String input) throws Exception {
        // If input contains NOT-allowed characters, throws.
        if (!input.matches("[0-9\t( )/%^+*)(-]*"))
            throw new Exception("NOT ALLOWED : !CHARACTER EXSITS");

        /* Now, it is certain that input only contains allowed characters. */
    }


    // Plent input strings into queue chunk by chunk, with some process.
    public static Queue<Element> parseString(String input) throws Exception{
        // Store parsed input string as class:Element in Queue considering unary '-'

        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */
        
        // Remove all spaces & tab
        input.replaceAll("[ \t]", "");

        // Tokenize input string by operator
        String[] parsedInput = input.split("[%^+*)(-]");
//        boolean isUnaryMinus = false;

        // TODO: 2020/04/27 "1---2 가능한지 답변 확인한 후, 연속된 Unary case 검토여부 확정"
        // TODO: 2020/04/27 "버려진 방법 : 0을 삽입하는 Algorithm을 적용해서 unary -를 미리 parsing한다" 
        // By using FIFO property of queue, it is possible to maintain origin order of chunks.
        Queue<Element> resultQueue = new LinkedList<>();
        // For every chunkes,
        for (String chunk : parsedInput) {
            // Convert 'chunked string' as 'Element'.
            Element newElem = new Element(chunk);

            // For continous operator cases,
            if (resultQueue.peek().isOpertor() && newElem.isOpertor()) {
                // 2nd operator is '-'
                if (newElem.getOperator() == '-') {
                    // Convert '-' to '~' to avoid confusing.
                    newElem.makeOperatorUnary();
//                    isUnaryMinus = true;
//                    continue;

                } else {
                    // 2nd operator excluding '-' comes,
                    throw new Exception("NOT ALLOWED : CONTINUOUS OPERATORS");
                }

            // For the case that starting with operator
            } else if (resultQueue.isEmpty() && newElem.isOpertor()) {
                if (newElem.getOperator() == '-') {
                    newElem.makeOperatorUnary();

                } else if (newElem.getOperator() != '(') {
                    throw new Exception("NOT ALLOWED : STARTS WITH OPERATORS EXCEPT '(' or '-'");

                }
            }

            // Add to queue.
            resultQueue.add(new Element(chunk));
        }

//            // TODO: 2020/04/27 "Remove this, it's just for testing"
//            /* NO NEED */
//            if (isUnaryMinus && newElem.isOpertor()) {
//                throw new Exception("It's time to set operand as negative, but, operator comes");
//            }
//            /* NO NEED */
//
//            // if isUnaryMinus is true, then, it's clear that newElem is operand
//            // FIXME: 2020/04/27 "Is to be set in top needed?"
//            if (isUnaryMinus) {
//                newElem.makeOperandNegative();
//                isUnaryMinus = false;
//            }


        // Input string stored as chunked Element in returning queue.
        return resultQueue;
    }


    // TODO: 2020/04/27 "Operator Stack과 Return Stack을 만들어두고 index를 하나씩 오른쪽으로 옮기면서 Operand가 나오면 그냥 바로 return에 push하고, Operator가 나왔을 때, top에 있는 친구와 priority를 비교해서 큰 애가 먼저 들어가있는 경우 pop시켜서 return에 넣어주면 간단하게 해결된다."
    // TODO: 2020/04/27 "단지, 이 때에 top에 있는 애와 같은 priority를 가지는 경우 left-associative인지 right-associative인지가 중요한데 Element.compareTo()에서 0이 나오는 경우 자체에 대해 left/right에 맞게 아예 배정을 해버리면 편할 것 같다. 원칙적으로는 같을 때에 left면 꺼내고 right면 집어넣는 것인데 그것을 그냥 compareTo에 반영해버리는 것이 훨씬 간단할 듯, 단지 설명만 잘 써놓자".
    // Convert infix expression to postfix expression.
    public static Stack<Element> infixToPostfix(Queue<Element> infixQueue) throws Exception {
        Stack<Element> operatorStack = new Stack<>();
        Stack<Element> postfixStack = new Stack<>();

        // Until infix is empty
        while (!infixQueue.isEmpty()) {
            // Poll 1 element
            Element currElem = infixQueue.poll();
            // Current Element is operator
            if (currElem.isOpertor()) {
                // operator is closing parenthesis
                if (currElem.getOperator() == ')') {
                    // Pop all the stored operators and push to the postfixStack until '('
                    while (operatorStack.peek().getOperator() != '(' || !operatorStack.isEmpty()) {
                        postfixStack.push(operatorStack.pop());
                    }

                    // When finding '(' ends without success
                    if (operatorStack.isEmpty())
                        throw new Exception("NOT ALLOWED : FAILED TO FIND PAIR OF ')'");

                    // Pop '(' without storing.
                    operatorStack.pop();


                // operator is not ')'
                } else {
                    // Compare priorities between 'currElem' and 'top element of Stack'
                    // If, current element has low-priority,
                    if (currElem.compareTo(operatorStack.peek()) < 0) {
                        // Pop operator on the top of the operatorstack that has high-priority, and push it to the postfixStack.
                        postfixStack.push(operatorStack.pop());
                    }
                    /* In Element.compareTo() method, pre-promised decisions for the same priority cases considering left-associative / right-associative. So, No need to consider 'same-priority' cases. */

                    operatorStack.push(currElem);
                }

                // currElem is operand
            } else {
                // When two operand wating in a row,
                if (!infixQueue.peek().isOpertor())
                    throw new Exception("NOT ALLOWED : CONTINUOUS OPERANDS");
                postfixStack.push(currElem);

            }
        }

        /* Now, infixQueue is empty */

        // Until operationStack is empty
        while (!operatorStack.isEmpty()) {
            postfixStack.push(operatorStack.pop());
        }

        /* Now, operatorStack is empty */

        // Helping garbage collector to delete unnecesary object
        infixQueue = null;
        operatorStack = null;

        // Return finished postfixStack.
        return postfixStack;
    }
}
