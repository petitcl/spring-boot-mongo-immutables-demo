package com.petitcl.springbootimmutablesmapstructdemo.authors.entities;

import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.CreateAuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorEntityMapper {

	Author categoryEntityToAuthor(AuthorEntity bookEntity);

	AuthorEntity createAuthorRequestToAuthorEntity(CreateAuthorRequest createAuthorRequest);

}
