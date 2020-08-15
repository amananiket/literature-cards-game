package com.amananiket.literaturecardsgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amananiket.literaturecardsgame.model.Game;
import com.amananiket.literaturecardsgame.service.GameService;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/game")
    public Game createGame(@RequestBody Game requestedGame) {
        return gameService.createGame(requestedGame);
    }

    @GetMapping("/game/{suggestion}")
    public String getGameId(@PathVariable String suggestion) {
        return gameService.getGameId(suggestion);
    }
}
