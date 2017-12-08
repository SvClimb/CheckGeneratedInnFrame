package com.svyat.Window;

import com.svyat.InnEngine.InnGenerator;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by svcli on 24.10.2017.
 */
public class Frame {
    // Объявление всех компонентов калькулятора.
    JPanel windowContent;
    static JTextField field_gener_inn;
    static JTextField field_check_inn;
    static JTextField field_quantity_string;
    JButton button_check;
    JButton button_gener;
    JButton button_createFile;
    JLabel label_gener_inn;
    JLabel label_check_inn;
    JLabel label_gener_data;
    static JComboBox comboBoxLengthINN;
    static JComboBox comboBoxOperationType;

    public JComboBox getComboBox() {
        return comboBoxLengthINN;
    }
    // В конструкторе создаются все компоненты
    // и добавляются на фрейм с помощью комбинации
    // Borderlayout и Gridlayout
    public Frame() {
        //Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("Проверка и генерация ИНН");
        windowContent = new JPanel();
        windowContent.setLayout(null);

        // создаем меню
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");

        menuBar.add(fileMenu);

        // создаем заголовки полей
        label_check_inn = new JLabel("ИНН для проверки:");
        label_check_inn.setBounds(20,20, 150, 30);
        windowContent.add(label_check_inn);

        label_gener_inn = new JLabel("Сгенерировать ИНН:");
        label_gener_inn.setBounds(20, 55, 150, 30);
        windowContent.add(label_gener_inn);

        label_gener_data = new JLabel("Создать файл ТД:");
        label_gener_data.setBounds(20, 90, 150, 30);
        windowContent.add(label_gener_data);


        // создаем поля для ввода
        field_check_inn = new JTextField("", 12);
        field_check_inn.setBounds(230, 20, 150, 30);
        windowContent.add(field_check_inn);

        field_gener_inn = new JTextField("", 10);
        field_gener_inn.setBounds(230, 55, 150, 30);
        field_gener_inn.setEditable(false);
        windowContent.add(field_gener_inn);

        field_quantity_string = new JTextField();
        field_quantity_string.setBounds(170, 90, 50, 30);
        field_quantity_string.setToolTipText("Количество строк в файле");
        windowContent.add(field_quantity_string);

        // создаем кнопки
        button_check = new JButton("Проверить ИНН");
        button_check.setBounds(390, 20 , 150,  30);
        windowContent.add(button_check);

        button_gener = new JButton("Сгенерировать ИНН");
        button_gener.setBounds(390, 55, 150, 30);
        windowContent.add(button_gener);

        button_createFile = new JButton("Создать файл");
        button_createFile.setBounds(390, 90, 150, 30);
        windowContent.add(button_createFile);

        // создаем комбо-бокс кол-во цифр в ИНН и тип создаваемого файла
        String[] itemsLenghtInn = {"10","12"};
        comboBoxLengthINN = new JComboBox(itemsLenghtInn);
        comboBoxLengthINN.setBounds(170, 55, 50, 30);
        comboBoxLengthINN.setToolTipText("Выберите длину ИНН");
        windowContent.add(comboBoxLengthINN);

        String[] itemsTypeOperation = {"Загрузка мобилы", "Загрузка фиксы"}; // "Обновление данных" пока не реализовано
        comboBoxOperationType = new JComboBox(itemsTypeOperation);
        comboBoxOperationType.setBounds(230, 90, 150, 30);
        comboBoxOperationType.setToolTipText("Тип операции, для которой генерируется файл");
        windowContent.add(comboBoxOperationType);


        // делаем размер окна достаточным
        // для того, чтобы вместить все компоненты
        frame.pack();
        frame.setContentPane(windowContent);

        // Наконец, отображаем окно
        String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        String motif = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
        String metal = "javax.swing.plaf.metal.MetalLookAndFeel";

        // играем о стилями отображения окна
        try {
            UIManager.setLookAndFeel(windows);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(frame);


        frame.setBounds(760, 390, 560, 200);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // обработчик на кнопки
        GeneratorEngine generatorEngine = new GeneratorEngine(this);
        button_gener.addActionListener(generatorEngine);
        button_check.addActionListener(generatorEngine);
        button_createFile.addActionListener(generatorEngine);
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
                int setInt = Integer.valueOf((String) comboBoxLengthINN.getSelectedItem());
                InnGenerator inn_gen = new InnGenerator(setInt);
                if (setInt == 10) {
                    Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn10()));
                }
                else if (setInt == 12) {
                    Frame.field_gener_inn.setText(String.valueOf(inn_gen.getInn12()));
                }
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
                            JOptionPane.showMessageDialog(myAlertPanel, "ИНН должен содержать ровно 10 или 12 цифр.");
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

            else if (src == parent.button_createFile){
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.CSV","*.*");
                JFileChooser chooserSaveDialog = new JFileChooser();
                chooserSaveDialog.setFileFilter(filter);
                chooserSaveDialog.setDialogTitle("Сохранить файл");
                chooserSaveDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int q = chooserSaveDialog.showDialog(null, "Создать файл");


            }
        }
    }

}
