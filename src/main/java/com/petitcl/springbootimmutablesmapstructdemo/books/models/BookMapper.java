package com.petitcl.springbootimmutablesmapstructdemo.books.models;

import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

	BookAuthor authorToBookAuthor(Author author);

}
