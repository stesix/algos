public class InsertionSort {

    public static void sort(int[] input) {
        int key;

        for (int i = 1; i < input.length; i++) {
            int j = i - 1;
            key = input[i];

            while (j >= 0 && input[j] > key) {
                input[j + 1] = input[j];
                input[j] = key;
                j--;
            }

            printNumbers(input);
        }
    }

    private static void printNumbers(int[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
        sort(input);
    }
}
