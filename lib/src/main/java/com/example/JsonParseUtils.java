package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jess Yuan on 11/10/2016.
 */

public class JsonParseUtils {

    /**
     * 把json字符串转为json对象
     * @param json
     * @return
     */
    public static JsonObject parseToJsonObject(String json) {
        JsonParser parser = new JsonParser();
        return ((JsonObject) parser.parse(json));
    }

    /**
     * 把json字符串转为json对象, 第二个参数按顺序的多层嵌套的json对象头部字符串
     * @param json
     * @param arg
     * @return
     */
    public static JsonObject parseToJsonObject(String json, String... arg) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject;

        ArrayList<String> args = new ArrayList<>();
        for (String s : arg) {
            args.add(s);
        }

        int n = args.size();

        if (n == 1) {
            return parser.parse(json).getAsJsonObject().getAsJsonObject(arg[0]);
        } else {
            jsonObject = parser.parse(json).getAsJsonObject().getAsJsonObject(arg[0]);
            args.remove(0);
            String[] a = args.toArray(new String[args.size()]);
            return parseToJsonObject(jsonObject.toString(), a);
        }

    }


    /** 把json字符串转为JsonArray对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToArray(String json, Class<T[]> clazz) {
        T[] list = new Gson().fromJson(json, clazz);

        return Arrays.asList(list);
    }

    /** 把json对象转为JsonArray对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToArray(JsonElement json, Class<T[]> clazz) {
        T[] list = new Gson().fromJson(json, clazz);

        return Arrays.asList(list);
    }

    /** 把json对象转为JsonArray对象, 第二个参数按顺序的多层嵌套的json对象头部字符串, 最后一个参数是
     *  JsonArray的头部字符串
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToArray(JsonElement json, Class<T[]> clazz, String... arg) {
        ArrayList<String> args = new ArrayList<>();
        for (String s : arg) {
            args.add(s);
        }

        String lastArg = args.get(args.size() - 1);
        args.remove(args.size() - 1);

        String jsonOb = parseToJsonObject(json.toString(), args.toArray(new String[args.size()]))
                .getAsJsonArray(lastArg).toString();

        T[] list = new Gson().fromJson(jsonOb, clazz);
        return Arrays.asList(list);
    }

    /** 把json字符串转为JsonArray对象, 第二个参数按顺序的多层嵌套的json对象头部字符串, 最后一个参数是
     *  JsonArray的头部字符串
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseToArray(String json, Class<T[]> clazz, String... arg) {
        ArrayList<String> args = new ArrayList<>();
        for (String s : arg) {
            args.add(s);
        }

        String lastArg = args.get(args.size() - 1);
        args.remove(args.size() - 1);

        String jsonOb = parseToJsonObject(json, args.toArray(new String[args.size()]))
                .getAsJsonArray(lastArg).toString();

        T[] list = new Gson().fromJson(jsonOb, clazz);
        return Arrays.asList(list);
    }

}
