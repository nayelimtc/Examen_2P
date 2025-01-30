package com.banquito.core.cardaccount.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.cardaccount.model.Branch;

@Repository
public interface BranchRepository extends MongoRepository<Branch, String> {
} 