# spring-boot-immutables-mapstruct-demo

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
Here are some insights learned during the development of this application:
- For a DTO class, it is not needed to annotate it with `JsonSerialize` nor `JsonProperty`; 
Jackson is clever enough to figure out the field names, based on bean names convention.
- Immutables will throw an error if a mandatory field is null during object initialization.
This forces the developer to think about fields nullability,
and it helps preventing them to create half initialized objects.
- Given than an immutable object is self validating, it means that this validation can conflict
with a classic validation framework like javax validation.
A solution is to annotate the DTO immutable class with 
`@Value.Style(validationMethod = Value.Style.ValidationMethod.NONE)`
  in order to let javax validation take place.
Another solution could be to not use immutables for DTO classes.
- Spring Data Mongodb does not seem to handle well immutables objects;
it seems to be a better option to use regular mutable objects for entities.
