package com.petitcl.springbootimmutablesmapstructdemo.authors.controllers;

import com.petitcl.springbootimmutablesmapstructdemo.authors.dtos.*;
import com.petitcl.springbootimmutablesmapstructdemo.authors.services.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(
	value = "/authors",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthorsController {

	private final AuthorsService authorsService;
	private final AuthorDtoMapper authorDtoMapper;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Mono<GetAuthorsDto> findAllAuthors() {
		return authorsService
			.findAll()
			.map(authorDtoMapper::authorToAuthorDto)
			.collectList()
			.map(ImmutableGetAuthorsDto::of);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Mono<AuthorDto> findAuthorById(@PathVariable String id) {
		return authorsService
			.findById(id)
			.map(authorDtoMapper::authorToAuthorDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Mono<AuthorDto> createAuthor(@RequestBody @Valid CreateAuthorDto request) {
		return authorsService.create(request)
			.map(authorDtoMapper::authorToAuthorDto);
	}

}
