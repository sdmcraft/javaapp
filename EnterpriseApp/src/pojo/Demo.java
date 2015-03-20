package pojo;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import ejb.session.stateless.SessionBeanRemote;

public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Properties properties = new Properties();
		properties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		properties.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
		InitialContext jndiContext = new InitialContext(properties);		
		//SessionBeanLocal bean = (SessionBeanLocal)jndiContext.lookup("EnterpriseApp/SessionBean/local");
		SessionBeanRemote bean = (SessionBeanRemote)jndiContext.lookup("EnterpriseApp/SessionBean/remote");
		bean.method();
	}

}
