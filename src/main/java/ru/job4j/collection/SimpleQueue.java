package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                T tmp = in.pop();
                out.push(tmp);
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}