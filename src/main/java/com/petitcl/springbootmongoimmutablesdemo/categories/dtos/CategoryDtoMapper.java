package com.petitcl.springbootmongoimmutablesdemo.categories.dtos;

import com.petitcl.springbootmongoimmutablesdemo.categories.models.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

	CategoryDto categoryToCategoryDto(Category category);

}
