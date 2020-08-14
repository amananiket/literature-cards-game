package com.amananiket.literaturecardsgame.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Declaration {
    Suit declaredSuit;
    Boolean isHigherSuit;
    String gameId;
    Map<Player, List<Card>> declaration;
    String playerAlias;
    String teamAlias;

    public Boolean isHigherSuit() {
        return this.isHigherSuit;
    }
}
