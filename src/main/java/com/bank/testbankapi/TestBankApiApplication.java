package com.bank.testbankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories("com.bank.testbankapi.*")
@ComponentScan("com.bank.testbankapi.*")
@SpringBootApplication(scanBasePackages = { "com.bank.testbankapi.*"})
@EntityScan("com.bank.testbankapi.*")
public class TestBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBankApiApplication.class, args);
	}

}
