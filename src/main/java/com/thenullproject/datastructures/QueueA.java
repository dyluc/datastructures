package com.thenullproject.datastructures;

import java.util.NoSuchElementException;

/**
 * A circular queue/circular buffer implementation using a static array. This implementation uses a read and write index to make sure dequeue stays an O(1) operation.
 *
 * Without a size variable, there is no way to know whether a queue is full or empty as the read and write indexes will eventually be the same as the capacity is reached. This can be overcome in two ways:
 *  - Introduce a buffer element (write index - 1) inside the array that can never contain a value, the queue implementation should not allow adding an item here
 *  - Introduce a size variable that tracks the number of items inside the queue and so this value will be 0 if empty, and equal to the capacity if full
 */
public class QueueA {

    private static final int DEFAULT_CAPACITY = 8;

    private final Object[] arr;
    private int size;
    private int read; // read index
    private int write; // write index

    public QueueA() {
        arr = new Object[DEFAULT_CAPACITY];
        read = write = size = 0;
    }

    /**
     * Add item to the queue. O(1) operation with WRITE index.
     * @param value value to assign to node
     */
    public void enqueue(Object value) {
        Object existing = arr[write];
        if (existing == null) {
            arr[write++] = value;
            if (write >= DEFAULT_CAPACITY)
                write = 0;
            size++;
        } else {
            throw new IllegalStateException("Reached queue max capacity");
        }
    }

    /**
     * Removes and returns item from the queue. O(1) operation with READ index.
     * @return the least-recently added item in the queue
     */
    public Object dequeue() {
        Object value = arr[read];
        if(value == null)
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        if(++read >= DEFAULT_CAPACITY)
            read = 0;
        size--;
        return value;
    }

    /**
     * Check if queue contains any items
     * @return true if the queue is empty, false otherwise
     */
    public boolean empty() {
        return size == 0;
        // without size variable (must use buffer element between read and write indexes)
//        return read == write;
    }

    /**
     * Check if queue contains any items
     * @return true if the queue is full, false otherwise
     */
    public boolean full() {
        return size == DEFAULT_CAPACITY;
    }

}
