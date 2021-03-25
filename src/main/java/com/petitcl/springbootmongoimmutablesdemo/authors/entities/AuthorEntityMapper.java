package com.petitcl.springbootmongoimmutablesdemo.authors.entities;

import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import com.petitcl.springbootmongoimmutablesdemo.authors.models.CreateAuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorEntityMapper {

	Author categoryEntityToAuthor(AuthorEntity bookEntity);

	AuthorEntity createAuthorRequestToAuthorEntity(CreateAuthorRequest createAuthorRequest);

}
