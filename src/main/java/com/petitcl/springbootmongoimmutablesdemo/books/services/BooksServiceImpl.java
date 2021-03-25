package com.petitcl.springbootmongoimmutablesdemo.books.services;

import com.petitcl.springbootmongoimmutablesdemo.books.models.*;
import com.petitcl.springbootmongoimmutablesdemo.books.repositories.BooksRepository;
import com.petitcl.springbootmongoimmutablesdemo.commons.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class BooksServiceImpl implements BooksService {

	private final BooksRepository booksRepository;

	@Override
	public Mono<Book> findById(String id) {
		return booksRepository.findById(id)
			.switchIfEmpty(Mono.error(new NotFoundException("Book not found")));
	}

	@Override
	public Flux<Book> findAll() {
		return booksRepository.findAll();
	}

	@Override
	public Mono<Book> create(String title, BookAuthor author, Set<BookCategory> categories) {
		final CreateBookRequest book = ImmutableCreateBookRequest.builder()
			.title(title)
			.author(author)
			.categories(categories)
			.rating(0.0f)
			.build();
		return booksRepository.create(book);
	}

}
