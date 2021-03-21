package com.petitcl.springbootimmutablesmapstructdemo.books.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.util.List;

@JsonDeserialize(builder = ImmutableGetBooksDto.Builder.class)
@Value.Immutable
public abstract class GetBooksDto {

	@Value.Parameter
	@JsonProperty("books")
	public abstract List<BookDto> getBooks();

}
