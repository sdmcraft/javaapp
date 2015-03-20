package org.sdm.slingdemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;

@Component
@Service(value = Filter.class)
@Properties({ @Property(name = "sling.filter.scope", value = "REQUEST"),
		@Property(name = "service.ranking", value = "100"),
		@Property(name = "pattern", value = "/mynode.json") })
public class SlingFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("SlingFilter-------->doFilter");
		filterChain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("SlingFilter-------->doInit");

	}

}
