package com.thenullproject.datastructures;

import java.util.NoSuchElementException;

/**
 * A queue implementation using a linked list with a tail pointer. A lot of the code here is very similar to the {@link LinkedList} implementation.
 */
public class QueueLL<T> {

    private ListNode<T> head;
    private ListNode<T> tail;

    private static class ListNode<T> {

        T value;
        ListNode<T> next;

        ListNode(T value, ListNode<T> next) {
            this.value = value;
            this.next = next;
        }

        ListNode(T value) {
            this(value, null);
        }


        void addAfter(T value) { // O(1)
            next = new ListNode<>(value, next);
        }

        ListNode<T> getNthNode(int n) {
            if(n == 0)
                return this;
            else if(n < 0 || next == null)
                return null;
            else
                return next.getNthNode(n - 1);
        }

    }

    /**
     * Add item to the queue. O(1) operation with TAIL pointer.
     * @param value value to assign to node
     */
    public void enqueue(T value) {
        var temp = new ListNode<>(value);
        if(tail == null) // set head and tail
            head = tail = temp;
        else {
            tail.next = temp;
            tail = temp;
        }
    }

    /**
     * Removes and returns item from the queue. O(1) operation with HEAD pointer.
     * @return the least-recently added item in the queue
     */
    public T dequeue() {
        if(head == null)
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        var temp = head;
        if((head = head.next) == null)
            tail = null;
        return temp.value;
    }

    /**
     * Check if queue contains any items
     * @return true if the queue is empty, false otherwise
     */
    public boolean empty() {
        return head == null;
    }
}
