package com.svyat.Library;

import java.io.*;
import java.util.ArrayList;

public class getLibrary {


    ArrayList<String> name;

    public getLibrary(String names, File file) throws IOException {
         this.name = new ArrayList<String>();
         this.name.add("");
         readLibrary(this.name, file);
         writeLibrary(name);
    }


    // Метод читает из файла и добавляет в массив
    private static void readLibrary(ArrayList<String> list, File file) throws IOException{
        try{
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
            list.add(strLine);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Ошибка чтения файла");
        }
    }
    private static void writeLibrary(ArrayList<String> arr){
        System.out.println(arr);
        for (int i = 0; i < arr.size(); i++){
            System.out.println(arr.get(i));
        }
    }
}
