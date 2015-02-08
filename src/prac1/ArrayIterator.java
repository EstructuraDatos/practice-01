package prac1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class ArrayIterator<T> implements Iterator<T>{
    private int size;
    private int current;
    private T[] items;

    public ArrayIterator(int size, T[] items) {
        this.size = size;
        this.current = 0;
        this.items = items;
    }
    
    @Override
    public boolean hasNext() {
        return (current<size);
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        current++;
        return items[current-1];
    }

    @Override
    public void remove() {
        for (int i = current; i < size; i++) {
            items[i-1] = items[i];
        }
        items[size-1]=null;
        current--;
    }
    
}
