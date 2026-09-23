package com.itheima.test;

import java.util.Random;

public class test {
    public static void main(String[] args) {
        //定义一维数组
        int[]tempArr={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //打乱顺序
        Random r=new Random();
        for (int i = 0; i <tempArr.length ; i++) {
            int index=r.nextInt(tempArr.length);
            //拿着遍历到的数据和临时索引进行交换
            int temp=tempArr[i];
            tempArr[i]=tempArr[index];
            tempArr[index]=temp;
        }
        //遍历数组
        for (int i = 0; i <tempArr.length ; i++) {
            System.out.print(tempArr[i]+" ");
        }
        System.out.println();

        //创建一个二维数组
        int[][] data=new int[4][4];

        //给数组添加数据
        for (int i = 0; i <tempArr.length ; i++) {
            data[i/4][i%4]=tempArr[i];
        }

    }
}
