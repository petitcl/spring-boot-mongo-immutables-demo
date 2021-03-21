package com.petitcl.springbootimmutablesmapstructdemo.authors.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.authors.entities.AuthorEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsEntityRepository extends ReactiveCrudRepository<AuthorEntity, String> {

}
