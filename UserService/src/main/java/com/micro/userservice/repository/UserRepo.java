package com.micro.userservice.repository;

import com.micro.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
