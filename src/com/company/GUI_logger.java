package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by serjoga on 09.04.17.
 */
public class GUI_logger {

    private static String[] elementsD;
    private static String[] elementsL;
    private static String[] elementsLeng;
    private static JComboBox selectLeng;
    private static JComboBox diamC;
    private static JComboBox longC;
    private static JTextField cnt, ant, antSumm;
    private static DbSQLite db;
    private static JCheckBox cb;
    private static JButton calculate, erace, exit;
    private static Font font;
    private static Font fontL;
    private static JFrame frame;
    private static String message;


    public static void main (String[] args) throws Throwable{

        db = new DbSQLite();

        elementsD = DbSQLite.getDiameter();

        elementsL = DbSQLite.getLong();

        elementsLeng = new String[]{"Ukranian","English","Russian"};

        message = "Помилка введення - тільки цифри!";

        font = new Font("Calibri", Font.BOLD, 18);

        fontL = new Font("Calibri", Font.BOLD,16);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame = new JFrame("Logger calculator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 500);
                frame.setLocation(400, 320);

                String[] leng = new String[7];
                leng = selectLenguage("Ukranian");

                cb = new JCheckBox("ISO 4480-83");
                cb.setFont(fontL);
                JLabel lbl0 = new JLabel(leng[1]);
                lbl0.setFont(fontL);
                JLabel lbl1 = new JLabel(leng[2]);
                lbl1.setFont(fontL);
                diamC = new JComboBox(elementsD);
                JLabel lbl2 = new JLabel(leng[3]);
                lbl2.setFont(fontL);
                longC = new JComboBox(elementsL);
                JLabel lbl3 = new JLabel(leng[4]);
                lbl3.setFont(fontL);
                JLabel lbl4 = new JLabel(leng[0]);
                lbl4.setFont(fontL);
                JLabel lbl5 = new JLabel(leng[7]);
                lbl5.setFont(fontL);
                selectLeng = new JComboBox(elementsLeng);
                selectLeng.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        String l = String.valueOf(selectLeng.getSelectedItem());
                        String[] tmp = selectLenguage(l);
                        lbl0.setText(tmp[1]);
                        lbl1.setText(tmp[2]);
                        lbl2.setText(tmp[3]);
                        lbl3.setText(tmp[4]);
                        lbl4.setText(tmp[0]);
                        lbl5.setText(tmp[7]);
                        calculate.setText(tmp[5]);
                        erace.setText(tmp[6]);
                        message = tmp[8];
                    }
                });
                cnt = new JTextField(12);
                cnt.setFont(font);

                calculate = new JButton(leng[5]);
                erace = new JButton(leng[6]);
                exit = new JButton("Exit");
                ant = new JTextField(12);
                antSumm = new JTextField(12);
                ant.setEditable(false);
                antSumm.setEditable(false);
                ant.setFont(font);
                antSumm.setFont(font);



                GridBagLayout gbl = new GridBagLayout();
                frame.setLayout(gbl);
                GridBagConstraints constraints = new GridBagConstraints();

                constraints.anchor = GridBagConstraints.NORTHWEST;
                constraints.fill   = GridBagConstraints.NONE;
                constraints.gridheight = 1;
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                constraints.gridx = GridBagConstraints.RELATIVE;
                constraints.gridy = GridBagConstraints.RELATIVE;
                constraints.insets = new Insets(10, 10, 0, 0);

                gbl.setConstraints(cb,constraints);
                frame.add(cb);

                constraints.gridwidth = GridBagConstraints.REMAINDER;

                gbl.setConstraints(lbl4,constraints);
                frame.add(lbl4);

                gbl.setConstraints(selectLeng,constraints);
                frame.add(selectLeng);


                gbl.setConstraints(lbl0, constraints);
                frame.add(lbl0);

                constraints.gridwidth = 1;

                gbl.setConstraints(lbl1, constraints);
                frame.add(lbl1);

                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(lbl2, constraints);
                frame.add(lbl2);

                constraints.gridwidth = 1;

                gbl.setConstraints(diamC, constraints);
                frame.add(diamC);

                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(longC, constraints);
                frame.add(longC);

                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(lbl3,constraints);
                frame.add(lbl3);
                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(cnt,constraints);
                frame.add(cnt);
                cnt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        try {
                            actionCalculate();
                        } catch (Throwable throwable) {
                            errSymbol();
                        }
                    }
                });

                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(calculate, constraints);
                frame.add(calculate);
                calculate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

                        try {
                            actionCalculate();
                        } catch (Throwable throwable) {
                            errSymbol();
                            //throwable.printStackTrace();
                        }


                    }
                });

                gbl.setConstraints(ant,constraints);
                frame.add(ant);

                gbl.setConstraints(lbl5,constraints);
                frame.add(lbl5);

                gbl.setConstraints(antSumm,constraints);
                frame.add(antSumm);

                constraints.gridwidth = 1;

                gbl.setConstraints(erace,constraints);
                frame.add(erace);
                erace.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        antSumm.setText("");
                    }
                });


                gbl.setConstraints(exit,constraints);
                frame.add(exit);
                exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);
            }
        });


    }

    private static void errSymbol(){
        JOptionPane.showMessageDialog(frame,message);
        cnt.setText("");
    }

    private static void actionCalculate() throws Throwable{
        double val = 0;


        String ds = diamC.getSelectedItem().toString();
        String ls = longC.getSelectedItem().toString();
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
            double amount = Math.ceil((count * val)*100)/100;
            double amountSumm = 0;
            ant.setText(String.valueOf(amount));
            if(antSumm.getText().equals("")){
                antSumm.setText(String.valueOf(amount));
            }else{
                amountSumm = Math.ceil((Double.parseDouble(antSumm.getText()) + amount)*100)/100;
                antSumm.setText(String.valueOf(amountSumm));
            }

        }catch (NumberFormatException e){
            e.printStackTrace();
        }


    }

    private static String[] selectLenguage(String leng){

        String[] vocabulary = new String[7];
        String[] vocabularyU = new String[]{"Виберіть мову","Виберіть","Діаметер см","Довжину м","Вкажіть кількисть","Рахувати","Стерти","Всього м.куб","Помилка введення - тільки цифри!"};
        String[] vocabularyE = new String[]{"Select a language","Select","Diameter sm","Length m","Specify the quantity","Calculate","Clear", "Total cbm","Input error - only digits!"};
        String[] vocabularyR = new String[]{"Выберите язык","Выберите","Диаметр см","Длину м","Укажите количество","Расчитать","Очистить", "Всего м.куб","Ошибка ввода - только цифры!"};

        if(leng.equals("English")){
            vocabulary = vocabularyE;
        }else if (leng.equals("Russian")){
            vocabulary = vocabularyR;
        }else {
            vocabulary = vocabularyU;
        }

        return vocabulary;
    }


}
