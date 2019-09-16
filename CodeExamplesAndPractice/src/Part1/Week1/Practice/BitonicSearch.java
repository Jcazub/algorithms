package Part1.Week1.Practice;

public class BitonicSearch {

    public static int BitonicSearch(int low, int high, int num, int[] array)
    {
        int mid = (low + high)/2;
        int result = 0;

        if (array[mid] == num) return mid;

        if (low == high) {
            return -1;
        }

        if (array[mid - 1] < array[mid] && array[mid + 1] < array[mid]) {

            result = BinarySearch(low, mid, num, array);
            if(result != -1) return result;

            result = ReverseBinarySearch(mid + 1, high, num, array);
            if(result != -1) return result;

        }

        if (array[mid - 1] < array[mid] && array[mid + 1] > array[mid])
        {
            if (num < array[mid])
            {
                result = BinarySearch(low, mid, num, array);
                if(result != -1) return result;
            }

            result = BitonicSearch(mid + 1, high, num, array);
            if(result != -1) return result;
        }

        if (array[mid - 1] > array[mid] && array[mid + 1] < array[mid])
        {
            result = BitonicSearch(low, mid, num, array);
            if(result != -1) return result;

            if (num < array[mid])
            {
                result = ReverseBinarySearch(mid + 1, high, num, array);
                if(result != -1) return result;
            }
        }

        return -1;

    }

    public static int BinarySearch(int low, int high, int num, int[] array)
    {
        int mid = (low + high)/2;

        if (num == array[mid]) return mid;

        if (low == high) return -1;

        if (num < array[mid]) {
            return BinarySearch(low, mid, num, array);
        }
        else
        {
            return BinarySearch(mid + 1, high, num, array);
        }
    }

    public static int ReverseBinarySearch(int low, int high, int num, int[] array)
    {
        int mid = (low + high)/2;

        if (num == array[mid]) return mid;

        if (low == high) return -1;

        if (num > array[mid]) {
            return ReverseBinarySearch(low, mid, num, array);
        }
        else
        {
            return ReverseBinarySearch(mid + 1, high, num, array);
        }
    }

    public static void main(String[] args) {

        int[] array = {1,3,5,6,8,10,11,14,66,76,77,79,100,22,19,13,9,4,0};

        System.out.println(BitonicSearch(0,array.length - 1, 100, array));
     }


}
