package com.petitcl.springbootmongoimmutablesdemo.authors.entities;

import com.petitcl.springbootmongoimmutablesdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Value.Immutable
@Document("authors")
@ImmutableMongoEntity
public abstract class AuthorEntity {

	@Id
	@Nullable
	public abstract String getId();

	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract LocalDate getDateOfBirth();

	public abstract Optional<LocalDate> getDateOfDeath();

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
