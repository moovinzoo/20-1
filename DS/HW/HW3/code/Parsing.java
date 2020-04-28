import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parsing {

    private static final Pattern OPERAND_WITH_SPACES = Pattern.compile("[ \t]*[0-9]+[ \t]*");

    // FIXME: 2020/04/26 "이렇게하면 throw 되나?"
    public static Stack<Element> processInput(String input) throws Exception {
        try {
            // Remove all the space/tab from input
            input = removeSpaces(input);
            /* Now, there's no spaces in input */

            // For the first step of Parsing, check if input string contains NOT-allowed characters.
            testInputContainsWrong(input);
            /* Now, it's clear that input only contains allowed characters. */

            // Check if parentheses pairing well.
            testParenthesesPairing(input);
            /* Now, it's clear that parentheses match. */

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

    public static String removeSpaces(String input) {
        return input.replaceAll("[ \t]*", "");
    }

    // FIXME: 2020/04/27 "if문 throw new~ 관련하여 Exception class 하나 따로 만들어서 handling 할 것."
    // For the first step of Parsing, check if input string contains NOT-allowed characters.
    public static void testInputContainsWrong(String input) throws Exception {
        // If input contains NOT-allowed characters, throws.
        if (!input.matches("[0-9/%^+*)(-]*"))
            throw new Exception("NOT ALLOWED : !CHARACTER EXSITS");

        /* Now, it is certain that input only contains allowed characters. */
    }

    public static void testParenthesesPairing(String input) throws Exception {
        int trackParentheses = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') trackParentheses++;
            else if (input.charAt(i) == ')') trackParentheses--;
        }

        if (trackParentheses != 0) throw new Exception("NOT ALLOWED: PARENTHESIS DO NOT MATCH.");
    }

    // New version. using Patter&Matcher
    // Plent input strings into queue chunk by chunk, with some process.
    public static Queue<Element> parseString(String input) throws Exception {
        /* No spaces. */
        /* No any NOT-ALLOWED characters. */
        /* Parentheses match. */

        Queue<Element> resultQueue = new LinkedList<>();
        // Store parsed input string as class:Element in Queue considering unary '-'

        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */

        // Extract operands with spaces
        Matcher operandMatcher = OPERAND_WITH_SPACES.matcher(input);
        int prevStart = -1;
        int prevEnd = 0;

        // Until there's more operand(with spaces back and forth)
        while (operandMatcher.find()) {
            // Renew the current index of start & end.
            int currStart = operandMatcher.start();
            int currEnd = operandMatcher.end();

            // Current : [currOperator(String after preceding group)][currOperand(group)]
            String currOperator = input.substring(prevEnd, currStart);
            String currOperand = operandMatcher.group();

            // Test validity of operator chunk.
            if (!currOperator.matches("[)]*[^*/%+-][-(]*") && !currOperator.matches("[^*/%+-][-(]*")) {
                throw new Exception("NOT ALLOWED: INVALID OPERATOR");
            }
            boolean isValidOperatorPrecedes = false;
            int trackingParentheses = 0;
            for (int i = 0; i < currOperator.length(); i++) {
                if (currOperator.charAt(i) == '(') {
                    trackingParentheses++;
                } else if (currOperator.charAt(i) == ')') {
                    trackingParentheses--;
                } else if (currOperator.charAt(i) == '-') {

                }
            }

            /* Now, operator chunk is valid */

            // Convert current operator to Element class instance and add it to Queue.

            // Convert current operand to Element class instance and add it to Queue.
            Element newOperand = new Element(currOperand);
            resultQueue.add(newOperand);


            // Process unary minus
            if (currOperator.matches("[)]*[^*/%+-]{1}[-]*[(]*[-]*")) {

            }

            if (currOperator.length() == 1) {
                if (currOperator.equals("(") || currOperator.equals(")")) {
                    throw new Exception("NOT ALLOWED: SINGLE OPERATOR THAT ( OR ).");
                }
            } else if (currOperator.length() > 1) {
                if (currOperator.startsWith("(")) {
                    throw new Exception("NOT ALLOWED: MULTIPLE OPERATORS STARTS WITH (.");
                } else if (currOperator.startsWith(")")) {
                    if (!currOperator.matches("[)]+[^*/%+-]{1}[(]+[~]*"))
                        throw new Exception("NOT ALLOWED: MULTIPLE OPERATORS STARTS WITH ) BUT RESTRICTED.");
                }
            } else {

            }


            // TODO: 2020/04/28 "찌꺼기 처리"

            // Update the prev index of start & end.
            prevStart = currStart;
            prevEnd = currEnd;
        }
        
        // Return
        return resultQueue;
    }
            // Start
//            if (prevEnd == 0) {
//                if (currOperator.length() == 1) {
//                    if (currOperator.equals("-")) {
//                       currOperator = "~";
//
//                    } else {
//                        if (!currOperator.equals("("))
//                            throw new Exception("NOT ALLOWED: STARTS WITH OPERATOR EXCEPT (, -");
//
//                    }
//
//                } else if (currOperator.length() == ) {
//
//                }
//            }


//            }
//            } else if (currStart - prevEnd == 0) {
//
//            }


        // Tokenize input string by operator
        // By using FIFO property of queue, it is possible to maintain origin order of chunks.
        // For every chunkes,
            // Convert 'chunked string' as 'Element'.
            // For continous operator cases,
                // 2nd operator is '-'
                    // Convert '-' to '~' to avoid confusing.
                    // 2nd operator excluding '-' comes,
                // For the case that starting with operator
            // Add to queue.
        // Input string stored as chunked Element in returning queue.












                // Old version.
//    // Plent input strings into queue chunk by chunk, with some process.
//    public static Queue<Element> parseString(String input) throws Exception{
//        // Store parsed input string as class:Element in Queue considering unary '-'
//
//        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */
//
//        // Remove all spaces & tab
//        input.replaceAll("[ \t]", "");
//
//        // Tokenize input string by operator
//        String[] parsedInput = input.split("[%^+*)(-]");
////        boolean isUnaryMinus = false;
//
//        // TODO: 2020/04/27 "1---2 가능한지 답변 확인한 후, 연속된 Unary case 검토여부 확정"
//        // TODO: 2020/04/27 "버려진 방법 : 0을 삽입하는 Algorithm을 적용해서 unary -를 미리 parsing한다"
//        // By using FIFO property of queue, it is possible to maintain origin order of chunks.
//        Queue<Element> resultQueue = new LinkedList<>();
//        // For every chunkes,
//        for (String chunk : parsedInput) {
//            // Convert 'chunked string' as 'Element'.
//            Element newElem = new Element(chunk);
//
//            // For continous operator cases,
//            if (resultQueue.peek().isOpertor() && newElem.isOpertor()) {
//                // 2nd operator is '-'
//                if (newElem.getOperator() == '-') {
//                    // Convert '-' to '~' to avoid confusing.
//                    newElem.makeOperatorUnary();
////                    isUnaryMinus = true;
////                    continue;
//
//                } else {
//                    // 2nd operator excluding '-' comes,
//                    throw new Exception("NOT ALLOWED : CONTINUOUS OPERATORS");
//                }
//
//            // For the case that starting with operator
//            } else if (resultQueue.isEmpty() && newElem.isOpertor()) {
//                if (newElem.getOperator() == '-') {
//                    newElem.makeOperatorUnary();
//
//                } else if (newElem.getOperator() != '(') {
//                    throw new Exception("NOT ALLOWED : STARTS WITH OPERATORS EXCEPT '(' or '-'");
//
//                }
//            }
//
//            // Add to queue.
//            resultQueue.add(new Element(chunk));
//        }
//
//        // Input string stored as chunked Element in returning queue.
//        return resultQueue;
//    }


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
                    while (currElem.compareTo(operatorStack.peek()) < 0) {
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
