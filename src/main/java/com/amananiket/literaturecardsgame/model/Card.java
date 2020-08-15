package com.amananiket.literaturecardsgame.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Card {
    Denomination denomination;
    Suit suit;

    @Override
    public boolean equals(Object otherCard) {
        if (this == otherCard) return true;
        if (otherCard == null || getClass() != otherCard.getClass()) return false;

        Card that = (Card) otherCard;

        if (!that.getSuit().name().equals(this.getSuit().name())) return false;
        return that.getDenomination().name().equals(this.getDenomination().name());
    }
}
