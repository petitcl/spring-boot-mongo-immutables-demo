package com.petitcl.springbootimmutablesmapstructdemo.books.models;

import org.immutables.value.Value;

@Value.Immutable
public abstract class BookCategory {

	public abstract String getId();

	public abstract String getName();

}
