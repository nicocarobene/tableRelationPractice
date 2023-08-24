package com.restBiblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BibliotecaDeLIbrosApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliotecaDeLIbrosApplication.class, args);
	}

}
