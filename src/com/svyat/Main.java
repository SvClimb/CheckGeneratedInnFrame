package com.svyat;

import com.svyat.InnEngine.InnGenerator;
import com.svyat.Library.getLibrary;
import com.svyat.Window.Frame;

import com.svyat.Window.Frame;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        getLibrary gl = new getLibrary("Concurent", new File("src\\com\\svyat\\FilePackage\\Concurent.txt"));
        getLibrary g2 = new getLibrary("Test", new File("src\\com\\svyat\\FilePackage\\test.txt"));
        Frame fr = new Frame();
    }
}
