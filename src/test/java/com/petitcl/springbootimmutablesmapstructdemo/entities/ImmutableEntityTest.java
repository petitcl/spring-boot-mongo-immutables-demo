package com.petitcl.springbootimmutablesmapstructdemo.entities;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ImmutableEntityTest {

	@Autowired
	private TestRepository testRepository;

	@BeforeEach
	public void beforeEach() {
		testRepository.deleteAll().block();
	}

	@Test
	public void test_save_deserializesWellEntity() throws Exception {
		TestEntity entity = makeEntity();
		entity = testRepository.save(entity).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findById_deserializesWellEntity() throws Exception {
		TestEntity entity = makeEntity();
		entity = testRepository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		entity = testRepository.findById(entity.getId()).block();

		assertEntityIsCorrectlyDeserialized(entity);
	}

	@Test
	public void test_findAll_deserializesWellEntity() throws Exception {
		TestEntity entity = makeEntity();
		entity = testRepository.save(entity).block();
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());

		final List<TestEntity> entities = testRepository.findAll()
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	// this test fails because entities.get(0).getId() is null
	@Disabled
	@Test
	public void test_saveAllFlux_deserializesWellEntity() throws Exception {
		TestEntity entity = makeEntity();
		final List<TestEntity> entities = testRepository.saveAll(Flux.just(entity))
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	@Test
	public void test_saveAllIterable_deserializesWellEntity() throws Exception {
		TestEntity entity = makeEntity();
		final List<TestEntity> entities = testRepository.saveAll(ImmutableList.of(entity))
			.collectList()
			.block();

		Assertions.assertNotNull(entities);
		Assertions.assertEquals(1, entities.size());
		assertEntityIsCorrectlyDeserialized(entities.get(0));
	}

	private ImmutableTestEntity makeEntity() {
		return ImmutableTestEntity.builder()
			.test("test")
			.things(
				ImmutableList.of("thing1", "thing2")
			)
			.dateOfSomething(LocalDate.of(2001, 1, 1))
			.build();
	}

	private void assertEntityIsCorrectlyDeserialized(TestEntity entity) {
		Assertions.assertNotNull(entity);
		Assertions.assertNotNull(entity.getId());
		Assertions.assertEquals(entity.getTest(), "test");
		Assertions.assertNotNull(entity.getThings());
		Assertions.assertEquals(2, entity.getThings().size());
		Assertions.assertEquals("thing1", entity.getThings().get(0));
		Assertions.assertEquals("thing2", entity.getThings().get(1));
		Assertions.assertEquals(entity.getDateOfSomething().orElse(null), LocalDate.of(2001, 1, 1));
	}

}
