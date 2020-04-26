public class Element {
    private Type type;
    private long integer;
    private char operator;

    // Default Constructor
    public Element() {
        this.type = null;
        this.integer = 0;
        this.operator = '\0';
    }

    // Constructor for type:INTEGER
    public Element(Type _type, long _integer) {
        this.type = _type;
        this.integer = _integer;
        this.operator = '\0';
    }

    // Constructor for type:OPERATOR
    public Element(Type _type, char _operator) {
        this.type = _type;
        this.integer = 0;
        this.operator = _operator;
    }

    // Constructor that needs to determine
    public Element(String chunkWithNoSpaces) {

        char firstChar = chunkWithNoSpaces.charAt(0);

        // _chunk holds integer
        if (firstChar >= '0' && firstChar <= '9') {}

//        } else if (firstChar == '+' || firstChar == '-' || )
    }

    public long getInteger() {
        return this.integer;
    }

    public char getOperator() {
        return this.operator;
    }

}
