package com.bisht.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.bisht.example.model.User;
import com.bisht.example.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private UserService userService;

	@Test
	void testGetAllusers() throws Exception {
		when(userService.findAll()).thenReturn(createUsers());
		mockMvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("John")))
				.andExpect(jsonPath("$[0].lastName", is("Bocelli")));
	}
	
	@Test
	void testGetUserById() throws Exception {
		when(userService.find(2l)).thenReturn(Optional.of(createUsers().get(1)));
		mockMvc.perform(get("/api/v1/users/2"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.firstName", is("Lou")))
				.andExpect(jsonPath("$.lastName", is("Vincent")));
	}
	
	@Test
	void testCreateUser() throws Exception {
		when(userService.create(any())).thenReturn(createUsers().get(0));
		mockMvc.perform(post("/api/v1/users")
		 		.contentType(MediaType.APPLICATION_JSON)
		 		.content(objectMapper.writeValueAsString(createUsers().get(0))))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("John")))
				.andExpect(jsonPath("$.lastName", is("Bocelli")));
	}
	
	@Test
	void testUpdateUser() throws Exception {
		when(userService.find(1l)).thenReturn(Optional.of(createUsers().get(0)));
		when(userService.updateUser(any(), any())).thenReturn(createUsers().get(0));
		mockMvc.perform(put("/api/v1/users/1")
		 		.contentType(MediaType.APPLICATION_JSON)
		 		.content(objectMapper.writeValueAsString(createUsers().get(0))))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("John")))
				.andExpect(jsonPath("$.lastName", is("Bocelli")));
	}
	
	@Test
	void testDeleteUser() throws Exception {
		when(userService.find(1l)).thenReturn(Optional.of(createUsers().get(0)));
		mockMvc.perform(delete("/api/v1/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.firstName", is("John")))
				.andExpect(jsonPath("$.lastName", is("Bocelli")));
	}
	
	private List<User> createUsers() {
		User user1 = new User();
		user1.setId(1);
		user1.setFirstName("John");
		user1.setLastName("Bocelli");
		User user2 = new User();
		user2.setId(2);
		user2.setFirstName("Lou");
		user2.setLastName("Vincent");
		return List.of(user1, user2);
	}

}
