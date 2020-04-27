import java.io.*;
import java.util.Stack;
import java.util.Queue;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input) throws Exception
	{
		// Convert input string to postfix stack.
        Stack<Element> postfixStack = Parsing.processInput(input);

        // Calculate postfix stack expression.
        long result = Calculator.calculate(postfixStack);

        // If there was no exception, print out postfix expression and result.
		System.out.println(postfixStack.toString());
		System.out.println(result);
	}
}
