package com.petitcl.springbootimmutablesmapstructdemo.commons.configurations;

import com.petitcl.springbootimmutablesmapstructdemo.commons.exceptions.NotFoundException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

	@Override
	public Map<String, Object> getErrorAttributes(
		ServerRequest request,
		ErrorAttributeOptions options
	) {
		final Map<String, Object> map = super.getErrorAttributes(request, options);
		final Throwable throwable = super.getError(request);
		if (throwable instanceof NotFoundException) {
			map.put("status", HttpStatus.NOT_FOUND.value());
			map.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
		}
		return map;
	}

}
