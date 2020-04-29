import java.lang.reflect.Constructor;

public class Element {
    // FIXME: 2020/04/27 "Enum 굳이 필요??"
    private boolean isOperand;
    private long operand;
    private char operator;


    // Constructor for type:Operand
    public Element(long _operand) {
        this.isOperand = true;
        this.operand = _operand;
        this.operator = '\0';
    }

    // Constructor for type:Operator
    public Element(char _operator) {
        this.isOperand = false;
        this.operand = 0;
        this.operator = _operator;
    }


    // Prevent name corruption between member variable and method
    public boolean isOpertor() {
        return !(this.isOperand);
    }

    public long getOperand() {
        return this.operand;
    }

    public char getOperator() {
        return this.operator;
    }


    public int compareTo(Element o) throws Exception{

        // Determine priority by covering all the cases
        char leftOpertor = this.getOperator();
        char rightOperator = o.getOperator();

        // Whatever is the operator at the top, always goes onto the operatorStack.
        if (leftOpertor == '(') return 1;
        // Whatever operator wanted to come in, always agree with that.
        if (rightOperator == '(') return 1;

        if (leftOpertor == '^') {
            // ^ is right-associative, return 1 for the same-priority case
            if (rightOperator == '^') return 1;
            else return 1;
        }
        if (rightOperator == '^') return -1;

        if (leftOpertor == '~') {
            // ~ is right-associative, return 1 for the same-priority case
            if (rightOperator == '~') return 1;
            else return 1;
        }
        if (rightOperator == '~') return -1;

        if (leftOpertor == '*' || leftOpertor == '%' || leftOpertor == '/') {
            // *, %, / are left-associative, return -1 for the same-priority case
            if (rightOperator == '*' || rightOperator == '%' || rightOperator == '/') return -1;
            else return 1;
        }
        if (rightOperator == '*' || rightOperator == '%' || rightOperator == '/') return -1;

        // Only-left case is left:+/- & right:+/-
        if (leftOpertor == '+' || leftOpertor == '-') {
            // +, - are left-associative, return even -1 for the same-priority case
            return -1;
        }
        throw new Exception("NOT ALLOWED: FAILED TO PAIR OPERATOR");
    }

}
