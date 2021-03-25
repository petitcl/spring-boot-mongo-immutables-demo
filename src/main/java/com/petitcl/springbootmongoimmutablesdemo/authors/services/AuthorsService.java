package com.petitcl.springbootmongoimmutablesdemo.authors.services;

import com.petitcl.springbootmongoimmutablesdemo.authors.dtos.CreateAuthorDto;
import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorsService {

	Flux<Author> findAll();

	Mono<Author> findById(String id);

	Mono<Author> create(CreateAuthorDto request);

}
