package org.sdm.slingdemo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.auth.core.spi.AbstractAuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.osgi.service.component.ComponentContext;

@Component(metatype = false, label = "Demo Authentication Handler", description = "Demo Authentication Handler")
@Service(value = AuthenticationHandler.class)
@Properties({
		@Property(name = "authtype", value = "demo", propertyPrivate = true),
		@Property(name = "path", value = { "/content/connect", "/" }) })
public class DemoAuthenticationHandler extends AbstractAuthenticationHandler {

	public DemoAuthenticationHandler() {
		super();
	}

	@Override
	public void dropCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public AuthenticationInfo extractCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean requestCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
	}

}
