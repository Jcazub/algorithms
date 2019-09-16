package Part1.Week2.IteratorAndBag;

import java.util.Iterator;

public class GenericBag<Item> implements Iterable<Item> {

    private Item[] s;
    private int N = 0;

    public GenericBag()
    {
        s = (Item[]) new Object[1];
    }

    public int size()
    {
        return N;
    }

    public void add(Item item)
    {
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
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
