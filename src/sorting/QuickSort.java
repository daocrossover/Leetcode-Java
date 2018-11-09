package sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 4, 8, 2, 55, 3, 4, 8, 6, 4, 0, 11, 34, 90, 23, 54, 77, 9, 2, 9, 4, 10};
        sort(arr);
        String out = "";
        for (int digit : arr) {
            out += (digit + ",");
        }
        System.out.println(out);
    }

    public static void sort(int[] arr) {
        qSort(arr, 0, arr.length-1);
    }

    private static void qSort(int[] arr, int low, int high){
        if (low < high){
            int pivot = partition(arr, low, high);
            qSort(arr, low, pivot-1);
            qSort(arr, pivot+1, high);
        }
    }

    private static int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        while (lo < hi) {
            while (lo < hi && nums[hi] >= pivot) --hi;
            nums[lo] = nums[hi];
            while (lo < hi && nums[lo] <= pivot) ++lo;
            nums[hi] = nums[lo];
        }
        nums[lo] = pivot;
        return lo;
    }
}
