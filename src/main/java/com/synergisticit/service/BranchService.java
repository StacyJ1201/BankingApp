package com.synergisticit.service;

import com.synergisticit.domain.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {
    Branch saveBranch(Branch branch);
    Optional<Branch> findBranchById(long branchId);
    List<Branch> findAllBranches();
    Branch updateBranch(long branchId, Branch branch);
    void deleteBranchById(long id);
}
