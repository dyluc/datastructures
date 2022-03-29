package com.thenullproject.datastructures;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Queue implementations using a linked list and a static array
 */
public class QueueTest {

    @Test
    @DisplayName("Should create new queue that is empty")
    public void shouldCreateNewEmptyQueue() {
        QueueLL<String> queue = new QueueLL<>();
        assertTrue(queue.empty());
    }

    @Test
    @DisplayName("Should throw no such element exception when trying to dequeue empty queue")
    public void shouldThrowNoSuchElementExceptionOnIllegalDequeue() {
        QueueLL<Byte> queue = new QueueLL<>();
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    // Nested tests
    @Nested
    @DisplayName("When array exists")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenArrayExists {

        private QueueLL<Integer> queue;

        @BeforeAll
        public void initialiseQueue() {
            queue = new QueueLL<>();
        }

        @Test
        @DisplayName("Enqueue items should result in non-empty queue")
        @Order(1)
        public void enqueue() {
            // enqueue 3 items
            queue.enqueue(100);
            queue.enqueue(200);
            queue.enqueue(300);

            // check if empty
            assertFalse(queue.empty());
        }

        @Test
        @DisplayName("Dequeue items should return correct items in order, result in empty queue")
        @Order(2)
        public void dequeue() {
            // dequeue 3 items
            Integer item1 = queue.dequeue();
            Integer item2 = queue.dequeue();
            Integer item3 = queue.dequeue();

            // assert values dequeued in order
            assertEquals(100, item1);
            assertEquals(200, item2);
            assertEquals(300, item3);

            assertTrue(queue.empty());
        }

    }

}
