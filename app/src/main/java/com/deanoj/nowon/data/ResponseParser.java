package com.deanoj.nowon.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by deano on 01/01/15.
 */
public class ResponseParser {

    public ResponseParser() {

    }

    public static void main (String... args)
    {

        ResponseParser rp = new ResponseParser();

        String result = null;

        try {
            JSONObject json = new JSONObject(Faker.RT_RESPONSE_JSON);
            System.out.print(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(result);
    }
}
