package com.petitcl.springbootimmutablesmapstructdemo.books.dtos;

import com.petitcl.springbootimmutablesmapstructdemo.books.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

	BookDto bookToBookDto(Book book);

}
