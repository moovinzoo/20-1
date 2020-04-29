import java.util.Queue;
import java.util.Stack;

public class Calculator {
    public static long calculate(Queue<Element> postfixStack) throws Exception {
        // Instantiate local variable for calculation.
        Stack<Long> operandStack = new Stack<>();

        // Until postfixStack is empty,
        while (!postfixStack.isEmpty()) {
            // Pop one element on the top.
            Element currElem = postfixStack.poll();

            // 1. Operator poped.
            if (currElem.isOpertor()) {
                char currOperator = currElem.getOperator();

                // 1) For whom has special operating order/way.
                if (currOperator == '^') {
                    long exponent = operandStack.pop();
                    long base = operandStack.pop();
                    // Prepare the ERROR case.
                    if ((base == 0) && (exponent < 0)) throw new Exception("NOT ALLOWED: OPERATION NOT DEFINED!");
                    operandStack.push((long)Math.pow(base, exponent));

                } else if (currOperator == '~') {
                    long toNegative = operandStack.pop();
                    operandStack.push(toNegative * (-1));

                // 2) For whom has ordinary operating order
                } else {
                    long right = operandStack.pop();
                    long left = operandStack.pop();
                    if (currOperator == '*') {
                        operandStack.push(left * right);
                    } else if (currOperator == '%') {
                        // Prepare the ERROR case.
                        if (right == 0) throw new Exception("NOT ALLOWED: OPERATION NOT DEFINED!");
                        operandStack.push(left % right);
                    } else if (currOperator == '/') {
                        // Prepare the ERROR case.
                        if (right == 0) throw new Exception("NOT ALLOWED: OPERATION NOT DEFINED!");
                        operandStack.push(left / right);
                    } else if (currOperator == '+') {
                        operandStack.push(left + right);
                    } else if (currOperator == '-') {
                        operandStack.push(left - right);
                    } else {
                        throw new Exception("NOT WORKING: NOT ALLOWED OPERATOR REMAINS!");
                    }
                }

            // 2. Operand poped.
            } else {
                operandStack.push(currElem.getOperand());
            }
        }

        // operandStack should've been empty.
        if (operandStack.size() != 1)
            throw new Exception("NOT WORKING: OPERAND REMAINS > 1");

        // Final value of operandStack is the result of calculation.
        long returnValue = operandStack.pop();

        // for helping garbage collector.
        operandStack = null;

        return returnValue;
    }
}
