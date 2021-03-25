package com.petitcl.springbootmongoimmutablesdemo.books.models;

import org.immutables.value.Value;

@Value.Immutable
public abstract class BookAuthor {

	public abstract String getId();

	public abstract String getFullName();

}
