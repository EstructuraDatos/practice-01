package prac1;

import prac1.exceptions.EmptyCollectionException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author dorian
 */
public interface SetADT<T> {
    /**
     * Add an element if it's not in the list
     * @param element 
     */
    public void add(T element);
    
    /**
     * Remove the element
     * @param element
     * @return the element removed or null if not exist
     */
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException;
    
    /**
     * Remove a random element
     * @return the element removed or null if the set don't have elements
     */
    public T removeRandom() throws EmptyCollectionException;
    
    /**
     * The union of the two sets
     * @param set
     * @return a new set equals to the union of these two sets
     */
    public SetADT<T> union(SetADT<T> set);
    
    /**
     * The intersect of both sets
     * @param set
     * @return the new set resultant
     */
    public SetADT<T> intersect(SetADT<T> set);
    
    /**
     * The diference of both sets
     * @param set
     * @return the new set
     */
    public SetADT<T> diference(SetADT<T> set);
    
    /**
     * Add all elements of the set
     * @param set 
     */
    public void addAll(SetADT<T> set);
    
    /**
     * 
     * @param target
     * @return 
     */
    public boolean contains(T target);
    
    /**
     * 
     * @param set
     * @return 
     */
    public boolean isEqualsTo(SetADT<T> set);
    
    /**
     * 
     * @return 
     */
    public boolean isEmpty();
    
    /**
     * 
     * @return 
     */
    public int size();
    
    /**
     * 
     * @return 
     */
    public Iterator<T> iterator();
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString();
}
