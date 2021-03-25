package com.petitcl.springbootmongoimmutablesdemo.commons.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class JacksonConfiguration {

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
			.modulesToInstall(GuavaModule.class);
	}

	@Bean
	public Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
		return new Jackson2JsonEncoder(mapper);
	}

	@Bean
	public Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
		return new Jackson2JsonDecoder(mapper);
	}

	@Bean
	public WebFluxConfigurer webFluxConfigurer(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder) {
		return new WebFluxConfigurer() {
			@Override
			public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
				configurer.defaultCodecs().jackson2JsonEncoder(encoder);
				configurer.defaultCodecs().jackson2JsonDecoder(decoder);
			}
		};

	}
}
