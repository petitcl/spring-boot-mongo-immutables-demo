package com.petitcl.springbootimmutablesmapstructdemo.authors.entities;

import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.CreateAuthorRequest;
import com.petitcl.springbootimmutablesmapstructdemo.commons.configurations.OptionalsMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
	uses = OptionalsMapper.class,
	componentModel = "spring"
)
public interface AuthorEntityMapper {

	@Mapping(
		source = "dateOfDeath",
		target = "dateOfDeath",
		qualifiedByName = {"Optionals", "wrap"}
	)
	Author categoryEntityToAuthor(AuthorEntity bookEntity);

	@Mapping(
		source = "dateOfDeath",
		target = "dateOfDeath",
		qualifiedByName = {"Optionals", "unwrap"}
	)
	AuthorEntity createAuthorRequestToAuthorEntity(CreateAuthorRequest createAuthorRequest);

}
