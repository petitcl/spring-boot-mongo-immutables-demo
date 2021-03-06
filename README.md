# spring-boot-mongo-immutables-demo

## Introduction
This repository contains a sample Spring Boot project
that demonstrates how to use immutables objects with [immutables](https://immutables.github.io/)
in an almost real life scenario.

This project is made with the following technologies:
- Spring Boot
- Spring Boot WebFlux
- Spring Boot Data Mongo Reactive
- Immutables (for immutable classes)
- lombok (for non immutable classes)
- MapStruct (for bean mapping)

The application is a simple JSON REST API that manages a list of books, authors and categories.
The exposed routes are as follows:
- `GET /books` => find all books
- `GET /books/:id` => find book with id `:id`
- `POST /books` => create a book
- `GET /categories` => find all categories
- `GET /categories/:id` => find category with id `:id`
- `POST /categories` => create a category
- `GET /authors` => find all authors
- `GET /authors/:id` => find author with id `:id`
- `POST /authors` => create an author

The server offers the classic features of a real life application such as JSON serialization / deserialization,
validation, business logic, storing data in a database (here mongodb) and unit testing.

## Installation
In order to run the project, you will need a running mongodb instance on port `27017`.
You can use you own mongodb server or simply run a dockerised version with `docker run -p 27017:27017 mongo:4.0-xenial`.

The command to run the server is: `mvn spring-boot:run`. The server will listen on port `8080`

## Insights 
Here are some insights learned during the development of this demo application:
- Immutables will throw an error if a mandatory field is null during object initialization.
  This forces the developer to think about fields nullability,
  and it helps preventing them to create half initialized objects.
- DTO (Data Transfer Objects) can be immutables objects. There are however some gotchas to be aware of:
    - It is not needed to annotate it with `JsonSerialize` nor `JsonProperty`; 
    Jackson is clever enough to figure out the field names, based on beans names convention.
    - Given than an immutable object is self validating, it means that this validation can conflict
    with a classic validation framework like javax validation.
    A solution is to annotate the DTO immutable class with
    `@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)`
    in order to let javax validation take place.
- Spring Data Mongodb can handle immutables objects. However, there are a some gotchas to be aware of
  (A complete meta annotation example that handles most of the points mentioned below can be found in
  [ImmutableMongoEntity.java](/src/main/java/com/petitcl/springbootmongoimmutablesdemo/commons/annotations/ImmutableMongoEntity.java)):
    - The `_id` property that cannot be known during insertion, which can conflict with the null checks of immutables
    if the `_id` property is mandatory.  This means that a workaround must be implemented for that:
        - either annotate the id field with `@Nullable`
        - or set the id field to be an `ObjectId` with a default value of `ObjectId.new()`
    - Spring Data is not able to find on its own the constructor / factory generated by Immutables.
      It is therefore necessary to create an annotation with `@InjectAnnotation`
      that will inject the `@PersistenceConstructor` annotation on the generated constructor.
      It is also needed to tell immutables to generate a constructor instead of a factory.
      This is done by adding the `@Value.Style(of = "new", allParameters = true)` annotation to the entity type.
    - If Guava is on classpath, collections fields will be generated as immutable guava collections.
      However, Spring Data does not handle guava collections
      ([spring-data-commons/1817](https://github.com/spring-projects/spring-data-commons/issues/1817)).
      This means the guava integration must be deactivated specifically for entitiy classes.
      This is easily done by adding `@Value.Style(jdkOnly = true)` annotation to the entity.
    - If `Optional<T>` are used, this might cause an incompatibility with immutables during deserialization.
      For that, it is necessary to add `@Value.Style(optionalAcceptNullable = true)`.
    - There seem to be an issue with `ReactiveMongoRepository#saveAll(Publisher<T>)`:
      the returned entities do not have their `_id` property set (TODO open issue ?).
