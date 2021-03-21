package com.petitcl.springbootimmutablesmapstructdemo.categories.models;

import com.petitcl.springbootimmutablesmapstructdemo.books.models.BookCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	BookCategory categoryToBookCategory(Category category);

}
