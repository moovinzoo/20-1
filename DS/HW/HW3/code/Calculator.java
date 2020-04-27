import java.util.Stack;

public class Calculator {
    public static long calculate(Stack<Element> postfixStack) {
        while (!postfixStack.isEmpty()) {
            Element currElem = postfixStack.pop();
            if (currElem.isOpertor()) {
                switch(currElem.getOperator()) {

                    case '^':
                    case '~':
                    case '*':
                    case '/':
                    case '%':
                    case '+':
                    case '-':
                }

            } else {

            }
        }
    }
}
