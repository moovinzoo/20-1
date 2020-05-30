import java.io.*;

public class Matching
{
    // Circular Doubly-Linked List
	public static CDLL<String> stringList = new CDLL<>();
	private static HT<Pair<Integer, Integer>> stringTable = new HT<>(100);

	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input) throws IOException {

		switch (input.charAt(0)) {
			case '<': // Filename with path
				// 절대경로, 상대경로 모두 가능하다.
				String filePath = input.substring(2, input.length() - 1);
				File newFile = new File(filePath);
				BufferedReader bfr = new BufferedReader(new FileReader(newFile));
				String currLine;
				while ((currLine = bfr.readLine()) != null) { // 파일 끝까지
				    stringList.add(currLine);
				}
				bfr.close();
				process(stringList);
				break;

			case '@': // 0-99 사이의 입력만 들어온다.
				break;

			case '?':
				break;
		}
	}

	private static void process(CDLL<String> strings) {

	}
}
