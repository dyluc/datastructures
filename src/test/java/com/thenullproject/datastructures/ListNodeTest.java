package com.thenullproject.datastructures;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Singly linked list tests
 */
public class ListNodeTest {

    @ParameterizedTest(name = "Index: {0}")
    @DisplayName("Value at should throw an IndexOutOfBoundsException for indexes less than 0 or greater than list size - 1.")
    @ValueSource(ints = {-5, 20})
    public void valueAtShouldThrowIndexOutOfBoundsExceptionForInvalidIndexes(int index) {
        LinkedList<Float> list = new LinkedList<>();

        // add 3 items to list
        list.pushBackWithoutTail(3.14f);
        list.pushBackWithoutTail(2.718f);
        list.pushBackWithoutTail(1.618f);

        assertThrows(IndexOutOfBoundsException.class, () -> list.valueAt(index));
    }

    // == Back of list ==

    @Test
    @DisplayName("Push back items WITHOUT TAIL should return correct size and values in order.")
    public void pushBackWithoutTail() {
        LinkedList<String> list = new LinkedList<>();

        // add 3 items to list
        list.pushBackWithoutTail("Item 1");
        list.pushBackWithoutTail("Item 2");
        list.pushBackWithoutTail("Item 3");

        // check size
        assertEquals(list.size(), 3);

        // check values ["Item 1", "Item 2", "Item 3"]
        assertEquals(list.valueAt(0), "Item 1");
        assertEquals(list.valueAt(1), "Item 2");
        assertEquals(list.valueAt(2), "Item 3");

    }

    @Test
    @DisplayName("Push back items WITH TAIL should return correct size and values in order.")
    public void pushBackWithTail() {
        LinkedList<Character> list = new LinkedList<>();

        // add 5 items to list
        list.pushBack('A');
        list.pushBack('B');
        list.pushBack('C');
        list.pushBack('D');
        list.pushBack('E');

        // check size
        assertEquals(list.size(), 5);

        // check values ['A', 'B', 'C', 'D', 'E']
        assertEquals(list.valueAt(0), 'A');
        assertEquals(list.valueAt(1), 'B');
        assertEquals(list.valueAt(2), 'C');
        assertEquals(list.valueAt(3), 'D');
        assertEquals(list.valueAt(4), 'E');
    }

    @Test
    @DisplayName("Pop back item WITH TAIL should return correct value, size and values in order.")
    public void popBack() {
        LinkedList<String> list = new LinkedList<>();

        // add 3 items
        list.pushBack("Number 3");
        list.pushFront("Number 2");
        list.pushFront("Number 1");
        list.pushBack("Number 4");

        // check size
        assertEquals(list.size(), 4);

        // pop back
        String poppedValue = list.popBack();

        // check value
        assertEquals(poppedValue, "Number 4");

        // check size
        assertEquals(list.size(), 3);

        // check values ["Number 1", "Number 2", "Number 3"]
        assertEquals(list.valueAt(0), "Number 1");
        assertEquals(list.valueAt(1), "Number 2");
        assertEquals(list.valueAt(2), "Number 3");
    }

    // == Front of list ==

    @Test
    @DisplayName("Push front items WITH TAIL should return correct size and values in order.")
    public void pushFront() {
        LinkedList<Integer> list = new LinkedList<>();

        // add 2 items to back of list
        list.pushBack(423);
        list.pushBack(21);

        // add 2 item to front of list
        list.pushFront(29001);
        list.pushFront(1);

        // check size
        assertEquals(list.size(), 4);

        // check values [1, 29001, 423, 21]
        assertEquals(list.valueAt(0), 1);
        assertEquals(list.valueAt(1), 29001);
        assertEquals(list.valueAt(2), 423);
        assertEquals(list.valueAt(3), 21);
    }

    @Test
    @DisplayName("Pop front item WITH TAIL should return correct value, size and values in order.")
    public void popFront() {
        LinkedList<String> list = new LinkedList<>();

        // add 3 items
        list.pushBack("Last Item");
        list.pushFront("Middle Item");
        list.pushFront("First Item");

        // check size
        assertEquals(list.size(), 3);

        // pop front
        String poppedValue = list.popFront();

        // check value
        assertEquals(poppedValue, "First Item");

        // check size
        assertEquals(list.size(), 2);

        // check values ["Middle Item", "Last Item"]
        assertEquals(list.valueAt(0), "Middle Item");
        assertEquals(list.valueAt(1), "Last Item");
    }

    @Test
    @DisplayName("Pop back and pop front should throw an NoSuchElementException for an empty list.")
    public void valueAtShouldThrowIndexOutOfBoundsExceptionForInvalidIndexes() {
        LinkedList<Object> list = new LinkedList<>();

        assertThrows(NoSuchElementException.class, list::popBack);
        assertThrows(NoSuchElementException.class, list::popFront);
    }

    @Test
    @DisplayName("Add items after should return correct size and values in order.")
    public void addAfter() {
        LinkedList<Integer> list = new LinkedList<>();

        // add 3 items
        list.pushBack(0xAB); // 171
        list.pushBack(0xCD); // 205
        list.pushBack(0xEF); // 239

        // check size
        assertEquals(list.size(), 3);

        // add after
        list.addAfter(1, 0x12); // 18
        list.addAfter(3, 0x82); // 130

        // check size
        assertEquals(list.size(), 5);

        // check values [0xAB, 0xCD, 0x12, 0xEF]
        assertEquals(list.valueAt(0), 171);
        assertEquals(list.valueAt(1), 205);
        assertEquals(list.valueAt(2), 18);
        assertEquals(list.valueAt(3), 239);
        assertEquals(list.valueAt(4), 130);
    }

    @Test
    @DisplayName("Add items before should return correct size and values in order.")
    public void addBefore() {
        LinkedList<String> list = new LinkedList<>();

        // add 3 items
        list.pushBack("Item 1");
        list.pushBack("Item 2");
        list.pushBack("Item 3");

        // check size
        assertEquals(list.size(), 3);

        // add before
        list.addBefore(0, "Item .5");
        list.addBefore(2, "Item 1.5");

        // check size
        assertEquals(list.size(), 5);

        // check values ["Item .5", "Item 1", "Item 1.5", "Item 2", "Item 3"]
        assertEquals(list.valueAt(0), "Item .5");
        assertEquals(list.valueAt(1), "Item 1");
        assertEquals(list.valueAt(2), "Item 1.5");
        assertEquals(list.valueAt(3), "Item 2");
        assertEquals(list.valueAt(4), "Item 3");
    }



}
