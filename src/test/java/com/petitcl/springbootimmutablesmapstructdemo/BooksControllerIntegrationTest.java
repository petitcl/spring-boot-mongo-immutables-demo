package com.petitcl.springbootimmutablesmapstructdemo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.repositories.CategoriesEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.fixtures.FixturesUtils;
import org.junit.jupiter.api.Assertions;
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
public class BooksControllerIntegrationTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private FixturesUtils fixturesUtils;

	@Autowired
	private CategoriesEntityRepository categoriesEntityRepository;

	@BeforeEach
	public void beforeEach() {
		fixturesUtils.upsertTestData();
	}

	@DisplayName("FindAllBooks - Success")
	@Test
	public void test_findAllBooks_success() {
		webTestClient.get()
			.uri("/books")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.books").exists()
			.jsonPath("$.books").value(hasSize(2));
	}

	@DisplayName("FindBookById - Success")
	@Test
	public void test_findBookById_success() {
		webTestClient.get()
			.uri("/books/1")
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists()
			.jsonPath("$.title").isEqualTo("The Lord of the Rings: The Fellowship of the Ring");
	}

	@DisplayName("FindBookById - Not Found")
	@Test
	public void test_findBookById_notFound() throws Exception {
		webTestClient.get()
			.uri("/books/4242")
			.exchange()
			.expectStatus()
			.isNotFound()
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("Book not found")
			.jsonPath("$.error").isEqualTo("Not Found");
	}

	@DisplayName("CreateBook - Success")
	@Test
	public void test_createBook_success() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("title", "The Lord of the Rings: The Two Towers")
			.put("authorId", "1")
			.put("categoryIds", ImmutableList.of("1", "2"))
			.build();
		webTestClient.post()
			.uri("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isOk()
			.expectBody()
			.jsonPath("$.id").exists()
			.jsonPath("$.rating").isEqualTo(0);
	}

	@DisplayName("CreateBook - Increment categories book count")
	@Test
	public void test_createBook_incrementCategoriesBookCount() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("title", "The Lord of the Rings: The Two Towers")
			.put("authorId", "1")
			.put("categoryIds", ImmutableList.of("1", "2"))
			.build();
		webTestClient.post()
			.uri("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isOk();
		final CategoryEntity category1 = categoriesEntityRepository.findById("1")
			.block();
		final CategoryEntity category2 = categoriesEntityRepository.findById("2")
			.block();
		Assertions.assertNotNull(category1, "Category 1 exists");
		Assertions.assertNotNull(category2, "Category 2 exists");
		Assertions.assertEquals(2, category1.getNumberOfBooks(), "Category 1 number of books is updated");
		Assertions.assertEquals(2, category2.getNumberOfBooks(), "Category 2 number of books is updated");
	}

	@DisplayName("CreateBook - Author Not Found")
	@Test
	public void test_createBook_authorNotFound() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("title", "The Lord of the Rings: The Two Towers")
			.put("authorId", "4242")
			.put("categoryIds", ImmutableList.of("1", "2"))
			.build();
		webTestClient.post()
			.uri("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isNotFound()
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("Author not found")
			.jsonPath("$.error").isEqualTo("Not Found");
	}

	@DisplayName("CreateBook - Category Not Found")
	@Test
	public void test_createBook_categoryNotFound() {
		final Map<String, Object> body = ImmutableMap.<String, Object>builder()
			.put("title", "The Lord of the Rings: The Two Towers")
			.put("authorId", "1")
			.put("categoryIds", ImmutableList.of("1", "4242"))
			.build();
		webTestClient.post()
			.uri("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(body))
			.exchange()
			.expectStatus()
			.isNotFound()
			.expectBody()
			.jsonPath("$.status").isEqualTo(404)
			.jsonPath("$.message").isEqualTo("Category not found")
			.jsonPath("$.error").isEqualTo("Not Found");
	}

	@DisplayName("CreateBook - Validation Error")
	@Test
	public void test_createBook_validationError() {
		webTestClient.post()
			.uri("/books")
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
