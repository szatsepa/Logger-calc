package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by serjoga on 09.04.17.
 */
public class WinOne {

    private static String[] elementsD;
    private static String[] elementsL;


    public static void main (String[] args) throws Throwable{

        DbSQLite db = new DbSQLite();

        elementsD = db.getDiameter();

        elementsL = db.getLong();

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Logger calculator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 500);
                frame.setLocation(400, 320);
                //JPanel panel = new JPanel();
                Box box = Box.createVerticalBox();

                box.add(new JLabel("Виберіть діаметер"));
                box.add(new JComboBox(elementsD));
                box.add(Box.createVerticalStrut(20));
                box.add(new JLabel("Виберіть довжину"));
                box.add(new JComboBox(elementsL));
                box.add(Box.createVerticalStrut(20));
                box.add(new JLabel("Вкажіть кількисть"));
                box.add(new JTextField(8));
                box.add(Box.createVerticalStrut(20));

                Box box1 = Box.createHorizontalBox();
                box1.add(new JButton("Рахувати"));
                box1.add(Box.createVerticalStrut(22));
                box1.add(new JButton("Стерти"));

                box.add(box1);
                box.add(Box.createVerticalStrut(12));

                box.add(new JTextArea());
                frame.getContentPane().add(box);
                frame.setVisible(true);
            }
        });
    }
}
