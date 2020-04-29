import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.ToDoubleBiFunction;

public class Calculator {
    public static long calculate(Stack<Element> postfixStack) throws Exception {
        // Instantiate local variable for calculation.
        Stack<Long> operandStack = new Stack<>();

        // Until postfixStack is empty,
        while (!postfixStack.isEmpty()) {
            // Pop one element on the top.
            Element currElem = postfixStack.pop();

            // Operator pops
            if (currElem.isOpertor()) {
                char currOperator = currElem.getOperator();

                if (currOperator == '^') {
                    long exponent = operandStack.pop();
                    long base = operandStack.pop();
                    operandStack.push((long)Math.pow(base, exponent));

                } else if (currOperator == '~') {
                    long toNegative = operandStack.pop();
                    operandStack.push(toNegative * (-1));

                } else {
                    // TODO: 2020/04/29 "pop시 ERROR"
                    // FIXME: 2020/04/29 "FROM HERE!!!"
                    long right = operandStack.pop();
                    long left = operandStack.pop();
//                    long right = operandStack.pop();
//                    long left = operandStack.pop();
                    if (currOperator == '*') {
                        operandStack.push(left * right);
                    } else if (currOperator == '%') {
                        operandStack.push(left % right);
                    } else if (currOperator == '/') {
                        operandStack.push(left / right);
                    } else if (currOperator == '+') {
                        operandStack.push(left + right);
                    } else if (currOperator == '-') {
                        operandStack.push(left - right);
                    } else {
                        throw new Exception("NOT WORKING: NOT ALLOWED OPERATOR REMAINS!");
                    }
                }

            // Operand pops
            } else {
                operandStack.push(currElem.getOperand());
            }
        }

        if (operandStack.size() != 1)
            throw new Exception("NOT WORKING: OPERAND REMAINS > 1");

        long returnValue = operandStack.pop();

        // for helping garbage collector.
        operandStack = null;

        return returnValue;
    }
}
