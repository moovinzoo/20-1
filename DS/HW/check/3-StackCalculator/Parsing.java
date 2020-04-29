import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parsing {
    public static final String CALCULATING_PATTERN = "\\^\\*\\/\\%\\+\\-";

    // FIXME: 2020/04/26 "이렇게하면 throw 되나?"
    public static Queue<Element> processInput(String input) throws Exception {
        try {
            // Check if there exist invalid chunk like [operand][spaces][operand].
            testSpacesBetweenOperands(input);
            /* Now, can remove spaces */

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
            Queue<Element> postfixQueue = infixToPostfix(infixQueue);

            /* Now, input string processed chunks-level and stored as Element in parsedString(queue). */

            // Return converted stack as result.
            return postfixQueue;

        } catch (Exception e) {
            // FIXME: 2020/04/27 "if문 throw new~ 관련하여 Exception class 하나 따로 만들어서 handling 할 것."
            throw e;
        }
    }


    public static void testSpacesBetweenOperands(String input) throws Exception{
        // Check if there exist invalid chunk like [operand][spaces][operand].
        if (input.matches("[0-9]+[ \t]+[0-9]+"))
            throw new Exception("ERROR");
    }

    public static String removeSpaces(String input) {
        // Remove all space/tab by replacing method.
        return input.replaceAll("[ \t]*", "");
    }

    // For the first step of Parsing, check if input string contains NOT-allowed characters.
    public static void testInputContainsWrong(String input) throws Exception {
        // If input contains NOT-allowed characters, throws.
//        if (!input.matches("[0-9/%^+*)(-]*"))
        if (!input.matches("[0-9" + CALCULATING_PATTERN + "\\)\\(]*"))
            throw new Exception("ERROR");
    }

    public static void testParenthesesPairing(String input) throws Exception {
        int trackParentheses = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') trackParentheses++;
            else if (input.charAt(i) == ')') trackParentheses--;

            // When trackParentheses ever goes negative, ERROR
            if (trackParentheses < 0) throw new Exception("ERROR");
        }

        if (trackParentheses != 0) throw new Exception("ERROR");
    }


    // New version. using Patter&Matcher
    // Plent input strings into queue chunk by chunk, with some process.
    public static Queue<Element> parseString(String input) throws Exception {
        /* No spaces. */
        /* No any NOT-ALLOWED characters. */
        /* Parentheses match. */

        // Store parsed input string as class:Element in Queue considering unary '-'
        /* Processes = {"Removing spaces", "Determine each Type(enum) of chunks"} */

        // By using FIFO property of queue, it is possible to maintain origin order of chunks.
        Queue<Element> resultQueue = new LinkedList<>();

        // Ready to find operands
//        final Pattern OPERAND_PATTERN = Pattern.compile("[-+]?[0-9]+");
        final Pattern OPERAND_PATTERN = Pattern.compile("[0-9]+");
        Matcher operandMatcher = OPERAND_PATTERN.matcher(input);
        int prevStart = -1;
        int prevEnd = 0;

        // Until there's more operand
        while (operandMatcher.find()) {
            // Renew the current index of start & end.
            int currStart = operandMatcher.start();
            int currEnd = operandMatcher.end();

            // Set current variables; [Operator(String after preceding group)] precedes [Operand(group)].
            String currOperand = operandMatcher.group();
            String currOperatorChunk = input.substring(prevEnd, currStart);

            // Store Operator & Operand into the resultQueue(infix form).
            try {
                // Test validity of [Operator] chunk and add them in result Queue first.
                processOperatorChunk(currOperatorChunk, resultQueue);
                /* Preceding [Operator] added in resultQueue */

                // Convert current operand to Element class instance and add it to Queue.
                processOperand(currOperand, resultQueue);
                /* Following [Operand] added in resultQueue */

            } catch (Exception e) {
                throw e;
            }

            // Update the prev index of start & end.
            prevStart = currStart;
            prevEnd = currEnd;
        }

        // If there remains some operator
        if (prevEnd < input.length()) {
            // It should only be "))...)"
            String lastOperatorChunk = input.substring(prevEnd);
            if (!lastOperatorChunk.matches("[\\)]+")) {
               throw new Exception("ERROR");
            }

            for (char currOperator : lastOperatorChunk.toCharArray()) {
                Element newElem = new Element(currOperator);
                resultQueue.add(newElem);
            }
        }
        /* Preceding [Operator] added in resultQueue */

        // Return
        return resultQueue;
    }


    public static void processOperatorChunk(String operatorChunk, Queue infixQueue) throws Exception {
        // 1. If currOperatorChunk is the first one,
        if (infixQueue.isEmpty()) {
            // Input string starts with operator chunk.
            if (operatorChunk.length() > 0) {
                // Test for 2 only forms of first-operator-chunk.
                if (!operatorChunk.matches("[\\-\\(]*")) {
                    throw new Exception("ERROR");
                }

                for (char currOperator : operatorChunk.toCharArray()) {
                    Element newElem;
                    if (currOperator == '-') {
                        newElem = new Element('~');
                    } else { // currOperator == '('
                        newElem = new Element('(');
                    }

                    infixQueue.add(newElem);
                }
            }

        // 2. currOperatorChunk is not the first one,
        } else {
            // Test for 2 only forms of nth-operator-chunk (n>1).
            if (!operatorChunk.matches("[\\)]*" + "[" + CALCULATING_PATTERN + "]" + "[\\-\\(]*") && !operatorChunk.matches("[" + CALCULATING_PATTERN + "]" + "[\\-\\(]*")) {
                throw new Exception("ERROR");
            }
            /* Now, operator chunk is valid */

            // Unary conversion ; make excessible '-' to '~'.
            boolean isValidOperatorPrecedes = false;
            for (char currOperator : operatorChunk.toCharArray()) {
                // Operator's validity already checked above, so there's no illegal operator duplication.
                // So, the only case that multiple operator exists is '-'s after one operator ; conver them to '~'
                Element newElem;

                // Case1: First operator that belongs to {^, *, /, %, +, -} comes,
                if (!isValidOperatorPrecedes && (""+currOperator).matches("[" + CALCULATING_PATTERN + "]")) {
                    isValidOperatorPrecedes = true;
                    newElem = new Element(currOperator);
                    // Case2: '-' comes after another operator that belongs to {^, *, /, %, +, -}.
                } else if (isValidOperatorPrecedes && (currOperator == '-')) {
                    newElem = new Element('~');
                    // Case3: case for (, ) comes
                } else {
                    newElem = new Element(currOperator);
                }

                infixQueue.add(newElem);
            }
        }
    }


    public static void processOperand(String operand, Queue infixQueue) throws Exception {
        // No need to check; [Operator] that starts with "0-" excluding "0" never comes.
        Element newOperand = new Element(Long.parseLong(operand));
        infixQueue.add(newOperand);
    }

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
    public static Queue<Element> infixToPostfix(Queue<Element> infixQueue) throws Exception {
        Stack<Element> operatorStack = new Stack<>();
        Queue<Element> postfixQueue = new LinkedList<>();

        // Until infix is empty
        while (!infixQueue.isEmpty()) {
            // Poll 1 element
            Element currElem = infixQueue.poll();
            // Current Element is operator
            if (currElem.isOpertor()) {
                // operator is closing parenthesis
                if (currElem.getOperator() == ')') {
                    // Pop all the stored operators and push to the postfixStack until '('
                    while (operatorStack.peek().getOperator() != '(' && !operatorStack.isEmpty()) {
                        postfixQueue.add(operatorStack.pop());
                    }

                    // When finding '(' ends without success
                    if (operatorStack.isEmpty())
                        throw new Exception("ERROR");

                    // Pop '(' without storing.
                    operatorStack.pop();


                // operator is not ')'
                } else {
                    // Compare priorities between 'currElem' and 'top element of Stack'
                    // If, current element has low-priority,
                    while (!operatorStack.isEmpty() && currElem.compareTo(operatorStack.peek()) < 0) {
                        // Pop operator on the top of the operatorstack that has high-priority, and push it to the postfixStack.
                        postfixQueue.add(operatorStack.pop());
                    }
                    /* In Element.compareTo() method, pre-promised decisions for the same priority cases considering left-associative / right-associative. So, No need to consider 'same-priority' cases. */

                    operatorStack.push(currElem);
                }

            // currElem is operand
            } else {
//                // When two operand wating in a row,
//                if (!infixQueue.peek().isOpertor())
//                    throw new Exception("NOT ALLOWED : CONTINUOUS OPERANDS");
                postfixQueue.add(currElem);

            }
        }

        /* Now, infixQueue is empty */

        // Until operationStack is empty
        while (!operatorStack.isEmpty()) {
            postfixQueue.add(operatorStack.pop());
        }

        /* Now, operatorStack is empty */

        // Helping garbage collector to delete unnecesary object
        infixQueue = null;
        operatorStack = null;

        // Return finished postfixStack.
        return postfixQueue;
    }
}
