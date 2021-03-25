package com.petitcl.springbootmongoimmutablesdemo.books.dtos;

import com.petitcl.springbootmongoimmutablesdemo.books.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookDtoMapper {

	BookDto bookToBookDto(Book book);

}
