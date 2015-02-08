/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import prac1.exceptions.EmptyCollectionException;

/**
 *
 * @author dorian
 */
public class CribaLinked {

   public static SetADT<Integer> cribaErastotenes(int n){
        SetADT<Integer> primos=new LinkedSet<Integer>();

        for (int i = 2; i <=n; i++) {
            primos.add(i);
        }
        
        int tope = (int) Math.floor(Math.sqrt(n)) + 1;
        for (int i = 2; i <=tope; i++) {
            if(primos.contains(i)){
                for (int j = i; j <=n && i*j<=n; j++) {
                    try {
                        primos.remove(i*j);
                    } catch (EmptyCollectionException ex){
                        Logger.getLogger(CribaArray.class.getName()).log(Level.SEVERE, null, ex);                    	 
                    }catch (NoSuchElementException ex){
                        Logger.getLogger(CribaArray.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        return primos;
    }
}
