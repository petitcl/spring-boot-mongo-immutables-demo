package com.petitcl.springbootimmutablesmapstructdemo.commons.annotations;

import org.immutables.annotate.InjectAnnotation;
import org.immutables.value.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PACKAGE, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@InjectAnnotation(
	target = InjectAnnotation.Where.FIELD,
	type = Id.class,
	code = ""
)
public @interface ImmutableMongoEntity2 {
}
