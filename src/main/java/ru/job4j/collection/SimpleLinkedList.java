package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {
    private Node<E> first;
    private Node<E> last;

    private int size = 0;
    private int modCount = 0;

    @Override
    public void add(E value) {
        final Node<E> tmp = last;
        Node<E> newNode = new Node<>(last, value, null);
        last = newNode;
        if (tmp == null) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        E rsl = null;
        Objects.checkIndex(index, size);
        int currentIndex = 0;
        Node<E> currentElement = first;
        if (index < 0 || index >= size) {
            rsl = null;
        }
        while (currentElement != null) {
            if (currentIndex == index) {
                rsl = currentElement.item;
                return rsl;
            }
            currentElement = currentElement.next;
            currentIndex++;
        }
        return rsl;
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
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}