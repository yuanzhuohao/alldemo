package com.example.abstractfactory;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class MarkKingdomFactory implements KingdomFactory {

    @Override
    public Castle createCastle() {
        return null;
    }

    @Override
    public Army createArmy() {
        return null;
    }

    @Override
    public King createKing() {
        return null;
    }
}
