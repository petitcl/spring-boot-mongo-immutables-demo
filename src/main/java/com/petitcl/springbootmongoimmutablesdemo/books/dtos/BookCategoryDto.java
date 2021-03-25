package com.petitcl.springbootmongoimmutablesdemo.books.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(builder = ImmutableBookCategoryDto.Builder.class)
@Value.Immutable
public abstract class BookCategoryDto {

	@JsonProperty("id")
	public abstract String getId();

	@JsonProperty("name")
	public abstract String getName();

}
