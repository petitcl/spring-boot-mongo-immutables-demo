package com.petitcl.springbootimmutablesmapstructdemo.authors.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.CreateAuthorRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorsRepository {

	Flux<Author> findAll();

	Mono<Author> findById(String id);

	Mono<Author> create(CreateAuthorRequest request);

}
