package com.petitcl.springbootimmutablesmapstructdemo.commons.configurations;

import com.google.common.collect.ImmutableList;
import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.AuthorEntity;
import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.ImmutableAuthorEntity;
import com.petitcl.springbootimmutablesmapstructdemo.authors.repositories.AuthorsEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.books.entities.*;
import com.petitcl.springbootimmutablesmapstructdemo.books.repositories.BooksEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.ImmutableCategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.repositories.CategoriesEntityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Profile("!test")
@RequiredArgsConstructor
@Configuration
public class DevDataConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(DevDataConfiguration.class);

	private final BooksEntityRepository booksEntityRepository;
	private final CategoriesEntityRepository categoriesEntityRepository;
	private final AuthorsEntityRepository authorsEntityRepository;

	@PostConstruct
	public void init() {
		final List<CategoryEntity> categoryEntities = makeCategories();
		final List<AuthorEntity> authorEntities = makeAuthors();
		Mono.zip(
			categoriesEntityRepository.deleteAll()
				.then(
					categoriesEntityRepository.saveAll(categoryEntities)
						.collectList()
				),
			authorsEntityRepository.deleteAll()
				.then(
					authorsEntityRepository.saveAll(authorEntities)
						.collectList()
				)
		)
			.flatMap(
				(t) -> booksEntityRepository.deleteAll()
					.then(
						booksEntityRepository.saveAll(makeBooks(t.getT1(), t.getT2()))
							.collectList()
					)
			)
			.subscribe(
				(x) -> LOGGER.info("successfully inserted dev data"),
				(e) -> LOGGER.error("Error occurred during dev data insertion", e)
			);
	}

	private List<AuthorEntity> makeAuthors() {
		return ImmutableList.of(
			ImmutableAuthorEntity.builder()
				.firstName("J. R. R.")
				.lastName("Tolkien")
				.dateOfBirth(LocalDate.of(1892, 1, 3))
				.dateOfDeath(LocalDate.of(1973, 9, 2))
				.build(),
			ImmutableAuthorEntity.builder()
				.firstName("J. K.")
				.lastName("Rowling")
				.dateOfBirth(LocalDate.of(1965, 7, 31))
				.build(),
			ImmutableAuthorEntity.builder()
				.firstName("Isaac")
				.lastName("Asimov")
				.dateOfBirth(LocalDate.of(1920, 1, 2))
				.dateOfDeath(LocalDate.of(1992, 4, 6))
				.build()
		);
	}

	private List<CategoryEntity> makeCategories() {
		return ImmutableList.of(
			ImmutableCategoryEntity.builder()
				.name("Heroic Fantasy")
				.numberOfBooks(1L)
				.build(),
			ImmutableCategoryEntity.builder()
				.name("Classics")
				.numberOfBooks(1L)
				.build(),
			ImmutableCategoryEntity.builder()
				.name("Science Fiction")
				.numberOfBooks(1L)
				.build()
		);
	}

	private List<BookEntity> makeBooks(List<CategoryEntity> categoryEntities, List<AuthorEntity> authorEntities) {
		final var categoryIdsByName = categoryEntities.stream()
			.collect(Collectors.toMap(CategoryEntity::getName, CategoryEntity::getId));
		final var authorsIdsByLastName = authorEntities.stream()
			.collect(Collectors.toMap(AuthorEntity::getLastName, AuthorEntity::getId));
		return ImmutableList.of(
			ImmutableBookEntity.builder()
				.title("The Lord of the Rings: The Fellowship of the Ring")
				.author(
					ImmutableBookAuthorEntity.builder()
						.id(authorsIdsByLastName.get("Tolkien"))
						.fullName("J. R. R. Tolkien")
						.build()
				)
				.addCategories(
					ImmutableBookCategoryEntity.builder()
						.id(categoryIdsByName.get("Heroic Fantasy"))
						.name("Heroic Fantasy")
						.build(),
					ImmutableBookCategoryEntity.builder()
						.id(categoryIdsByName.get("Classics"))
						.name("Classics")
						.build()
				)
				.rating(4.23f)
				.build()
		);
	}

}
