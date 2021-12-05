import java.io.*;

public class Matching
{
	// Circular Doubly-Linked List
	public static CDLL<String> stringList = new CDLL<>();
	private static HT<Pair> stringTable = new HT<>(100);

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
				String filePath = input.substring(2);
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
				String index = input.substring(2);
				stringTable.print(Integer.parseInt(index));
				break;

			case '?':
				break;
		}
	}

	private static void process(CDLL<String> strings) {
		for (int i = 0; i < strings.size(); i++) {
			String line = strings.get(i);
			for (int j = 0; j <= line.length() - 6; j++) {
				String subStr = line.substring(j, j+6);
				stringTable.insert(subStr, new Pair(i + 1, j + 1));
			}
		}
	}
}
