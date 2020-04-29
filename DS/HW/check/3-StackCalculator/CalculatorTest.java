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
//				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
				System.out.println("ERROR");
			}
		}
	}

	private static void command(String input) throws Exception
	{
		// Convert input string to postfix stack.
        Queue<Element> postfixQueue = Parsing.processInput(input);

        // Calculate postfix stack expression.
		String resultStr = printQueue(postfixQueue);
		long resultLong;
		try {
			resultLong = Calculator.calculate(postfixQueue);
		} catch (Exception e) {
			throw e;
		}

        // If there was no exception, print out postfix expression and result.
		System.out.println(resultStr);
		System.out.println(resultLong);
	}

	public static String printQueue(Queue<Element>postfixQueue) {
		// Using StringBuffer not to repeatedly assign new memory area
		StringBuffer sb = new StringBuffer();

		// To prevent element missing, using iterator
		for (Element currElem : postfixQueue) {
			if (currElem.isOpertor()) {
				sb.append(currElem.getOperator());
			} else {
				sb.append(currElem.getOperand());
			}

			// Concat " " after each element.
			sb.append(" ");
		}

		if (sb.length() != 0) {
			// Delete the last " ".
			sb.deleteCharAt(sb.length() - 1);
		}

		String resultStr = sb.toString();
		// Help to free the instance
		sb = null;

		return resultStr;
	}
}
