package com.petitcl.springbootmongoimmutablesdemo.books.entities;

import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import com.petitcl.springbootmongoimmutablesdemo.books.models.CreateBookRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookEntityMapper {

	Book bookEntityToBook(BookEntity bookEntity);

	BookEntity createBookRequestToBookEntity(CreateBookRequest createBookRequest);

}
