package com.example.abstractfactory;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class JessKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new JessCastle();
    }

    @Override
    public Army createArmy() {
        return new JessArmy();
    }

    @Override
    public King createKing() {
        return new JessKing();
    }
}
