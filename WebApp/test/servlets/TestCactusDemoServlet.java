package servlets;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.ServletTestCase;
import org.apache.cactus.WebRequest;

public class TestCactusDemoServlet extends ServletTestCase{
	 public TestCactusDemoServlet(String theName)
	    {
	        super(theName);
	    }

	    public static Test suite()
	    {
	        return new TestSuite(TestCactusDemoServlet.class);
	    }

	    public void beginSaveToSessionOK(WebRequest webRequest)
	    {
	        webRequest.addParameter("testparam", "it works!");
	    }

	    public void testSaveToSessionOK()
	    {
	    	CactusDemoServlet servlet = new CactusDemoServlet();
	        servlet.doGet(request);
	        assertEquals("it works!", session.getAttribute("testAttribute"));
	    }
}
