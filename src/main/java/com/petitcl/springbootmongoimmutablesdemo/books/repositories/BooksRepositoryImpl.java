package com.petitcl.springbootmongoimmutablesdemo.books.repositories;

import com.petitcl.springbootmongoimmutablesdemo.books.entities.BookEntityMapper;
import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import com.petitcl.springbootmongoimmutablesdemo.books.models.CreateBookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class BooksRepositoryImpl implements BooksRepository {

	private final BooksEntityRepository entityRepository;
	private final BookEntityMapper bookEntityMapper;

	@Override
	public Flux<Book> findAll() {
		return entityRepository
			.findAll()
			.map(bookEntityMapper::bookEntityToBook);
	}

	@Override
	public Mono<Book> findById(String id) {
		return entityRepository
			.findById(id)
			.map(bookEntityMapper::bookEntityToBook);
	}

	@Override
	public Mono<Book> create(CreateBookRequest book) {
		return Mono.just(book)
			.map(bookEntityMapper::createBookRequestToBookEntity)
			.flatMap(entityRepository::save)
			.map(bookEntityMapper::bookEntityToBook);
	}

}
