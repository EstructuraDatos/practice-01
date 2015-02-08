/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public class LinkedIterator<T> implements Iterator<T> {
    private LinearNode<T> current;

    public LinkedIterator(LinearNode<T> list, int size) {
        this.current =list;
    }
    
    @Override
    public boolean hasNext() {
        return (current!=null);
    }

    @Override
    public T next() {
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        T target=current.getElement();
        current=current.getNext();
        return target;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
