package com.petitcl.springbootimmutablesmapstructdemo.commons.configurations;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(
	componentModel = "spring"
)
@Named("Optionals")
public class OptionalsMapper {

	@Named("unwrap")
	public <T> T unwrap(Optional<T> optional) {
		return optional.orElse(null);
	}

	@Named("wrap")
	public <T> Optional<T> wrap(T source) {
		return Optional.ofNullable(source);
	}

}
