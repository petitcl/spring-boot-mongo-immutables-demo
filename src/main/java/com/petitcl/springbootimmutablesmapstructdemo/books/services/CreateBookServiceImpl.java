package com.petitcl.springbootimmutablesmapstructdemo.books.services;

import com.google.common.collect.ImmutableSet;
import com.petitcl.springbootimmutablesmapstructdemo.authors.services.AuthorsService;
import com.petitcl.springbootimmutablesmapstructdemo.books.dtos.CreateBookDto;
import com.petitcl.springbootimmutablesmapstructdemo.books.models.Book;
import com.petitcl.springbootimmutablesmapstructdemo.books.models.BookMapper;
import com.petitcl.springbootimmutablesmapstructdemo.categories.models.CategoryMapper;
import com.petitcl.springbootimmutablesmapstructdemo.categories.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CreateBookServiceImpl implements CreateBookService {

	private final BooksService booksService;
	private final CategoriesService categoriesService;
	private final AuthorsService authorsService;
	private final BookMapper bookMapper;
	private final CategoryMapper categoryMapper;

	@Override
	public Mono<Book> create(CreateBookDto createBookDto) {
		return Mono.zip(
			// find author or throw
			authorsService.findById(createBookDto.getAuthorId())
				.map(bookMapper::authorToBookAuthor),
			// find categories or throw
			Flux.fromIterable(createBookDto.getCategoryIds())
				.flatMap(categoriesService::findById)
				.map(categoryMapper::categoryToBookCategory)
				.collect(ImmutableSet.toImmutableSet())
		)
			// create book
			.flatMap(
				(t) -> booksService.create(
					createBookDto.getTitle(),
					t.getT1(),
					t.getT2()
				)
			)
			// update categories count
			.flatMap(
				(book) -> Flux.fromIterable(book.getCategories())
					.concatMap((c) -> categoriesService.incrementNumberOfBooks(c.getId()))
					.collectList()
					.map((categories) -> book)
			);
	}

}
