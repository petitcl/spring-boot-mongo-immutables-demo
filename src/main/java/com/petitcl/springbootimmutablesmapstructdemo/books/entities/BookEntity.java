package com.petitcl.springbootimmutablesmapstructdemo.books.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document("books")
@Data
@AllArgsConstructor
@Builder
public class BookEntity {

	@Id
	private String id;

	private String title;

	private BookAuthorEntity author;

	@Singular
	private Set<BookCategoryEntity> categories;

	private Float rating;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

	@Data
	@AllArgsConstructor
	@Builder
	public static class BookAuthorEntity {

		private String id;

		private String fullName;
	}

	@Data
	@AllArgsConstructor
	@Builder
	public static class BookCategoryEntity {

		private String id;

		private String name;
	}

}
