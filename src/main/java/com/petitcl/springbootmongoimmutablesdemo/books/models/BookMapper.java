package com.petitcl.springbootmongoimmutablesdemo.books.models;

import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

	BookAuthor authorToBookAuthor(Author author);

}
