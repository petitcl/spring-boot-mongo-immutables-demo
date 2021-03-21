package com.petitcl.springbootimmutablesmapstructdemo.authors.services;

import com.petitcl.springbootimmutablesmapstructdemo.authors.dtos.CreateAuthorDto;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorsService {

	Flux<Author> findAll();

	Mono<Author> findById(String id);

	Mono<Author> create(CreateAuthorDto request);

}
