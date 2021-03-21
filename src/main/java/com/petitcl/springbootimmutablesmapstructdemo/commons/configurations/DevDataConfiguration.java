package com.petitcl.springbootimmutablesmapstructdemo.commons.configurations;

import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.AuthorEntity;
import com.petitcl.springbootimmutablesmapstructdemo.authors.repositories.AuthorsEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.books.entities.BookEntity;
import com.petitcl.springbootimmutablesmapstructdemo.books.repositories.BooksEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.repositories.CategoriesEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Profile("!test")
@RequiredArgsConstructor
@Configuration
public class DevDataConfiguration {

	private final BooksEntityRepository booksEntityRepository;
	private final CategoriesEntityRepository categoriesEntityRepository;
	private final AuthorsEntityRepository authorsEntityRepository;

	@PostConstruct
	public void init() {
		final Flux<CategoryEntity> categoryEntities = makeCategories();
		final Flux<AuthorEntity> authorEntities = makeAuthors();
		Mono.zip(
			categoriesEntityRepository.deleteAll()
				.thenMany(categoriesEntityRepository.saveAll(categoryEntities))
				.collectList(),
			authorsEntityRepository.deleteAll()
				.thenMany(authorsEntityRepository.saveAll(authorEntities))
				.collectList()
		)
			.flatMap((t) -> {
				final Flux<BookEntity> books = makeBooks(t.getT1(), t.getT2());
				return booksEntityRepository.deleteAll()
					.thenMany(booksEntityRepository.saveAll(books))
					.collectList();
			})
			.subscribe();
	}

	private Flux<AuthorEntity> makeAuthors() {
		return Flux.just(
			AuthorEntity.builder()
				.firstName("J. R. R.")
				.lastName("Tolkien")
				.dateOfBirth(LocalDate.of(1892, 1, 3))
				.dateOfDeath(LocalDate.of(1973, 9, 2))
				.build(),
			AuthorEntity.builder()
				.firstName("J. K.")
				.lastName("Rowling")
				.dateOfBirth(LocalDate.of(1965, 7, 31))
				.build(),
			AuthorEntity.builder()
				.firstName("Isaac")
				.lastName("Asimov")
				.dateOfBirth(LocalDate.of(1920, 1, 2))
				.dateOfDeath(LocalDate.of(1992, 4, 6))
				.build()
		);
	}

	private Flux<CategoryEntity> makeCategories() {
		return Flux.just(
			CategoryEntity.builder()
				.name("Heroic Fantasy")
				.numberOfBooks(1L)
				.build(),
			CategoryEntity.builder()
				.name("Classics")
				.numberOfBooks(1L)
				.build(),
			CategoryEntity.builder()
				.name("Science Fiction")
				.numberOfBooks(1L)
				.build()
		);
	}

	private Flux<BookEntity> makeBooks(List<CategoryEntity> categoryEntities, List<AuthorEntity> authorEntities) {
		final var categoryIdsByName = categoryEntities.stream()
			.collect(Collectors.toMap(CategoryEntity::getName, CategoryEntity::getId));
		final var authorsIdsByLastName = authorEntities.stream()
			.collect(Collectors.toMap(AuthorEntity::getLastName, AuthorEntity::getId));
		return Flux.just(
			BookEntity.builder()
				.title("The Lord of the Rings: The Fellowship of the Ring")
				.author(
//					BookEntity.BookAuthorEntity.of(authorsIdsByLastName.get("Tolkien"), "J. R. R. Tolkien")
					BookEntity.BookAuthorEntity.builder()
						.id(authorsIdsByLastName.get("Tolkien"))
						.fullName("J. R. R. Tolkien")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of(categoryIdsByName.get("Heroic Fantasy"), "Heroic Fantasy")
					BookEntity.BookCategoryEntity.builder()
						.id(categoryIdsByName.get("Heroic Fantasy"))
						.name("Heroic Fantasy")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of(categoryIdsByName.get("Classics"), "Classics")
					BookEntity.BookCategoryEntity.builder()
						.id(categoryIdsByName.get("Classics"))
						.name("Classics")
						.build()
				)
				.rating(4.23f)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build()
		);
	}

}
