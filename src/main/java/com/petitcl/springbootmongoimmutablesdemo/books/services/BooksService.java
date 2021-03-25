package com.petitcl.springbootmongoimmutablesdemo.books.services;

import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import com.petitcl.springbootmongoimmutablesdemo.books.models.BookAuthor;
import com.petitcl.springbootmongoimmutablesdemo.books.models.BookCategory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface BooksService {

	Mono<Book> findById(String id);

	Flux<Book> findAll();

	Mono<Book> create(String title, BookAuthor author, Set<BookCategory> categories);

}
