package Hoorcollege.Gui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class ToolBar extends JToolBar {
    private ButtonListener buttonListener;
    private JButton saveBtn;
    private JButton refreshBtn;

    public class ButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clicked = (JButton) e.getSource();
            if (clicked == saveBtn) {
                buttonListener.saveToDB();
            } else if (clicked == refreshBtn) {
                buttonListener.loadFromDB();
            }
        }
    }

    public void setButtonListener(ButtonListener s){
        this.buttonListener = s;
    }

    public ToolBar(){
        Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        //border weghalen --> toolbar wordt draggable
        //setBorder(b);
        setFloatable(false);
        saveBtn = new JButton();
        refreshBtn = new JButton();
        saveBtn.setIcon(new ImageIcon("Images/Save16.gif"));
        saveBtn.setToolTipText("Save");
        refreshBtn.setIcon(new ImageIcon("Images/Refresh16.gif"));
        refreshBtn.setToolTipText("Refresh");
        setVisible(true);
        //setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(saveBtn);
        this.addSeparator();
        this.add(refreshBtn);
        saveBtn.addActionListener(new ButtonClick());
        refreshBtn.addActionListener(new ButtonClick());
    }

    public ImageIcon createImageIcon(String path){
        URL url = getClass().getResource(path);
        ImageIcon icon = null;
        if(url==null) System.out.println("Kan icon niet opladen");
        else icon = new ImageIcon(url);

        return icon;
    }

}
