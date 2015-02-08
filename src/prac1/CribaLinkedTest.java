/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author dorian
 */
public class CribaLinkedTest {
    
    @Test
    public void cribaErastotenes() {
        LinkedSet<Integer> result=new LinkedSet<Integer>();
        result.add(2);
        result.add(3);
        result.add(5);
        result.add(7);
        assertTrue(CribaLinked.cribaErastotenes(10).isEqualsTo(result));
        
        result.add(11);
        assertTrue(CribaLinked.cribaErastotenes(11).isEqualsTo(result));
        
        
        result.add(13);
        result.add(17);
        result.add(19);
        result.add(23);
        result.add(29);
        result.add(31);
        result.add(37);
        assertTrue(CribaLinked.cribaErastotenes(40).isEqualsTo(result));
    }
}
