import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		// value는 정렬안된 숫자들의 배열이며 value.length 는 배열의 크기가 된다.
		// 결과로 정렬된 배열은 리턴해 주어야 하며, 두가지 방법이 있으므로 잘 생각해서 사용할것.
		// 주어진 value 배열에서 안의 값만을 바꾸고 value를 다시 리턴하거나
		// 같은 크기의 새로운 배열을 만들어 그 배열을 리턴할 수도 있다.

		// i번째 순회가 끝난 뒤에, 우측부터 크기가 큰 순서대로 i개의 data의 정렬이 완료되었음을 확신할 수 있다.
        for (int i = 1; i < value.length; i++)
		{
			// 처음부터 검사하고, 우측의 정렬이 완료된 i개 원소는 제외한다.
			for (int j = 0; j < value.length - i; j++)
			{
				// 왼쪽의 data가 더 크면 swap한다.
			    if (value[j] > value[j+1])
				{
					int tmp = value[j];
					value[j] = value[j + 1];
					value[j + 1] = tmp;
				}
			}
		}

		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] dataset)
	{
	    // Dataset에 대한 순회가 이루어질 때마다 최소한, i개의 data가 정렬되었다고 확신할 수 있다.
        // ** 시작할 때부터, 첫 원소는 length=1인 subArr에 대하여 정렬되어 있는 셈이므로 비약이 없다.
        for (int i = 1; i < dataset.length; i++)
		{
			// i번째 data를 그 전까지의 정렬된 (i-1)개의 data들과 비교하여 옳은 자리에 삽입한다.
            // ** i번째 data가 정렬이 완료된 왼쪽의 subArr의 가장 큰 수(rightmost = i-1) 보다 작다면, 옳은 자리를 찾아 탐색을 시작한다.
			if (dataset[i] < dataset[i-1])
			{
				for (int j = i; j > 0; j--)
				{
					// 들어갈 자리를 먼저 찾고 해당하는 자리보다 우측에 있는 data들을 오른쪽으로 한칸씩 이동시키는 연산이 필요하다.
					// ** 이 때, i번째 data를 시작으로, 옳은 자리까지 이동할 때까지 swap해주는 방식을 이용함으로써 연산을 간단하게 할 수 있다.
					if (dataset[j] < dataset[j-1])
					{
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
	private static int[] DoHeapSort(int[] value)
	{
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, MergeSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		HeapSort.sort(value);

		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
	{
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, MergeSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		MergeSort.sort(value, 0, value.length - 1);

		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		// Data set이 비어있거나, 1개의 데이터만 존재하는 경우 ; 정렬이 필요하지 않다.
		if (value.length < 2) return value;

		// Data set에 대한 정렬이 필요한 경우, QuickSort 클래스의 static method를 이용해 정렬을 실행한다.
		// 이 때, 정렬이 완료된 dataset을 리턴받을 필요는 없다.
		//** 주소값이 참조될 수 있도록 인자로써 전달하는 것으로 충분하기 때문에, return하면 괜한 overload가 된다.
		QuickSort.sort(value, 0, value.length - 1);

		return value;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] dataset)
	{
		// data의 자릿수를 비교하기 위해 n을 증가시켜가며 10^n으로 data들을 나누는 연산을 수행하게 된다.
		// ** 이 때, 몫이 0보다 큰 data의 수를 카운트함으로써 정렬의 종결시점을 알 수 있다.
		// ** cnt == 0이 되면, 최고자릿수까지 빠짐 없이 이 전의 정렬에서 모두 한번씩 자릿수에 맞게 정렬되었음을 뜻하기 때문에 종결한다.

		int size = dataset.length;
		int remainOperand = 10;
		int divideOperand = 1; // *10 at the end of while.
	    int cntAliveData = 1; // Initial dummy value;

		// 각 자릿수별 정렬에 있어 기존의 위치를 유지하는 Stable-Algorithm을 사용해야 한다.
		// 빠른 수행시간을 확보하기 위해 자릿수가 0, 1, ... , 10인 수들을 모아놓는 LinkedList로 구현된 Queue를 10개 만든다.
		// ** 순서대로 집어넣고 순서대로 꺼냄으로써 자릿수별 정렬을 하면서도 자릿수가 같은 이들간에 기존의 순서를 훼손하지 않는 Stablity를 확보할 수 있다.
		ArrayList<Queue<Integer>> buckets = new ArrayList<>();

        for (int i = 0; i < 10; i++)
		{
			// Integer형만 사용할 것이므로 10개의 bucket에 대해 type에 맞게 instantiate 해준다.
            buckets.add(new LinkedList<Integer>());
		}
        // 이제 buckets.get(0) ~ buckets.get(9)까지 존재.

        // 가장 자릿수가 큰 수까지 모두 한 번 이상 버킷에 들어갔다 나올 때까지
		while (cntAliveData > 0)
		{
			cntAliveData = 0; // 초기화

			// 이전 자릿수까지 정렬된 순서대로
			for (int i = 0; i < size; i++)
			{
				// data의 자릿수를 digit에 저장한다.
			    int digit = dataset[i];
			    digit /= divideOperand;
			    digit %= remainOperand;

			    if (digit > 0) cntAliveData++;

			    // 자릿수에 맞는 bucket에 다시 담는다.
				buckets.get(digit).add(dataset[i]);
			}

			// bucket_0부터 bucket_9까지 각 bucket에 쌓여있는 data를 넣은 순서대로 dequeue해서 dataset에 차곡차곡 담는다.
			int cnt = 0;
			for (int i = 0; i < 10; i++)
			{
				while (!buckets.get(i).isEmpty())
				{
					// 자동으로 type casting 되므로 Integer -> int로 담기는 것에 크게 신경쓰지 않아도 된다.
					dataset[cnt++] = buckets.get(i).poll();
				}
			}

			// 나누는 수와 나머지를 구하는 수를 모두 10씩 곱해주고 하나 윗 자리수를 조사하러 간다.
			divideOperand *= 10;
		}

		return dataset;
	}
}
