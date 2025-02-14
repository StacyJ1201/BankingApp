package com.synergisticit.service;

import com.synergisticit.domain.Branch;
import com.synergisticit.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    BranchRepository branchRepository;

    @Override
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Optional<Branch> findBranchById(long branchId) {
        return branchRepository.findById(branchId);
    }

    @Override
    public List<Branch> findAllBranches() {

        return branchRepository.findAll();
    }

    @Override
    public Branch updateBranch(long branchId, Branch branch) {
        Optional<Branch> foundBranch = branchRepository.findById(branchId);
        Branch updateBranch = foundBranch.get();

        updateBranch.setBranchId(branchId);
        updateBranch.setBranchAddress(branch.getBranchAddress());
        updateBranch.setBranchName(branch.getBranchName());
        updateBranch.setBranchAccount(branch.getBranchAccount());

        return branchRepository.save(updateBranch);
    }

    @Override
    public void deleteBranchById(long id) {
        branchRepository.deleteById(id);
    }
}
