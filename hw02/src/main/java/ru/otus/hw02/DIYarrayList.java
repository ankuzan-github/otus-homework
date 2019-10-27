package ru.otus.hw02;

import java.util.*;

public class DIYarrayList<T> implements List<T> {

    private Object[] array;

    private int size;
    private int capacity;

    public DIYarrayList() {
        array = new Object[]{};
        capacity = 0;
    }

    public DIYarrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            array = new Object[initialCapacity];
            capacity = initialCapacity;
        } else {
            array = new Object[]{};
        }
    }

    public DIYarrayList(Collection<T> collection) {
        if (collection.size() > 0) {
            array = collection.toArray();
            capacity = array.length;
        } else {
            array = new Object[]{};
            capacity = 0;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size > 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null)
                    return true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(o))
                    return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new DIYIterator();
    }

    @Override
    public Object[] toArray() {
        var returnArray = new Object[size];
        System.arraycopy(array, 0, returnArray, 0, size);
        return returnArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public boolean add(T item) {
        newCapacity(size + 1);
        array[size] = item;
        size++;
        return true;
    }

    private void newCapacity(int minCapacity) {
        if (capacity <= minCapacity) {
            var newCapacity = capacity + (capacity >> 1);
            if (newCapacity <= minCapacity)
                newCapacity = minCapacity + (minCapacity >> 1);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        newCapacity(this.size + collection.size());
        System.arraycopy(collection.toArray(), 0, array, this.size, collection.size());
        size += collection.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public T get(int index) {
        if (index < 0 && index >= size)
            throw new IndexOutOfBoundsException(String.format("Failed to get element by %s index. Collection size if %s", index, array.length));
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public ListIterator<T> listIterator() {
        return new DIYListIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("The method is not implemented.");
    }

    private class DIYIterator implements Iterator<T> {
        int cursor;
        int lastReturned = -1;
        DIYarrayList<T> list = DIYarrayList.this;

        @Override
        public boolean hasNext() {
            return cursor != list.size;
        }

        @Override
        public T next() {
            if (cursor < size) {
                lastReturned = ++cursor;
                return list.get(lastReturned);
            }
            else
                throw new NoSuchElementException();
        }
    }

    private class DIYListIterator extends DIYIterator implements ListIterator<T> {

        @Override
        public boolean hasPrevious() {
            return (cursor - 1) >= 0;
        }

        @Override
        public T previous() {
            if (cursor > 0) {
                lastReturned = --cursor;
                return list.get(lastReturned);
            }
            else
                throw new NoSuchElementException();
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The method is not implemented.");
        }

        @Override
        public void set(T e) {
            if (lastReturned < 0)
                throw new IllegalStateException();
            else
                list.array[lastReturned] = e;
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException("The method is not implemented.");
        }
    }
}
