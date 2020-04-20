package com.lucian.flightreservation.service;

import java.util.List;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;

public interface UserService {

	List<UserDto> getUsers();

	User getUser(long id);

	User updateUser(User user);

	void deleteUser(long id);
}
