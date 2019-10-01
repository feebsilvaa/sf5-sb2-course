package com.feedev.api.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.feedev.api.entities.User;
import com.feedev.api.repositories.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger log = LoggerFactory.getLogger(DataInitializr.class);

	private static final String USER_DEFAULT_NAME = "USER DEFAULT";

	private static final String USER_DEFAULT_EMAIL = "user.default@sf5sb2.com";
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// Rotina para inserir usuário default para testes caso não exista
		log.info("Verificando se existe usuário default.");
		List<User> users = this.userRepository.findAll();
		boolean hasDefaultUser = false;
		
		for (User user : users) {
			if (user.getName().equals(USER_DEFAULT_NAME)) {
				log.info("Usuário existe!");
				hasDefaultUser = true;
			}
		}
		
		if (!hasDefaultUser) {
			User user = new User();
			user.setName(USER_DEFAULT_NAME);
			user.setEmail(USER_DEFAULT_EMAIL);
			log.info("Criando massa de dados inicial. {}", user);			
			this.userRepository.save(user);	
		}
		
	}

}
