package com.bisht.example.controller;

import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.bisht.example.model.User;
import com.bisht.example.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User", description = "Users Api")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Operation(summary = "Fetch all users", description = "Fetches all users from data source")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping
	public Collection<User> getAllUsers() {
		return userService.findAll();
	}

	@Operation(summary = "Fetch a user by its id", description = "Fetches a users from data source by his id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
		LOGGER.info("Getting user for userId {} ", userId);
		return userService.find(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Create a new user", description = "Create a new user in backend")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@PostMapping
	public User createUser(@Valid @RequestBody User user) {
		return userService.create(user);
	}

	@Operation(summary = "Updates a user", description = "Updates a user details in backend")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "When user does not exist") })
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) {
		User updatedUser = userService.updateUser(userId, userDetails);
		LOGGER.info("User updated for userId {} ", userId);
		return ResponseEntity.ok(updatedUser);
	}

	@Operation(summary = "Deletes a user", description = "Deletes a user in backend")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "successful operation") })
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long userId) throws Exception {
		User user = userService.find(userId).orElse(null);
		if (Objects.isNull(user)) {
			return ResponseEntity.notFound().build();
		}

		userService.delete(userId);
		return ResponseEntity.ok(user);
	}

}
