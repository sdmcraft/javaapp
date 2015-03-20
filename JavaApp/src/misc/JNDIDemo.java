package misc;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name  = "/media/DATAPART1/sdm";
		try {			
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, 
			    "com.sun.jndi.fscontext.RefFSContextFactory");


		    // Create the initial context
		    Context ctx = new InitialContext(env);

		    // Look up an object
		    Object obj = ctx.lookup(name);

		    // Print it
		    System.out.println(name + " is bound to: " + obj);
			    
		} catch (NamingException e) {
		    System.err.println("Problem looking up " + name + ": " + e);
		}

	}

}
