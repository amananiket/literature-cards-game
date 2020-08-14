package com.amananiket.literaturecardsgame.model;

import java.util.List;

import lombok.Data;

@Data
public class Team {
    List<Player> players;
    String teamAlias;
}
