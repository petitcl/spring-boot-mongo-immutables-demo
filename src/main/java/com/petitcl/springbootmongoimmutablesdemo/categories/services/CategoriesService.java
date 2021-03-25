package com.petitcl.springbootmongoimmutablesdemo.categories.services;

import com.petitcl.springbootmongoimmutablesdemo.categories.dtos.CreateCategoryDto;
import com.petitcl.springbootmongoimmutablesdemo.categories.models.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriesService {

	Flux<Category> findAll();

	Mono<Category> findById(String id);

	Mono<Category> create(CreateCategoryDto createCategoryDto);

	Mono<Category> incrementNumberOfBooks(String id);

}
