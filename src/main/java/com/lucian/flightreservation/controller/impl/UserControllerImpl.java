package com.lucian.flightreservation.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.lucian.flightreservation.service.SecurityService;
import com.lucian.flightreservation.service.UserService;

@RestController
@RequestMapping("/users")
public class UserControllerImpl {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@GetMapping
	public ResponseEntity<List<UserDto>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable long id) {
		return ResponseEntity.ok(userService.getUser(id).toDto());
	}

	@PutMapping
	public ResponseEntity<UserDto> updateFlight(@RequestBody User user) {
		return ResponseEntity.ok(userService.updateUser(user).toDto());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
		userService.deleteUser(id);

		return ResponseEntity.ok().build();
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
			return ResponseEntity.notFound().build();
		}

	}
}