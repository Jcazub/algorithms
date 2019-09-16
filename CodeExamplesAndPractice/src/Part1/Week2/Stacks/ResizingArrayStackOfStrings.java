package Part1.Week2.Stacks;

public class ResizingArrayStackOfStrings {

    private String[] s;
    private int N = 0;

    public ResizingArrayStackOfStrings()
    {
        s = new String[1];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(String item)
    {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public String pop()
    {
        // can cause loitering i.e. item in index not being taken care
        // of by garbage collection
        //return s[--N];

        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    private void resize(int capacity)
    {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }

        s = copy;
    }
}