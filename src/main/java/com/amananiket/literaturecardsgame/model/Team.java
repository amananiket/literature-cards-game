package com.amananiket.literaturecardsgame.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Team {
    List<Player> players;
    TeamAlias teamAlias;
}
