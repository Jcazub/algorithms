package Part1.Week2.IteratorAndBag;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericLinkedStackWithIterator<Item> implements Iterable<Item> {

    private Node first = null;

    private class Node
    {
        Item item;
        Node next;
    }

    public boolean isEmpty()
    {
        return first == null;
    }

    public void push(Item item)
    {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Item pop()
    {
        Item item = first.item;
        first = first.next;
        return item;
    }

    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;

        public boolean hasNext() {

            return current.next != null;
        }

        public Item next()
        {

            if (current == null) throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

    }


}
