package com.amananiket.literaturecardsgame.model;

import java.util.List;

import lombok.Data;

@Data
public class Player {
    String playerAlias;
    String teamAlias;
    List<Card> hand;

    public Player(String playerAlias, String teamAlias) {
        this.playerAlias = playerAlias;
        this.teamAlias = teamAlias;
    }
}
