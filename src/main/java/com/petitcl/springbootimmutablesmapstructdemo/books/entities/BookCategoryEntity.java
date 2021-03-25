package com.petitcl.springbootimmutablesmapstructdemo.books.entities;

import com.petitcl.springbootimmutablesmapstructdemo.commons.annotations.ImmutableMongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.immutables.value.Value;

@Value.Immutable
@ImmutableMongoEntity
public abstract class BookCategoryEntity {

	public abstract String getId();

	public abstract String getName();

}
