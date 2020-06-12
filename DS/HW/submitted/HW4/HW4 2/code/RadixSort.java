import java.util.Arrays;

public class RadixSort {
    public static int[] sort(int[] _dataset, int size)
    {
        // 원래 값의 보호를 위해 복사본을 생성한다.
        // ** dataset에 대한 접근이 빈번해서 clone을 만들어서 local에서 순회하기로 결정
        // ** parameter로 넘겨진 원본 dataset의 size는 한번의 순회로 pos/neg dataset을 구분하기 위해 둘의 size를 합한 length로 선언되어 있다. 따라서 clone을 만듦에 있어 pos/neg data 각각의 size에 맡게 선언해줌이 옳다.
        int[] dataset = Arrays.copyOfRange(_dataset, 0, size);

        // 최대자릿수를 구한다.
        int max = findMaxLen(dataset, size);

        // Radix sort에 필요한 보조 subArr들을 초기화한다.
        int[] sortedArr = new int[size];// 각 자릿수에 대해 재정렬한 subArr
	    int[] remainderArr = new int[size];// data의 자릿수만 저장하는 subArr
	    int[] digitBucket = new int[10];// 0~9의 자릿수의 data를 모아놓을 bucket

        int remainOperand = 10;// 나머지 연산을 수행할 operand, 자릿수를 남기므로 10
        int divideOperand = 1;// 자릿수를 초점하기 위해 사용하는 operand, 10씩 곱해진다.
         int cntRequireSort = size;// 나머지 연산이 0이 된 data들에 대해서는 더이상 sort할 필요가 없다. 0이 된 data들을 왼쪽에 몰아놓고 0이 아닌 값들에 대해서만 sorting을 진행하기로 한다. 처음엔 모두 순회해야하므로 size로 설정한다.

        // 최대 자릿수까지 각 자릿수마다
        // ** max 자릿수까지 divideOperand를 10씩 곱해가며 자릿수를 올라가며 조사
	    for (int i = 0; i < max; i++, divideOperand *= 10)
        {
            // digitBucket을 초기화한다.
            for (int j = 0; j < 10; j++)
            {
                digitBucket[j] = 0;
            }

            // dataset의 각 data가 현재 조사하는 자릿수가 뭔지 remainderArr에 저장한다.
            for (int j = 0; j < size; j++)
            {
                int digit = (dataset[j] / divideOperand) % remainOperand;
                remainderArr[j] = digit; // remainderArr에 해당 index의 data의 자릿수가 뭔지 저장한다.
                digitBucket[digit]++; // 0~9의 bucket에 각 수에 대항하는 자릿수가 몇개 나왔는지 저장한다.
                if (digit > 0) cntRequireSort++;
            }

            // 순회 없이 remainderArr에서 바로 정렬하기 위해서 참조하기 위한 index가 필요하다
            // ** index의 기능을 수행할 수 있도록 자릿수의 빈도수를 누적시킨다.
            for (int j = 1; j < 10; j++)
            {
                digitBucket[j] += digitBucket[j - 1];
            }

            // 빈도수를 index화 하기 위해서 뒤에서부터 순회를 시작한다.
            for (int j = size - 1; j >= 0; j--)
            {
                // 이전 단계까지 sort된, 현재 데이터의 digit을 알아낸다.
                int digit = remainderArr[j];
                // 현재 데이터가 저장되어야 할 index를 알아낸다.
                // ** bucket에서 하나를 빼고 나면, index를 하나 줄인다.
                // **** 그래야 dataset에서 앞서있는 원소가 그대로 앞에 위치할 수 있다.
                // **** 즉, 중복에 대한 treat이자 sorting을 stable하게 만드는 요소.
                int sortedArrIndex = (digitBucket[digit]--) - 1;
                sortedArr[sortedArrIndex] = dataset[j];
            }

            // 이번 자릿수에 대해 완료된 정렬을 dataset에 옮겨온다.
            for (int j = 0; j < size; j++)
            {
                dataset[j] = sortedArr[j];
            }
        }

	    return dataset;
    }
//    public static int[] sort(int[] _dataset, int size)
//    {
//        // 원래 값의 보호를 위해 복사본을 생성한다.
//        // ** dataset에 대한 접근이 빈번해서 clone을 만들어서 local에서 순회하기로 결정
//        // ** parameter로 넘겨진 원본 dataset의 size는 한번의 순회로 pos/neg dataset을 구분하기 위해 둘의 size를 합한 length로 선언되어 있다. 따라서 clone을 만듦에 있어 pos/neg data 각각의 size에 맡게 선언해줌이 옳다.
//        int[] dataset = Arrays.copyOfRange(_dataset, 0, size);
//
//        // 최대자릿수를 구한다.
//        int max = findMaxLen(dataset, size);
//
//        // Radix sort에 필요한 보조 subArr들을 초기화한다.
//        int[] sortedArr = new int[size];// 각 자릿수에 대해 재정렬한 subArr
//        int[] remainderArr = new int[size];// data의 자릿수만 저장하는 subArr
//        int[] digitBucket = new int[10];// 0~9의 자릿수의 data를 모아놓을 bucket
//
//        int remainOperand = 10;// 나머지 연산을 수행할 operand, 자릿수를 남기므로 10
//        int divideOperand = 1;// 자릿수를 초점하기 위해 사용하는 operand, 10씩 곱해진다.
//        int prevCntDataRequireSorting = size;// 나머지 연산이 0이 된 data들에 대해서는 더이상 sort할 필요가 없다. 0이 된 data들을 왼쪽에 몰아놓고 0이 아닌 값들에 대해서만 sorting을 진행하기로 한다. 처음엔 모두 순회해야하므로 size로 설정한다.
//
//        // 최대 자릿수까지 각 자릿수마다
//        // ** max 자릿수까지 divideOperand를 10씩 곱해가며 자릿수를 올라가며 조사
//        for (int i = 0; i < max; i++, divideOperand *= 10)
//        {
//            int nextCntDataRequireSorting = prevCntDataRequireSorting;
//            // digitBucket을 초기화한다.
//            for (int j = 0; j < 10; j++)
//            {
//                digitBucket[j] = 0;
//            }
//
//            // dataset의 각 data가 현재 조사하는 자릿수가 뭔지 remainderArr에 저장한다.
//            for (int j = size - prevCntDataRequireSorting; j < size; j++)
//            {
//                int digit = (dataset[j] / divideOperand) % remainOperand;
//                remainderArr[j] = digit; // remainderArr에 해당 index의 data의 자릿수가 뭔지 저장한다.
//                digitBucket[digit]++; // 0~9의 bucket에 각 수에 대항하는 자릿수가 몇개 나왔는지 저장한다.
//
//                if (digit == 0) nextCntDataRequireSorting--;
//            }
//
//            // 순회 없이 remainderArr에서 바로 정렬하기 위해서 참조하기 위한 index가 필요하다
//            // ** index의 기능을 수행할 수 있도록 자릿수의 빈도수를 누적시킨다.
//            for (int j = 1; j < 10; j++)
//            {
//                digitBucket[j] += digitBucket[j - 1];
//            }
//
//            // 빈도수를 index화 하기 위해서 뒤에서부터 순회를 시작한다.
//            for (int j = size - 1; j >= 0; j--)
//            {
//                // 이전 단계까지 sort된, 현재 데이터의 digit을 알아낸다.
//                int digit = remainderArr[j];
//                // 현재 데이터가 저장되어야 할 index를 알아낸다.
//                // ** bucket에서 하나를 빼고 나면, index를 하나 줄인다.
//                // **** 그래야 dataset에서 앞서있는 원소가 그대로 앞에 위치할 수 있다.
//                // **** 즉, 중복에 대한 treat이자 sorting을 stable하게 만드는 요소.
//                int sortedArrIndex = (digitBucket[digit]--) - 1;
//                sortedArr[sortedArrIndex] = dataset[j];
//            }
//
//            // 이번 자릿수에 대해 완료된 정렬을 dataset에 옮겨온다.
//            for (int j = 0; j < size; j++)
//            {
//                dataset[j] = sortedArr[j];
//            }
//        }
//
//        return dataset;
//    }

    private static int findMaxLen(int[] dataset, int size)
    {
            int maxLen = 0;
            int maxData = dataset[0];
            for (int i = 1; i < size; i++)
            {
                if (dataset[i] > maxData) maxData = dataset[i];
            }

            for (; maxData > 0; maxLen++)
            {
                maxData /= 10;
            }

            return maxLen;
    }
}
