package Part1.Week2.Practice;

import Part1.Week2.IteratorAndBag.GenericResizingArrayStackWithIterator;

public class QueueWithTwoStacks<Item> {

    private GenericResizingArrayStackWithIterator<Item> enqueueStack;
    private GenericResizingArrayStackWithIterator<Item> dequeueStack;

    public QueueWithTwoStacks()
    {
        enqueueStack = new GenericResizingArrayStackWithIterator<>();
        dequeueStack = new GenericResizingArrayStackWithIterator<>();
    }

    public boolean isEmpty()
    {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public void enqueue(Item item)
    {
        enqueueStack.push(item);
    }

    public Item dequeue()
    {
        if (dequeueStack.isEmpty())
        {
            while(!enqueueStack.isEmpty())
            {
                dequeueStack.push(enqueueStack.pop());
            }
        }

        return dequeueStack.pop();
    }

}
