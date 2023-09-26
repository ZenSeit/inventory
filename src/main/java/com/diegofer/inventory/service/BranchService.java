package com.diegofer.inventory.service;

import com.diegofer.inventory.model.Branch;
import com.diegofer.inventory.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    private final DatabaseClient dbClient;

    public Flux<Branch> getBranches(){
        return branchRepository.findAll();
    }

    public Mono<Branch> addBranch(Branch branch){

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
