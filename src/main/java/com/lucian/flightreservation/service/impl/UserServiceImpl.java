package com.lucian.flightreservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;
import com.lucian.flightreservation.repository.UserRepository;
import com.lucian.flightreservation.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDto> getUsers() {
		List<UserDto> users = new ArrayList<>();
		for (User user : userRepository.findAll())
			users.add(user.toDto());

		return users;
	}

	@Override
	public User getUser(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

}
