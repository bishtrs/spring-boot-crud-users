package com.bisht.example.service.impl;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bisht.example.exception.UserNotFoundException;
import com.bisht.example.model.User;
import com.bisht.example.repository.UserRepository;
import com.bisht.example.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> find(Long id) {
		return Optional.ofNullable(userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id "+ id)));
	}
	
	@Override
	public User create(User user) {
		return userRepository.save(user);
	}


	@Override
	public User updateUser(Long userId, User updatedUser) {
		User user = find(userId).orElse(null);
		if (Objects.isNull(user)) {
			LOGGER.error("User not found for userId {} ", userId);
			throw new UserNotFoundException("User not found with id "+ userId);
		}
		updatedUser.setCreatedDate(user.getCreatedDate());
		updatedUser.setId(userId);
		LOGGER.info("Updating user with new details {}", updatedUser);
		return userRepository.save(updatedUser);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

}
