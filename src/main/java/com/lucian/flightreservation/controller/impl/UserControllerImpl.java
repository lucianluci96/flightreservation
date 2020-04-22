package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;
import com.lucian.flightreservation.repository.UserRepository;
import com.lucian.flightreservation.service.SecurityService;
import com.lucian.flightreservation.service.UserService;

@RestController
@RequestMapping("/users")
public class UserControllerImpl {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<UserDto>(user.toDto(), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<UserDto> updateFlight(@RequestBody User user) {	
		user.setPassword(encoder.encode(user.getPassword()));
		
		User userEmail = userRepository.findByEmail(user.getEmail());

		if (userEmail == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		userService.updateUser(user);
		return new ResponseEntity<UserDto>(user.toDto(), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

	@PostMapping("/registerUser")
	public ResponseEntity<UserDto> register(@RequestBody User user) {
		return ResponseEntity.ok(securityService.register(user));

	}

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody User user) {
		boolean loginResponse = securityService.login(user.getEmail(), user.getPassword());

		if (loginResponse) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}
}