package Part1.Week2.Queues;

public class ResizingArrayQueueOfStrings {

    private String[] s;
    private int head = 0;
    private int tail = 0;

    public ResizingArrayQueueOfStrings()
    {
        s = new String[1];
    }

    public boolean isEmpty()
    {
        return head == tail;
    }

    public void push(String item)
    {
        if (tail == s.length) resize(2 * (tail - head));
        s[tail++] = item;
    }

    public String pop()
    {
        String item = s[head];
        s[head++] = null;
        if(tail - head == s.length/4) resize(s.length/2);
        return item;
    }

    private void resize(int capacity)
    {
        String[] copy = new String[capacity];
        int j = 0;
        for (int i = head; i < tail; i++, j++)
        {
            copy[j] = s[i];
        }

        // reset pointer values
        head = 0;
        tail = j;

        s = copy;
    }


}
