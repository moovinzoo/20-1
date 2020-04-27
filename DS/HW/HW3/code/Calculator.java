import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Calculator {
    public static long calculate(Stack<Element> postfixStack) throws Exception {
        // Instantiate local variable for calculation.
        Queue<Long> operandQueue = new LinkedList<>();

        // Until postfixStack is empty,
        while (!postfixStack.isEmpty()) {
            // Pop one element on the top.
            Element currElem = postfixStack.pop();

            // Operator pops
            if (currElem.isOpertor()) {
                Element newElem;

                if (currElem.getOperator() == '^') {
                    long base = operandQueue.poll();
                    long exponent = operandQueue.poll();
                    operandQueue.add((long)Math.pow(base, exponent));

                } else if (currElem.getOperator() == '~') {
                    if (operandQueue.size() == 2) {
                        operandQueue.
                    }
                    long toNegative = operandQueue.();
                    operandQueue.add(toNegative * (-1));

                } else {
                    // currElem.getOperator() is {*, /, %, +, -}

                }
                switch(currElem.getOperator()) {
                    case '^': {
                        break;
                    }
                    case '~': {
                        break;
                    }
                    case '*': {
                        long left = operandQueue.poll();
                        long right = operandQueue.poll();
                        operandQueue.add(left * right);
                        break;
                    }
                    case '/': {
                        long left = operandQueue.poll();
                        long right = operandQueue.poll();
                        operandQueue.add(left / right);
                        break;
                    }
                    case '%': {
                        long left = operandQueue.poll();
                        long right = operandQueue.poll();
                        operandQueue.add(left / right);
                        break;
                    }
                    case '+': {
                        long left = operandQueue.poll();
                        long right = operandQueue.poll();
                        operandQueue.add(left / right);
                        break;
                    }
                    case '-': {
                        long left = operandQueue.poll();
                        long right = operandQueue.poll();
                        operandQueue.add(left / right);
                        break;
                    }
                    default: throw new Exception("NOT WORKING: NOT ALLOWED OPERATOR REMAINS!");
                }

            // Operand pops
            } else {
                operandQueue.add(currElem.getOperand());
            }
        }
    }
}
