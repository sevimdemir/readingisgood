package com.getir.readingisgood.persist.repository;

import com.getir.readingisgood.persist.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

}
