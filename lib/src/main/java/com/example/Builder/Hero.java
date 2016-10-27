package com.example.Builder;

/**
 * Created by Jess Yuan on 27/10/2016.
 */

public final class Hero {

    private final Armor mArmor;
    private final HairColor mHairColor;
    private final HairType mHairType;
    private final Profession mProfession;
    private final Weapon mWeapon;
    private final String name;

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("This is a ").append(this.mProfession).append(" hero\n");
        sb.append("Name: ").append(this.getName()).append('\n');
        if (mArmor != null) {
            sb.append("Armor: ").append(this.mArmor).append('\n');
        }

        if (mHairColor != null) {
            sb.append("HairColor: ").append(mHairColor).append('\n');
        }

        if (mHairType != null) {
            sb.append("HairType: ").append(mHairType).append('\n');
        }

        if (mWeapon != null) {
            sb.append("Weapon: ").append(mWeapon).append('\n');
        }

        return sb.toString();
    }

    private Hero(Builder builder) {
        this.mArmor = builder.mArmor;
        this.mHairColor = builder.mHairColor;
        this.mHairType = builder.mHairType;
        this.mProfession = builder.mProfession;
        this.mWeapon = builder.mWeapon;
        this.name = builder.name;
    }

    public static class Builder {
        private Armor mArmor;
        private HairColor mHairColor;
        private HairType mHairType;
        private final Profession mProfession;
        private Weapon mWeapon;
        private final String name;

        Builder(Profession profession, String name) {
            if (profession == null || name == null) {
                throw new IllegalArgumentException("Profession and Name can not be null");
            }
            this.mProfession = profession;
            this.name = name;
        }

        public Builder withArmor(Armor armor) {
            this.mArmor = armor;
            return this;
        }

        public Builder withHairColor(HairColor hairColor) {
            this.mHairColor = hairColor;
            return this;
        }

        public Builder withHairType(HairType hairType) {
            this.mHairType = hairType;
            return this;
        }

        public Builder withWeapon(Weapon weapon) {
            this.mWeapon = weapon;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }

    }


}
