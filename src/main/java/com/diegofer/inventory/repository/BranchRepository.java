package com.diegofer.inventory.repository;

import com.diegofer.inventory.model.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends ReactiveCrudRepository<Branch, String> {
}
