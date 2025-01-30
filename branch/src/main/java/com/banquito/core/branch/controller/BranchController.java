package com.banquito.core.branch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/branches") // Endpoint
public class BranchController {
    private final BranchService service;
    private final BranchMapper mapper;

    public BranchController(BranchService service, BranchMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
}
