import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private InnerNode first;
    private InnerNode last;
    private int size;

    private class InnerNode {
        Item item;
        InnerNode next;
        InnerNode prev;
    }

    private class DequeIterator implements Iterator<Item> {

        InnerNode current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (!this.hasNext()) throw new NoSuchElementException();

            Item item = current.item;

            current = current.prev;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Deque() {

    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        InnerNode node = new InnerNode();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = first;
        }
        else {
            InnerNode oldFirst = first;
            first = node;
            first.prev = oldFirst;
            oldFirst.next = first;
        }

        size++;

    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        InnerNode node = new InnerNode();
        node.item = item;

        if (isEmpty()) {
            first = node;
            last = first;
        } else {
            InnerNode oldLast = last;
            last = node;
            last.next = oldLast;
            oldLast.prev = last;

        }

        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;

        if (first.equals(last)) {
            first = null;
            last = null;
        } else {
            first = first.prev;
            first.next.prev = null; // maybe unnecessary
            first.next = null;
        }

        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;

        if (first.equals(last)) {
            first = null;
            last = null;
        } else {
            last = last.next;
            last.prev.next = null; // maybe unnecessary
            last.prev = null;
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {



        Deque<Integer> testDeque = new Deque<>();

        System.out.println("Is the deque empty?: " + testDeque.isEmpty());
        System.out.println("Current size?: " + testDeque.size());
        System.out.println();

        System.out.println("adding items to queue");
        testDeque.addFirst(3);
        testDeque.addFirst(2);
        testDeque.addFirst(1);

        System.out.println();
        System.out.println("current items in queue order: ");
        for (int i : testDeque) {
            System.out.println(i);
        }
        System.out.println();

        System.out.println("Is the deque empty?: " + testDeque.isEmpty());
        System.out.println("Current size?: " + testDeque.size());
        System.out.println();

        System.out.println("adding items to queue");
        testDeque.addLast(4);
        testDeque.addLast(5);

        System.out.println("current items in queue order: ");
        for (int i : testDeque) {
            System.out.println(i);
        }
        System.out.println();

        testDeque.removeFirst();
        System.out.println("Current size?: " + testDeque.size());
        testDeque.removeLast();
        testDeque.removeFirst();
        System.out.println(testDeque.isEmpty());
        System.out.println(testDeque.size());
    }


}
