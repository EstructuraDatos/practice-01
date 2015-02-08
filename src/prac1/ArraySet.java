package prac1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import prac1.exceptions.EmptyCollectionException;

/**
 *
 * @author dorian
 */
public class ArraySet<T> implements SetADT<T> {
    private static final Random rand=new Random();
    private final int DEFAULT_SIZE = 5;
    private int capacity;
    private T[] content;
    private int count;

    public ArraySet() {
        count=0;
        capacity=DEFAULT_SIZE;
        content=(T[])new Object[DEFAULT_SIZE];
    }
    
    @Override
    public void add(T element) {
        if(!this.contains(element) && element!= null){
            if(this.size()==capacity){
                newSize();
            }
            content[count]=element;
            count++;
        }
    }
    
    private void newSize(){
        T[] buffer=(T[])new Object[this.capacity+DEFAULT_SIZE];
        //System.arraycopy(content, 0, buffer, 0, count);
        //se podría usar la instrucción anterior comentada pero al no permitir
        //usar la API de java, la alternativa es:
        for(int i=0; i<capacity; i++){
        	buffer[i]=content[i];
        }
        capacity=capacity+DEFAULT_SIZE;
        content=buffer;
    }

    @Override
    public T remove(T element) throws EmptyCollectionException, NoSuchElementException{
        T target=null;
        
        if(this.isEmpty()){
            throw new EmptyCollectionException("ArraySet");
            
        }else if(!this.contains(element)){
            throw new NoSuchElementException();
            
        }else{
            boolean found=false;
            for (Iterator<T> it = this.iterator(); it.hasNext() && !found;) {
                target = it.next();
                if(target.equals(element)){
                    found=true;
                    it.remove();
                    count--;
                }
            }
        }
        return target;
    }

    @Override
    public T removeRandom() throws EmptyCollectionException{
        if(this.isEmpty()){
            throw new EmptyCollectionException("ArraySet");
            
        }else{
            int index=rand.nextInt(count);
            return remove(content[index]);
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
        boolean findIt=false;
        for (Iterator<T> it = this.iterator(); it.hasNext() && !findIt;) {
            if(it.next().equals(target)){
                findIt=true;
            }
        }
        return findIt;
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
        return new ArrayIterator<T>(count, content);
    }
    
    @Override
    public String toString(){
        StringBuilder result=new StringBuilder();
        if(!this.isEmpty()){
            for (Iterator<T> it = this.iterator(); it.hasNext();) {
                result.append(it.next().toString());
                result.append(", ");
            }
            result.delete(result.length()-2, result.length());
        }
        return result.toString();
    }
}
