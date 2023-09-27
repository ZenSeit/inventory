package com.diegofer.inventory.repository;

import com.diegofer.inventory.model.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product, String> {

    Flux<Product> findByBranchId(String branchId);
}
