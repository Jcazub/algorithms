package Part1.Week2.IteratorAndBag;

import java.util.Iterator;

public class GenericResizingArrayStackWithIterator<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    public GenericResizingArrayStackWithIterator()
    {
        s = (Item[]) new Object[1];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public void push(Item item)
    {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public Item pop()
    {
        // can cause loitering i.e. item in index not being taken care
        // of by garbage collection
        //return s[--N];

        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }

        s = copy;
    }

    public Iterator<Item> iterator()
    {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {

        private int current = N;

        public boolean hasNext()
        {
            return current > 0;
        }

        public Item next()
        {
            return s[--current];
        }
    }


}
