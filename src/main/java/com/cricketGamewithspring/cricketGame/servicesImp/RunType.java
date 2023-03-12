package com.cricketGamewithspring.cricketGame.servicesImp;

public enum RunType {

    WIDE(1),
    NOBALL(1),
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    WICKET(7);

    private int value;

    private RunType(int value) {
        this.value = value;
    }

    public int getRun() {
        return this.value;
    }
}
