import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BigInteger
{
    public static final String QUIT_COMMAND = "quit";
    public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";
    public static final String MSG_INVALID_EXPRESSION = "수식이 잘못되었습니다.";

    // implement this
    public static final Pattern SPACE_PATTERN = Pattern.compile("[ ]*");
    public static final Pattern INTEGER_WITH_SIGN = Pattern.compile("[-+]?[0-9]+");
    public static final int MAX_INTEGER_SIZE = 100;

    /*
    public BigInteger(final int i)
    {
    }
  
    public BigInteger(final int[] num1)
    {
    }
  
    public BigInteger(final String s)
    {
    }

    public BigInteger add(final BigInteger big)
    {
    }
  
    public BigInteger subtract(final BigInteger big)
    {
    }
  
    public BigInteger multiply(final BigInteger big)
    {
    }
  
    @Override
    public String toString()
    {
    }
  */
//    static BigInteger evaluate(final String input) throws IllegalArgumentException
    static void evaluate(final String input) throws IllegalArgumentException
    {
        try
        {
        }
        catch (IllegalArgumentException e){
            System.err.println(MSG_INVALID_EXPRESSION);
        }

//        Matcher expressionMatcher = EXPRESSION_PATTERN.matcher(input);
//         implement here
//         Test
        System.out.println("string : " + input);
        Matcher integerMatcher = INTEGER_WITH_SIGN.matcher(input);
        String leftIntWithSpaces = integerMatcher.group(0);
        String remainder = integerMatcher.replaceFirst("");
        char operation = remainder.charAt(0);
        String rightIntWithSpaces = remainder.substring(1, remainder.length());

        // Test
        System.out.println("leftIntWithSpaces : " + leftIntWithSpaces);
        System.out.println("operation : " + operation);
        System.out.println("rightIntWithSpaces : " + rightIntWithSpaces);
//        Matcher operationMatcher = OPERATION_PATTERN.matcher(input);
        // parse input
        // using regex is allowed

        // One possible implementation
//         BigInteger num1 = new BigInteger(arg1);
        // BigInteger num2 = new BigInteger(arg2);
        // BigInteger result = num1.add(num2);
        // return result;
    }

    public static void main(final String[] args) throws Exception
    {
        try (InputStreamReader isr = new InputStreamReader(System.in))
        {
            try (BufferedReader reader = new BufferedReader(isr))
            {
                boolean done = false;
                while (!done)
                {
                    final String input = reader.readLine();

                    try
                    {
                        done = processInput(input);
                    }
                    catch (final IllegalArgumentException e)
                    {
                        System.err.println(MSG_INVALID_INPUT);
                    }
                }
            }
        }
    }

    static boolean processInput(final String input) throws IllegalArgumentException
    {
        final boolean quit = isQuitCmd(input);

        if (quit)
        {
            return true;
        }
        else
        {
            /*
            final BigInteger result = evaluate(input);
            System.out.println(result.toString());
  
            return false;

             */
            evaluate(input);
            return false;
        }
    }

    static boolean isQuitCmd(final String input)
    {
        return input.equalsIgnoreCase(QUIT_COMMAND);
    }
}
