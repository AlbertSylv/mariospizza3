/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariospizza;

//Her tester vi vores pizza class methods!!!

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Albert
 */
public class PizzaTest {
    
    public PizzaTest() {
    }

    /**
     * Test of getPizzaNr method, of class Pizza.
     */
    @Test
    public void testGetPizzaNr() {
        System.out.println("getPizzaNr");
        Pizza instance = new Pizza("8");
        String expResult = "8";
        String result = instance.getPizzaNr();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPizzaNr method, of class Pizza.
     */
    @Test
    public void testSetPizzaNr() {
        System.out.println("setPizzaNr");
        String pizzaNr = "2";
        Pizza instance = new Pizza("2");
        instance.setPizzaNr(pizzaNr);
        
    }

    /**
     * Test of getNavn method, of class Pizza.
     */
    @Test
    public void testGetNavn() {
        System.out.println("getNavn");
        Pizza instance = new Pizza("2","Abdulla",8,"j");
        String expResult = "Abdulla";
        String result = instance.getNavn();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of setNavn method, of class Pizza.
     */
    @Test
    public void testSetNavn() {
        System.out.println("setNavn");
        String navn = "Abdulla";
        Pizza instance = new Pizza("Abdulla");
        instance.setNavn(navn);
    }

    /**
     * Test of getPris method, of class Pizza.
     */
    @Test
    public void testGetPris() {
        System.out.println("getPris");
        Pizza instance = new Pizza("2","Abdulla",8,"j");
        int expResult = 8;
        int result = instance.getPris();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of setPris method, of class Pizza.
     */
    @Test
    public void testSetPris() {
        System.out.println("setPris");
        int pris = 22;
        Pizza instance = new Pizza("22");
        instance.setPris(pris);
    
    }

    /**
     * Test of getIngredienser method, of class Pizza.
     */
    @Test
    public void testGetIngredienser() {
        System.out.println("getIngredienser");
        Pizza instance = new Pizza("2","Abdulla",8,"oost");
        String expResult = "oost";
        String result = instance.getIngredienser();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIngredienser method, of class Pizza.
     */
    @Test
    public void testSetIngredienser() {
        System.out.println("setIngredienser");
        String ingredienser = "oost";
        Pizza instance = new Pizza("oost");
        instance.setIngredienser(ingredienser);
        
    }
    
}
