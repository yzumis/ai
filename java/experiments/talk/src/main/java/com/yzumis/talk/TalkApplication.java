package com.yzumis.talk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.yzumis.talk.configuration.SwaggerConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Import(SwaggerConfiguration.class)
public class TalkApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TalkApplication.class, args);
	}

}
