import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parsing {

    public static final String CALCULATING_PATTERN = "\\^\\*\\/\\%\\+\\-";

    public static Queue<Element> processInput(String input) throws Exception {
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

        // Convert infix to postfix, and return postfixQueue.
        return infixToPostfix(infixQueue);
    }


    public static void testSpacesBetweenOperands(String input) throws Exception{
        // Check if there exist invalid chunk like [operand][spaces][operand].
        if (input.matches(".*[0-9]+[ \t]+[0-9]+.*"))
            throw new Exception("NOT ALLOWED : SPACE BETWEEN OPERANDS");
    }

    public static String removeSpaces(String input) {
        // Remove all space/tab by replacing method.
        return input.replaceAll("[ \t]*", "");
    }

    // For the first step of Parsing, check if input string contains NOT-allowed characters.
    public static void testInputContainsWrong(String input) throws Exception {
        // If input contains NOT-allowed characters, throws.
        if (!input.matches("[0-9" + CALCULATING_PATTERN + "\\)\\(]*"))
            throw new Exception("NOT ALLOWED : ~(CHARACTER SET) EXSITS");
    }

    public static void testParenthesesPairing(String input) throws Exception {
        int trackParentheses = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') trackParentheses++;
            else if (input.charAt(i) == ')') trackParentheses--;

            // When trackParentheses ever goes negative, ERROR
            if (trackParentheses < 0) throw new Exception("NOT ALLOWED: PARENTHESIS CLOSED BEFORE OPEN.");
        }

        if (trackParentheses != 0) throw new Exception("NOT ALLOWED: PARENTHESIS DO NOT MATCH.");
    }


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
        final Pattern OPERAND_PATTERN = Pattern.compile("[0-9]+");
        Matcher operandMatcher = OPERAND_PATTERN.matcher(input);
        int prevEnd = 0;

        // Until there's more operand
        while (operandMatcher.find()) {
            // Renew the current index of start & end.
            int currStart = operandMatcher.start();
            int currEnd = operandMatcher.end();

            // Set current variables; [Operator(String after preceding group)] precedes [Operand(group)].
            String currOperand = operandMatcher.group();
            String currOperatorChunk = input.substring(prevEnd, currStart);


            /* Store Operator & Operand into the resultQueue(infix form). */

            // Test validity of [Operator] chunk and add them in result Queue first.
            processOperatorChunk(currOperatorChunk, resultQueue);
            /* Preceding [Operator] added in resultQueue */

            // Convert current operand to Element class instance and add it to Queue.
            processOperand(currOperand, resultQueue);
            /* Following [Operand] added in resultQueue */

            // Update the prev index of end.
            prevEnd = currEnd;
        }

        // If there remains some operator
        if (prevEnd < input.length()) {
            // It should only be "))...)"
            String lastOperatorChunk = input.substring(prevEnd);
            if (!lastOperatorChunk.matches("[\\)]+")) {
               throw new Exception("NOT ALLOWED: ENDS WITH ILLEGAL OPERATORS");
            }

            for (char currOperator : lastOperatorChunk.toCharArray()) {
                Element newElem = new Element(currOperator);
                resultQueue.add(newElem);
            }
        }
        /* Preceding [Operator] added in resultQueue */

        return resultQueue;
    }


    public static void processOperatorChunk(String operatorChunk, Queue<Element> infixQueue) throws Exception {
        // 1. If currOperatorChunk is the first one,
        if (infixQueue.isEmpty()) {
            // Input string starts with operator chunk.
            if (operatorChunk.length() > 0) {
                // Test for 2 only forms of first-operator-chunk.
                if (!operatorChunk.matches("[\\-\\(]*")) {
                    throw new Exception("NOT ALOWED: STARTS WITH INVALID OPERATOR");
                }

                // If the form is legal, split operator-chunk by single operator.
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
                throw new Exception("NOT ALLOWED: INVALID OPERATOR");
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

                // Add operator element.
                infixQueue.add(newElem);
            }
        }
    }


    public static void processOperand(String operand, Queue<Element> infixQueue) {
        // No need to check; [Operator] that starts with "0-" excluding "0" never comes.
        Element newOperand = new Element(Long.parseLong(operand));
        // Add operand element.
        infixQueue.add(newOperand);
    }


    // Convert infix expression to postfix expression.
    public static Queue<Element> infixToPostfix(Queue<Element> infixQueue) throws Exception {
        Stack<Element> operatorStack = new Stack<>();
        Queue<Element> postfixQueue = new LinkedList<>();

        // Until infix is empty.
        while (!infixQueue.isEmpty()) {
            // Poll single element.
            Element currElem = infixQueue.poll();
            // 1. Current Element is operator.
            if (currElem.isOpertor()) {
                // 1) Operator is ')'.
                if (currElem.getOperator() == ')') {
                    // Pop all the stored operators and push to the postfixStack until '('
                    while (operatorStack.peek().getOperator() != '(' && !operatorStack.isEmpty()) {
                        // Get single operator from top of the operatorStack and add it to postfixQueue.
                        postfixQueue.add(operatorStack.pop());
                    }

                    // When finding '(' ends without success
                    if (operatorStack.isEmpty())
                        throw new Exception("NOT ALLOWED : FAILED TO FIND PAIR OF ')'");

                    // Remove remained '(' by pop without storing.
                    operatorStack.pop();

                // 2) Operator is not ')'.
                } else {
                    // Compare priorities between 'currElem' and 'top element of Stack'
                    // If, current element has low-priority,
                    while (!operatorStack.isEmpty() && currElem.compareTo(operatorStack.peek()) < 0) {
                        // Pop operator on the top of the operatorstack that has high-priority, and push it to the postfixStack.
                        postfixQueue.add(operatorStack.pop());
                    }
                    /* In Element.compareTo() method, pre-promised decisions for the same priority cases considering left-associative / right-associative. So, No need to consider 'same-priority' cases. */

                    // Push operator to operatorStack.
                    operatorStack.push(currElem);
                }

            // 2. CurrElem is operand
            } else {
                // ERROR already handled in advance, so, simply add to postfixQueue.
                postfixQueue.add(currElem);
            }
        }
        /* Now, infixQueue is empty */


        // Move all remained operators from operatorStack to postfixQueue.
        while (!operatorStack.isEmpty()) {
            postfixQueue.add(operatorStack.pop());
        }
        /* Now, operatorStack is empty */

        // Help garbage collector to delete unnecesary object.
        infixQueue = null;
        operatorStack = null;

        // Return finished postfixStack.
        return postfixQueue;
    }
}
