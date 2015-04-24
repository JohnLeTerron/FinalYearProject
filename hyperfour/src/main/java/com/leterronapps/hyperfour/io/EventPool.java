package com.leterronapps.hyperfour.io;

import java.util.Vector;

/**
 * Created by williamlea on 29/01/15.
 */
public class EventPool<T> {

    private Vector<T> pool;

    public  EventPool() {
        pool = new Vector<>(20);
    }

    public void addEvent(T event) {
        pool.add(event);
    }

    public T get(int index) {
        return pool.get(index);
    }

    public void clear() {
        pool.clear();
    }

    public int size() {
        return pool.size();
    }

    public Vector<T> getEvents() {
        return pool;
    }
}
