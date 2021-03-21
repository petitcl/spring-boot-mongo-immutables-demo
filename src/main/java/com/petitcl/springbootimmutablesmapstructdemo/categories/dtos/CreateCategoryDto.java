package com.petitcl.springbootimmutablesmapstructdemo.categories.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotEmpty;

@JsonDeserialize(builder = ImmutableCreateCategoryDto.Builder.class)
@Value.Style(
	validationMethod = Value.Style.ValidationMethod.NONE
)
@Value.Immutable
public abstract class CreateCategoryDto {

	@JsonProperty("name")
	@NotEmpty
	public abstract String getName();

}
