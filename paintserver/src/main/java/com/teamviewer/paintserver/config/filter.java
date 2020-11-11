package com.teamviewer.paintserver.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class filter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
		IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "http://52.188.32.192:5555");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");     //허용할 request http METHOD : POST, GET, DELETE, PUT
		//response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");    //요청 허용 헤더(ajax : X-Requested-With)
		response.setHeader("Access-Control-Allow-Credentials", "true");
		// (cf. 요청 허용 헤더 : "Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization")
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {

	}
}
