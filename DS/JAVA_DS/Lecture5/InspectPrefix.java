package Lecture5;

public class InspectPrefix {
    static String convert(String input) {
        // PrefixToPostfix
        if (isPre(input)) {
            // pre: A valid prefix expression.
            // return the equivalen postfix expression.
            char ch = input.charAt(0); // first character of input
            String newString = input.substring(1, input.length()); // Delete 1st

            if (isIdentifier(ch)) {
                return ""+ch;
            } else { // ch is operator
                String postfix1 = convert(newString);
                String postfix2 = convert(newString);

                return postfix1+postfix2+ch; // concat
            }

        } else {
            // pre: Not a valid prefix expression.
            // return error message.
            return "ERROR";
        }

    }

    static boolean isPre(String input) {
        // return true if the string A[first...last] is in prefix form.
        // otherwise return false
        char[] A = input.toCharArray();

        int first = 0;
        int last = A.length - 1;
        int lastChar = endPre(A, first, last);
        if (lastChar == last) return true;
        else return false;
    }

    static int endPre(char []A, int first, int last) {
        // input A[first...last],
        // return the position of the end of the prefix expression beginning at A[first], if one exists
        if (first > last) return -1; // not prefix
        if (isIdentifier(A[first])) {
            return first;
        } else if (isOperator(A[first])) {
            int firstEnd = endPre(A, first+1, last);
            if (firstEnd == -1) return -1; // not prefix
            else return endPre(A, firstEnd+1, last);
        } else {
            return -1; // not prefix
        }
    }

    static boolean isIdentifier(char ch) {
        if ((int)ch >= '0' && (int)ch <= '9') return true;
        else return  false;
    }

    static boolean isOperator(char ch) {
        if (ch == '-' || ch == '+') return true;
        else return  false;
    }


    public static void main(String args[]) {

        System.out.println(convert("+12"));
    }
}
