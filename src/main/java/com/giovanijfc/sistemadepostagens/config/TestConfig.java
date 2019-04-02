package com.giovanijfc.sistemadepostagens.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.giovanijfc.sistemadepostagens.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService db;
	
	@Bean
	public boolean instantiateDataBase() throws Exception {
		db.instantiateDataBase();
		return true;
	}
}
