package Hoorcollege.Gui;

import javax.swing.*;
import java.awt.*;

public class TextPane extends JPanel{
    private JTextArea textArea;

    public TextPane(){
        textArea = new JTextArea();
        setLayout(new BorderLayout());
        setVisible(true);
        textArea.setVisible(true);
        add(textArea,BorderLayout.WEST);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
