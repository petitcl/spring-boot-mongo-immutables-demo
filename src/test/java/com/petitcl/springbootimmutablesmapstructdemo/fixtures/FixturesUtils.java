package com.petitcl.springbootimmutablesmapstructdemo.fixtures;

import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.AuthorEntity;
import com.petitcl.springbootimmutablesmapstructdemo.authors.repositories.AuthorsEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.books.entities.BookEntity;
import com.petitcl.springbootimmutablesmapstructdemo.books.repositories.BooksEntityRepository;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.repositories.CategoriesEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;

@Service
public class FixturesUtils {

	@Autowired
	private BooksEntityRepository booksEntityRepository;

	@Autowired
	private CategoriesEntityRepository categoriesEntityRepository;

	@Autowired
	private AuthorsEntityRepository authorsEntityRepository;

	public void upsertTestData() {
		Mono.zip(
			categoriesEntityRepository.deleteAll()
				.thenMany(categoriesEntityRepository.saveAll(makeCategories()))
				.collectList(),
			booksEntityRepository.deleteAll()
				.thenMany(booksEntityRepository.saveAll(makeBooks()))
				.collectList(),
			authorsEntityRepository.deleteAll()
				.thenMany(authorsEntityRepository.saveAll(makeAuthors()))
				.collectList()
		)
			.block();
	}

	private Flux<AuthorEntity> makeAuthors() {
		return Flux.just(
			AuthorEntity.builder()
				.id("1")
				.firstName("J. R. R.")
				.lastName("Tolkien")
				.dateOfBirth(LocalDate.of(1892, 1, 3))
				.dateOfDeath(LocalDate.of(1973, 9, 2))
				.build(),
			AuthorEntity.builder()
				.id("2")
				.firstName("J. K.")
				.lastName("Rowling")
				.dateOfBirth(LocalDate.of(1965, 7, 31))
				.build(),
			AuthorEntity.builder()
				.id("3")
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
				.id("1")
				.name("Heroic Fantasy")
				.numberOfBooks(1)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build(),
			CategoryEntity.builder()
				.id("2")
				.name("Classics")
				.numberOfBooks(1)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build(),
			CategoryEntity.builder()
				.id("3")
				.name("Science Fiction")
				.numberOfBooks(1)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build()
		);
	}

	private Flux<BookEntity> makeBooks() {
		return Flux.just(
			BookEntity.builder()
				.id("1")
				.title("The Lord of the Rings: The Fellowship of the Ring")
				.author(
//					BookEntity.BookAuthorEntity.of("1", "J. R. R. Tolkien")
					BookEntity.BookAuthorEntity.builder()
						.id("1")
						.fullName("J. R. R. Tolkien")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of("1", "Heroic Fantasy")
					BookEntity.BookCategoryEntity.builder()
						.id("1")
						.name("Heroic Fantasy")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of("2", "Classics")
					BookEntity.BookCategoryEntity.builder()
						.id("2")
						.name("Classics")
						.build()
				)
				.rating(4.23f)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build(),
			BookEntity.builder()
				.id("2")
				.title("Foundation")
				.author(
//					BookEntity.BookAuthorEntity.of("3", "Isaac Asimov")
					BookEntity.BookAuthorEntity.builder()
						.id("3")
						.fullName("Isaac Asimov")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of("2", "Classics")
					BookEntity.BookCategoryEntity.builder()
						.id("2")
						.name("Classics")
						.build()
				)
				.category(
//					BookEntity.BookCategoryEntity.of("2", "Science Fiction")
					BookEntity.BookCategoryEntity.builder()
						.id("3")
						.name("Science Fiction")
						.build()
				)
				.rating(4.17f)
				.createdDate(Instant.now())
				.lastModifiedDate(Instant.now())
				.build()
		);
	}

}
