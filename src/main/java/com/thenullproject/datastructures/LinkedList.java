package com.thenullproject.datastructures;

import java.util.NoSuchElementException;

/**
 * Linked lists are a recursive data structures, similar to tree's, because it has a recursive definition.
 * A linked lists are comprised of nodes, that contain a reference to the next node in the list with the same type.
 *
 * Something to note about a LinkedList, like other Abstract Data Type, enforces invariants. The 2 invariants a LinkedList
 * enforces are:
 *
 * 1). A list is never circularly linked. In other words, there is a final node within the list with a next pointer of null.
 * 2). The size value is always correct.
 *
 * To achieve this, a list implementation must only allow methods of the list to modify internal fields. This can be done
 * by:
 *
 * 1). Declaring fields private and ensuring data encapsulation.
 * 2). No method of the list should return a node. In Java, this can be done by means of a static nested class declared private.
 *     That way, only the internals of the list implementation can get access to the node implementation, only returning node values
 *     from the methods, and not the nodes themselves.
 *
 * Doubly linked lists with a sentinel node have some invariants too (note that these are now circularly linked lists, and the sentinel node is
 * an implementation detail of the ADT):
 *
 * 1). There will always be a sentinel node, even for empty lists, so a list's reference to its sentinel node will never be null.
 * 2). Because it is circularly linked, no node in the list will have a null value for it's next or previous pointer (may point to sentinel).
 * 3). For any node a, if a.next equals b, then b.prev must equal a.
 * 4). For any node c, if c.prev equals b, then b.next must equal c.
 * 6). The size value is always correct (doesn't include sentinel).
 *
 * In an empty list, the sentinel's previous and next fields point to itself.
 *
 */
public class LinkedList<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

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

    public LinkedList() {
        size = 0;
    }

    public void pushFront(T value) {
        head = new ListNode<>(value, head);
        if(tail == null) // set tail pointer
            tail = head;
        size++;
    }

    public T popFront() {
        if(head == null)
            throw new NoSuchElementException();
        ListNode<T> temp = head;
        if((head = head.next) == null)
            tail = null;
        size--;
        return temp.value;
    }

    /**
     * Push item to back of list, implementation does include TAIL pointer. O(1) operation.
     * @param value value to assign to node
     */
    public void pushBack(T value) {
        ListNode<T> temp = new ListNode<>(value);
        if(tail == null) // set head and tail
            head = tail = temp;
        else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    /**
     * Push item to back of list, implementation does not include TAIL pointer. O(n) operation.
     * @param value value to assign to node
     */
    public void pushBackWithoutTail(T value) {
        if(head == null)
            head = new ListNode<>(value);
        else {
            ListNode<T> temp = head;
            while(temp.next != null)
                temp = temp.next;
            temp.next = new ListNode<>(value);
        }

        size++;
    }

    public T popBack() {
        if(head == null)
            throw new NoSuchElementException();
        ListNode<T> temp = head;
        while(temp.next != tail)
            temp = temp.next;
        tail = temp;
        size--;
        return tail.next.value;
    }

    public void addAfter(int index, T value) { // O(n)
        ListNode<T> nthNode = head.getNthNode(index);
        if(nthNode == null)
            throw new IndexOutOfBoundsException();
        nthNode.addAfter(value);
        size++;
    }

    public void addBefore(int index, T value) { // O(n)
        if(index == 0)
            pushFront(value);
        else {
            ListNode<T> nthNode = head.getNthNode(index - 1);
            if(nthNode == null)
                throw new IndexOutOfBoundsException();
            nthNode.addAfter(value);
            size++;
        }
    }

    public T valueAt(int index) {
        ListNode<T> nthNode = head.getNthNode(index);
        if(nthNode == null)
            throw new IndexOutOfBoundsException();
        return nthNode.value;
    }

    public int size() {
        return size;
    }
}
