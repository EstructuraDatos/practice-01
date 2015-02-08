/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1.exceptions;

/**
 *
 * @author dorian
 */
public class EmptyCollectionException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1728863744722975877L;

	public EmptyCollectionException(String collection) {
        super("La colección: " + collection + " está vacía.");
    }
    
}
