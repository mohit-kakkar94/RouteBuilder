package com.example.RouteBuilderProject.repository;

import com.example.RouteBuilderProject.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
