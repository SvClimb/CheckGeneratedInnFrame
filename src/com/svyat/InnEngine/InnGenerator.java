package com.svyat.InnEngine;

import com.svyat.Window.Frame;

import java.util.Random;

/**
 * Created by svcli on 24.10.2017.
 */
public class InnGenerator {


    private long inn10;
    private long inn12;
    private boolean inn_true;
    private static byte[] spep10 = {2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static byte[] spep12_n2 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static byte[] spep12_n1 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};

    public long getInn10() {
        return inn10;
    }

    public long getInn12() {
        return inn12;
    }

    public InnGenerator(int i)
    {
        if (i == 10) {
            inn_true = false;
            while (inn_true != true) {
                inn10 = Inn_10_gen();
                inn_true = Check_10_inn(inn10);
                System.out.println(inn10);
                System.out.println(Check_10_inn(inn10));
            }
        }
        else if (i == 12) {
            inn_true = false;
            while (inn_true != true) {
                inn12 = Inn_12_gen();
                inn_true = Check_12_inn(inn12);
                System.out.println(inn12);
                System.out.println(Check_12_inn(inn12));
            }

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


    private boolean Check_12_inn(long in){
        String str12 = String.valueOf(in);
        int sum_12_2 = 0;
        int control_number_n1 = 0;
        int control_number_n2 = 0;

        for(int i=0; i<spep12_n2.length; i++){
            sum_12_2 = sum_12_2 + (Integer.parseInt( String.valueOf(str12.charAt(i)) )*spep12_n2[i]);
        }

        if (sum_12_2%11==10) control_number_n2=0;
        else control_number_n2 = sum_12_2%11;

        int sum_12 = 0;
        for(int j=0; j<spep12_n1.length; j++){
            sum_12 = sum_12 + (Integer.parseInt( String.valueOf(str12.charAt(j)) )*spep12_n1[j]);
        }
        if (sum_12%11==10) control_number_n1=0;
        else control_number_n1 = sum_12%11;

        if ((control_number_n1==Integer.parseInt( String.valueOf(str12.charAt(11)))) && (control_number_n2==Integer.parseInt( String.valueOf(str12.charAt(10))))) return true;
        else return false;
    }

}
