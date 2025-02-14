package com.synergisticit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActionsController {

    @GetMapping("/bankActions")
    public String showActionButtons(){

        return"bankActions";
    }
}
