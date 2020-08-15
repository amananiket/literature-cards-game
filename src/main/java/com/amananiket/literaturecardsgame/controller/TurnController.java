package com.amananiket.literaturecardsgame.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amananiket.literaturecardsgame.model.Turn;

@RestController
public class TurnController {

    @PostMapping("/turn")
    public Turn createTurn()
}
