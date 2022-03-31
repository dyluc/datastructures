package com.thenullproject.datastructures;

/**
 * A simple custom implementation of a dynamic array (mutable array with automatic resizing)
 */
public class Array {

    private static final int INITIAL_CAPACITY = 16;

    private Object[] arr;
    private int capacity;
    private int size;

    public Array() {
        this(INITIAL_CAPACITY);
    }

    public Array(int initialCapacity) {
        if(initialCapacity <= 0)
            throw new IllegalArgumentException("Initial size cannot be less than or equal to zero.");
        else if(initialCapacity <= INITIAL_CAPACITY)
            capacity = INITIAL_CAPACITY;
        else
            capacity = Integer.highestOneBit(initialCapacity) << 1;
        arr = new Object[capacity];
        size = 0;
    }

    public Object get(int i) {
        checkIndexWithinBounds(i);
        return arr[i];
    }

    public void set(int i, Object value) {
        checkIndexWithinBounds(i);
        arr[i] = value;
    }

    public void push(Object value) {
        if(size == capacity) {
            // allocate new array
            // Object[] temp = Arrays.copyOf(arr, Integer.highestOneBit(capacity) << 1);
            Object[] temp = new Object[capacity << 1];
            for(int i = 0; i < arr.length; i++)
                temp[i] = arr[i];
            arr = temp;
            capacity = capacity << 1;
        }
        arr[size++] = value;
    }

    public Object pop() {
        if(size > 0)
            return arr[--size];
        throw new IndexOutOfBoundsException("Cannot pop off empty list");
    }

    public void remove(int i) {
        checkIndexWithinBounds(i);
        for(int j = i; j < size - 1; j++)
            arr[j] = arr[j+1];
        size--;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    // private methods

    private void checkIndexWithinBounds(int i) {
        if(i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Provided index is out of bounds");
    }
}
