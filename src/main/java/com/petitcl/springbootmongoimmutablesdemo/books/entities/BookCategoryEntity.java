package com.petitcl.springbootmongoimmutablesdemo.books.entities;

import com.petitcl.springbootmongoimmutablesdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableMongoEntity
public abstract class BookCategoryEntity {

	public abstract String getId();

	public abstract String getName();

}
