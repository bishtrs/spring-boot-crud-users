package com.bisht.example.service;

import java.util.Collection;
import java.util.Optional;

import com.bisht.example.model.User;

public interface UserService {

	Collection<User> findAll();

	Optional<User> find(Long id);
	
	User create(User user);

	User updateUser(Long userId, User user);

	void delete(Long id);

}
