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
 * Tests for circular Queue implementation using a static array
 */
public class QueueATest {

    @Test
    @DisplayName("Should create new queue that is empty")
    public void shouldCreateNewEmptyQueue() {
        QueueA queue = new QueueA();
        assertTrue(queue.empty());
        assertFalse(queue.full());
    }

    @Test
    @DisplayName("Should throw no such element exception when trying to dequeue empty queue")
    public void shouldThrowNoSuchElementExceptionOnIllegalDequeue() {
        QueueA queue = new QueueA();
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    // Nested tests
    @Nested
    @DisplayName("When array exists")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenArrayExists {

        private QueueA queue;

        @BeforeAll
        public void initialiseQueue() {
            queue = new QueueA();
        }

        @Test
        @DisplayName("Enqueue items should result in non-empty queue")
        @Order(1)
        public void enqueue() {
            // enqueue 3 items
            queue.enqueue('A');
            queue.enqueue('B');
            queue.enqueue('C');

            // check if empty
            assertFalse(queue.empty());
        }

        @Test
        @DisplayName("Dequeue items should return correct items in order, result in empty queue")
        @Order(2)
        public void dequeue() {
            // dequeue 3 items
            char item1 = (char) queue.dequeue();
            char item2 = (char) queue.dequeue();
            char item3 = (char) queue.dequeue();

            // assert values dequeued in order
            assertEquals('A', item1);
            assertEquals('B', item2);
            assertEquals('C', item3);

            assertTrue(queue.empty());
        }
    }

    @Test
    @DisplayName("Enqueue items should throw illegal state exception when reached max capacity")
    public void shouldThrowIllegalStateExceptionWhenAtMaxCapacity() {
        QueueA queue = new QueueA();

        for(int i = 0; i < 8; i++)
            queue.enqueue(i);

        assertTrue(queue.full());
        assertFalse(queue.empty());
        assertThrows(IllegalStateException.class, () -> queue.enqueue(8));
    }


}
