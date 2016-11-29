package com.example;

/**
 * Created by JessYuan on 15/11/2016.
 */

public class Main {
    public static void main(String[] args) {
        String str = "asdfadf}\n{asdf";
        System.out.println(str);
        str = str.replaceAll("\\}\\n\\{","\\},\\{");
        System.out.println(str);
    }
}
