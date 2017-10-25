package com.svyat.Window;

import com.svyat.InnEngine.InnGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.svyat.Window.Frame.comboBox;

/**
 * Created by svcli on 24.10.2017.
 */
public class GeneratorEngine implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        int setInt = Integer.valueOf((String)comboBox.getSelectedItem());
        InnGenerator inn_gen = new InnGenerator(setInt);
        if (setInt == 10) Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn10()));
        else if(setInt == 12) Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn12()));
    }
}
