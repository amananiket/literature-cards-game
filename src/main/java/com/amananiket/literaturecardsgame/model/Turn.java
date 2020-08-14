package com.amananiket.literaturecardsgame.model;

import lombok.Data;

@Data
public class Turn {
    String gameId;
    Card calledCard;
    Player callingPlayer;
    Player calledPlayer;
}
