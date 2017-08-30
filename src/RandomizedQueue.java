import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int size;
    private int capacity;

    public RandomizedQueue() {  // construct an empty randomized queue
        size = 0;
        capacity = 1;
        queue = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {  // is the queue empty?
        return size == 0;
    }

    public int size() {  // return the number of items on the queue
        return size;
    }

    public void enqueue(Item item) { // add the item
        if (item == null) {
            throw new NullPointerException();
        }
        if (size + 1 > capacity) {
            capacity *= 2;
            Item[] newQueue = (Item[]) new Object[capacity];
            int index = 0;
            for (Item x : queue) {
                newQueue[index++] = x;
            }
            queue = newQueue;
        }
        queue[size++] = item;

    }

    public Item dequeue() { // remove and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(size);
        Item lost = queue[random];
        queue[random] = queue[--size];
        queue[size] = null;
        if (capacity / 3 > size) {
            capacity /= 2;
            Item[] newQueue = (Item[]) new Object[capacity];
            int index = 0;
            for (int x = 0; x < capacity; x++) {
                newQueue[index++] = queue[x];
            }
            queue = newQueue;
        }
        return lost;
    }

    public Item sample() { // return (but do not remove) a random item
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int random = StdRandom.uniform(size);
        return queue[random];
    }

    public Iterator<Item> iterator() {  // return an independent iterator over items in random order
        return new Iterator<Item>() {
            int pointer = 0;
            int arraySize = size;
            int[] random;

            @Override
            public boolean hasNext() {
                while (pointer < arraySize) {
                    return true;
                }
                return false;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (random == null) {
                    random = new int[arraySize];
                    for (int id = 0; id < arraySize; id++) {
                        random[id] = id;
                    }
                    StdRandom.shuffle(random);
                }
                return queue[random[pointer++]];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}