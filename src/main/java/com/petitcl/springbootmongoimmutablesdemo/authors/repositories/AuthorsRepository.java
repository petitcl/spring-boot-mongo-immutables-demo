package com.petitcl.springbootmongoimmutablesdemo.authors.repositories;

import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import com.petitcl.springbootmongoimmutablesdemo.authors.models.CreateAuthorRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorsRepository {

	Flux<Author> findAll();

	Mono<Author> findById(String id);

	Mono<Author> create(CreateAuthorRequest request);

}
