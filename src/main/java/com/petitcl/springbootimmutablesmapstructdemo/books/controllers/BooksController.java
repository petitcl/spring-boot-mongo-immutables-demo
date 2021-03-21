package com.petitcl.springbootimmutablesmapstructdemo.books.controllers;

import com.petitcl.springbootimmutablesmapstructdemo.books.dtos.*;
import com.petitcl.springbootimmutablesmapstructdemo.books.services.BooksService;
import com.petitcl.springbootimmutablesmapstructdemo.books.services.CreateBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(
	value = "/books",
	produces = MediaType.APPLICATION_JSON_VALUE
)
public class BooksController {

	private final BookDtoMapper bookMapper;
	private final BooksService booksService;
	private final CreateBookService createBookService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public Mono<GetBooksDto> findAllBooks() {
		return booksService
			.findAll()
			.map(bookMapper::bookToBookDto)
			.collectList()
			.map(ImmutableGetBooksDto::of);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public Mono<BookDto> findBookById(@PathVariable String id) {
		return booksService
			.findById(id)
			.map(bookMapper::bookToBookDto);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(
		consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public Mono<BookDto> createBook(@RequestBody @Valid CreateBookDto request) {
		return createBookService.create(request)
			.map(bookMapper::bookToBookDto);
	}

}
