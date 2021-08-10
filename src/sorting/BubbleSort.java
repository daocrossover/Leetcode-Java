package sorting;

public class BubbleSort {
    public static void sort(int[] arr) {
        int tmp, len = arr.length;
        for (int i = 0; i < len - 1; ++i) {
            boolean exchanged = false;
            for (int j = 0; j < len - 1 - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    exchanged = true;
                }
            }
            if (!exchanged) break;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{17, 53, 71, 36, 46, 41, 23, 12};
        sort(arr);
        String out = "";
        for (int digit : arr) {
            out += (digit + ",");
        }
        System.out.println(out);
    }
}
