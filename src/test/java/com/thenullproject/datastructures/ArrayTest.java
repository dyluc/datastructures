package com.thenullproject.datastructures;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {

    @ParameterizedTest(name = "Initial Capacity: {0}, Set Capacity: {1}")
    @DisplayName("Should create array with correct capacity using initial capacity constructor")
    @CsvSource({
            "2, 16",
            "12, 16",
            "33, 64",
            "255, 256"
    })
    public void shouldCreateArrayWithCorrectInitialCapacity(int initialCapacity, int setCapacity) {

        Array array = new Array(initialCapacity);
        assertEquals(array.capacity(), setCapacity);

    }

    @Test
    @DisplayName("Should create array with correct capacity using default constructor")
    public void shouldCreateArrayWithCorrectInitialCapacity() {
        Array array = new Array();
        assertEquals(array.capacity(), 16);
    }

    @ParameterizedTest(name = "Initial Capacity: {0}")
    @DisplayName("Should throw illegal argument exception if initial capacity is less than or equal to zero")
    @ValueSource(ints = {-5, 0})
    public void shouldThrowIllegalArgumentExceptionIfInitialCapacityLessThanEqualToZero(int initialCapacity) {
        assertThrows(IllegalArgumentException.class, () -> new Array(initialCapacity));
    }

    // Nested tests
    @Nested
    @DisplayName("When array exists")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class WhenArrayExists {
        private Array array;

        @BeforeAll
        public void initialiseArray() {
            // create array with initial capacity of 5
            array = new Array(5);
        }

        @Test
        @DisplayName("Push items should return correct capacity and size")
        @Order(1)
        public void push() {
            // push 17 items
            for(int i = 1; i <= 17; i++)
                array.push("Item " + i);

            // check size
            assertEquals(array.size(), 17);

            // check capacity
            assertEquals(array.capacity(), 32);
        }

        @Test
        @DisplayName("Pop items should return correct capacity, size and value")
        @Order(2)
        public void pop() {
            // pop 2 items
            array.pop();
            Object item = array.pop();

            // check value
            assertEquals(item, "Item 16");

            // check size
            assertEquals(array.size(), 15);

            // check capacity
            assertEquals(array.capacity(), 32);
        }

        @Test
        @DisplayName("Get item should return correct item")
        @Order(3)
        public void get() {
            // get item at index 7
            Object item = array.get(7);

            // check value
            assertEquals(item, "Item 8");

        }

        @Test
        @DisplayName("Set item should update item")
        @Order(4)
        public void set() {
            int index = array.size() - 1;
            String replace = "Hello World";

            // set last item to "Hello World"
            array.set(index, replace);

            // check value
            assertEquals(array.get(index), replace);

        }


    }


}