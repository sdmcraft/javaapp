package org.sdm.slingdemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FelixFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException {
		System.out.println("FelixFilter-------->doFilter");
		filterChain.doFilter(req, resp);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("FelixFilter-------->doInit");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
