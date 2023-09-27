package com.diegofer.inventory.usecases;

import com.diegofer.inventory.dto.ProductDTO;
import com.diegofer.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AddStockToProduct {

    private final ProductRepository productRepository;

    private final ModelMapper mapper;

    public Mono<ProductDTO> apply(String productId, int stock){
        return productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Product with id: " + productId + " was not found")))
                .flatMap(product ->
                    productRepository.save(product.addStock(stock))).map(product -> mapper.map(product, ProductDTO.class));

    }



}
