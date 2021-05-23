package Hoorcollege.Gui;

import Hoorcollege.Model.Person;
import Hoorcollege.Logic.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.prefs.Preferences;


public class MainFrame extends JFrame {
    private ToolBar bar;
    private TextPane textField;
    private FormPanel formPanel;
    private MenuBalk menuBalk;
    private static MainFrame instance;
    private Controller controller;
    private TablePanel tablePanel;
    private PrefDialog prefDialog;
    private Preferences prefs;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;
    private TimeSlotDialog timeSlotDialog;

    private MainFrame(String titel){
        super(titel);
        //this.setVisible(true);

        this.controller = new Controller();

        this.setSize(500,500);
        this.setMinimumSize(new Dimension(300,300));

        Container c = this.getContentPane();
        c.setLayout(new BorderLayout());

        textField = new TextPane();
        //c.add(textField, BorderLayout.CENTER);
        bar = new ToolBar();
        c.add(bar, BorderLayout.NORTH);



        formPanel = new FormPanel();
        //c.add(formPanel,BorderLayout.WEST);

        menuBalk = new MenuBalk();
        setJMenuBar(menuBalk);

        tablePanel = new TablePanel();
        //c.add(tablePanel,BorderLayout.CENTER);
        tablePanel.setPeople(controller.getPeople());
        tablePanel.setVisible(true);

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Person DB",tablePanel);
        tabbedPane.add("Messages",textField);


        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,formPanel,tabbedPane);
        splitPane.setOneTouchExpandable(true);
        c.add(splitPane);

        prefDialog = new PrefDialog(this);
        prefDialog.setLocationRelativeTo(this);

        timeSlotDialog = new TimeSlotDialog(this);
        timeSlotDialog.setLocationRelativeTo(this);

        prefs = Preferences.userRoot().node("db");

        bar.setButtonListener(new ButtonListener() {
            @Override
            public void saveToDB() {
                controller.saveToDB();
            }

            @Override
            public void loadFromDB() {
                controller.loadFromDB();
                for(Person p: controller.getPeople()){
                    System.out.println(p);
                }
                tablePanel.refresh();
            }

           /* @Override
            public void writeText(String text) {
                textField.getTextArea().append(text);
                //JOptionPane.showMessageDialog(null,"U hebt op de knop "+text+" gedrukt!");
            }*/
        });

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventPerformed(FormEvent fe) {
                /*String naam = fe.getNaam();
                String occupation = fe.getOccupation();
                int ageCat = fe.getAgeCat().getId();
                String emplCat = fe.getEmplCat();
                String gender = fe.getGender();
                textField.getTextArea().append(naam+" "+occupation+" "+ageCat+" "+emplCat+" "+gender+"\n");*/
                controller.addPerson(fe);
                tablePanel.refresh();
                for(Person p:controller.getPeople()){
                    System.out.println(p.getNaam());
                }
                if(controller.getPeople()==null) System.out.println("null");
                else System.out.println(controller.getPeople()==tablePanel.getTableModel().getPeople());


            }
        });

        menuBalk.setMenuListener(new MenuListener() {
            @Override
            public void menuItemClicked(MenuEvent me) {
                //als formpanel weer zichtbaar moet worden --> split locatie moet op breedte van minimumafmeting formpanel
                if(me.getIsChecked()) splitPane.setDividerLocation((int)formPanel.getMinimumSize().getWidth());
                formPanel.setVisible(me.getIsChecked());
            }
        });

        menuBalk.setMenuListener2(new MenuListener() {
            @Override
            public void menuItemClicked(MenuEvent me) {
                formPanel.getNaamField().setText(me.getTekst());
            }
        });

        menuBalk.setMenuListener3(new MenuListener() {
            @Override
            public void menuItemClicked(MenuEvent me) {
                try {
                    controller.loadFromFile(me.getFile());
                    tablePanel.refresh();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"Could not load file.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuBalk.setMenuListener6(new MenuListener() {
            @Override
            public void menuItemClicked(MenuEvent me) {
                    controller.loadFromDB();
                    for(Person p: controller.getPeople()){
                        System.out.println(p);
                    }
                    tablePanel.refresh();
            }
        });

        menuBalk.setMenuListener4(new MenuListener() {
            @Override
            public void menuItemClicked(MenuEvent me) {
                try {
                    controller.saveToFile(me.getFile());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(),"Could not load file.","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        menuBalk.setMenuListener5(me -> prefDialog.setVisible(true));

        menuBalk.setMenuListener7(me -> timeSlotDialog.setVisible(true));

        tablePanel.setTableListener(new TableListener() {
            @Override
            public void removeRow(int index) {
                controller.removeItem(index);
            }
        });

        prefDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferencesSet(String user, String pass, int port) {
                prefs.put("user",user);
                prefs.put("password",pass);
                prefs.putInt("port",port);
            }
        });

        String user = prefs.get("user","");
        String pass = prefs.get("password","");
        int port = prefs.getInt("port",3306);

        prefDialog.setPreferences(user, pass, port);

        //this.add(new JButton("Click"),BorderLayout.SOUTH);
        //this.add(new JTextField("Click"),BorderLayout.SOUTH);

        /*JButton hello = new JButton("Hello");
        JButton goodbye = new JButton("Goodbye");
        add(hello,BorderLayout.NORTH);
        add(goodbye,BorderLayout.SOUTH);*/
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("De applicatie werd afgesloten.");
                controller.disconnect();
                //MainFrame met alle componenten afsluiten
                dispose();
                //garbage colllector laten werken
                System.gc();
            }
        });


    }

    public static MainFrame getMainFrame(){
        if(instance==null) instance = new MainFrame("Mijn Programma");
        return instance;

    }

    public Controller getController() {
        return controller;
    }
}
