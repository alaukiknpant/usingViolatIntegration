package lazy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class LazyList<T> {

    public class Node<T> {
        /**
         * actual item
         */
        T item;
        /**
         * item's hash code
         */
        int key;
        /**
         * next Node in list
         */
        Node<T> next;
        /**
         * If true, Node is logically deleted.
         */
        boolean marked;

        boolean tagged;
        /**
         * Synchronizes Node.
         */
        Lock lock;

        /**
         * Constructor for usual Node
         *
         * @param item element in list
         */
        Node(T item) { // usual constructor
            this.item = item;
            this.key = item.hashCode();
            this.next = null;
            this.marked = false;
            this.tagged = false;
            this.lock = new ReentrantLock();
        }

        /**
         * Constructor for sentinel Node
         *
         * @param key should be min or max int value
         */
        Node(int key) { // sentinel constructor
            this.item = null;
            this.key = key;
            this.next = null;
            this.marked = false;
            this.tagged = false;
            this.lock = new ReentrantLock();
        }

        Node(T item, boolean tag) {
            this.item = item;
            this.key = item.hashCode();
            this.next = null;
            this.marked = false;
            this.tagged = tag;
            this.lock = new ReentrantLock();
        }

        /**
         * Lock Node
         */
        void lock() {
            lock.lock();
        }

        /**
         * Unlock Node
         */
        void unlock() {
            lock.unlock();
        }
    }


    /**
     * First list Node
     */
    private Node<T> head;

    /**
     * Constructor
     */
    public LazyList() {
        // Add sentinels to start and end
        this.head = new Node<>(Integer.MIN_VALUE);
        this.head.next = new Node<>(Integer.MAX_VALUE);
    }

    /**
     * Check that prev and curr are still in list and adjacent
     */
    private boolean validate(Node<T> pred, Node<T> curr) {
        return !pred.marked && !curr.marked && pred.next == curr;
    }

    private boolean validateReplace(Node<T> pred, Node<T> curr) {
        return !pred.marked && !curr.marked && pred.next == curr && !pred.tagged && !curr.tagged;
    }

    /**
     * Add an element.
     *
     * @param item
     *            element to add
     * @return true iff element was not there already
     */
    public boolean add(T item) {
        int key = item.hashCode();
        while (true) {
            Node<T> pred = this.head;
            Node<T> curr = head.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        if (curr.key == key) { // present
                            return false;
                        } else { // not present
                            Node<T> Node = new Node<>(item);
                            Node.next = curr;
                            pred.next = Node;
                            return true;
                        }
                    }
                } finally { // always unlock
                    curr.unlock();
                }
            } finally { // always unlock
                pred.unlock();
            }
        }
    }

    private boolean add(T item, boolean tag) {
        int key = item.hashCode();
        while (true) {
            Node<T> pred = this.head;
            Node<T> curr = head.next;
            while (curr.item.hashCode() < item.hashCode()) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validateReplace(pred, curr)) {
                        if (curr.key == key) { // present
                            return false;
                        } else { // not present
                            Node<T> Node = new Node<>(item, tag);
                            Node.next = curr;
                            pred.next = Node;
                            return true;
                        }
                    }
                } finally { // always unlock
                    curr.unlock();
                }
            } finally { // always unlock
                pred.unlock();
            }
        }
    }

    /**
     * Remove an element.
     *
     * @param item
     *            element to remove
     * @return true iff element was present
     */
    public boolean remove(T item) {
        int key = item.hashCode();
        while (true) {
            Node<T> pred = this.head;
            Node<T> curr = head.next;
            while (curr.key < key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    if (validate(pred, curr)) {
                        if (curr.key != key) { // present
                            return false;
                        } else { // absent
                            curr.marked = true; // logically remove
                            pred.next = curr.next; // physically remove
                            return true;
                        }
                    }
                } finally { // always unlock curr
                    curr.unlock();
                }
            } finally { // always unlock pred
                pred.unlock();
            }
        }
    }

    public boolean replace(T oldItem, T newItem) {
        boolean changed = add(newItem, true);
        changed = changed | remove(oldItem);
        int key = newItem.hashCode();
        while(true) {
            Node<T> pred = this.head;
            Node<T> curr = head.next;
            while(curr.key != key) {
                pred = curr;
                curr = curr.next;
            }
            pred.lock();
            try {
                curr.lock();
                try {
                    curr.tagged = false;
                    return changed;
                } finally {
                    curr.unlock();
                }
            } finally {
                pred.unlock();
            }
        }
    }

    /**
     * Test whether element is present
     *
     * @param item
     *            element to test
     * @return true iff element is present
     */
    public boolean contains(T item) {
        int key = item.hashCode();
        Node<T> curr = this.head;
        while (curr.key < key)
            curr = curr.next;
        return curr.key == key && !curr.marked && !curr.tagged;
    }

    @Override
    public synchronized String toString() {
        Node<T> ptr = head.next;
        StringBuilder sb = new StringBuilder("[ ");
        while (ptr.item != null) {
            sb.append(ptr.item.toString() + " ");
            ptr = ptr.next;
        }
        sb.append("]");
        return sb.toString();
    }

}