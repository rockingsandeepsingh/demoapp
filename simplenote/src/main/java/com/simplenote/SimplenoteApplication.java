/*
 * 
 */
package com.simplenote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// TODO: Auto-generated Javadoc
/**
 * The Class SimplenoteApplication.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.simplenote"})
@EnableJpaRepositories(basePackages="com.simplenote.dao")
@EnableTransactionManagement
@EntityScan(basePackages="com.simplenote.entities")
public class SimplenoteApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SimplenoteApplication.class, args);
	}

}
