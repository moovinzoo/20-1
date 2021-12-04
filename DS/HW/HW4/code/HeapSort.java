public class HeapSort
{
    public static void sort(int[] dataset)
    {
        int size = dataset.length;

        // percolateDown하며 dataset을 maxHeap으로 바꾼다.
        for (int i = size / 2; i >= 1; i--)
        {
           percolateDown(dataset, i, size);
        }

        // 최대값이 보장된 root를 맨 뒤로 보냄으로써 maxHeap에서 배제하고(Deletion) size를 1 줄인 나머지를 percolateDown 함으로써 다시 maxHeap을 만족시킨다.
        // ** 이를 (size - 1)번 반복함으로써 dataset의 오른쪽에 (size - 1)번째 max data를 차곡차곡 쌓을 수 있고, 남은 1개의 원소는 자연스레 min data가 된다.
        // **** 따라서 정렬이 완료된다.
        for (int i = size; i >= 2; i--)
        {
            swap(dataset, 1, i);
            percolateDown(dataset, 1, i - 1);
        }
    }

    public static void percolateDown(int[] dataset, int i, int size)
    {
        int child = 2 * i;
        int rightChild = 2 * i + 1;

        if (child <= size)
        {
            if ((rightChild <= size) && (dataset[child - 1] < dataset[rightChild - 1]))
            {
                child = rightChild;
            }

            if (dataset[i - 1] < dataset[child - 1])
            {
                swap(dataset, i, child);
                percolateDown(dataset, child, size);
            }
        }

    }

    // dataset의 i번째와 j번째 data를 swap한다.
    public static void swap(int[] dataset, int i, int j)
    {
        int tmp = dataset[i - 1];
        dataset[i - 1] = dataset[j - 1];
        dataset[j - 1] = tmp;
    }
}
