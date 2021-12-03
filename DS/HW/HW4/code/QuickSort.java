public class QuickSort {
    public static void sort(int[] dataset, int leftmostIndex, int rightmostIndex)
    {
        // Base case ; 재귀적으로 실행되기 때문에 필요하다.
        // ** 또한, overload를 줄일 수 있다.
        if (leftmostIndex == rightmostIndex) return;

        // 피봇을 선정함에 있어, 치우치지 않게 하기 위해 가장 왼쪽에 위치한 데이터와 가장 오른쪽에 위치한 데이터의 중간값으로 정한다.
        int pivot = dataset[(leftmostIndex + rightmostIndex) / 2];

        // Index 값들을 지정한다.
        // ** leftIndex는 왼쪽에서부터 출발하여 오른쪽으로 진행하고, rightIndex는 오른쪽에서부터 출발하여 왼쪽으로 진행한다.
        int leftIndex = leftmostIndex;
        int rightIndex = rightmostIndex;

        // Partition을 진행한다.
        // ** leftIndex와 rightIndex가 교차하면, partitioning이 끝난 것이다.
        while (leftIndex <= rightIndex)
        {
            // dataset의 왼편에서 pivot보다 작지 않은 data를 찾으면, 그곳을 가리킨 채로 멈춘다.
            while (dataset[leftIndex] < pivot)
                leftIndex++;
            // dataset의 오른편에서 pivot보다 크지 않은 data를 찾으면, 그곳을 가리킨 채로 멈춘다.
            while (dataset[rightIndex] > pivot)
                rightIndex--;

            // 만약 두 인덱스가 교차하지 않았다면,
            if (leftIndex <= rightIndex)
            {
                // 옳지 않은 partition에 있는 두 data를 swap한다.
                int tmp = dataset[leftIndex];
                dataset[leftIndex] = dataset[rightIndex];
                dataset[rightIndex] = tmp;

                // Swap이 끝난 data의 pair은 한번 더 검사하지 않고 지나친다.
                leftIndex++;
                rightIndex--;
            }
        }
        // 두 index가 교차할 때까지 진행했으며, leftIndex는 늘리고 rightIndex는 동시에 줄였다. 따라서,
        // ** dataset의 크기가 짝수일 때 ; leftIndex의 바로 오른쪽에 rightIndex가 위치하고 있다.
        // ** dataset의 크기가 홀수일 때 ; leftIndex와 rightIndex가 같은 곳을 가리키고 있다.
        // ** 따라서, leftIndex 또는 rightIndex를 새로운 정렬의 재귀적 선언에 있어 제외할지 검토하지 않기로 결정했다.
        // **** dataset의 크기가 짝수인 경우, 둘을 제외하는 것이 올바르지 않기 때문에 이를 제외할지 검토하기 위해선 매번 dataset의 길이가 짝수인지 검사해야 한다.
        // **** 이 검사로 인한 overload가 dataset의 크기가 홀수인 경우에 data 하나를 정렬 범위에 중첩하는 overload보다 오히려 과중할 것이라 여겨지기 때문이다.


        // Pivot을 기준으로 partitioning 된 양 옆의 새로운 partition들을 정렬이 필요한 경우 재귀적으로 다시 Quicksort한다.
        // ** 오른편의 partition이 비어있지 않다면, 정렬한다.
        if (leftIndex < rightmostIndex)
            sort(dataset, leftIndex, rightmostIndex);
        // ** 왼편의 partition이 비어있지 않다면, 정렬한다.
        if (rightmostIndex > leftmostIndex)
            sort(dataset, leftmostIndex, rightIndex);
    }
}
