package com.example.Builder;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public enum HairColor {

    WHITE, BLOND, RED, BROWN, BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

}