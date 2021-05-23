package Hoorcollege.Gui;

import Hoorcollege.Model.Teller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MijnListener implements ActionListener {
    private Teller teller;
    private JTextField textfield;

    public MijnListener(JTextField jt,Teller t){
        teller = t;
        textfield = jt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int t  = teller.getTeller();
        t++;
        this.teller.setTeller(t);
        textfield.setText(Integer.toString(teller.getTeller()));

    }
}
