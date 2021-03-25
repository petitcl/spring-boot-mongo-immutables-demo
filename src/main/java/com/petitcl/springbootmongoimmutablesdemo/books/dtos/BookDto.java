package com.petitcl.springbootmongoimmutablesdemo.books.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.Set;

@JsonDeserialize(builder = ImmutableBookDto.Builder.class)
@Value.Immutable
public abstract class BookDto {

	@JsonProperty("id")
	public abstract String getId();

	@JsonProperty("title")
	public abstract String getTitle();

	@JsonProperty("author")
	public abstract BookAuthorDto getAuthor();

	@JsonProperty("categories")
	public abstract Set<BookCategoryDto> getCategories();

	@JsonProperty("rating")
	public abstract Float getRating();

}
