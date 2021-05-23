package Hoorcollege.Gui;

import Hoorcollege.Model.Teller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MijnScherm extends JFrame{

    private JTextField tekstvak=null;
    private JButton incr;
    private JButton reset;
    private Teller teller;

    public MijnScherm(){
        teller = new Teller();
        JPanel panelHoog = new JPanel();

        ColorChange cc = (e)->panelHoog.setBackground(new Color(e.getX()%255,e.getY()%255,0));
        panelHoog.addMouseMotionListener(cc);
        panelHoog.add(getTekstvak());
        this.add(panelHoog, BorderLayout.NORTH);


        JPanel panelLaag = new JPanel();
        ColorChange cc2 = (e)->panelLaag.setBackground(new Color(e.getX()%255,e.getY()%255,0));
        panelLaag.addMouseMotionListener(cc2);
        panelLaag.setLayout(new GridLayout(1,2));
        incr = new JButton("Increment");
        panelLaag.add(incr);
        reset = new JButton("Reset");
        panelLaag.add(reset);
        this.incr.addActionListener(new MijnInnerListener());
        this.reset.addActionListener(new MijnAndereListener(tekstvak,teller));
        this.add(panelLaag, BorderLayout.SOUTH);
    }

    public JTextField getTekstvak(){
        if(tekstvak==null){
            tekstvak = new JTextField(10);
            Font f = new Font("Arial",Font.BOLD,24);
            tekstvak.setFont(f);
        }
        return tekstvak;
    }

    public class MijnInnerListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int t = teller.getTeller();
            t++;
            teller.setTeller(t);
            tekstvak.setText(Integer.toString(teller.getTeller()));
        }
    }

    public static void main(String[] args) {
        MijnScherm venster = new MijnScherm();
        venster.setVisible(true);
        venster.setSize(300,300);

        venster.setDefaultCloseOperation(venster.EXIT_ON_CLOSE);
        MijnScherm venster2 = new MijnScherm();
        venster2.setVisible(true);
        venster2.setSize(300,300);

        venster2.setDefaultCloseOperation(venster.EXIT_ON_CLOSE);
    }

    public Teller getTeller() {
        return teller;
    }

    public void setTeller(Teller t) {
        this.teller = t;
    }
}
