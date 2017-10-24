package com.svyat.InnEngine;

import java.util.Random;

/**
 * Created by svcli on 24.10.2017.
 */
public class InnGenerator {
    Random random = new Random();
    private long inn10;
    private long inn12;
    private static byte[] spep10 = {2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static byte[] spep12_1 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static byte[] spep12_2 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    public InnGenerator(int i)
    {
        if (i == 10){
            inn10 = Inn_10_gen();
            System.out.println(inn10);
            System.out.println(Check_10_inn(inn10));
        }
        else if (i == 12){
            inn12 = Inn_12_gen();
            System.out.println(inn12);
        }

    }
    private long Inn_10_gen(){
        long gen10 = 1000000000L + (long)(Math.random()*8999999999L);
        return gen10;
    }

    private long Inn_12_gen(){
        long gen12 = 100000000000L + (long)(Math.random()*899999999999L);
        return gen12;
    }

    private boolean Check_10_inn(long in){
        String str = String.valueOf(in);
        int sum = 0;
        int control_number = 0;
        for(int i=0; i<spep10.length; i++){
            sum = sum + (Integer.parseInt( String.valueOf(str.charAt(i)) )*spep10[i]);
        }
        if (sum%11==10) control_number=0;
        else control_number = sum%11;

        if (control_number==Integer.parseInt( String.valueOf(str.charAt(9)))) return true;
        else return false;
    }

}
