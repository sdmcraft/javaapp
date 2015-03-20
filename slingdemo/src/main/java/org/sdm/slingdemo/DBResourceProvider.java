package org.sdm.slingdemo;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceProvider;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.framework.BundleContext;


@Component(name = "DBResourceProvider",
label = "DBResourceProvider", description = "Sample DB Resource Provider", configurationFactory = true)
@Service
@Properties({
    @Property(name = "service.description", value = "Sample DB Resource Provider")
    , @Property(name = "service.vendor", value = "org.sdm.slingdemo")
    , @Property(name = ResourceProvider.ROOTS, value = "/content/mynamespace/products")
    , @Property(name = "jdbc.url", value = "jdbc:mysql://localhost:3306/sling-test")
    , @Property(name = "jdbc.user", value = "root")
    , @Property(name = "jdbc.pass", value = "welcome")
    , @Property(name = SlingConstants.PROPERTY_RESOURCE_TYPE, value = "/apps/dbprovider/dbprovider.jsp")
})
public class DBResourceProvider implements ResourceProvider
{
	 protected void activate(BundleContext bundleContext, Map<?, ?> props) throws SQLException {
		 
	        providerRoot = props.get(ROOTS).toString();
	        resourceType = props.get(PROPERTY_RESOURCE_TYPE).toString();
	 
	        this.providerRootPrefix = providerRoot.concat("/");
	     
	        this.ds = JdbcConnectionPool.create(props.get("jdbc.url").toString(), props.get("jdbc.user").toString(), props.get("jdbc.pass").toString());
	 
	        log.info("providerRoot: "+providerRoot);
	        log.info("providerRootPrefix: "+providerRootPrefix);
	        log.info("resourceType: "+resourceType);
	        log.info("H2 connection pool: "+ds);
	   }
	   
	    protected void deactivate() throws SQLException {
	     
	        this.ds.dispose();
	        this.ds = null;
	        this.providerRoot = null;
	        this.providerRootPrefix = null;
	        this.resourceType = null;
	   }
    @Override
    public Resource getResource(ResourceResolver arg0, String arg1)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Resource getResource(ResourceResolver arg0, HttpServletRequest arg1, String arg2)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Resource> listChildren(Resource arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
