package com.cooking.helpFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 06.11.2014.
 */
public class Testing {

    public static void main(String[] args){


        Map<Integer, Long> map = new HashMap();

        map.put(1, (long) 10000);
        System.out.println(map.get(1));

        map.put(1, (long) 12312);
        System.out.println(map.get(1));
    }

}
