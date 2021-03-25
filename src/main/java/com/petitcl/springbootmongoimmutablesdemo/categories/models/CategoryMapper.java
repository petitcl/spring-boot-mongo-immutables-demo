package com.petitcl.springbootmongoimmutablesdemo.categories.models;

import com.petitcl.springbootmongoimmutablesdemo.books.models.BookCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	BookCategory categoryToBookCategory(Category category);

}
