package Part1.Week2.Stacks;

public class FixedCapacityStackOfStrings {

    private String[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings(int capacity)
    {
        s = new String[capacity];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(String item)
    {
        s[N++] = item;
    }

    public String pop()
    {
        // can cause loitering i.e. item in index not being taken care
        // of by garbage collection
        //return s[--N];

        String item = s[--N];
        s[N] = null;
        return item;
    }
}
