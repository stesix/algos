public class MergeSort {

    public static void sort(int[] input) {
        merge_srt(input, 0, input.length - 1);
    }

    private static void merge_srt(int[] input, int p, int r) {
        if (p >= r)
            return;

        int q = p + (r - p)/2;
        merge_srt(input, p, q);
        merge_srt(input, q + 1, r);
        merge(input, p, q, r);
    }

    private static void merge(int[] input, int p, int q, int r) {
        int[] left = new int[q - p + 1];
        int[] right = new int[r - q];

        for (int i = 0; i < q - p + 1; i++)
            left[i] = input[p + i];

        System.out.print("LEFT: ");
        printNumbers(left);

        for (int i = 0; i < r - q; i++)
            right[i] = input[q + i + 1];

        System.out.print("RIGHT: ");
        printNumbers(right);

        int i = 0;
        int j = 0;
        int k = p;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                input[k] = left[i];
                i++;
            } else {
                input[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            input[k++] = left[i++];
        }


        while (j < right.length) {
            input[k++] = right[j++];
        }


    }

    private static void printNumbers(int[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
        sort(input);
        printNumbers(input);
    }
}
