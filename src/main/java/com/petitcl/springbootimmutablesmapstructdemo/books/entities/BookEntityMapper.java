package com.petitcl.springbootimmutablesmapstructdemo.books.entities;

import com.petitcl.springbootimmutablesmapstructdemo.books.models.Book;
import com.petitcl.springbootimmutablesmapstructdemo.books.models.CreateBookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

	Book bookEntityToBook(BookEntity bookEntity);

	BookEntity createBookRequestToBookEntity(CreateBookRequest createBookRequest);

}
