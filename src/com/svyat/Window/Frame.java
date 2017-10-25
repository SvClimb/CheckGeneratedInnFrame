package com.svyat.Window;

import com.svyat.InnEngine.InnGenerator;

import javax.swing.*;

/**
 * Created by svcli on 24.10.2017.
 */
public class Frame {
    // Объявление всех компонентов калькулятора.
    JPanel windowContent;
    static JTextField field_gener_inn;
    JTextField field_check_inn;
    JButton button_check;
    JButton button_gener;
    JLabel label_gener_inn;
    JLabel label_check_inn;



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

        label_gener_inn = new JLabel("Сгенерированный ИНН:");
        label_gener_inn.setBounds(20, 50, 150, 30);
        windowContent.add(label_gener_inn);


        // создаем поля
        field_check_inn = new JTextField("", 10);
        field_check_inn.setBounds(170, 20, 150, 30);
        windowContent.add(field_check_inn);

        field_gener_inn = new JTextField("", 10);
        field_gener_inn.setBounds(170, 50, 150, 30);
        field_gener_inn.setEditable(false);
        windowContent.add(field_gener_inn);

        // создаем кнопки
        button_check = new JButton("Проверить ИНН");
        button_check.setBounds(20, 100 , 150,  40);
        windowContent.add(button_check);

        button_gener = new JButton("Сгенерировать ИНН");
        button_gener.setBounds(170, 100, 150, 40);
        windowContent.add(button_gener);

        // делаем размер окна достаточным
        // для того, чтобы вместить все компоненты
        frame.pack();
        frame.setContentPane(windowContent);

        // Наконец, отображаем окно
      //  frame.setSize(400,200);
        frame.setBounds(760, 390, 400, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// обработчик на кнопки
        GeneratorEngine generatorEngine = new GeneratorEngine();
        button_gener.addActionListener(generatorEngine);
    }
}
