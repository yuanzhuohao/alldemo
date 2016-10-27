package com.example.abstractfactory;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public interface KingdomFactory {
    Castle createCastle();
    Army createArmy();
    King createKing();
}
