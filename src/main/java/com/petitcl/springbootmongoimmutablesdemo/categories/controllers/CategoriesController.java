package com.petitcl.springbootmongoimmutablesdemo.categories.controllers;

import com.petitcl.springbootmongoimmutablesdemo.categories.dtos.*;
import com.petitcl.springbootmongoimmutablesdemo.categories.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(
	value = "/categories",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class CategoriesController {

	private final CategoriesService categoriesService;
	private final CategoryDtoMapper categoryDtoMapper;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Mono<GetCategoriesDto> findAllCategories() {
		return categoriesService
			.findAll()
			.map(categoryDtoMapper::categoryToCategoryDto)
			.collectList()
			.map(ImmutableGetCategoriesDto::of);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Mono<CategoryDto> findCategoryById(@PathVariable String id) {
		return categoriesService
			.findById(id)
			.map(categoryDtoMapper::categoryToCategoryDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Mono<CategoryDto> createCategory(@RequestBody @Valid CreateCategoryDto request) {
		return categoriesService.create(request)
			.map(categoryDtoMapper::categoryToCategoryDto);
	}

}
