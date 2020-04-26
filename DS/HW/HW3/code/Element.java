public class Element {
    // FIXME: 2020/04/27 "Enum 굳이 필요??"
    private boolean isOperand;
    private long operand;
    private char operator;
//    private int priority;

//    // Default Constructor
//    public Element() {
//        this.type = null;
//        this.operand = 0;
//        this.operator = '\0';
//    }

    // Constructor for type:Operand
    public Element(long _integer) {
        this.isOperand = true;
        this.operand = _integer;
        this.operator = '\0';
    }

    // Constructor for type:Operator
    public Element(char _operator) {
        this.isOperand = false;
        this.operand = 0;
        this.operator = _operator;
    }

    // Constructor that needs to determine
    public Element(String chunkWithNoSpaces) {

        char firstChar = chunkWithNoSpaces.charAt(0);

        // _chunk holds operand
        if (firstChar >= '0' && firstChar <= '9') {
            this.isOperand = true;
            this.operand = Long.parseLong(chunkWithNoSpaces);
            this.operator = '\0';
        // _chunk holds operator
        } else {
            this.isOperand = false;
            this.operand = 0;
            this.operator = chunkWithNoSpaces.charAt(0);
        }
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

    public int compareTo(Element o) {
        // TODO: 2020/04/27 "체크하려고 만든거니 나중에 지우자"
        if (!this.isOpertor() || !o.isOpertor()) {
            System.out.println("Don't compare operand");
        }

        // Determine priority by covering all the cases
        // FIXME: 2020/04/27 "최대 비교 횟수가 8번이니까 평균적으로 4.5번 거치는 셈이라고 러프하게 보면 차라리 어레이 할당하는게 나으려나"
        char leftOpertor = this.getOperator();
        char rightOperator = o.getOperator();
        if (leftOpertor == '(' || leftOpertor == ')') {
            if (rightOperator == '(' || rightOperator == ')') return 0;
            else return 1;
        }
        if (rightOperator == '(' || rightOperator == ')') return -1;

        if (leftOpertor == '^' || leftOpertor == '~') {
            if (rightOperator == '^' || rightOperator == '~') return 0;
            else return 1;
        }
        if (rightOperator == '^' || rightOperator == '~') return -1;

        if (leftOpertor == '*' || leftOpertor == '%' || leftOpertor == '/') {
            if (rightOperator == '*' || rightOperator == '%' || rightOperator == '/') return 0;
            else return 1;
        }
        if (rightOperator == '*' || rightOperator == '%' || rightOperator == '/') return -1;

        // 요기부터
        if (leftOpertor == '+' || leftOpertor == '-') {
            if (rightOperator == '+' || leftOpertor == '-') return 0;
            else {
                System.out.println("Operator comparing failed!");
            }
        }

        System.out.println("Operator comparing failed!");
        // 요기까지 지워주면 된다.

        // TODO: 2020/04/27 "하단부터는 주석처리된 코드로 충분하다. 왜냐하면 위에서 모든 케이스에 대해서 커버했기 때문에 그저 디버깅용"
        // return 0; // Only-left case is left:+/- & right:+/-
        return 0; // Only-left case is left:+/- & right:+/-
    }

}
