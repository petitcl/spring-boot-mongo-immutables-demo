package com.petitcl.springbootmongoimmutablesdemo.authors.services;

import com.petitcl.springbootmongoimmutablesdemo.authors.dtos.AuthorDtoMapper;
import com.petitcl.springbootmongoimmutablesdemo.authors.dtos.CreateAuthorDto;
import com.petitcl.springbootmongoimmutablesdemo.authors.models.Author;
import com.petitcl.springbootmongoimmutablesdemo.authors.repositories.AuthorsRepository;
import com.petitcl.springbootmongoimmutablesdemo.commons.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {

	private final AuthorsRepository authorsRepository;
	private final AuthorDtoMapper authorDtoMapper;

	@Override
	public Flux<Author> findAll() {
		return authorsRepository.findAll();
	}

	@Override
	public Mono<Author> findById(String id) {
		return authorsRepository.findById(id)
			.switchIfEmpty(Mono.error(new NotFoundException("Author not found")));
	}

	@Override
	public Mono<Author> create(CreateAuthorDto request) {
		return Mono.just(request)
			.map(authorDtoMapper::createAuthorDtoCreateAuthorRequest)
			.flatMap(authorsRepository::create);
	}

}
