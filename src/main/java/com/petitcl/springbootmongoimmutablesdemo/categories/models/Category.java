package com.petitcl.springbootmongoimmutablesdemo.categories.models;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Category {

	public abstract String getId();

	public abstract String getName();

	public abstract long getNumberOfBooks();

}
