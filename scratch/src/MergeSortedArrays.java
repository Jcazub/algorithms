import java.util.LinkedList;
import java.util.Queue;

public class MergeSortedArrays {

    public static int[] mergeSortedArrays(int[] x, int[] y) {
        int sizeX = x.length;
        int sizeY = y.length;

        if (sizeX > sizeY) {
            return sortArrays(x,y);
        } else {
            return sortArrays(y,x);
        }

    }

    private static int[] sortArrays(int[] larger, int[] smaller) {

        int largerIndex = larger.length - 1;
        int smallerIndex = smaller.length - 1;
        int replaceIndex = largerIndex;
        LinkedList<Integer> largerQueue = new LinkedList<>();

        while(largerIndex >= 0 && smallerIndex >= 0) {

            if (larger[largerIndex] != 0) {

                if (replaceIndex < largerIndex) {
                    largerIndex = replaceIndex;
                }

                if (largerQueue.isEmpty()) {

                }
                if (larger[largerIndex] > smaller[smallerIndex]) {
                    larger[replaceIndex] = larger[largerIndex];
                    largerIndex--;
                } else {
                    larger[replaceIndex] = smaller[smallerIndex];
                    smallerIndex--;
                }
                replaceIndex--;
            } else {
                largerIndex--;
            }

        }
        return larger;
    }

    // possible bugs: what is the largest X num is in the last index?
    // should we be comparing the values at 0 index?
}
