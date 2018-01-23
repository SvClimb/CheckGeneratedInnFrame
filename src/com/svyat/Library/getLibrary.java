package com.svyat.Library;

import java.io.*;
import java.util.ArrayList;

public class getLibrary {
    static ArrayList<String> listConcuren = new ArrayList<String>();

    //
    private void readLibrary(ArrayList<String> list, File file) throws IOException{
        try{
            FileInputStream fstream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
            list.add(strLine);
            }
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}
