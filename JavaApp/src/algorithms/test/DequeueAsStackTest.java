package algorithms.test;

import org.junit.Test;

import algorithms.DequeueAsStack;
import algorithms.StackImpl;
import junit.framework.TestCase;

public class DequeueAsStackTest extends TestCase {
	@Test
    public void testPop() throws Exception {
        DequeueAsStack instance = new DequeueAsStack(0);
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Queue empty!!!".equals(ex.getMessage()), true);
        }
        
        instance = new DequeueAsStack(1);
        instance.push(1);
        assertEquals(1, instance.pop());
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Queue empty!!!".equals(ex.getMessage()), true);
        }

        instance = new DequeueAsStack(2);
        instance.push(1);
        instance.push(2);
        assertEquals(2, instance.pop());
        assertEquals(1, instance.pop());
        try {
            instance.pop();
        } catch (Exception ex) {
            assertEquals("Queue empty!!!".equals(ex.getMessage()), true);
        }        
    }
	
	 @Test
	    public void testPush() throws Exception {
		 DequeueAsStack instance = new DequeueAsStack(0);
	        try {
	            instance.push(1);
	        } catch (Exception ex) {
	            assertEquals("Queue full!!!".equals(ex.getMessage()), true);
	        }
	        instance = new DequeueAsStack(1);
	        instance.push(1);
	        try {
	            instance.push(2);
	        } catch (Exception ex) {
	            assertEquals("Queue full!!!".equals(ex.getMessage()), true);
	        }
	    }
}
