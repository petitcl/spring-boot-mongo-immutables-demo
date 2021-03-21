package com.petitcl.springbootimmutablesmapstructdemo.authors.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Document("authors")
@Data
@AllArgsConstructor
@Builder
public class AuthorEntity {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;

	private LocalDate dateOfDeath;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

}
