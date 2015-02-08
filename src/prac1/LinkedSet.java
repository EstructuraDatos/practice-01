package prac1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import prac1.exceptions.EmptyCollectionException;

/**
 *
 * @author dorian
 */
public class LinkedSet<T> implements SetADT<T> {
    private static final Random rand=new Random();
    private int count;
    private LinearNode<T> firstNode;

    public LinkedSet() {
        firstNode=null;
        count=0;
    }
    
    
    
    @Override
    public void add(T element) {
        if(!this.contains(element) && element!= null){
            LinearNode<T> node=new LinearNode<T>(element);
            node.setNext(firstNode);
            firstNode=node;
            count++;
        }
    }

    @Override
    public T remove(T target) throws EmptyCollectionException, NoSuchElementException {
        T removed=null;
        if(this.isEmpty()){
            throw new EmptyCollectionException("LinkedSet");
            
        }else{
            boolean found=false;
            LinearNode<T> previous, current;
            
            if(firstNode.getElement().equals(target)){
                removed=firstNode.getElement();
                firstNode=firstNode.getNext();
                
            }else{
                previous=firstNode;
                current=previous.getNext();
                
                for(int i=1; i<count && !found; i++){
                    if(current.getElement().equals(target)){
                        found=true;
                        removed=current.getElement();
                        previous.setNext(current.getNext());
                        
                    }else{
                        previous=current;
                        current=previous.getNext();
                    }
                }
                
                if(!found){
                    throw new NoSuchElementException();
                }
            }
            count--;
        }
        
        return removed;
    }

    @Override
    public T removeRandom() throws EmptyCollectionException {
       if(this.isEmpty()){
            throw new EmptyCollectionException("LinkedSet");
            
        }else{
           T removed;
           LinearNode<T> previous, current;
           int index=rand.nextInt(count)+1;
            
           if(index==1){
               removed=firstNode.getElement();
               firstNode=firstNode.getNext();
           } else{
               previous=firstNode;
               for(int i=2; i<index; i++){
                   previous=previous.getNext();
               }
               current=previous.getNext();
               removed=current.getElement();
               previous.setNext(current.getNext());
               
           }
           
           count--;
           return removed;
        }
    }

    @Override
    public SetADT<T> union(SetADT<T> set) {
        SetADT<T> union=new ArraySet<T>();
        union.addAll(this);
        union.addAll(set);
        return union;
    }

    @Override
    public SetADT<T> intersect(SetADT<T> set) {
        Iterator<T> iterator;
        SetADT<T> setWhereSearch, result=new ArraySet<T>();
        
        if(set.size()>this.size()){
            iterator=set.iterator();
            setWhereSearch=this;
        }else{
            iterator=this.iterator();
            setWhereSearch=set;
        }
        for (; iterator.hasNext();) {
            T target = iterator.next();
            if(setWhereSearch.contains(target)){
                result.add(target);
            }
        }
        
        return result;
    }

    @Override
    public SetADT<T> diference(SetADT<T> set) {
        SetADT<T> result=new ArraySet<T>();
        for (Iterator<T> it = this.iterator(); it.hasNext();) {
            T target = it.next();
            if(!set.contains(target)){
                result.add(target);
            }
        }
        return result;
    }

    @Override
    public void addAll(SetADT<T> set) {
        for (Iterator<T> it = set.iterator(); it.hasNext();) {
            this.add(it.next());
        }
    }

    @Override
    public boolean contains(T target) {
        boolean found=false;
        if(!isEmpty()){
            for (Iterator<T> it = this.iterator(); it.hasNext() && !found;) {
                if(it.next().equals(target)){
                    found=true;
                }
            }
        }
        return found;
    }

    @Override
    public boolean isEqualsTo(SetADT<T> set) {
        boolean equals=true;
        if(set.size()==this.size()){
            for (Iterator<T> it = this.iterator(); it.hasNext() && equals;) {
                if(!set.contains(it.next())){
                    equals=false;
                }
            }
        }else{
            equals=false;
        }
        return equals;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator<T>(firstNode, count);
    }
    
    
    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        if(!this.isEmpty()){
            for (Iterator<T> it = this.iterator(); it.hasNext();) {
                result.insert(0, it.next().toString());
                result.insert(0, ", ");
            }
            result.delete(0, 2);
        }
        return result.toString();
    }
    
}
