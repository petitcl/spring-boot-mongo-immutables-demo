package com.petitcl.springbootmongoimmutablesdemo.categories.repositories;

import com.petitcl.springbootmongoimmutablesdemo.categories.models.Category;
import com.petitcl.springbootmongoimmutablesdemo.categories.models.CreateCategoryRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriesRepository {

	Flux<Category> findAll();

	Mono<Category> findById(String id);

	Mono<Category> create(CreateCategoryRequest request);

	Mono<Category> incrementNumberOfBooks(String id);

}
