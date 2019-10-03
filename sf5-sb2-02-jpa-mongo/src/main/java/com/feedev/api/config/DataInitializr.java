package com.feedev.api.config;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.feedev.api.model.User;
import com.feedev.api.repository.UserRepository;

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
			log.info("Criando massa de dados inicial.");			
			createUser(USER_DEFAULT_NAME, USER_DEFAULT_EMAIL);
		}
		
		if (users.isEmpty()) {
			createUser("João Batista", "joao@sf5sb2.com");
			createUser("João Bosco", "joao@sf5sb2.com");
			createUser("Maria", "maria@sf5sb2.com");		
		}
		
		log.info("");
		
		Optional<User> userOpt = this.userRepository.findById(2L);
		if (userOpt.isPresent()) { 
			log.info("Usuário encontrado: {}", userOpt.get());
		}
		
//		log.info("listaDeUsuarios: {}", this.userRepository.listaDeUsuarios());
//		log.info("listaDeUsuariosComNome: {}", this.userRepository.listaDeUsuariosComNome("ao"));
		log.info("findByNameContaining: {}", this.userRepository.findByNameContaining("ão"));
//		log.info("listaDeUsuariosComNomeExato: {}", this.userRepository.listaDeUsuariosComNomeExato("Joao Batista"));
		log.info("findByName: {}", this.userRepository.findByName("Maria"));
		
		log.info("");		
	}
	
	private void createUser(String name, String email) {
		User user = new User(name, email);		
		this.userRepository.save(user);	
	}
	
}