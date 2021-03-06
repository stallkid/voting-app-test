package com.cooperativism.voting;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableMongoAuditing
@EnableRabbit
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VotacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotacaoApplication.class, args);
	}

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}

}
