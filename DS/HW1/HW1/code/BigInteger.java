import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO : case either LEFT or RIGHT is 0
// TODO : calculate level(redundant)

public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

    // implement this
    public static final Pattern SPACE_PATTERN = Pattern.compile("[ ]*");
    public static final Pattern INTEGER_WITH_SIGN = Pattern.compile("[-+]?[0-9]+");
    public static final int MAX_INTEGER_SIZE = 100;

    private byte[] byteArr;
    private String sign;

    public BigInteger(int i)
    {
        // 0짜리 하나 만들기
        if (i == 0) {
            this.byteArr = new byte[1]; // Initial value is { 0 }
            this.sign = "";
        // 1짜리 만들기
        } else if ( i == -1) {
            this.byteArr = new byte[1];
            this.getByteArr()[0] = (byte)1;
            this.sign = "";
        // i 사이즈 만들기
        } else {
            this.byteArr = new byte[i];
        }
    }

//    public BigInteger(byte[] arr)
//    {
//        this.byteArr = new byte[1];
//        byteArr[0]++; // Making { 1 }
//        this.sign = "";
//    }

    public BigInteger(String s)
    {
        // TODO : Assign sign of bigInteger
        this.sign = s.substring(0, 1);

        // TODO : Making String to Byte(Integer) ARR
        this.byteArr = new byte[MAX_INTEGER_SIZE];
        for (int i = 1; i < s.length(); i++) {
            this.byteArr[MAX_INTEGER_SIZE - s.length() + i] = (byte) (s.charAt(i) - '0');
        }
    }

    public void setSign(String _sign) {
        this.sign = _sign;
    }

    public String getSign() {
        return sign;
    }

    public byte[] getByteArr() {
        return byteArr;

    }

    public int compare(BigInteger operand) {
        // TODO : Absolute value
        byte[] left = this.getByteArr();
        byte[] right = operand.getByteArr();

        for (int i = 0; i < MAX_INTEGER_SIZE; i++) {
            if (left[i] == right[i]) continue; // PASS, NEXT
            if (left[i] > right[i]) return 1;
            else return -1;
        }

        return 0;
    }
    public boolean isZero() {
        byte[] tmp = this.getByteArr();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] != 0) return false;
        }

        return true;
    }

    public String getOppositeSign() {
        if (this.getSign().equals("+")) {
            return "-";
        } else {
            return "+";
        }
    }

    public BigInteger duplicate() {
        BigInteger returnValue = new BigInteger(this.byteArr.length);
        for (int i = 0; i < returnValue.getByteArr().length; i++) {
            returnValue.getByteArr()[i] = this.getByteArr()[i];
        }
        returnValue.setSign(this.getSign());

        return returnValue;
    }

    public BigInteger add(BigInteger smallRight, boolean fromMul)
    {
        // TODO : Initialize return value & determine sign
        BigInteger returnValue;

        if (!fromMul) {
            returnValue = new BigInteger(MAX_INTEGER_SIZE + 1);
        }

        // TODO : Calculate
        int leftLen = this.getByteArr().length;
        int rightLen = smallRight.getByteArr().length;
        for (int i = leftLen - 1; i >= 0; i--) {
            int j = i - (leftLen-rightLen);
            byte left = this.getByteArr()[i];
            byte right;
            if(i < smallRight.getByteArr().length) {
                right = smallRight.getByteArr()[i];
            } else {
                right = 0;
            }

            if (i != 199) returnValue.getByteArr()[i+1] += (byte)((left + right) % 10);
            returnValue.getByteArr()[i] += (byte)((left + right) / 10);
        }

        return returnValue;
    }

    public BigInteger subtract(BigInteger smallRight, boolean isFlip)
    {
        // TODO : Initialize return value & determine sign
        BigInteger returnValue = new BigInteger(MAX_INTEGER_SIZE);
        if (isFlip) {
            returnValue.setSign(this.getOppositeSign());
//        } else {
//            returnValue.setSign(this.getSign());
//        }

        // TODO : Calculate
        for (int i = 0; i < this.getByteArr().length; i++) {
            byte left = this.getByteArr()[i];
            byte right;
            if (smallRight.getByteArr().length > i) {
                right = smallRight.getByteArr()[i];
            } else {
                right = (byte)0;
            }
            byte result = (byte) (left - right + 10);


            returnValue.getByteArr()[i] += (byte)(result % 10);
            if (i > 0) returnValue.getByteArr()[i-1] += (byte)(result / 10 - 1);
        }

        return returnValue;
    }

    public BigInteger multiply(BigInteger right)
    {
        if (this.isZero() || right.isZero()) {
            return new BigInteger(0);
        } else {
            // TODO : Initialize return value & determine sign
            BigInteger returnValue = new BigInteger(MAX_INTEGER_SIZE * 2);
            if (this.getSign().equals(right.getSign())) {
                returnValue.setSign("+");
            } else {
                returnValue.setSign("-");
            }


            // TODO : calculate
            BigInteger tmp = new BigInteger(-1);
            BigInteger left = this.duplicate();
            System.out.println("tmp : " + tmp.toString());
//                try {
//                    System.in.read();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            while(!right.isZero()) {
//                BigInteger tmp = new BigInteger(MAX_INTEGER_SIZE);
                System.out.println("before : " + right.toString());
                right = right.subtract(tmp, false);
                System.out.println("after : " + right.toString());
                returnValue.add(this, true);
            }

            return returnValue;
        }
    }


    @Override
    public String toString()
    {
        // TODO : printout byteArr as String
        String tmpSign = this.getSign();
        byte[] tmpArr = this.getByteArr();

        String tmpStr = "";
        boolean isContinuedZero = true;
        if (tmpSign.equals("-")) tmpStr += tmpSign;
        for (int i = 0; i < tmpArr.length; i++) {
            if (tmpArr[i] == 0 && isContinuedZero) continue;
            isContinuedZero = false;
            tmpStr += tmpArr[i];
        }

        if (tmpStr.equals("")) return "0";

        return tmpStr;
    }

    static BigInteger evaluate(String input) throws IllegalArgumentException
    {
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
            integerMatcher.appendReplacement(sb,"");
            cnt++;
        }
        integerMatcher.appendTail(sb);

        // TODO : Determine operation sign
        String operation = sb.toString();
        if (operation.length() == 0) {
            operation = "+";
        }

        // One possible implementation
        BigInteger num1 = new BigInteger(intStr1);
        BigInteger num2 = new BigInteger(intStr2);


        BigInteger result = null;
        // Add
        if (operation.equals("+")) { // add, same sign
            if (num1.getSign().equals(num2.getSign())) {
                result = num1.add(num2, false);
            } else { // add, different sign
                if (num1.compare(num2) > 0) {
                    result = num1.subtract(num2, false);
                } else if (num1.compare(num2) < 0){
                    result = num2.subtract(num1, false);
                } else {
                    result = new BigInteger(0);
                }
            }
        // Subtract
        } else if (operation.equals("-")) { // subtract, different sign
            if (!num1.getSign().equals(num2.getSign())) {
                result = num1.add(num2, false);
            } else { // subtract, same sign
                if (num1.compare(num2) > 0){
                    result = num1.subtract(num2, false);
                } else if (num1.compare(num2) < 0){
                    result = num2.subtract(num1, true);
                } else {
                    result = new BigInteger(0);
                }
            }
        // Multiply
        } else if (operation.equals("*")) {
            if (num1.compare(num2) > 0) {
                result = num1.multiply(num2);
            } else {
                result = num2.multiply(num1);
            }

        } else {
            System.out.println("ERROR : Cannot read operation-sign");
        }

        return result;
    }

    public static void main(String[] args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in))
        {
            try (BufferedReader reader = new BufferedReader(isr))
            {
                boolean done = false;
                while (!done)
                {
                    String input = reader.readLine();

                    try
                    {
                        done = processInput(input);
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput(String input) throws IllegalArgumentException
    {
        boolean quit = isQuitCmd(input);

        if (quit)
        {
            return true;
        }
        else
        {
            BigInteger result = evaluate(input);
            System.out.println(result.toString());

            return false;
        }
    }

    static boolean isQuitCmd(String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
