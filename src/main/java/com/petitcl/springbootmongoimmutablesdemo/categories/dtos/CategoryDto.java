package com.petitcl.springbootmongoimmutablesdemo.categories.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

@JsonDeserialize(builder = ImmutableCategoryDto.Builder.class)
@Value.Immutable
public abstract class CategoryDto {

	@JsonProperty("id")
	public abstract String getId();

	@JsonProperty("name")
	public abstract String getName();

	@JsonProperty("numberOfBooks")
	public abstract long getNumberOfBooks();

}
