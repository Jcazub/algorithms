import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int front;
    private int capacity;

    public RandomizedQueue() {
        items = (Item[]) new Object[1];
        capacity = 1;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        int[] indexArray = new int[front];
        int current = 0;

        RandomizedQueueIterator() {
            for (int i = 0; i < front; i++) {
                indexArray[i] = i;
            }
            StdRandom.shuffle(indexArray);
        }

        @Override
        public boolean hasNext() {
            return current < indexArray.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[indexArray[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return front;
    }

    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();

        if (size() == capacity) resizeArray(capacity * 2);

        items[front++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        int dequeueIndex = StdRandom.uniform(front);

        Item item = items[dequeueIndex];

        --front;
        if (dequeueIndex != front) {
            items[dequeueIndex] = items[front];
        }

        items[front] = null;

        // resize if necessary
        if (size() == (capacity/4)) resizeArray(capacity / 2);

        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return items[StdRandom.uniform(front)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }

        for (int i : randomizedQueue) {
            System.out.println("Random iterator entry: " + i);
        }

        System.out.println();

        for (int i = 0; i < 10; i++) {
            System.out.println("Sample: " + randomizedQueue.sample());
            System.out.println("Dequeue: " + randomizedQueue.dequeue());
        }
    }

    private void resizeArray(int newLength) {

        if (newLength < 1) newLength = 1;

        Item[] newArray = (Item[]) new Object[newLength];

        int i;
        for (i = 0; i < front; i++) {
            newArray[i] = items[i];
        }

        capacity = newLength;
        front = i;
        items = newArray;
    }
}
