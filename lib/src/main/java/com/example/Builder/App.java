package com.example.Builder;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public class App {
    public static void main(String[] args) {
        Hero warrior = new Hero.Builder(Profession.WARRIOR, "Jess")
                                .withArmor(Armor.CHAIN_MAIL)
                                .withHairColor(HairColor.BROWN)
                                .withHairType(HairType.CURLY)
                                .build();
        System.out.println(warrior.toString());
    }
}
