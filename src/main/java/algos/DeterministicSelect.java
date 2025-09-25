package algos;

import util.Metrics;

public class DeterministicSelect {

    public static int select(int[] arr, int k) {
        Metrics.enter();
        int result = selectRec(arr, 0, arr.length - 1, k - 1); // k is 1-based
        Metrics.exit();
        return result;
    }


    private static int selectRec(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];

        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) return arr[k];
        else if (k < pivotIndex) return selectRec(arr, left, pivotIndex - 1, k);
        else return selectRec(arr, pivotIndex + 1, right, k);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        Metrics.incAllocations();
        int i = left;
        for (int j = left; j < right; j++) {
            Metrics.incComparisons();
            if (arr[j] <= pivot) {
                int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i]; arr[i] = arr[right]; arr[right] = temp;
        return i;
    }
}
