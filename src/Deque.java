import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Storage<Item> first;
    private Storage<Item> last;

    public Deque() { // construct an empty deque
        first = null;
        last = null;
    }

    public boolean isEmpty() {   // is the deque empty?
        return first == null;
    }

    public int size() {  // return the number of items on the deque
        if (isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Item items : this) {
            count++;
        }
        return count;
    }

    public void addFirst(Item item) {   // add the item to the front

        if (item == null) {
            throw new java.lang.IllegalArgumentException("Item is null");
        }
        if (first == null && last == null) {
            Storage<Item> storage = new Storage<>(item, null, null);
            first = storage;
            last = storage;
            return;
        }

        Storage<Item> newFirst = new Storage<>(item, first, null);
        first.setBefore(newFirst);
        first = newFirst;
    }

    public void addLast(Item item) { // add the item to the end
        if (item == null) {
            throw new java.lang.IllegalArgumentException("Item is null");
        }
        if (first == null && last == null) {
            Storage<Item> storage = new Storage<Item>(item, null, null);
            first = storage;
            last = storage;
            return;
        }
        Storage<Item> newLast = new Storage<Item>(item, null, last);
        last.setNext(newLast);
        last = newLast;
    }

    public Item removeFirst() {  // remove and return the item from the front
        if (first == null && last == null) {
            throw new java.util.NoSuchElementException();
        }
        final Storage<Item> rf = first;
        first = rf.getNext();
        if (first == null) {
            last = null;
        } else {
            first.setBefore(null);
        }
        return rf.getObject();
    }

    public Item removeLast() { // remove and return the item from the end
        if (first == null && last == null) {
            throw new java.util.NoSuchElementException();
        }
        final Storage<Item> rl = last;
        last = rl.getBefore();
        if (last != null) {
            last.setNext(null);
        } else {
            first = null;
            last = null;
        }
        rl.setNext(null);
        rl.setBefore(null);
        return rl.getObject();
    }

    public Iterator<Item> iterator() {   // return an iterator over items in order from front to end
        return new Iterator<Item>() {
            Storage<Item> current = first;

            @Override
            public boolean hasNext() {
                return (current != null);
            }


            @Override
            public Item next() {
                if (current == null) {
                    throw new java.util.NoSuchElementException();
                }
                final Item obj = current.getObject();
                current = current.getNext();
                return obj;
            }

            @Override
            public void remove() {
                throw new java.lang.UnsupportedOperationException();
            }


        };
    }

    private static class Storage<Item> {
        private Item object;
        private Storage<Item> next;
        private Storage<Item> before;

        public Storage(Item object, Storage<Item> next, Storage<Item> before) {
            this.object = object;
            this.next = next;
            this.before = before;
        }

        public Item getObject() {
            return this.object;
        }

        public Storage<Item> getNext() {
            return this.next;
        }

        public Storage<Item> getBefore() {
            return this.before;
        }

        public void setObject(Item object) {
            this.object = object;
        }

        public void setNext(Storage<Item> item) {
            this.next = item;
        }

        public void setBefore(Storage<Item> item) {
            this.before = item;
        }
    }
}