package Hoorcollege.Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TimeSlotDialog extends JDialog {
    private JButton addTimeSlot;
    private JSpinner dayBegin, monthBegin, yearBegin, dayEnd, monthEnd, yearEnd;
    private JPanel dateBeginPanel, dateEndPanel, buttonPanel;

    public TimeSlotDialog(JFrame parent){
        super(parent,false);
        dateBeginPanel = new JPanel();
        dateEndPanel = new JPanel();
        buttonPanel = new JPanel();
        setLayout(new BorderLayout());

        Border b1 = BorderFactory.createTitledBorder("Startdatum");
        Border b2 = BorderFactory.createTitledBorder("Einddatum");
        dateBeginPanel.setBorder(b1);
        dateEndPanel.setBorder(b2);

        add(dateBeginPanel,BorderLayout.NORTH);
        add(dateEndPanel, BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);

    }

}
