package com.diegofer.inventory.usecases;

import com.diegofer.inventory.dto.BranchDTO;
import com.diegofer.inventory.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterProduct {


    private final DatabaseClient dbClient;

    public Mono<ProductDTO> save(ProductDTO product){
        String newId = UUID.randomUUID().toString();
        dbClient.sql("INSERT INTO Product(id, name, description, inventoryStock, category, branch_id) VALUES(:id, :name, :description, :inventoryStock, :category, :branchId)")
                .bind("id", newId)
                .bind("name", product.getName())
                .bind("description", product.getDescription())
                .bind("inventoryStock", product.getInventoryStock())
                .bind("category", product.getCategory())
                .bind("branchId", product.getBranchId())
                .fetch()
                .one()
                .subscribe();
        product.setId(newId);
        return Mono.just(product);
    }

}
