package com.thenullproject.datastructures;

/**
 * Linked list implementation without the fuss...
 */
public sealed interface LinkedListV2<T> {
    record Empty<T>() implements LinkedListV2<T> {}
    record Node<T>(T value, Node<T> next) implements LinkedListV2<T> {}
}
