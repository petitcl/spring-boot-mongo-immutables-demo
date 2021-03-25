package com.petitcl.springbootmongoimmutablesdemo.authors.dtos;

import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import com.petitcl.springbootmongoimmutablesdemo.authors.models.CreateAuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDtoMapper {

	AuthorDto authorToAuthorDto(Author author);

	CreateAuthorRequest createAuthorDtoCreateAuthorRequest(CreateAuthorDto authorDto);

}
