package com.diegofer.inventory.repository;

import com.diegofer.inventory.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
}
