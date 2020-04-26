import java.util.Arrays;
import java.util.concurrent.*;

public class ParallelMergeSort {
    private LogTime[] array;
    private int minSize;
    private final ForkJoinPool pool;

    private class mergeSortTask extends RecursiveAction {

        LogTime[] array;
        int left;
        int right;

        public mergeSortTask(LogTime[] array, int left, int right) {
            this.array = array;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (right - left <= minSize) {
                mergeSort(array, left, right);
            } else {
                int mid = left + (right - left) / 2;
                invokeAll(new mergeSortTask(array, left, mid), new mergeSortTask(array, mid, right));
                merge(array, left, mid, right);
            }
        }
    }

    public static void mergeSort(LogTime[] x, int start, int end) {
        if (end - start == 1) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(x, start, mid);
        mergeSort(x, mid, end);
        merge(x, start, mid, end);
    }

    public static void merge(LogTime[] x, int start, int mid, int end) {
        LogTime[] temp = new LogTime[end - start];
        int i = 0;
        int p1 = start;
        int p2 = mid;
        while (p1 < mid && p2 < end) {
            if (x[p1].compareTo(x[p2]) <= 0) { // There should be <= to maintain the order of two items with same value.
                temp[i++] = x[p1++];
            } else {
                temp[i++] = x[p2++];
            }
        }
        while (p1 < mid) {        // If left hand array is left.
            temp[i++] = x[p1++];
        }
        while (p2 < end) {       // If right hand array is left.
            temp[i++] = x[p2++];
        }
        // Copy temp[] to array x.
        System.arraycopy(temp, 0, x, start, temp.length);
    }

    /**
     * parallel merge sort an array.
     * @param array    the array needed sorting
     * @param minSize  the threshold for performing multithreaded merge sort
     * @param threads  max number of threads involving parallel merge sort
     */

    public ParallelMergeSort(LogTime[] array, int minSize, int threads) {
        this.array = array;
        this.minSize = minSize;
        pool = new ForkJoinPool(threads);
    }

    public void parallelMergeSort() {
        pool.invoke(new mergeSortTask(array, 0, array.length));
    }


    /* Helper function */
    static boolean isSorted(LogTime[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1].compareTo(a[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
