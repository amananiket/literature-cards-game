package com.amananiket.literaturecardsgame.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
    Team teamRed;
    Team teamBlue;
    Player activePlayer;
    String gameAlias;
}
