package com.example.Builder;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public enum  Weapon {
    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
