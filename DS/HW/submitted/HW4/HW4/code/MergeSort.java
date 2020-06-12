public class MergeSort {
    public static void sort(int[] dataset, int leftmostIndex, int rightmostIndex)
    {
        // Mergesort는 divide-and-conquer의 방식에 의한다.
        // ** 1. Divide, 2. Recursive call, 3. Conquer(Merge)에 절차에 따라 알고리즘을 작성한다.

        // 1. Divide
        // ** dataset의 크기가 <= 1 인 경우에 더 이상 나눌 수 없다.
        // **** leftmostIndex >= rightmostIndex 인 경우가 이에 해당한다. 둘의 값이 같을 때 dataset의 크기는 1이기 때문이다.
        // ** 더 이상 나눌 수 없는 subArr는 2. Recursive, 3. Conquere할 필요 없다.
        if (leftmostIndex >= rightmostIndex)
           return;
        int mid = (leftmostIndex + rightmostIndex) / 2;

        // 2. Recursive call
        sort(dataset, leftmostIndex, mid);
        sort(dataset, mid + 1, rightmostIndex);

        // 3. Conquer(Merge)
        // ** 크기가 > 1일 때에 항상 <1.Divide>해서 <2.Recursive call> 했기 때문에 이 곳에 최초로 도달하는 지점에서 merge의 대상이 되는 subArr는 각각 크기가 <= 1이다.
        // **** 왜냐하면, 크기가 <= 1일 때 <1.Divide>의 조건을 만족하지 못해 이 전에 재귀적으로 호출된 sort 메소드(한 단계 상위 nesting된 메소드)의 <2.Recursive call> 이후 지점으로 돌아간다.
        // **** 최초의 Merge 대상이 된 두 subArr는 크기가 <= 1이기에 각각 이미 정렬되어 있다.
        // **** 또한, 이곳에서 합쳐지며 정렬된 크기가 두배인 subArr가 된므로 병합하는 두 subArr는 항상 각각 정렬되어 있다고 보장할 수 있다.

        // Merge의 대상이 되는 두 subArr의 시작점을 지정한다.
        int leftIndex = leftmostIndex;
        int rightIndex = mid + 1;

        // 합쳐질 mergeArr의 크기를 지정하고, index를 초기화한다.
        int[] mergedArr = new int[rightmostIndex - leftmostIndex + 1];
        int mergeIndex = 0;

        // subArr 둘 중 하나가 모두 옮겨질 때까지
        while ((leftIndex <= mid) && (rightIndex <= rightmostIndex))
        {
            // 각각의 index가 가리키는 대상 중 크기가 작은 것을 채워넣는다.
            if (dataset[leftIndex] <= dataset[rightIndex])
            {
                // 채운 뒤엔 mergeIndex와 left/right Index를 각각 증가시킨다.
                mergedArr[mergeIndex++] = dataset[leftIndex++];
            } else {
                mergedArr[mergeIndex++] = dataset[rightIndex++];
            }
        }

        // Left subArr에 data가 남아있다면 크기가 큰 것일테니 mergeIndex에 차례대로 넣어준다.
        while (leftIndex <= mid)
        {
            mergedArr[mergeIndex++] = dataset[leftIndex++];
        }

        // 둘 모두 비어있는 일은 없기 때문에 left/right subArr가 비어있는지 검사하는 순서는 상관이 없다.
        while (rightIndex <= rightmostIndex)
        {
            mergedArr[mergeIndex++] = dataset[rightIndex++];
        }

        // Merge가 끝난 mergeArr를 기존 dataset으로 옮겨준다.
        mergeIndex = 0;
        for (int i = leftmostIndex; i <= rightmostIndex; i++)
        {
            dataset[i] = mergedArr[mergeIndex++];
        }
    }
}
