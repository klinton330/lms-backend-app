package com.app.lms.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.lms.payload.auth.LoginRequest;
import com.app.lms.payload.auth.UserInfoResponse;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.RoleRepository;
import com.app.lms.security.jwt.JWTUtils;
import com.app.lms.security.service.UserDetailsImpl;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	//@Autowired
	//private EmployeeRespository userRepository;
	//@Autowired
	//private RoleRepository roleRepository;
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTUtils jwtutils;

	@PostMapping("/login")
	public ResponseEntity<UserInfoResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
		String endpoint = ServletUriComponentsBuilder.fromRequestUri(request).build().toUriString();
		System.out.println("EndPoint:"+endpoint);
		// logger.info("API Endpoint:" + endpoint);
		// logger.info("Login Request" + loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		// authentication.getPrincipal(); is type of UserDetails
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		// logger.info("UserDetails:" + userDetails);
		ResponseCookie jwtCookie = jwtutils.generateJwtCookie(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getUsername(), roles));

	}
}
