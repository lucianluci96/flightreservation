package com.lucian.flightreservation.service;

import com.lucian.flightreservation.dto.UserDto;
import com.lucian.flightreservation.entities.User;

public interface SecurityService {

	boolean login(String username, String password);

	UserDto register(User user);
}
