package com.cooking.helpFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 06.11.2014.
 */
public class StringToJson {

    Map<String, String> json = new HashMap<String, String>();

    public StringToJson(String string){
        String index = "";
        String value = "";
        for(Integer i = 1; i < string.length(); i++){
            while (string.charAt(i) != ':') {
                if(string.charAt(i) != '\"')
                    index += string.charAt(i);
                i++;
            }
            i++;
            while (string.charAt(i) != ',' && string.charAt(i) != '}') {
                if(i >= string.length())
                    break;
                if(string.charAt(i) != '\"')
                    value += string.charAt(i);
                i++;
            }

            json.put(index, value);
            index = "";
            value = "";
        }
    }

    public Map<String, String> getJson() {
        return json;
    }
}
