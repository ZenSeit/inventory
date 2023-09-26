package com.diegofer.inventory.controller;

import com.diegofer.inventory.model.Branch;
import com.diegofer.inventory.service.BranchService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public Flux<Branch> viewAllBranches(){
        return branchService.getBranches();
    }

    @PostMapping
    public Mono<Branch> addBranch(@RequestBody Branch branch){
        return branchService.addBranch(branch);
    }



}
