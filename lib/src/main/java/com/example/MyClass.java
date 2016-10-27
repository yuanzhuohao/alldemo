package com.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Arrays;

public class MyClass {

    public static void main(String[] args) {

        String json = "{\n" +
                "  \"data\": {\n" +
                "    \"list\": [\n" +
                "      {\n" +
                "        \"registrationno\": \"GPSone公仔\",\n" +
                "        \"commno\": \"9011\",\n" +
                "        \"vehicleid\": 21653,\n" +
                "        \"terminaltypename\": \"501\",\n" +
                "        \"terminaltypeid\": 34,\n" +
                "        \"extime\": \"1900-01-01 15:22:12.0\",\n" +
                "        \"userinfolist\": [],\n" +
                "        \"timestamp\": \"783296523\",\n" +
                "        \"operation\": \"insert\",\n" +
                "        \"registrationnocolor\": \"registrationnocolor\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"registrationno\": \"软件开发部测试(&1)\",\n" +
                "        \"commno\": \"15807560429\",\n" +
                "        \"vehicleid\": 21666,\n" +
                "        \"terminaltypename\": \"202\",\n" +
                "        \"terminaltypeid\": 1,\n" +
                "        \"extime\": \"1899-12-30 00:00:00.0\",\n" +
                "        \"userinfolist\": [],\n" +
                "        \"timestamp\": \"783296523\",\n" +
                "        \"operation\": \"insert\",\n" +
                "        \"registrationnocolor\": \"registrationnocolor\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"registrationno\": \"软件开发部测试2(&2)\",\n" +
                "        \"commno\": \"15820557343\",\n" +
                "        \"vehicleid\": 21685,\n" +
                "        \"terminaltypename\": \"202\",\n" +
                "        \"terminaltypeid\": 1,\n" +
                "        \"extime\": \"1899-12-30 00:00:00.0\",\n" +
                "        \"userinfolist\": [],\n" +
                "        \"timestamp\": \"783296523\",\n" +
                "        \"operation\": \"insert\",\n" +
                "        \"registrationnocolor\": \"registrationnocolor\"\n" +
                "      }]\n" +
                "  },\n" +
                "  \"msg\": \"Hello world!\",\n" +
                "  \"count\": 100\n" +
                "}";

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(json).getAsJsonObject().getAsJsonObject("data").getAsJsonArray("list");
        System.out.println(JsonParseUtils.parseToArray(json, Vehicle[].class, "data", "list").get(0).getCommno());



    }
}
