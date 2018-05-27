public class BubbleSort {
    public static void sort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = input.length - 1; j > i; j--) {
                if (input[j] < input[j - 1]) {
                    int tmp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = tmp;
                }
            }
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
