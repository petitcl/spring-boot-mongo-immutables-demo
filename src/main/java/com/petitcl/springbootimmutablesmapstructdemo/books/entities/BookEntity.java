package com.petitcl.springbootimmutablesmapstructdemo.books.entities;

import com.petitcl.springbootimmutablesmapstructdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Set;

@Value.Immutable
@Document("books")
@ImmutableMongoEntity
public abstract class BookEntity {

	@Id
	@Nullable
	public abstract String getId();

	public abstract String getTitle();

	public abstract BookAuthorEntity getAuthor();

	public abstract Set<BookCategoryEntity> getCategories();

	public abstract Float getRating();

	@Value.Default
	@CreatedDate
	public Instant getCreatedDate() {
		return Instant.now();
	}

	@Value.Default
	@LastModifiedDate
	public Instant getLastModifiedDate() {
		return Instant.now();
	}

}
