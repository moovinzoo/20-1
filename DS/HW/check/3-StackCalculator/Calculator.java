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
                        throw new Exception("ERROR");
                    }
                }

            // Operand pops
            } else {
                operandStack.push(currElem.getOperand());
            }
        }

        if (operandStack.size() != 1)
            throw new Exception("ERROR");

        long returnValue = operandStack.pop();

        // for helping garbage collector.
        operandStack = null;

        return returnValue;
    }
}
