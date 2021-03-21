package com.petitcl.springbootimmutablesmapstructdemo.books.services;

import com.petitcl.springbootimmutablesmapstructdemo.books.dtos.CreateBookDto;
import com.petitcl.springbootimmutablesmapstructdemo.books.models.Book;
import reactor.core.publisher.Mono;

public interface CreateBookService {

	Mono<Book> create(CreateBookDto createBookDto);

}
