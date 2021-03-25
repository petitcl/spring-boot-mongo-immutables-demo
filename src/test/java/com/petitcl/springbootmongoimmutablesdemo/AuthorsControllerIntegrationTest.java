package com.petitcl.springbootmongoimmutablesdemo;

import com.google.common.collect.ImmutableMap;
import com.petitcl.springbootmongoimmutablesdemo.fixtures.FixturesUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.StringContains.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class AuthorsControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private FixturesUtils fixturesUtils;

	@BeforeEach
	public void beforeEach() {
		fixturesUtils.upsertTestData().block();
	}

	@DisplayName("FindAllAuthors - Success")
	@Test
	public void test_findAllAuthors_success() {
		webTestClient.get()
			.uri("/authors")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.authors").exists()
			.jsonPath("$.authors").value(hasSize(3));
	}

	@DisplayName("FindAuthorById - Success")
	@Test
	public void test_findAuthorById_success() {
		webTestClient.get()
			.uri("/authors/1")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists()
			.jsonPath("$.lastName").isEqualTo("Tolkien");
	}

	@DisplayName("FindAuthorById - Not Found")
	@Test
	public void test_findAuthorById_notFound() throws Exception {
		webTestClient.get()
			.uri("/authors/4242")
			.exchange()
			.expectStatus()
			.isNotFound()
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("Author not found")
			.jsonPath("$.error").isEqualTo("Not Found");
	}

	@DisplayName("CreateAuthor - Success - with death date")
	@Test
	public void test_createAuthor_successWithDeathDate() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("firstName", "Philip K.")
			.put("lastName", "Dick")
			.put("dateOfBirth", "1928-12-16")
			.put("dateOfDeath", "1982-03-02")
			.build();
		webTestClient.post()
			.uri("/authors")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists();
	}

	@DisplayName("CreateAuthor - Success - without death date")
	@Test
	public void test_createAuthor_successWithoutDeathDate() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("firstName", "Stephen")
			.put("lastName", "King")
			.put("dateOfBirth", "1947-12-21")
			.build();
		webTestClient.post()
			.uri("/authors")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists();
	}

	@DisplayName("CreateAuthor - Validation Error")
	@Test
	public void test_createAuthor_validationError() {
		webTestClient.post()
			.uri("/authors").contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue("{}"))
			.exchange()
			.expectStatus()
			.isBadRequest()
			.expectBody()
			.jsonPath("$.status").isEqualTo(400)
			.jsonPath("$.errors").isArray()
			.jsonPath("$.message").value(containsString("Validation failed"))
			.jsonPath("$.error").isEqualTo("Bad Request");
	}

}
