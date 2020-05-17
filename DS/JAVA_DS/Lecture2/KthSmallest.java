package Lecture2;

public class KthSmallest {

    static int findKthSmallest(int[] intArr, int k, int first, int last) {
        // Select a pivot item p;
        int pivot = intArr[first];
        int pivotIndex = partition(intArr, pivot, first, last); // pivot이 위치한 자리 알아내기

        int pivotOrder = (pivotIndex - first + 1);
        if (k < pivotOrder ) {
            return findKthSmallest(intArr, k, first, pivotIndex - 1);

        } else if (k == pivotOrder ) {
            return pivot;

        } else {
            return findKthSmallest(intArr, k - pivotOrder , pivotIndex + 1, last);

        }
    }

    static int partition(int[] intArr, int pivot, int first, int last) {
        int[] newArr = new int[last - first + 1];
        int currIndex = 0;

        for (int i = first; i <= last; i++) {
            if (intArr[i] < pivot) newArr[currIndex++] = intArr[i];
        }
        int pivotIndex = first + currIndex;
        newArr[currIndex++] = pivot;
        for (int i = first; i <= last; i++) {
            if (intArr[i] > pivot) newArr[currIndex++] = intArr[i];
        }

        for (int i = 0; i < newArr.length; i++) {
            intArr[first + i] = newArr[i];
        }

        return pivotIndex;
    }

    public static void main(String[] args) {
        int[] A = {5, 7, 3, 1, 9, 8};
        for (int i = 0; i < A.length; i++) {
            int kth = i + 1;
            int tmp = findKthSmallest(A, kth, 0, A.length - 1);
            System.out.println(kth + "th smallest: " + tmp);
        }
    }
}
