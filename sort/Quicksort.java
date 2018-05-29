public class Quicksort {

    private static int partition(int[] input, int p, int r) {
        int pivot = input[r];
        int i = p - 1;

        for (int j = p; j < r; j++) {
            if (input[j] <= pivot) {
                i++;
                swap(input, i, j);
            }
        }

        swap(input, i + 1, r);
        return i + 1;
    }

    public static void quicksort(int[] input, int p, int r) {
        if (p >= r)
            return;

        int q = partition(input, p, r);
        System.out.println("Pivot: " + q + ", val: " + input[q]);
        printNumbers(input);
        quicksort(input, p, q - 1);
        quicksort(input, q + 1, r);
    }


    public static void sort(int[] input) {
        quicksort(input, 0, input.length - 1);
    }

    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
        printNumbers(input);
        sort(input);
        printNumbers(input);
    }

    private static void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }

    private static void printNumbers(int[] input) {
        System.out.print("Array: " + input[0]);
        for (int i = 1; i < input.length; i++) {
            System.out.print(", " + input[i]);
        }
        System.out.println("");
    }
}
