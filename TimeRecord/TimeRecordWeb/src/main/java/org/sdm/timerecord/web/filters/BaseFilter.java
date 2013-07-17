package org.sdm.timerecord.web.filters;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.sdm.timerecord.business.Context;
import org.sdm.timerecord.business.ContextFactory;

public class BaseFilter implements Filter {

	private InitialContext initialContext;
	private String jndiProvider;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		Principal principal = ((HttpServletRequest) req).getUserPrincipal();
		try {
			ContextFactory contextFactory = (ContextFactory) initialContext
					.lookup("TimeRecordEnterprise-1.0/ContextFactory/remote");
			List<Principal> principalList = new ArrayList<Principal>();
			principalList.add(principal);
			Context context = contextFactory.getContext(principalList);
			((HttpServletRequest) req).getSession().setAttribute("Context",
					context);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

		chain.doFilter(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			jndiProvider = filterConfig.getServletContext().getInitParameter(
					"jndi-provider");
			Properties properties = new Properties();
			properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(javax.naming.Context.PROVIDER_URL, jndiProvider);
			initialContext = new InitialContext(properties);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}

}
