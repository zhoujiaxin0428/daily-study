package com.itheima.ui;

import java.util.Random;

public class CodeUtil {
    public static String getCode(){
        char[] arr={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H'};
        Random r=new Random();
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i <4 ; i++) {
            int index=r.nextInt(arr.length);
            sb.append(arr[index]);
        }
        return sb.toString();
    }
}
