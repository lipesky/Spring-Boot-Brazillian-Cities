package com.example.evoluum1.interceptors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor{

	private final Logger apiLog = Logger.getLogger(LoggerInterceptor.class.getName());
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		final String time = (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date());
		apiLog.info(String.format("Received request for '%s' from %s at %s", request.getRequestURI(), request.getRemoteAddr(), time));
		return true;
	}
	
}

