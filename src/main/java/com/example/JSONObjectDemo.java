package com.example;

import org.json.JSONObject;

public class JSONObjectDemo {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("100_tmeit", true);
        json.put("101_tmeen", false);

        System.out.println(json);

        String key = "100_tmeit12";
        boolean flag = false;
        if (json.has(key)) {
            flag = Boolean.valueOf((boolean) json.get(key));
        }
        System.out.println(flag); 

        if(flag)
            System.out.println("The flag is set to true");
        else 
            System.err.println("false");
    }
}
