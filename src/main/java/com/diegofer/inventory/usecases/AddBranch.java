package com.diegofer.inventory.usecases;

import com.diegofer.inventory.model.Branch;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddBranch {


    private final DatabaseClient dbClient;

    public Mono<Branch> AddBranch(Branch branch){
        String newId = UUID.randomUUID().toString();
        dbClient.sql("insert into Branch(id, name, location) values(:id, :name, :location)")
                .bind("id", newId)
                .bind("name", branch.getName())
                .bind("location", branch.getLocation())
                .fetch()
                .one()
                .subscribe();
        branch.setId(newId);
        return Mono.just(branch);
    }



}
