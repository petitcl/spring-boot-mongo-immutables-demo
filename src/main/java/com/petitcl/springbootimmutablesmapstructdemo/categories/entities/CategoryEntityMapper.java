package com.petitcl.springbootimmutablesmapstructdemo.categories.entities;

import com.petitcl.springbootimmutablesmapstructdemo.categories.models.Category;
import com.petitcl.springbootimmutablesmapstructdemo.categories.models.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

	Category categoryEntityToCategory(CategoryEntity bookEntity);

	CategoryEntity createCategoryRequestToCategoryEntity(CreateCategoryRequest createCategoryRequest);

}
