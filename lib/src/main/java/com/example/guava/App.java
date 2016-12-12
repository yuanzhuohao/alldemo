package com.example.guava;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JessYuan on 08/12/2016.
 */

public class App {

    public static void main(String[] args) {
        ImmutableSet<String> COLOR_NAME = ImmutableSet.of("RED", "BLUE", "YELLOW", "PINK", "WHITE", "BLACK");

        ImmutableList<String> strings = ImmutableList.copyOf(COLOR_NAME);

        for (String string : strings) {
            System.out.println(string);
        }

        Preconditions.checkNotNull(args);
    }
}
