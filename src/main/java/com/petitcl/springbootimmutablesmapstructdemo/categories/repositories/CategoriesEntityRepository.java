package com.petitcl.springbootimmutablesmapstructdemo.categories.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesEntityRepository extends ReactiveCrudRepository<CategoryEntity, String> {

}
