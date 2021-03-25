package com.petitcl.springbootmongoimmutablesdemo.books.models;

import org.immutables.value.Value;

import java.util.Set;

@Value.Immutable
public abstract class CreateBookRequest {

	public abstract String getTitle();

	public abstract BookAuthor getAuthor();

	public abstract Set<BookCategory> getCategories();

	public abstract Float getRating();

}
