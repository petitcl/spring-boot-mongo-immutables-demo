package com.petitcl.springbootimmutablesmapstructdemo.authors.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.AuthorEntityMapper;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.Author;
import com.petitcl.springbootimmutablesmapstructdemo.authors.models.CreateAuthorRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class AuthorsRepositoryImpl implements AuthorsRepository {

	private final AuthorsEntityRepository entityRepository;
	private final AuthorEntityMapper authorEntityMapper;

	@Override
	public Flux<Author> findAll() {
		return entityRepository.findAll()
			.map(authorEntityMapper::categoryEntityToAuthor);
	}

	@Override
	public Mono<Author> findById(String id) {
		return entityRepository.findById(id)
			.map(authorEntityMapper::categoryEntityToAuthor);
	}

	@Override
	public Mono<Author> create(CreateAuthorRequest request) {
		return Mono.just(request)
			.map(authorEntityMapper::createAuthorRequestToAuthorEntity)
			.flatMap(entityRepository::save)
			.map(authorEntityMapper::categoryEntityToAuthor);
	}

}
