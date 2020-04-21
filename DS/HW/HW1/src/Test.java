import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.math.BigInteger;

public class Test {
    public static final Pattern SPACE_PATTERN = Pattern.compile("[ ]*");
    public static final Pattern INTEGER_WITH_SIGN = Pattern.compile("[-+]?[0-9]+");
    public static final int MAX_INTEGER_SIZE = 100;

    public static void main(String[] args) throws IOException {
//        InputStreamReader isr = new InputStreamReader(System.in);
//        BufferedReader reader = new BufferedReader(isr);
//        final String input = reader.readLine();

        /* Sample input */
        String input = " -   12345    -      3456       ";
        System.out.println("string : " + input); // for Test

        // TODO : Remove spaces from input string
        String inputWithoutSpaces = SPACE_PATTERN.matcher(input).replaceAll("");
        System.out.println("string without spaces : " + inputWithoutSpaces);

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

        // Test for intStrings
        System.out.println("BigInteger1 : " + intStr1);
        System.out.println("BigInteger2 : " + intStr2);
        // TODO : Determine operation sign
        String operation = sb.toString();
        if (operation.length() == 0) {
            operation = "+";
        }

        // Test for operation-sign
        System.out.println("Operation : " + operation);

        // TODO : Making String to Byte(Integer) ARR
        byte[] intByteArr = new byte[MAX_INTEGER_SIZE];
        for (int i = 1; i < intStr1.length(); i++) {
            intByteArr[MAX_INTEGER_SIZE - intStr1.length() + i] = (byte)(intStr1.charAt(i) - '0');
        }

        // TODO : printout byteArr as String
        System.out.println("Testing byteArr to String");
        String tmpStr = "";
        for (int i = 0; i < MAX_INTEGER_SIZE; i++) {
            byte tmpByte = intByteArr[i];
            if (tmpByte == 0) continue;
            else tmpStr += tmpByte;
        }
        System.out.println(tmpStr);
    }
}
