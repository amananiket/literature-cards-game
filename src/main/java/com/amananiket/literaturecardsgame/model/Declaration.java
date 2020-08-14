package com.amananiket.literaturecardsgame.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Declaration {
    Map<Player, List<Card>> declaration;
}
