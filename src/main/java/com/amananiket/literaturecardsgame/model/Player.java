package com.amananiket.literaturecardsgame.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    String playerAlias;
    TeamAlias teamAlias;
    List<Card> hand;

    public Player(String playerAlias, TeamAlias teamAlias) {
        this.playerAlias = playerAlias;
        this.teamAlias = teamAlias;
    }
}
