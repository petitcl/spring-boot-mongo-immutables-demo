package com.petitcl.springbootmongoimmutablesdemo.books.repositories;

import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import com.petitcl.springbootmongoimmutablesdemo.books.models.CreateBookRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BooksRepository {

	Flux<Book> findAll();

	Mono<Book> findById(String id);

	Mono<Book> create(CreateBookRequest book);

}
