package com.petitcl.springbootmongoimmutablesdemo.categories.services;

import com.petitcl.springbootmongoimmutablesdemo.categories.dtos.CreateCategoryDto;
import com.petitcl.springbootmongoimmutablesdemo.categories.models.Category;
import com.petitcl.springbootmongoimmutablesdemo.categories.models.ImmutableCreateCategoryRequest;
import com.petitcl.springbootmongoimmutablesdemo.categories.repositories.CategoriesRepository;
import com.petitcl.springbootmongoimmutablesdemo.commons.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CategoriesServiceImpl implements CategoriesService {

	private final CategoriesRepository categoriesRepository;

	@Override
	public Flux<Category> findAll() {
		return categoriesRepository.findAll();
	}

	@Override
	public Mono<Category> findById(String id) {
		return categoriesRepository.findById(id)
			.switchIfEmpty(Mono.error(new NotFoundException("Category not found")));
	}

	@Override
	public Mono<Category> create(CreateCategoryDto createCategoryDto) {
		return Mono.just(createCategoryDto)
			.map((dto) -> ImmutableCreateCategoryRequest.of(createCategoryDto.getName(), 0))
			.flatMap(categoriesRepository::create);
	}

	@Override
	public Mono<Category> incrementNumberOfBooks(String id) {
		return categoriesRepository.incrementNumberOfBooks(id)
			.switchIfEmpty(Mono.error(new NotFoundException("Category not found")));
	}

}
