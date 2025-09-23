package algos;
public class QuickSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        Utils.shuffle(a); // randomize input to avoid adversarial cases
        quickIter(a, 0, a.length - 1, m);
    }

    private static void quickIter(int[] a, int lo, int hi, Metrics m) {
        while (lo < hi) {
            if (hi - lo <= CUTOFF) {
                Utils.insertionSort(a, lo, hi, m);
                break;
            }

            int pivotIndex = Utils.randomPivot(lo, hi);
            Utils.swap(a, pivotIndex, hi);
            int p = partition(a, lo, hi, m);

            int leftSize = p - lo;
            int rightSize = hi - p;
            if (leftSize < rightSize) {
                m.enter(); quickIter(a, lo, p - 1, m); m.exit();
                lo = p + 1;
            } else {
                m.enter(); quickIter(a, p + 1, hi, m); m.exit();
                hi = p - 1;
            }
        }
    }

    private static int partition(int[] a, int lo, int hi, Metrics m) {
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            m.incComparisons();
            if (a[j] < pivot) { Utils.swap(a, i, j); i++; }
        }
        Utils.swap(a, i, hi);
        return i;
    }
}
