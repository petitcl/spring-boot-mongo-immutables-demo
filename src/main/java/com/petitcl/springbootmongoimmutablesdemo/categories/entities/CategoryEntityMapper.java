package com.petitcl.springbootmongoimmutablesdemo.categories.entities;

import com.petitcl.springbootmongoimmutablesdemo.categories.models.Category;
import com.petitcl.springbootmongoimmutablesdemo.categories.models.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

	Category categoryEntityToCategory(CategoryEntity bookEntity);

	CategoryEntity createCategoryRequestToCategoryEntity(CreateCategoryRequest createCategoryRequest);

}
