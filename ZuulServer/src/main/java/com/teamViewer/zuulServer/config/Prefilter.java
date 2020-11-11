package com.teamViewer.zuulServer.config;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpHeaders;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class Prefilter extends ZuulFilter {
	private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("do PreFilter ");

		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		ctx.setSendZuulResponse(false);
		ctx.setResponseBody("API key not authorized");
		ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
		ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
		return null;
	}
}
