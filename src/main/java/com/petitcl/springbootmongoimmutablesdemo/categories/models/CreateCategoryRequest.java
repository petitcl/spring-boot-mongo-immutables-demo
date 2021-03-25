package com.petitcl.springbootmongoimmutablesdemo.categories.models;

import org.immutables.value.Value;

@Value.Immutable
public abstract class CreateCategoryRequest {

	@Value.Parameter
	public abstract String getName();

	@Value.Parameter
	public abstract long getNumberOfBooks();

}
