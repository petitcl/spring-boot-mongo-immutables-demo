package com.petitcl.springbootmongoimmutablesdemo.authors.models;

import org.immutables.value.Value;

import java.time.LocalDate;
import java.util.Optional;

@Value.Immutable
public abstract class Author {

	public abstract String getId();

	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract LocalDate getDateOfBirth();

	public abstract Optional<LocalDate> getDateOfDeath();

	@Value.Derived
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}
