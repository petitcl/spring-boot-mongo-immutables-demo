package com.petitcl.springbootimmutablesmapstructdemo.books.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@JsonDeserialize(builder = ImmutableCreateBookDto.Builder.class)
@Value.Style(
	validationMethod = Value.Style.ValidationMethod.NONE
)
@Value.Immutable
public abstract class CreateBookDto {

	@JsonProperty("title")
	@NotEmpty
	public abstract String getTitle();

	@JsonProperty("authorId")
	@NotEmpty
	public abstract String getAuthorId();

	@JsonProperty("categoryIds")
	@NotEmpty
	public abstract Set<String> getCategoryIds();

}
