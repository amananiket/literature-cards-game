package com.amananiket.literaturecardsgame.model;

public enum Denomination {
    ACE("A", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13);

    String value;

    public int getNotion() {
        return notion;
    }

    public boolean isHigherSet() {
        return notion > 7;
    }

    int notion;

    Denomination(String value, int notion) {
        this.value = value;
        this.notion = notion;
    }
}
