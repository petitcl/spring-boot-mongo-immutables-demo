package com.petitcl.springbootimmutablesmapstructdemo.entities;

import com.petitcl.springbootimmutablesmapstructdemo.commons.annotations.ImmutableMongoEntity;
import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Nullable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Value.Immutable
@Document("test")
@ImmutableMongoEntity
public abstract class TestEntity {

	@Id
	@Nullable
	public abstract String getId();

	public abstract String getTest();

	public abstract List<String> getThings();

	public abstract Optional<LocalDate> getDateOfSomething();

}
