package com.petitcl.springbootmongoimmutablesdemo.entities;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends ReactiveCrudRepository<TestEntity, String> {

}
