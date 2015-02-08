/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prac1;

import prac1.exceptions.EmptyCollectionException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author dorian
 */
public class LinkedSetTest {
    
    private LinkedSet<Integer> list;
    
    @Before
    public void setUp() {
        list=new LinkedSet<Integer>();
    }
    
    @Test
    public void addTest(){
        //tamaño que tenía
        int size=list.size();
        assertEquals(0, size);
        
        //añado dos objetos
        list.add(5);
        list.add(10);
        
        //compruebo que añadió los objetos
        assertEquals(size+2, list.size());
        
        //intento añadir dos objetos no permitidos
        //uno repetido
        list.add(5);
        //y un null
        list.add(null);
        
        //compruebo que el tamaño no varió
        assertEquals(size+2, list.size());
        
        //compruebo que los elementos son los correctos
        assertEquals("5, 10", list.toString());
    }
    
    @Test
    public void testRemoveOk() throws EmptyCollectionException{
        //añado tres objetos
        list.add(8);
        list.add(24);
        list.add(11);

        //obtengo el tamaño que tiene
        int size=list.size();
        assertEquals(3, size);
        
        //elimino uno
        int eliminado=list.remove(24);   
        //compruebo que devolvió el objeto correcto
        assertEquals(24, eliminado);
        //compruebo el tamaño
        assertEquals(size-1, list.size());
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveEmpty() throws EmptyCollectionException{
        //Intento borrar un objeto aleatorio estando vacio
        list.remove(5);
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testRemoveNoExist() throws EmptyCollectionException {
        //Intento borrar un objeto no existente
        list.add(3);
        list.remove(5);
    }
    
    @Test
    public void testRemoveRandomOk() throws EmptyCollectionException{
        //añado tres objetos
        list.add(8);
        list.add(24);
        list.add(11);

        //obtengo tamaño correcto...
        assertEquals(3, list.size());
        
        //elimino uno
        int eliminado=list.removeRandom();   
        //compruebo el tamaño
        assertEquals(2, list.size());

        //compruebo que no exista ya el elemento eliminado
        assertFalse(list.contains(eliminado));
    }
    
    @Test(expected=EmptyCollectionException.class)
    public void testRemoveRandomWhenEmpty() throws EmptyCollectionException{
        //creo un arraySet nuevo e intento borrar un objeto aleatorio estando vacio
        list.removeRandom();
    }
    
    @Test
    public void testAddAll(){
        //creo dos listas
        ArraySet<Integer> prueba=new ArraySet<Integer>();
        ArraySet<Integer> prueba2=new ArraySet<Integer>();
        
        //añado algunas entradas a una
        prueba.add(5);
        prueba.add(3);
        prueba.add(8);
        
        //añado la lista entera al otro
        prueba2.addAll(prueba);
        
        //ambos tendrán que ser iguales
        assertTrue(prueba.isEqualsTo(prueba2));
    }
    
    @Test
    public void testContains(){
        //pruebo mientras esté vacío
        assertFalse(list.contains(4));
        
        list.add(3);
        list.add(7);
        
        assertTrue(list.contains(3));
        assertTrue(list.contains(7));
        assertFalse(list.contains(10));
    }
    
    @Test
    public void testIsEqualsTo(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        ArraySet<Integer> list2=new ArraySet<Integer>();
        
        assertTrue(list1.isEqualsTo(list2));
        
        list1.add(5);
        list1.add(2);
        list2.add(5);
        list2.add(2);
        
        assertTrue(list1.isEqualsTo(list2));
        
        list1.add(9);
        assertFalse(list1.isEqualsTo(list2));
        
    }
    
    @Test
    public void testIsEmpty(){
        assertTrue(list.isEmpty());
        list.add(6);
        assertFalse(list.isEmpty());
    }
    
    @Test
    public void testSize(){
        assertEquals(0, list.size());
        list.add(3);
        assertEquals(1, list.size());
        list.add(2);
        list.add(9);
        assertEquals(3, list.size());
        try {
            list.removeRandom();
        } catch (EmptyCollectionException ex) {
            Logger.getLogger(ArraySetTest.class.getName()).log(Level.INFO, null, ex);
        }
        assertEquals(2, list.size());
        
    }
    
    @Test
    public void testUnion(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        ArraySet<Integer> list2=new ArraySet<Integer>();
        SetADT<Integer> result;
        
        result=list1.union(list2);
        assertEquals(0, result.size());
        
        list1.add(5);
        result=list1.union(list2);
        assertEquals(1, result.size());
        
        list2.add(5);
        result=list1.union(list2);
        assertEquals(1, result.size());
        
        list2.add(8);
        result=list1.union(list2);
        assertEquals(2, result.size());
        
        //compruebo que el contenido de este último es correcto
        assertEquals("5, 8", result.toString());
    }
    
    @Test
    public void testIntersect(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        ArraySet<Integer> list2=new ArraySet<Integer>();
        SetADT<Integer> result;
        
        result=list1.intersect(list2);
        assertEquals(0, result.size());
        
        list1.add(5);
        result=list1.intersect(list2);
        assertEquals(0, result.size());
        
        list2.add(5);
        result=list1.intersect(list2);
        assertEquals(1, result.size());
        
        list2.add(8);
        result=list1.intersect(list2);
        assertEquals(1, result.size());
        
        //compruebo que la lista es correcta
        assertEquals("5", result.toString());
    }
    
    @Test
    public void testDiference(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        ArraySet<Integer> list2=new ArraySet<Integer>();
        SetADT<Integer> result;
        
        result=list1.diference(list2);
        assertEquals(0, result.size());
        
        list1.add(5);
        result=list1.diference(list2);
        assertEquals(1, result.size());
        
        list2.add(5);
        result=list1.diference(list2);
        assertEquals(0, result.size());
        
        list2.add(8);
        list1.add(15);
        list1.add(3);
        result=list1.diference(list2);
        assertEquals(2, result.size());
        //compruebo que la lista es correcta
        assertEquals("15, 3", result.toString());
        
        result=list2.diference(list1);
        assertEquals(1, result.size());
        //compruebo que la lista es correcta
        assertEquals("8", result.toString());
    }
    
    @Test
    public void testNewSize(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        for (int i = 0; i < 20; i++) {
            list1.add(i);
        }
        
        assertEquals(20, list1.size());
        assertTrue(list1.contains(0));
        assertTrue(list1.contains(6));
        assertTrue(list1.contains(19));
    }
    
    @Test
    public void testToString(){
        ArraySet<Integer> list1=new ArraySet<Integer>();
        assertEquals("", list1.toString());
        for (int i = 0; i < 10; i++) {
            list1.add(i);
        }
        assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", list1.toString());
    }
}
