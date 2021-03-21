package com.petitcl.springbootimmutablesmapstructdemo.books.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(builder = ImmutableBookAuthorDto.Builder.class)
@Value.Immutable
public abstract class BookAuthorDto {

	@JsonProperty("id")
	public abstract String getId();

	@JsonProperty("fullName")
	public abstract String getFullName();

}
