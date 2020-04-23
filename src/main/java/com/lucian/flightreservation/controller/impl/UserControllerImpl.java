package com.lucian.flightreservation.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;
import com.lucian.flightreservation.repository.UserRepository;
import com.lucian.flightreservation.service.SecurityService;
import com.lucian.flightreservation.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "User Management System", description = "User Management System")
public class UserControllerImpl {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@ApiOperation(value = "View a list of available users")
	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());

	}

	@ApiOperation(value = "Get user by ID")
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		return new ResponseEntity<UserDto>(user.toDto(), HttpStatus.OK);
	}

	@ApiOperation(value = "Update user")
	@PutMapping
	public ResponseEntity<UserDto> updateFlight(@RequestBody @Valid User user) {
		user.setPassword(encoder.encode(user.getPassword()));

		User userEmail = userRepository.findByEmail(user.getEmail());

		if (userEmail == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		userService.updateUser(user);
		return new ResponseEntity<UserDto>(user.toDto(), HttpStatus.ACCEPTED);
	}

	@ApiOperation(value = "Delete user")
	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
		User user = userService.getUser(id);

		if (user == null) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

	}

	@ApiOperation(value = "Register User")
	@PostMapping("/registerUser")
	public ResponseEntity<UserDto> register(@RequestBody @Valid User user) {
		return ResponseEntity.ok(securityService.register(user));

	}

	@ApiOperation(value = "Login User")
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody @Valid User user) {
		boolean loginResponse = securityService.login(user.getEmail(), user.getPassword());

		if (loginResponse) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return errors;
	}
}