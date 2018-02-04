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
    private static JTextField cnt, ant;
    private static DbSQLite db;
    private static JCheckBox cb;
    private static JButton calculate, erace;


    public static void main (String[] args) throws Throwable{

        db = new DbSQLite();

        elementsD = DbSQLite.getDiameter();

        elementsL = DbSQLite.getLong();

        elementsLeng = new String[]{"Ukranian","English","Russian"};



        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Logger calculator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 500);
                frame.setLocation(400, 320);

                String[] leng = new String[7];
                leng = selectLenguage("Ukranian");

                cb = new JCheckBox("ISO 4480-83");
                JLabel lbl0 = new JLabel(leng[1]);
                JLabel lbl1 = new JLabel(leng[2]);
                diamC = new JComboBox(elementsD);
                JLabel lbl2 = new JLabel(leng[3]);
                longC = new JComboBox(elementsL);
                JLabel lbl3 = new JLabel(leng[4]);
                JLabel lbl4 = new JLabel(leng[0]);
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
                        calculate.setText(tmp[5]);
                        erace.setText(tmp[6]);
                    }
                });
                cnt = new JTextField(12);
                calculate = new JButton(leng[5]);
                erace = new JButton(leng[6]);
                ant = new JTextField(12);



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

                constraints.gridwidth = GridBagConstraints.REMAINDER;
                gbl.setConstraints(calculate, constraints);
                frame.add(calculate);
                calculate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {

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
                            double amount = count * val;
                            ant.setText(String.valueOf(amount));

                        }catch (NumberFormatException e){
                            e.printStackTrace();
                        }





                    }
                });

                gbl.setConstraints(ant,constraints);
                frame.add(ant);

                gbl.setConstraints(erace,constraints);
                frame.add(erace);
                erace.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        frame.dispose();
                    }
                });

                frame.setVisible(true);
            }
        });


    }

    private static String[] selectLenguage(String leng){

        String[] vocabulary = new String[7];
        String[] vocabularyU = new String[]{"Виберіть мову","Виберіть","Діаметер см","Довжину м","Вкажіть кількисть","Рахувати","Стерти"};
        String[] vocabularyE = new String[]{"Select a language","Select","Diameter sm","Length m","Specify the quantity","Calculate","Clear"};
        String[] vocabularyR = new String[]{"Выберите язык","Выберите","Диаметр см","Длину м","Укажите количество","Расчитать","Очистить"};

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
