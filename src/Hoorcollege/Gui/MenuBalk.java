package Hoorcollege.Gui;

import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class MenuBalk extends JMenuBar{
    private JMenu fileMenu, windowMenu, showMenu;
    private JMenuItem exportDataItem, importDataItem, exitItem,enterName,prefItem, importDataFromDB,timeSlotItem;
    private JCheckBoxMenuItem personFormItem;
    private MenuListener menuListener;
    private MenuListener menuListener2;
    private MenuListener menuListener3;
    private MenuListener menuListener4;
    private MenuListener menuListener5;
    private MenuListener menuListener6;
    private MenuListener menuListener7;
    private JFileChooser fileChooser;

    public MenuBalk(){

        //file menu

        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.setFont(new Font("Courier",Font.ITALIC,16));

        exportDataItem =  new JMenuItem("Export...");
        importDataItem = new JMenuItem("Import...");
        importDataFromDB = new JMenuItem("Import from DB...");
        enterName = new JMenuItem("Enter Name");
        exitItem = new JMenuItem("Exit");
        exitItem.setMnemonic(KeyEvent.VK_X);
        importDataItem.setMnemonic(KeyEvent.VK_I);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));


        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.add(importDataFromDB);
        fileMenu.addSeparator();
        fileMenu.add(enterName);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        //window menu

        windowMenu = new JMenu("Window");

        showMenu = new JMenu("Show");
        personFormItem = new JCheckBoxMenuItem("Person Form");
        personFormItem.setSelected(true);

        prefItem = new JMenuItem("Preferences ...");
        prefItem.setMnemonic(KeyEvent.VK_P);
        prefItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));

        timeSlotItem = new JMenuItem("Set TimeSlot ...");
        timeSlotItem.setMnemonic(KeyEvent.VK_T);
        timeSlotItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));

        showMenu.add(personFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefItem);
        windowMenu.addSeparator();
        windowMenu.add(timeSlotItem);

        add(fileMenu);
        add(windowMenu);

        fileChooser = new JFileChooser();

        //Filters instellen op het bestandstype dat moet geopend worden door de FileChooser
        MijnFileFilter filter1 = new MijnFileFilter("pdf");
        fileChooser.addChoosableFileFilter(filter1);
        fileChooser.addChoosableFileFilter(new MijnFileFilter("png"));

        //default selectie van de file filter instellen
        fileChooser.setFileFilter(filter1);

        personFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean personFormChecked = personFormItem.isSelected();
                MenuEvent me = new MenuEvent(personFormChecked);
                if(menuListener!=null)menuListener.menuItemClicked(me);
            }
        });

        importDataItem.addActionListener(e -> {
            if(fileChooser.showOpenDialog(MainFrame.getMainFrame())==JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                MenuEvent me = new MenuEvent(file);
                menuListener3.menuItemClicked(me);
            }
        });

        importDataFromDB.addActionListener(e->{
            MenuEvent me = new MenuEvent();
            menuListener6.menuItemClicked(me);
        });

        exportDataItem.addActionListener(e -> {
            if(fileChooser.showSaveDialog(MainFrame.getMainFrame())==JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                MenuEvent me = new MenuEvent(file);
                menuListener4.menuItemClicked(me);
            }
        });

        prefItem.addActionListener(e -> menuListener5.menuItemClicked(new MenuEvent()));

        timeSlotItem.addActionListener(e -> menuListener7.menuItemClicked(new MenuEvent()));



        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int confirmCode = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),"Do you really want to exit?","Confirm Exit",JOptionPane.OK_CANCEL_OPTION);
                if(JOptionPane.showConfirmDialog(MainFrame.getMainFrame(),"Do you really want to exit?","Confirm Exit",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                    WindowListener[] windowListener = MainFrame.getMainFrame().getWindowListeners();
                    for(WindowListener w:windowListener){
                        w.windowClosing(new WindowEvent(MainFrame.getMainFrame(),0));
                    }
                    System.exit(0);
                }
                //JOptionPane.showOptionDialog(MainFrame.getMainFrame(),)
            }
        });

        enterName.addActionListener(e -> {
            MenuEvent me = new MenuEvent(JOptionPane.showInputDialog(MainFrame.getMainFrame(),"Please fill in your name:","Name Dialog",JOptionPane.OK_OPTION|JOptionPane.INFORMATION_MESSAGE));
            menuListener2.menuItemClicked(me);
        });



    }
    public void setMenuListener(MenuListener ml){
        this.menuListener = ml;
    }

    public void setMenuListener2(MenuListener ml){
        this.menuListener2 = ml;
    }

    public void setMenuListener3(MenuListener menuListener3) {
        this.menuListener3 = menuListener3;
    }

    public void setMenuListener4(MenuListener menuListener4) {
        this.menuListener4 = menuListener4;
    }

    public void setMenuListener5(MenuListener menuListener5) {
        this.menuListener5 = menuListener5;
    }

    public void setMenuListener6(MenuListener menuListener6) {
        this.menuListener6 = menuListener6;
    }

    public void setMenuListener7(MenuListener menuListener7) {
        this.menuListener7 = menuListener7;
    }
}
