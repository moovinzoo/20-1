import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigInteger {
    // implement this
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

    public static final Pattern SPACE_PATTERN = Pattern.compile("[ ]*");
    public static final Pattern INTEGER_WITH_SIGN = Pattern.compile("[-+]?[0-9]+");
    public static final int MAX_INTEGER_SIZE = 100;

    private byte[] byteArr;
    private String sign;

    public BigInteger(int i) {
        // 0짜리 하나 만들기
        if (i == 0) {
            this.byteArr = new byte[1]; // Initial value is { 0 }
            this.sign = "";
        } else {
            this.byteArr = new byte[i];
        }
    }

    public BigInteger(byte[] _byteArr, String _sign) {
        this.byteArr = _byteArr;
        this.sign = _sign;
    }

    public BigInteger(String s) {
        if (s.equals("0") || s.equals("-0")|| s.equals("+0")) {
            this.byteArr = new byte[1]; // Initial value is { 0 }
            this.sign = "";
        } else {
            // TODO : Assign private members of bigInteger
            this.sign = s.substring(0, 1);
            this.byteArr = new byte[s.length() - 1];
            for (int i = 1; i < s.length(); i++) {
                this.byteArr[i - 1] = (byte)(s.charAt(i) - '0');
            }

        }
    }

    public BigInteger add(BigInteger right) {
        // TODO : Handling the cases for 0
        if (this.isZero()) {
            return right;
        } else if (right.isZero()) {
            return this;
        } else {
            // TODO : Initialize return value & determine sign
            byte[] bigArr, smallArr;
            if (this.compare(right) > 0) {
                bigArr = this.byteArr;
                smallArr = right.byteArr;
            } else {
                bigArr = right.byteArr;
                smallArr = this.byteArr;
            }
            int bigLen = bigArr.length;
            int smallLen = smallArr.length;

            int resultLen = bigLen + 1; // base on Left(bigger) one
            byte[] resultArr = new byte[resultLen];
            String resultSign = this.getSign();

            // TODO : Calculate
            for (int i = 1; i <= bigLen; i++) {

                int indexLeft = bigLen - i;
                int indexRight = smallLen - i;
                int indexResult = indexLeft + 1;

                byte leftValue = bigArr[indexLeft];
                byte rightValue = (indexRight >= 0)?(smallArr[indexRight]):0;
                byte tmpValue = resultArr[indexResult];

                byte tmpResult = (byte)(leftValue + rightValue + tmpValue);
                byte sum = (byte) (tmpResult % 10);
                byte carry = (byte) (tmpResult / 10);
                resultArr[indexResult] = sum;
                if (carry > 0) {
                    resultArr[indexResult - 1] = carry;
                }
            }

            return new BigInteger(resultArr, resultSign);
        }
    }


    public BigInteger subtract(BigInteger smallRight, boolean isFlip) {
        // TODO : Handling the cases for 0
        if (smallRight.isZero()) {
            if (isFlip) {
                this.setSign(this.getOppositeSign());
            }
            return this;

        } else {
            // TODO : Initialize return value & determine sign
            byte[] leftArr = this.getByteArr();
            byte[] rightArr = smallRight.getByteArr();
            int leftLen = leftArr.length;
            int rightLen = rightArr.length;

            int resultLen = leftLen; // base on Left(bigger) one
            String resultSign;
            byte[] resultArr = new byte[resultLen];
            if (isFlip) {
                resultSign = this.getOppositeSign();
            } else {
                resultSign = this.getSign();
            }

            // TODO : Calculate
            for (int i = 1; i <= leftLen; i++) {
                // Initialize index
                int indexLeft = leftLen - i;
                int indexRight = rightLen - i;
                int indexResult = indexLeft;

                // Initialize the byte value at index
                byte leftValue = leftArr[indexLeft];
                byte rightValue = (indexRight >= 0)?(rightArr[indexRight]):0;
                byte tmpValue = resultArr[indexResult];

                // Calculate the result
//                if (tmpValue < 0) {
//                    resultArr[indexResult - 1] -= 1;
//                }
                byte tmpResult = (byte)(10 + leftValue - rightValue + tmpValue);
                byte sum = (byte) (tmpResult % 10);
                byte carry = (byte) (tmpResult / 10);
                resultArr[indexResult] = sum;
                if (carry == 0) {
                    resultArr[indexResult - 1] -= 1;
                }
            }

            return new BigInteger(resultArr, resultSign);
        }
    }

    public BigInteger multiply(BigInteger smallRight) {
        // TODO : Handling the cases for 0
        if (smallRight.isZero()) {
            return smallRight;

        } else {
            // TODO : Initialize return value & determine sign
            byte[] leftArr = this.getByteArr();
            byte[] rightArr = smallRight.getByteArr();
            int leftLen = leftArr.length;
            int rightLen = rightArr.length;

            int resultLen = leftLen + rightLen; // base on Left(bigger) one
            String resultSign;
            byte[] resultArr = new byte[resultLen];
            if (this.getSign().equals(smallRight.getSign())) {
                resultSign = "+";
            } else {
                resultSign = "-";
            }

            // TODO : Calculate
            for (int i = 1; i <= rightLen; i++) {
                int indexRight = rightLen - i;
                for (int j = 1; j <= leftLen; j++) {
                    int indexLeft = leftLen - j;
                    int indexResult = resultLen - (i + j - 1); //coz, i&j starts at 0

                    byte leftValue = leftArr[indexLeft];
                    byte rightValue = rightArr[indexRight];
                    byte tmpValue = resultArr[indexResult];

                    byte tmpResult = (byte)(leftValue * rightValue + tmpValue);
                    byte sum = (byte) (tmpResult % 10);
                    byte carry = (byte) (tmpResult / 10);
                    resultArr[indexResult] = sum;
                    resultArr[indexResult - 1] += carry;
                }
            }

            return new BigInteger(resultArr, resultSign);
        }
    }

    public void setSign(String _sign) {
        this.sign = _sign;
    }

    public String getSign() {
        return this.sign;
    }

    public String getOppositeSign() {
        if (this.getSign().equals("+")) {
            return "-";
        } else if(this.getSign().equals("-")){
            return "+";
        } else {
            return "";
        }
    }

    public byte[] getByteArr() {
        return this.byteArr;
    }

    public boolean isZero() {
        if (this.getSign().equals("")) {
            return true;
        } else if (this.getByteArr().length == 1 && this.getByteArr()[0] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int compare(BigInteger operand) {
        // TODO : Compare the size of two BigInteger
        byte[] left = this.getByteArr();
        byte[] right = operand.getByteArr();
        if (left.length != right.length) {
            // TODO : Longer means bigger by constructor
            if (left.length > right.length) return 1;
            else return -1;

        } else {
            // TODO : Absolute value
            for (int i = 0; i < left.length; i++) {
                if (left[i] == right[i]) continue; // PASS, NEXT
                if (left[i] > right[i]) return 1;
                else return -1;
            }

            return 0;
        }
    }

    @Override
    public String toString() {
        // TODO : printout byteArr as String
        if (this.isZero()) {
            return "0";
        } else {
            String tmpSign = this.getSign();
            byte[] tmpArr = this.getByteArr();

            String returnStr = "";
            boolean isContinuedZero = true;
            if (tmpSign.equals("-")) returnStr += tmpSign;

            for (int i = 0; i < tmpArr.length; i++) {
                if (isContinuedZero && tmpArr[i] == 0) continue;
                isContinuedZero = false;
                returnStr += tmpArr[i];
            }

            return returnStr;
        }
    }

    static BigInteger evaluate(String input) throws IllegalArgumentException {
        // implement here
        // parse input
        // using regex is allowed

        // TODO : Remove spaces from input string
        String inputWithoutSpaces = SPACE_PATTERN.matcher(input).replaceAll("");

        // TODO : Extract intStrings with sign
        Matcher integerMatcher = INTEGER_WITH_SIGN.matcher(inputWithoutSpaces);
        StringBuffer sb = new StringBuffer();
        int cnt = 1;
        String intStr1 = null, intStr2 = null;
        while (integerMatcher.find()) {
            String tmp = integerMatcher.group(); // tmp ; 각각의 BigInteger 추출
            if (tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
                tmp = "+" + tmp;
            }
            if (cnt == 1) {
                intStr1 = tmp;
            } else {
                intStr2 = tmp;
            }
            integerMatcher.appendReplacement(sb, "");
            cnt++;
        }
        integerMatcher.appendTail(sb);

        // TODO : Determine operation sign
        String operation = sb.toString();
        if (operation.length() == 0) {
            operation = "+";
        }

        // TODO : TOSS; Assign new instance of BigInteger
        // One possible implementation
        BigInteger num1 = new BigInteger(intStr1);
        BigInteger num2 = new BigInteger(intStr2);

        BigInteger result;
        // Add
        if (operation.equals("+")) { // add, same sign
            if (num1.getSign().equals(num2.getSign())) {
                result = num1.add(num2);
            } else { // add, different sign
                if (num1.compare(num2) > 0) {
                    result = num1.subtract(num2, false);
                } else if (num1.compare(num2) < 0) {
                    result = num2.subtract(num1, false);
                } else {
                    result = new BigInteger(0);
                }
            }

        // Subtract
        } else if (operation.equals("-")) { // subtract, different sign
            if (!num1.getSign().equals(num2.getSign())) {
                result = num1.add(num2);
            } else { // subtract, same sign
                if (num1.compare(num2) > 0) {
                    result = num1.subtract(num2, false);
                } else if (num1.compare(num2) < 0) {
                    result = num2.subtract(num1, true);
                } else {
                    result = new BigInteger(0);
                }
            }

        // Multiply
        } else {
            if (num1.compare(num2) > 0) {
                result = num1.multiply(num2);
            } else {
                result = num2.multiply(num1);
            }
        }

        return result;
    }


    public static void main (String[]args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            try (BufferedReader reader = new BufferedReader(isr)) {
                boolean done = false;
                while (!done) {
                    String input = reader.readLine();

                    try {
                        done = processInput(input);
                    } catch (IllegalArgumentException e) {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput (String input) throws IllegalArgumentException
    {
        boolean quit = isQuitCmd(input);

        if (quit) {
            return true;
        } else {
            BigInteger result = evaluate(input);
            System.out.println(result.toString());

            return false;
        }
    }

    static boolean isQuitCmd (String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
