package com.diegofer.inventory.router;

import com.diegofer.inventory.dto.BranchDTO;
import com.diegofer.inventory.dto.ProductDTO;
import com.diegofer.inventory.dto.UserDTO;
import com.diegofer.inventory.model.Branch;
import com.diegofer.inventory.usecases.*;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BranchRouter {

    private WebClient branch;

    public BranchRouter() {
        branch = WebClient.create("http://localhost:8080");
    }

    @Bean
    public RouterFunction<ServerResponse> viewAllBranches(ViewBranches viewBranches) {
        return route(GET("/branches"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(viewBranches.getBranches(), BranchDTO.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> addNewBranch(AddBranch addBranch) {
        return route(POST("/branches").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BranchDTO.class)
                        .flatMap(branchDTO -> addBranch.AddBranch(branchDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))

                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> registerNewProduct(RegisterProduct registerProduct) {
        return route(POST("/products").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> registerProduct.save(productDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))

                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> registerNewUser(RegisterUser registerUser) {
        return route(POST("/users").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(UserDTO.class)
                        .flatMap(userDTO -> registerUser.save(userDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))

                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).bodyValue(throwable.getMessage()))));
    }

    @Bean
    public RouterFunction<ServerResponse> patchAddProductStock(AddStockToProduct addStockToProduct) {
        return route(PATCH("/products/id/{id}/stock/{stock}/add"),
                request -> addStockToProduct.apply(request.pathVariable("id"), Integer.valueOf(request.pathVariable("stock")))
                        .flatMap(item -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(item))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getProductsByBranch(GetProductsByBranchId useCase) {
        return route(GET("/products/{branch_id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("branch_id")), ProductDTO.class))
                        .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_FOUND).bodyValue(throwable.getMessage()))
        );


    }

}