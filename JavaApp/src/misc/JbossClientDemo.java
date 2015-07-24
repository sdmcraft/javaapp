package misc;

import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;

//import org.sdm.timerecord.business.Context;
//import org.sdm.timerecord.business.ContextFactory;
//import org.sdm.timerecord.business.action.UpdatePrincipalRemote;


public class JbossClientDemo implements Serializable
{
    public static void main(String[] args) throws Exception
    {
    	
//    public static void main(String[] args) throws Exception
//    {
//
//        Properties properties = new Properties();
//        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
//        properties.put(javax.naming.Context.PROVIDER_URL, "jnp://127.0.0.1:1099");
//
//        InitialContext initialContext = new InitialContext(properties);
//        ContextFactory contextFactory = (ContextFactory) initialContext.lookup("ContextFactoryImpl/remote");
//        Principal principal = new MyPrincipal();
//
//        List<Principal> principalList = new ArrayList<Principal>();
//        principalList.add(principal);
//
//        Context context = contextFactory.getContext(null);
//
//        UpdatePrincipalRemote updatePrincipalRemote = (UpdatePrincipalRemote) initialContext.lookup("UpdatePrincipal/remote");
//        Map<String, String[]> params = new HashMap<String, String[]>();
//        params.put("name", new String[] { "client2" });
//        updatePrincipalRemote.execute(params, context);
    }
//    }
}


