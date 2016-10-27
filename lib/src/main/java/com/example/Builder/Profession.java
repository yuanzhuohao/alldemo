package com.example.Builder;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public enum Profession {
    WARRIOR, THIEF, MAGE, PRIEST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
