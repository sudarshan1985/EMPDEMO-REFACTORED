package com.company.app.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * HTTP request filter.
 */
public class ValidationFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//NO-OP on purpose.
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new ValidatingHttpRequest((HttpServletRequest) request), response);
	}

	@Override
	public void destroy() {
		//NO-OP on purpose.
	}

}
