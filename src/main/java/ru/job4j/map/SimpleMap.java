package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= table.length * LOAD_FACTOR) {
            expand();
        }
        int index = key == null ? 0 : indexFor(Objects.hashCode(key));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }

        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        modCount = 0;
        capacity = capacity * 2;
        MapEntry<K, V>[] tmp = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : tmp) {
            if (el != null) {
                int index = el.key == null ? 0 : indexFor(Objects.hashCode(el.key));
                table[index] = el;
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = key == null ? 0 : indexFor(Objects.hashCode(key));
        if (table[index] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[index].key)
                    && Objects.equals(key, table[index].key)) {
                rsl = table[index].value;
            }
        }
        return rsl;
    }


    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[index].key)
                    && Objects.equals(table[index].key, key)) {
                rsl = true;
                table[index] = null;
                count--;
                modCount++;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int cursor = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < table.length && table[cursor] == null) {
                    cursor++;
                }
                return cursor != table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}