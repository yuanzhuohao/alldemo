package com.example.abstractfactory;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class Demo {
    public static void createKingdom(KingdomFactory factory) {
        factory.createKing().getDescribe();
        factory.createCastle().getDescribe();
        factory.createArmy().getDescribe();
    }

    public static void main(String[] args) {
        createKingdom(new JessKingdomFactory());
//        createKingdom(new MarkKingdomFactory());
    }
}
