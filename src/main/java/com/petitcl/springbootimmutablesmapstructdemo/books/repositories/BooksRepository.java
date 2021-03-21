package com.petitcl.springbootimmutablesmapstructdemo.books.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.books.models.Book;
import com.petitcl.springbootimmutablesmapstructdemo.books.models.CreateBookRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BooksRepository {

	Flux<Book> findAll();

	Mono<Book> findById(String id);

	Mono<Book> create(CreateBookRequest book);

}
