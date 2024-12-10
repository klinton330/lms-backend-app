package com.app.lms.security.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		final Map<String, Object> body = new HashMap<>();
		if ("Full authentication is required to access this resource".equals(authException.getMessage())) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
			body.put("error", "Unauthorized");
			body.put("message", authException.getMessage());
			body.put("path", request.getServletPath());
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			body.put("status", HttpServletResponse.SC_FORBIDDEN);
			body.put("error", "Forbidden");
			body.put("message", authException.getMessage());
			body.put("path", request.getServletPath());

		}

		//logger.error(body);
		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);

	}

}
