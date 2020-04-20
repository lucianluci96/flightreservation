package com.lucian.flightreservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;
import com.lucian.flightreservation.repository.UserRepository;
import com.lucian.flightreservation.service.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserDetailsServiceImp userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean login(String username, String password) {

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());

		authenticationManager.authenticate(token);

		boolean result = token.isAuthenticated();

		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}

		return result;
	}

	@Override
	public UserDto register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));

		UserDto result = userRepository.save(user).toDto();
		return result;
	}

}
