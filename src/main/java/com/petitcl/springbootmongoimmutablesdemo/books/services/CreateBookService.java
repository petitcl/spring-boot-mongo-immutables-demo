package com.petitcl.springbootmongoimmutablesdemo.books.services;

import com.petitcl.springbootmongoimmutablesdemo.books.dtos.CreateBookDto;
import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import reactor.core.publisher.Mono;

public interface CreateBookService {

	Mono<Book> create(CreateBookDto createBookDto);

}
