package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/**
 * Created by serjoga on 09.04.17.
 */
public class WinOne {

    private static String[] elementsD;
    private static String[] elementsL;
    private static JComboBox Cdiam;
    private static JComboBox Clong;
    private static JTextField cnt;
    private static DbSQLite db;


    public static void main (String[] args) throws Throwable{

         db = new DbSQLite();

        elementsD = DbSQLite.getDiameter();

        elementsL = DbSQLite.getLong();

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

                Cdiam = new JComboBox(elementsD);
                box.add(Cdiam);
                box.add(Box.createVerticalStrut(20));
                box.add(new JLabel("Виберіть довжину"));

                Clong = new JComboBox(elementsL);
                box.add(Clong);
                box.add(Box.createVerticalStrut(20));
                box.add(new JLabel("Вкажіть кількисть"));

                cnt = new JTextField(8);
                box.add(cnt);
                box.add(Box.createVerticalStrut(20));

                Box box1 = Box.createHorizontalBox();
                JButton calc = new JButton("Рахувати");
                calc.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        double val = 0;


                            String ds = Cdiam.getSelectedItem().toString();
                            String ls = Clong.getSelectedItem().toString();
                            String countS = cnt.getText();
                            int count = Integer.parseInt(countS);

                            String[] q = {ds, ls};
                        try {
                            val = DbSQLite.getValue(q);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        try{
                            double amount = count * val;
                            System.out.print("Pi " + amount);
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }





                    }
                });
                box1.add(calc);
                box1.add(Box.createVerticalStrut(22));

                JButton erase = new JButton("Стерти");
                erase.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                    }
                });
                box1.add(erase);

                box.add(box1);
                box.add(Box.createVerticalStrut(12));

                box.add(new JTextArea());
                frame.getContentPane().add(box);
                frame.setVisible(true);
            }
        });


    }


}
