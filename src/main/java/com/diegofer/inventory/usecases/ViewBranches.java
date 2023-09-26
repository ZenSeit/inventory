package com.diegofer.inventory.usecases;

import com.diegofer.inventory.dto.BranchDTO;
import com.diegofer.inventory.model.Branch;
import com.diegofer.inventory.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class ViewBranches {

    private final BranchRepository branchRepository;

    private final ModelMapper mapper;

    public Flux<BranchDTO> getBranches(){
        return branchRepository.findAll().switchIfEmpty(Flux.empty()).map(branch -> mapper.map(branch, BranchDTO.class));
    }
}
