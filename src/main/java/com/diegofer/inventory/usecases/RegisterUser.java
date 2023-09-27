package com.diegofer.inventory.usecases;

import com.diegofer.inventory.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterUser {

    private final DatabaseClient dbClient;

    public Mono<UserDTO> save(UserDTO user){
        String newId = UUID.randomUUID().toString();
        dbClient.sql("INSERT INTO User(id, name, password, email, role, branch_id) VALUES(:id, :name, :password, :email, :role, :branchId)")
                .bind("id", newId)
                .bind("name", user.getName())
                .bind("password", user.getPassword())
                .bind("email", user.getEmail())
                .bind("role", user.getRole())
                .bind("branchId", user.getBranchId())
                .fetch()
                .one()
                .subscribe();
        user.setId(newId);
        return Mono.just(user);
    }

}
