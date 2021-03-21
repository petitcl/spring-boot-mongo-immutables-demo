package com.petitcl.springbootimmutablesmapstructdemo.categories.repositories;

import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntity;
import com.petitcl.springbootimmutablesmapstructdemo.categories.entities.CategoryEntityMapper;
import com.petitcl.springbootimmutablesmapstructdemo.categories.models.Category;
import com.petitcl.springbootimmutablesmapstructdemo.categories.models.CreateCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

@RequiredArgsConstructor
@Service
public class CategoriesRepositoryImpl implements CategoriesRepository {

	private final CategoriesEntityRepository entityRepository;
	private final ReactiveMongoTemplate mongoTemplate;
	private final CategoryEntityMapper categoryEntityMapper;

	@Override
	public Flux<Category> findAll() {
		return entityRepository.findAll()
			.map(categoryEntityMapper::categoryEntityToCategory);
	}

	@Override
	public Mono<Category> findById(String id) {
		return entityRepository.findById(id)
			.map(categoryEntityMapper::categoryEntityToCategory);
	}

	@Override
	public Mono<Category> create(CreateCategoryRequest request) {
		return Mono.just(request)
			.map(categoryEntityMapper::createCategoryRequestToCategoryEntity)
			.flatMap(entityRepository::save)
			.map(categoryEntityMapper::categoryEntityToCategory);
	}

	@Override
	public Mono<Category> incrementNumberOfBooks(String id) {
		return Mono.just(id)
			.map((i) -> {
				final Query query = Query.query(
					Criteria.where("id").is(i)
				);
				final Update update = new Update();
				update.inc("numberOfBooks");
				final FindAndModifyOptions options = new FindAndModifyOptions()
					.returnNew(true);
				return Tuples.of(query, update, options);
			})
			.flatMap((t) -> mongoTemplate.findAndModify(t.getT1(), t.getT2(), t.getT3(), CategoryEntity.class))
			.map(categoryEntityMapper::categoryEntityToCategory);
	}

}
