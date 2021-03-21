package com.petitcl.springbootimmutablesmapstructdemo.authors.dtos;

import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.CreateAuthorRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorDtoMapper {

	AuthorDto authorToAuthorDto(Author author);

	CreateAuthorRequest createAuthorDtoCreateAuthorRequest(CreateAuthorDto authorDto);

}
