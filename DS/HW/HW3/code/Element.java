import java.lang.reflect.Constructor;

public class Element {
    // FIXME: 2020/04/27 "Enum 굳이 필요??"
    private boolean isOperand;
    private long operand;
    private char operator;

    /* NO NEED */
//    private int priority;

//    // Default Constructor
//    public Element() {
//        this.type = null;
//        this.operand = 0;
//        this.operator = '\0';
//    }
    /* NO NEED */

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

    /* NO NEED */
//     Constructor that needs to determine
//    public Element(String chunkWithNoSpaces) {
//
//        char firstChar = chunkWithNoSpaces.charAt(0);
//
//        // _chunk holds operand
//        if (firstChar >= '0' && firstChar <= '9') {
//            this.isOperand = true;
//            this.operand = Long.parseLong(chunkWithNoSpaces);
//            this.operator = '\0';
//        // _chunk holds operator
//        } else {
//            this.isOperand = false;
//            this.operand = 0;
//            this.operator = chunkWithNoSpaces.charAt(0);
//        }
//    }
    /* NO NEED */

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
        // TODO: 2020/04/27 "체크하려고 만든거니 나중에 지우자"
//        if (!this.isOpertor() || !o.isOpertor()) {
//            throw new Exception("NOT ALLOWED: COMPARING BETWEEN OPERATOR AND OPERAND");
//        }

//        if (o == null) return 1;

        // Determine priority by covering all the cases
        // FIXME: 2020/04/27 "최대 비교 횟수가 8번이니까 평균적으로 4.5번 거치는 셈이라고 러프하게 보면 차라리 어레이 할당하는게 나으려나"
        char leftOpertor = this.getOperator();
        char rightOperator = o.getOperator();

        if (leftOpertor == '(' || leftOpertor == ')') {
            if (rightOperator == '(' || rightOperator == ')') return 0;
            else return 1;
        }
        if (rightOperator == '(' || rightOperator == ')') return -1;

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

        // FIXME: 2020/04/27 "ERROR HANDLING을 println으로 할지 throws로 할지 통일"

        // Only-left case is left:+/- & right:+/-
        if (leftOpertor == '+' || leftOpertor == '-') {
            // +, - are left-associative, return even -1 for the same-priority case
            return -1;
        }

        throw new Exception("NOT ALLOWED: FAILED TO PAIR OPERATOR");
    }

    public void makeOperatorUnary() {
        this.operand = '~';
    }
}
