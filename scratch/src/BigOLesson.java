import java.util.LinkedList;

public class BigOLesson {

    public static void test() {


        // 0(N2)
        // if N = the amount of elements in the array
        // NxN = N2
        // 0(1)
        // Big O in terms of runtime: calculate what is done, whoever many times
        // Simplifying it in the most dominant terms

        int[] intArray = {1,2,3,4,5};

        // 1 int + 1 int + 1 int + 1 int = 4 int

        int count = 0;

        int length = intArray.length;

        LinkedList<Integer> countList = new LinkedList<Integer>();

        for (int i = 0; i < length; i++)
        {
            for (int j = 0; j < length; j++)
            {
                countList.add(intArray[j]);
                //count++;
            }
        }

        for (Integer currentInt : countList)
        {
            System.out.println(currentInt);
        }

       // System.out.println(count);


       // System.out.println(count);
    }


}
