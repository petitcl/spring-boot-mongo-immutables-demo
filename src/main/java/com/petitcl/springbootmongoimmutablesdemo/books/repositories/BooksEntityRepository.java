package com.petitcl.springbootmongoimmutablesdemo.books.repositories;

import com.petitcl.springbootmongoimmutablesdemo.books.entities.BookEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksEntityRepository extends ReactiveCrudRepository<BookEntity, String> {

}
