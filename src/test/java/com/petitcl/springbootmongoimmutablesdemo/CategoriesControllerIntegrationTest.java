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
public class CategoriesControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private FixturesUtils fixturesUtils;

	@BeforeEach
	public void beforeEach() {
		fixturesUtils.upsertTestData().block();
	}

	@DisplayName("FindAllCategories - Success")
	@Test
	public void test_findAllCategories_success() {
		webTestClient.get()
			.uri("/categories")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.categories").exists()
			.jsonPath("$.categories").value(hasSize(3));
	}

	@DisplayName("FindCategoryById - Success")
	@Test
	public void test_findCategoryById_success() {
		webTestClient.get()
			.uri("/categories/1")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists()
			.jsonPath("$.name").isEqualTo("Heroic Fantasy");
	}

	@DisplayName("FindCategoryById - Not Found")
	@Test
	public void test_findCategoryById_notFound() throws Exception {
		webTestClient.get()
			.uri("/categories/4242")
			.exchange()
			.expectStatus()
			.isNotFound()
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("Category not found")
			.jsonPath("$.error").isEqualTo("Not Found");
	}

	@DisplayName("CreateCategory - Success")
	@Test
	public void test_createCategory_success() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("name", "Nonfiction")
			.build();
		webTestClient.post()
			.uri("/categories")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists()
			.jsonPath("$.numberOfBooks").isEqualTo(0);
	}

	@DisplayName("CreateCategory - Validation Error")
	@Test
	public void test_createCategory_validationError() {
		webTestClient.post()
			.uri("/categories")
			.contentType(MediaType.APPLICATION_JSON)
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
