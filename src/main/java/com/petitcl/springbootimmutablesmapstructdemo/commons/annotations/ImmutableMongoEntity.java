package com.petitcl.springbootimmutablesmapstructdemo.commons.annotations;

import org.immutables.annotate.InjectAnnotation;
import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that can be place on an immutable entity class,
 * or a package containing only immutables entity classes.
 * All classes affected will be configured to be compatible with Spring Data Mongodb
 */
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Value.Style(
	// generate a public constructor with all arguments
	// instead of a static factory method
	of = "new",
	allParameters = true,
	// disable guava integration
	jdkOnly = true,
	// fix issue with optionals deserialization
	optionalAcceptNullable = true,
	passAnnotations = Id.class
)
// inject @PersistenceConstructor annotation on the generated constructor
@InjectAnnotation(
	target = InjectAnnotation.Where.CONSTRUCTOR,
	type = PersistenceConstructor.class
)
public @interface ImmutableMongoEntity {
}
