package com.petitcl.springbootimmutablesmapstructdemo.categories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("categories")
@Data
@AllArgsConstructor
@Builder
public class CategoryEntity {

	@Id
	private String id;

	private String name;

	private long numberOfBooks;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant lastModifiedDate;

}
