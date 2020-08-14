package com.amananiket.literaturecardsgame.model;

public enum Denomination {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    String value;

    Denomination(String value) {
        this.value = value;
    }
}
