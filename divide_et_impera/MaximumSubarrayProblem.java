public class MaximumSubarrayProblem {

    public static Subarray findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = -Integer.MAX_VALUE/2;
        int sum = 0;
        int maxLeft = 0;
        int maxRight = 0;

        for (int i = mid; i > low; i--) {
            sum += A[i];

            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = -Integer.MAX_VALUE/2;
        sum = 0;

        for (int i = mid + 1; i < high + 1; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new Subarray(maxLeft, maxRight, leftSum + rightSum);
    }

    public static Subarray findMaxSubarray(int[] A, int low, int high) {
        if (high == low)
            return new Subarray(low, high, A[low]);

        int mid = (low + high)/2;

        Subarray left = findMaxSubarray(A, low, mid);
        System.out.println("left (fms)\t- low = " + left.low + ", high = " + left.high + ", sum = " + left.sum);
        Subarray right = findMaxSubarray(A, mid + 1, high);
        System.out.println("right (fms)\t- low = " + right.low + ", high = " + right.high + ", sum = " + right.sum);
        Subarray cross = findMaxCrossingSubarray(A, low, mid, high);
        System.out.println("cross (fms)\t- low = " + cross.low + ", high = " + cross.high + ", sum = " + cross.sum);

        if (left.sum >= right.sum && left.sum >= cross.sum)
            return left;

        if (right.sum >= left.sum && right.sum >= cross.sum)
            return right;

        return cross;
    }

    private static void printResults(Subarray fms, int[] input, int[] verify) {
        System.out.print(input[fms.low]);
        for (int i = fms.low + 1; i <= fms.high; i++) {
            System.out.print(", " + input[i]);
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        int[] input = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, 7 };
        int[] result = { 18, 20, -7, 12 };

        Subarray fms = findMaxSubarray(input, 0, input.length - 1);

        printResults(fms, input, result);
    }
}

class Subarray {
    int low;
    int high;
    int sum;

    Subarray(int l, int h, int s) {
        low = l;
        high = h;
        sum = s;
    }
}
