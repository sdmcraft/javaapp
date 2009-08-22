package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DemoFilter implements Filter {

	ServletContext ctx;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		ctx.setAttribute("hitCount", Integer.toString((Integer.parseInt(ctx
				.getAttribute("hitCount").toString()) + 1)));
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		ctx = config.getServletContext();
		if (ctx.getAttribute("hitCount") == null)
			ctx.setAttribute("hitCount", "0");

	}

}
