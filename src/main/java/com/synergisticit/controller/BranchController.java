package com.synergisticit.controller;

import com.synergisticit.domain.Address;
import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class BranchController {

    @Autowired
    BranchService branchService;

    @GetMapping("/branch")
    public String getBranches(Model model){
        model.addAttribute("branches", branchService.findAllBranches());
        return "branchList";
    }

    @GetMapping("/branchForm")
    public String showBranchForm(Model model){
        Branch branch = new Branch();
        branch.setBranchAddress(new Address());
        model.addAttribute("branch", branch);
        return "branchForm";
    }

    @GetMapping("/updateBranch/{branchId}")
    public String showUpdateForm(@PathVariable long branchId, Model model){
        Optional<Branch> foundBranch = branchService.findBranchById(branchId);

        if(foundBranch.isPresent()){
            model.addAttribute("branch", foundBranch);
            return "branchForm";
        }
        return "redirect:/branch";
    }

    @PostMapping("/branchForm")
    public String saveBranch(@Valid @ModelAttribute("branch") Branch branch, BindingResult br, Model model){
        if(br.hasErrors()){
            return "branchForm";
        }
        if(branch.getBranchId() != null){
            branchService.updateBranch(branch.getBranchId(), branch);
            return "redirect:/branch";
        } else {
            branchService.saveBranch(branch);
            return "redirect:/branch";
        }
    }

    @GetMapping("/deleteBranch/{branchId}")
    public String deleteBranch(@PathVariable long branchId){
        branchService.deleteBranchById(branchId);
        return "redirect:/branch";
    }
}
