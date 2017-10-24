package com.svyat.Window;

import com.svyat.InnEngine.InnGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by svcli on 24.10.2017.
 */
public class GeneratorEngine implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        InnGenerator inn_gen = new InnGenerator(12);
    }
}
