package com.petitcl.springbootmongoimmutablesdemo.categories.entities;

import com.petitcl.springbootmongoimmutablesdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.time.Instant;

@Value.Immutable
@Document("categories")
@ImmutableMongoEntity
public abstract class CategoryEntity {

	@Id
	@Nullable
	public abstract String getId();

	public abstract String  getName();

	public abstract long getNumberOfBooks();

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
