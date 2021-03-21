package com.petitcl.springbootimmutablesmapstructdemo.categories.dtos;

import com.petitcl.springbootimmutablesmapstructdemo.categories.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

	CategoryDto categoryToCategoryDto(Category category);

}
