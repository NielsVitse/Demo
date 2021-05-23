package Hoorcollege.Gui;

import Hoorcollege.Model.Teller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MijnAndereListener implements ActionListener {
    private Teller teller;
    private JTextField textfield;

    public MijnAndereListener(JTextField jt, Teller t){
        teller = t;
        textfield = jt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        teller.setTeller(0);
        textfield.setText(Integer.toString(teller.getTeller()));
    }
}
