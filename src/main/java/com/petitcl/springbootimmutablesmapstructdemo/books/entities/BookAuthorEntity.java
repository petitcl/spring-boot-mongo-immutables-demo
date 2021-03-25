package com.petitcl.springbootimmutablesmapstructdemo.books.entities;

import com.petitcl.springbootimmutablesmapstructdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableMongoEntity
public abstract class BookAuthorEntity {

	public abstract String getId();

	public abstract String getFullName();
}
