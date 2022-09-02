package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;

    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentElement = first;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }
        return currentElement.item;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            final int expectedModCount = modCount;
            private Node<E> cursor = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = cursor.item;
                cursor = cursor.next;
                return rsl;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
        }
    }
}