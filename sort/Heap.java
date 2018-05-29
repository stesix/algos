class Heap {
    private static int lastId;

    public static void heapify(int[] input) {
        for (int i = lastId/2; i >= 0; i--)
            maxheap(input, i);
    }

    public static void maxheap(int[] input, int idx) {
        int left = 2*idx;
        int right = 2*idx + 1;
        int max = idx;

        if (left <= lastId && input[left] > input[idx])
            max = left;

        if (right <= lastId && input[right] > input[max])
            max = right;

        if (max != idx) {
            swap(input, idx, max);
            maxheap(input, max);
        }
    }

    public static void heapsort(int[] input) {
        heapify(input);

        for (int i = lastId; i > 0; i--) {
            swap(input, 0, i);
            lastId--;
            maxheap(input, 0);
        }
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

    public static void sort(int[] input) {
        heapsort(input);
    }

    public static int extractMax(int[] input) {
        if (lastId < 1)
            return -1;

        int max = input[0];
        input[0] = input[lastId--];
        maxheap(input, 0);
        return max;
    }

    public static boolean increaseKey(int[] input, int idx, int key) {
        if (key < input[idx])
            return false;

        input[idx] = key;

        while (idx > 0 && input(idx/2) < input[idx]) {
            swap(input, idx/2, idx);
            idx = idx/2;
        }
    }

    public static void main(String[] args) {
        int[] input = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
        lastId = input.length - 1;

        sort(input);
        printNumbers(input);
    }

}
