package com.svyat.Window;

import com.svyat.InnEngine.InnGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by svcli on 24.10.2017.
 */
public class Frame {
    // Объявление всех компонентов калькулятора.
    JPanel windowContent;
    static JTextField field_gener_inn;
    static JTextField field_check_inn;
    JButton button_check;
    JButton button_gener;
    JLabel label_gener_inn;
    JLabel label_check_inn;
    static JComboBox comboBox;

    public JComboBox getComboBox() {
        return comboBox;
    }
    // В конструкторе создаются все компоненты
    // и добавляются на фрейм с помощью комбинации
    // Borderlayout и Gridlayout
    public Frame() {
        //Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("Проверка и генерация ИНН");
        windowContent = new JPanel();
        windowContent.setLayout(null);

        // создаем заголовки полей
        label_check_inn = new JLabel("ИНН для проверки:");
        label_check_inn.setBounds(20,20, 150, 30);
        windowContent.add(label_check_inn);

        label_gener_inn = new JLabel("Сгенерировать ИНН:");
        label_gener_inn.setBounds(20, 50, 150, 30);
        windowContent.add(label_gener_inn);


        // создаем поля
        field_check_inn = new JTextField("", 10);
        field_check_inn.setBounds(230, 20, 150, 30);
        windowContent.add(field_check_inn);

        field_gener_inn = new JTextField("", 10);
        field_gener_inn.setBounds(230, 50, 150, 30);
        field_gener_inn.setEditable(false);
        windowContent.add(field_gener_inn);

        // создаем кнопки
        button_check = new JButton("Проверить ИНН");
        button_check.setBounds(20, 100 , 150,  40);
        windowContent.add(button_check);

        button_gener = new JButton("Сгенерировать ИНН");
        button_gener.setBounds(170, 100, 150, 40);
        windowContent.add(button_gener);

        // создаем комбо-бокс
        String[] items = {"10","12"};
        comboBox = new JComboBox(items);
        comboBox.setBounds(170, 50, 50, 30);
        windowContent.add(comboBox);

        // делаем размер окна достаточным
        // для того, чтобы вместить все компоненты
        frame.pack();
        frame.setContentPane(windowContent);

        // Наконец, отображаем окно
        frame.setBounds(760, 390, 400, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // обработчик на кнопки
        GeneratorEngine generatorEngine = new GeneratorEngine(this);
        button_gener.addActionListener(generatorEngine);
        button_check.addActionListener(generatorEngine);
    }



    public class GeneratorEngine implements ActionListener {
        Frame parent;

        GeneratorEngine(Frame parent){
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();


            Object src = e.getSource();

            if (src == parent.button_gener) {
                int setInt = Integer.valueOf((String) comboBox.getSelectedItem());
                InnGenerator inn_gen = new InnGenerator(setInt);
                if (setInt == 10) Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn10()));
                else if (setInt == 12) Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn12()));
            }

            else if (src == parent.button_check){

                String dispFieldText=parent.field_check_inn.getText();

                JPanel myAlertPanel = new JPanel();
                if (!"".equals(dispFieldText)){
                    try {
                        int lengthDisplayFieldText = dispFieldText.length();
                        long innForCheck = Long.parseLong(dispFieldText);
                        String statusCheck;

                        if (((lengthDisplayFieldText == 10 || lengthDisplayFieldText == 12))) {
                            InnGenerator inn_gen = new InnGenerator(innForCheck);

                            if (inn_gen.bool == true) statusCheck = ("Корректный");
                            else statusCheck = ("Некорректный");

                            JOptionPane.showMessageDialog(myAlertPanel,statusCheck +  " ИНН" );
                        }

                        else if ((lengthDisplayFieldText != 10 || lengthDisplayFieldText != 12)) {
                            JOptionPane.showMessageDialog(myAlertPanel, "ИНН должен содержать 10 или 12 цифр.");
                            Frame.field_check_inn.setText(null);
                        }
                    }
                    catch (NumberFormatException n){
                        JOptionPane.showMessageDialog(myAlertPanel, "ИНН должен содержать только цифры.");
                        Frame.field_check_inn.setText(null);
                    }
                }
                else JOptionPane.showMessageDialog(myAlertPanel,"Введите ИНН для проверки.");
            }
        }
    }

}
