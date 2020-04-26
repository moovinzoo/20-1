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

	private static void command(String input)
	{
		// TODO: 2020/04/26 공백제거
        Stack<Element> postfixStack = Parsing.infixToPostfix(input);

		// TODO: 2020/04/26 Parsing(각 함수로 쪼개기)
		// TODO: 2020/04/26 각 함수로 쪼개기
		// TODO: 2020/04/26 Infix to Postfix and store it
		// TODO: 2020/04/26 Check ERRORs

	}
}
