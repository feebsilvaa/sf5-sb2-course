package com.feedev.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedev.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
