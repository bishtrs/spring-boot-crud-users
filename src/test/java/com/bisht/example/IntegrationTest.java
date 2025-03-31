package com.bisht.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.bisht.example.model.User;
import com.fasterxml.jackson.databind.JsonNode;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Profile("test")
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	@LocalServerPort
	private int localServerPort;

	@Autowired
	private TestRestTemplate restTemplate;

	private String baseUrl;

	@BeforeEach
	public void setUp() {
		baseUrl = "http://127.0.0.1:" + localServerPort + "/api/v1/users";
	}

	@Test
	@Order(1)
	public void testCreateUsers() {
		User user1 = createUser();
		ResponseEntity<User> result = restTemplate.postForEntity(baseUrl, user1, User.class);
		assertNotNull(result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("John", result.getBody().getFirstName());
	}

	@Test
	@Order(2)
	public void testGetAllusers() {
		ResponseEntity<JsonNode> result = restTemplate.withBasicAuth("user", "password")
				.getForEntity(baseUrl, JsonNode.class);
		assertNotNull(result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(1, result.getBody().size());
	}

	@Test
	@Order(3)
	public void testGetUserbyId() {
		ResponseEntity<User> result = restTemplate.getForEntity(baseUrl + "/1", User.class);
		assertNotNull(result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals(result.getBody().getId(), 1);
	}

	@Test
	@Order(4)
	public void testUpdateUser() {
		User user = restTemplate.getForEntity(baseUrl + "/1", User.class).getBody();
		user.setEmail("john.bocelli2@wellsfargo.com");
		restTemplate.put(baseUrl + "/1", user);
		ResponseEntity<User> result = restTemplate.getForEntity(baseUrl + "/1", User.class);
		assertNotNull(result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("john.bocelli2@wellsfargo.com", result.getBody().getEmail());
	}

	@Test
	@Order(5)
	public void testDeleteUser() {
		restTemplate.delete(baseUrl + "/1");
		try {
			restTemplate.getForEntity(baseUrl + "/1", User.class).getBody();
		} catch (HttpClientErrorException  e) {
			assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}

	private User createUser() {
		User user1 = new User();
		user1.setId(1);
		user1.setFirstName("John");
		user1.setLastName("Bocelli");
		user1.setEmail("john.bocelli@wellsfargo.com");
		return user1;
	}

}
