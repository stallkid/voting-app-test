package com.cooperativism.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@EnableMongoAuditing
@SpringBootApplication
public class VotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

}
