package com.diegofer.inventory.usecases;

import com.diegofer.inventory.dto.ProductDTO;
import com.diegofer.inventory.model.Product;
import com.diegofer.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetProductsByBranchId {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    public Flux<ProductDTO> apply(String branchId){

        return productRepository.findByBranchId(branchId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Branch not found")))
                .map(product -> mapper.map(product, ProductDTO.class));
    }
}
