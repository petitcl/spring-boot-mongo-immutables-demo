package com.petitcl.springbootimmutablesmapstructdemo.authors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@JsonDeserialize(builder = ImmutableGetAuthorsDto.Builder.class)
@Value.Immutable
public abstract class GetAuthorsDto {

	@Value.Parameter
	@JsonProperty("authors")
	public abstract List<AuthorDto> getAuthors();

}
