import java.io.*;
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
        Queue<Element> postfixQueue = Parsing.processInput(input);

        // Calculate postfix stack expression.
		String resultStr = printQueue(postfixQueue);
		long resultLong = Calculator.calculate(postfixQueue);

        // If there was no exception, print out postfix expression and result.
		System.out.println(resultStr);
		System.out.println(resultLong);
	}

	public static String printQueue(Queue<Element>postfixQueue) {
		// Using StringBuffer not to repeatedly assign new memory area
		StringBuffer sb = new StringBuffer();

		// To prevent element missing, using iterator
		for (Element currElem : postfixQueue) {
			sb.append(currElem.isOpertor()?(currElem.getOperator()):(currElem.getOperand()));
		}

		String resultStr = sb.toString();
		// Help to free the instance
		sb = null;

		return resultStr;
	}
}
