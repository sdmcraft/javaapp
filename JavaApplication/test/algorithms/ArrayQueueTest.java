/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algorithms;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author satyam
 */
public class ArrayQueueTest {

    public ArrayQueueTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEquals()
    {
        ArrayQueue instance = new ArrayQueue(10);
        ArrayQueue instance2 = new ArrayQueue(10);
        assertEquals(false,instance.equals(null));        
        assertEquals(true,instance.equals(instance));
        assertEquals(true,instance.equals(instance2));
        assertEquals(false,instance.equals("a"));     
        instance.insert("a");
        instance.insert("b");
        instance.insert("c");
        instance.remove();
        instance2.insert("a");
        instance2.insert("a1");        
        instance2.insert("b");
        instance2.insert("c");
        instance2.remove();
        instance2.remove();
        assertEquals(false,instance.equals(instance2));     
        instance.flush();
        instance2.flush();
        instance.insert("a");
        instance.insert("b");
        instance.insert("c");
        instance.remove();
        instance2.insert("a");        
        instance2.insert("b");
        instance2.insert("c");        
        instance2.remove();        
        assertEquals(true,instance.equals(instance2));             
        instance.flush();
        instance2.flush();
        
    }
    /**
     * Test of insert method, of class ArrayQueue.
     */
//    @Test
//    public void testInsert() {        
//        Object item = "item";
//        ArrayQueue instance = new ArrayQueue(1);
//        instance.insert(item);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class ArrayQueue.
//     */
//    @Test
//    public void testRemove() {
//        System.out.println("remove");
//        ArrayQueue instance = null;
//        Object expResult = null;
//        Object result = instance.remove();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of empty method, of class ArrayQueue.
//     */
//    @Test
//    public void testEmpty() {
//        System.out.println("empty");
//        ArrayQueue instance = null;
//        boolean expResult = false;
//        boolean result = instance.empty();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of display method, of class ArrayQueue.
//     */
//    @Test
//    public void testDisplay() {
//        System.out.println("display");
//        ArrayQueue instance = null;
//        instance.display();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getQueue method, of class ArrayQueue.
//     */
//    @Test
//    public void testGetQueue() {
//        System.out.println("getQueue");
//        ArrayQueue instance = null;
//        String expResult = "";
//        String result = instance.getQueue();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

}