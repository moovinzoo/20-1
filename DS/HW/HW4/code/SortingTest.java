import java.io.*;
import java.util.*;

public class SortingTest {
	public static void main(String args[]) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			boolean isRandom = false;    // 입력받은 배열이 난수인가 아닌가?
			int[] value;    // 입력 받을 숫자들의 배열
			String nums = br.readLine();    // 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r') {
				// 난수일 경우
				isRandom = true;    // 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);    // 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);    // 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);    // 최대값

				Random rand = new Random();    // 난수 인스턴스를 생성한다.

				value = new int[numsize];    // 배열을 생성한다.
				for (int i = 0; i < value.length; i++)    // 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			} else {
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];    // 배열을 생성한다.
				for (int i = 0; i < value.length; i++)    // 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true) {
				int[] newvalue = (int[]) value.clone();    // 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0)) {
					case 'B':    // Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':    // Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':    // Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':    // Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':    // Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':    // Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;    // 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom) {
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				} else {
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++) {
						System.out.println(newvalue[i]);
					}
				}

			}
		} catch (IOException e) {
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value) {
		// value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
		// 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
		// 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
		// 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.

		// i번째 순회가 끝난 뒤에, 우측부터 크기가 큰 순서대로 i개의 data의 정렬이 완료되었음을 확신할 수 있다.
		for (int i = 1; i < value.length; i++) {
			// 처음부터 검사하고, 우측의 정렬이 완료된 i개 원소는 제외한다.
			for (int j = 0; j < value.length - i; j++) {
				// 왼쪽의 data가 더 크면 swap한다.
				if (value[j] > value[j + 1]) {
					int tmp = value[j];
					value[j] = value[j + 1];
					value[j + 1] = tmp;
				}
			}
		}

		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] dataset) {
		// Dataset에 대한 순회가 이루어질 때마다 최소한, i개의 data가 정렬되었다고 확신할 수 있다.
		// ** 시작할 때부터, 첫 원소는 length=1인 subArr에 대하여 정렬되어 있는 셈이므로 비약이 없다.
		for (int i = 1; i < dataset.length; i++) {
			// i번째 data를 그 전까지의 정렬된 (i-1)개의 data들과 비교하여 옳은 자리에 삽입한다.
			// ** i번째 data가 정렬이 완료된 왼쪽의 subArr의 가장 큰 수(rightmost = i-1) 보다 작다면, 옳은 자리를 찾아 탐색을 시작한다.
			if (dataset[i] < dataset[i - 1]) {
				for (int j = i; j > 0; j--) {
					// 들어갈 자리를 먼저 찾고 해당하는 자리보다 우측에 있는 data들을 오른쪽으로 한칸씩 이동시키는 연산이 필요하다.
					// ** 이 때, i번째 data를 시작으로, 옳은 자리까지 이동할 때까지 swap해주는 방식을 이용함으로써 연산을 간단하게 할 수 있다.
					if (dataset[j] < dataset[j - 1]) {
						int tmp = dataset[j];
						dataset[j] = dataset[j - 1];
						dataset[j - 1] = tmp;
					}

					// dataset[j] > dataset[j-1]이라면,
				}

			}
			// i번째 data가 그 전까지 정렬된 data의 rightmost data보다 작지 않다면, 옳은 자리에 있는 것이므로 i번째 data에 대한 정렬을 종료한다.
			// ** 이로써 [0, i]인 (i+1)개가 정렬되었다고 확신할 수 있으므로 i를 1 증가시킨다.
		}

		return dataset;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value) {
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, HeapSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		HeapSort.sort(value);

		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value) {
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, MergeSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		MergeSort.sort(value, 0, value.length - 1);

		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value) {
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, QuickSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		QuickSort.sort(value, 0, value.length - 1);

		return value;
	}

	private static int[] DoRadixSort(int[] dataset)
	{
		int size = dataset.length;
		if (size < 2) return dataset;

		// RadixSort를 위한 전처리 과정
		// 1. 가장 큰 수를 찾는다.
		// 2. 가장 작은 수를 찾는다.
		// 3. 양수와 음수를 따로 모은다.
		int numNegData = 0;
		int numPosData = 0;
		int[] negDataset = new int[size];
		int[] posDataset = new int[size];

		for (int i = 0; i < size; i++)
		{
			int tmpData = dataset[i];
			if (tmpData >= 0)
			{
				// 양수인 것은 그대로 posDataset에 모은다.
				posDataset[numPosData++] = tmpData;
			}
			else
			{
				// 음수인 것은 양수로 만들어 모은다.
				negDataset[numNegData++] = tmpData * (-1);
			}
		}

		int[] negSortedDataset;
		int[] posSortedDataset;
		if (numNegData > 0)
		{
			// 음수를 다시 -1을 곱해 역순으로 dataset에 집어넣는다.
			negSortedDataset = RadixSort.sort(negDataset, numNegData);
			for (int i = 0; i < numNegData; i++)
			{
				dataset[i] = (-1) * negSortedDataset[numNegData - i - 1];
			}
		}
		if (numPosData > 0)
		{
			// 그 뒤에 양수를 정순으로 옮겨준다.
			posSortedDataset = RadixSort.sort(posDataset, numPosData);
			for (int i = 0; i < numPosData; i++)
			{
				dataset[numNegData + i] = posSortedDataset[i];
			}
		}

        return dataset;
    }
}
