package com.petitcl.springbootmongoimmutablesdemo.authors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;

@JsonDeserialize(builder = ImmutableAuthorDto.Builder.class)
@Value.Immutable
public abstract class AuthorDto {

	@JsonProperty("id")
	public abstract String getId();

	@JsonProperty("firstName")
	public abstract String getFirstName();

	@JsonProperty("lastName")
	public abstract String getLastName();

	@JsonProperty("dateOfBirth")
	public abstract LocalDate getDateOfBirth();

	@JsonProperty("dateOfDeath")
	public abstract Optional<LocalDate> getDateOfDeath();

}
