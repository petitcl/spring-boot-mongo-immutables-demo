package com.petitcl.springbootimmutablesmapstructdemo.categories.services;

import com.petitcl.springbootimmutablesmapstructdemo.categories.dtos.CreateCategoryDto;
import com.petitcl.springbootimmutablesmapstructdemo.categories.models.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoriesService {

	Flux<Category> findAll();

	Mono<Category> findById(String id);

	Mono<Category> create(CreateCategoryDto createCategoryDto);

	Mono<Category> incrementNumberOfBooks(String id);

}
