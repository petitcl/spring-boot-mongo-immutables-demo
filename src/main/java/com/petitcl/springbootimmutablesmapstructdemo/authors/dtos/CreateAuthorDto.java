package com.petitcl.springbootimmutablesmapstructdemo.authors.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@JsonDeserialize(builder = ImmutableCreateAuthorDto.Builder.class)
@Value.Immutable
@Value.Style(
	validationMethod = Value.Style.ValidationMethod.NONE
)
public abstract class CreateAuthorDto {

	@NotEmpty
	@JsonProperty("firstName")
	public abstract String getFirstName();

	@NotEmpty
	@JsonProperty("lastName")
	public abstract String getLastName();

	@NotNull
	@JsonProperty("dateOfBirth")
	public abstract LocalDate getDateOfBirth();

	@JsonProperty("dateOfDeath")
	public abstract Optional<LocalDate> getDateOfDeath();

}
