package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by serjoga on 09.04.17.
 */
class LabelFrame extends JFrame {

    //створюємо панель
    JPanel panel=new JPanel();
    int i=0;

    LabelFrame(){
        // вирівнювання за замовчуванням (CENTER)
        JLabel label1 = new JLabel("Багато левів, ");
        // вирівнювання вліво
        JLabel label2 = new JLabel("тигрів з тигрицями", SwingConstants.LEFT);
        // мітка без тексту, вирівнювання за замовчуванням
        JLabel label3 = new JLabel();
        // створюємо іконку
        Icon icon = new ImageIcon("icon.gif");
        // створюємо мітку із зображенням
        JLabel label4 = new JLabel(icon);
        // задаємо текст для label3
        label3.setText("і ведмедів");
        // встановлюємо вирівнювання
        label3.setHorizontalAlignment(SwingConstants.RIGHT);
        //додаємо мітки в панель
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        JButton button = new JButton("PYZDETS");
        // додаємо обробник подій, що визначається у внутрішньому класі
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                i++;
                label1.setText("OGO PIZDARYKI");
                label2.setText(actionEvent.getActionCommand());
            }
        });
        panel.add(button);
        //додаємо панель у фрейм
        this.add(panel);
    }

}
