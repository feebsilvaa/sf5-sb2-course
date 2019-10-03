package com.feedev.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.feedev.api.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

//	@Query("FROM User") // JPQL
//	public List<User> listaDeUsuarios();
//	
//	@Query(""
//			+ "SELECT u "
//			+ "FROM User u "
//			+ "WHERE u.name like %:name%") // JPQL - ... WHERE name LIKE '%value%'
//	public List<User> listaDeUsuariosComNome(String name);
	
	public List<User> findByNameContaining(String name); // Spring data JPA - ... WHERE name LIKE '%value%'
	
//	@Query(""
//			+ "FROM User "
//			+ "WHERE name = :name") // JPQL - ... WHERE name = 'value'
//	public List<User> listaDeUsuariosComNomeExato(String name);
	
	public List<User> findByName(String name); // Spring Data JPA - ... WHERE name = 'value'
	
	
}
