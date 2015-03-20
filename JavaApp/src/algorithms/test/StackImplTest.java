/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.StackImpl;

/**
 *
 * @author satyam
 */
public class StackImplTest extends TestCase{

    public StackImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        try {
            new StackImpl(-1);
        } catch (Exception ex) {
            assertEquals("Size cannot be < 0".equals(ex.getMessage()), true);
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of pop method, of class StackImpl.
     */
    @Test
    public void testPop() throws Exception {
        StackImpl instance = new StackImpl(0);
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Nothing to pop!!!".equals(ex.getMessage()), true);
        }
        
        instance = new StackImpl(1);
        instance.push(1);
        assertEquals(1, instance.pop());
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Nothing to pop!!!".equals(ex.getMessage()), true);
        }

        instance = new StackImpl(2);
        instance.push(1);
        instance.push(2);
        assertEquals(2, instance.pop());
        assertEquals(1, instance.pop());
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Nothing to pop!!!".equals(ex.getMessage()), true);
        }        
    }

    /**
     * Test of push method, of class StackImpl.
     */
    @Test
    public void testPush() throws Exception {
        StackImpl instance = new StackImpl(0);
        try {
            instance.push(1);
        } catch (Exception ex) {
            assertEquals("Stack full. Cannot push!!!".equals(ex.getMessage()), true);
        }
        instance = new StackImpl(1);
        instance.push(1);
        try {
            instance.push(2);
        } catch (Exception ex) {
            assertEquals("Stack full. Cannot push!!!".equals(ex.getMessage()), true);
        }
    }
    /**
     * Test of peek method, of class StackImpl.
     */
    @Test
    public void testPeek() throws Exception {        
        StackImpl instance = new StackImpl(0);
        try {
            instance.peek();
        } catch (Exception ex) {
            assertEquals("Nothing to peek!!!".equals(ex.getMessage()), true);
        }
        
        instance = new StackImpl(1);
        instance.push(1);
        assertEquals(1, instance.peek());
        assertEquals(1, instance.pop());
        try {
            instance.peek();
        } catch (Exception ex) {
            assertEquals("Nothing to peek!!!".equals(ex.getMessage()), true);
        }

        instance = new StackImpl(2);
        instance.push(1);
        instance.push(2);
        assertEquals(2, instance.peek());
        assertEquals(2, instance.pop());
        assertEquals(1, instance.peek());        
        assertEquals(1, instance.pop());
        try {
            instance.peek();
        } catch (Exception ex) {
            assertEquals("Nothing to peek!!!".equals(ex.getMessage()), true);
        }        
    }
    /**
     * Test of isEmpty method, of class StackImpl.
     */
    @Test
    public void testIsEmpty() throws Exception{        
        StackImpl instance = new StackImpl(0);
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        
        instance = new StackImpl(1);
        expResult = false;
        instance.push(1);
        result = instance.isEmpty();
        assertEquals(expResult, result);        
        
        expResult = true;
        instance.pop();
        result = instance.isEmpty();
        assertEquals(expResult, result);                
        
    }
}