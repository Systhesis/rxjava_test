package com.neil.rxjava_test.utils;

import java.util.ArrayList;
import java.util.List;

public class DataUtils {


    //耗时操作
    public static List<Integer> getData() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
