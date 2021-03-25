package com.petitcl.springbootmongoimmutablesdemo.categories.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@JsonDeserialize(builder = ImmutableGetCategoriesDto.Builder.class)
@Value.Immutable
public abstract class GetCategoriesDto {

	@Value.Parameter
	@JsonProperty("categories")
	public abstract List<CategoryDto> getCategories();

}
