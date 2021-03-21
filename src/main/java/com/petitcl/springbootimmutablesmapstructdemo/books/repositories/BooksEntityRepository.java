package com.petitcl.springbootimmutablesmapstructdemo.books.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.books.entities.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksEntityRepository extends ReactiveCrudRepository<BookEntity, String> {

}
