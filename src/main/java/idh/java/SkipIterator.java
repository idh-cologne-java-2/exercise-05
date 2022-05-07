package idh.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SkipIterator<T> implements Iterator<T> {

    Iterator<T> iterator;

    int n;
    int size;
    int index;

    public SkipIterator(Iterator<T> iterator, int n, int size) {
        this.iterator = iterator;
        this.n = n;
        this.size = size;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        return index + n + 1 < size;
    }

    @Override
    public T next() {
        try {
            index += n + 1;
            for (int i = 0; i < n; i++) {
                iterator.next();
            }

            return iterator.next();
        }
        catch (NoSuchElementException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
