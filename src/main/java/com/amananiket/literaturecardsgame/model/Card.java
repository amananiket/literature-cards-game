package com.amananiket.literaturecardsgame.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Card {
    Denomination denomination;
    Suit suit;
}
