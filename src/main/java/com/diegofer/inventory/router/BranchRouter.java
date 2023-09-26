package com.diegofer.inventory.router;

import com.diegofer.inventory.dto.BranchDTO;
import com.diegofer.inventory.model.Branch;
import com.diegofer.inventory.usecases.ViewBranches;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
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

}