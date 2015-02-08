/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1;

/**
 *
 * @author dorian
 */
public class LinearNode<T> {
    private LinearNode<T> next;
    private T element;

    public LinearNode() {
        next=null;
        element=null;
    }

    public LinearNode(T element) {
        this.next = null;
        this.element = element;
    }
    
    public void setNext(LinearNode<T> node){
        next=node;
    }
    
    public LinearNode<T> getNext(){
        return next;
    }
    
    public T getElement(){
        return element;
    }
    
    public void setElement(T element){
        this.element=element;
    }
}
