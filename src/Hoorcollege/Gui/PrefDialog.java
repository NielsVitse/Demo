package Hoorcollege.Gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.ColorModel;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class PrefDialog extends JDialog {
    private JButton okButton, cancelButton;
    private JSpinner portSpinner, monthSpinner, daySpinner;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;
    private JSlider slider;
    private JLabel imageLabel;

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }



    public PrefDialog(JFrame parent){
        super(parent,"Preference", false);
        setSize(400,700);

        userField = new JTextField(20);
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');

        SpinnerListModel listSpinnerModel = new SpinnerListModel();
        listSpinnerModel.setList(Arrays.asList(new DateFormatSymbols().getWeekdays()));
        portSpinner = new JSpinner(new SpinnerNumberModel(3026,0,9999,1));
        monthSpinner = new JSpinner(listSpinnerModel);

        Calendar calendar = Calendar.getInstance();


        LocalDateTime d = LocalDateTime.of(2021,5,20,0,0);
        d.getDayOfWeek();



        //calendar.setTime(d);
        Date initDate = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date minDate = Date.from(LocalDateTime.of(2021,1,1,0,0).atZone(ZoneId.systemDefault()).toInstant());
        Date maxDate = Date.from(LocalDateTime.of(2021,12,31,23,59).atZone(ZoneId.systemDefault()).toInstant());
        //Date minDate = Date.from(LocalDate.of(2021,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        //Date maxDate = Date.from(LocalDate.of(2021,12,31).atStartOfDay(ZoneId.systemDefault()).toInstant());


        SpinnerDateModel daySpinnerModel = new SpinnerDateModel(initDate,minDate,maxDate,Calendar.HOUR_OF_DAY);
        daySpinner = new JSpinner(daySpinnerModel);

        slider = new JSlider(JSlider.VERTICAL,0,20,10);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);

        Hashtable labelTable = new Hashtable();
        Font sliderLabelFont = new Font("Arial",Font.PLAIN,12);
        JLabel sliderLabel1 = new JLabel("Onvoldoende");
        sliderLabel1.setFont(sliderLabelFont);
        JLabel sliderLabel2 = new JLabel("Matig");
        sliderLabel2.setFont(sliderLabelFont);
        JLabel sliderLabel3 = new JLabel("Voldoende");
        sliderLabel3.setFont(sliderLabelFont);
        JLabel sliderLabel4 = new JLabel("Goed");
        sliderLabel4.setFont(sliderLabelFont);
        JLabel sliderLabel5 = new JLabel("Uitstekend");
        sliderLabel5.setFont(sliderLabelFont);

        labelTable.put(0,sliderLabel1);
        labelTable.put(5,sliderLabel2);
        labelTable.put(10,sliderLabel3);
        labelTable.put(15,sliderLabel4);
        labelTable.put(20,sliderLabel5);
        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setPreferredSize(new Dimension(125,100));

        ImageIcon s[] = new ImageIcon[5];
        s[0] = new ImageIcon("Images/Settings image 1.jpg");
        s[1] = new ImageIcon("Images/Settings image 2.jpg");
        s[2] = new ImageIcon("Images/Settings image 3.jpg");
        s[3] = new ImageIcon("Images/Settings image 4.jpg");
        s[4] = new ImageIcon("Images/Settings image 5.jpg");
        imageLabel = new JLabel("",JLabel.CENTER);
        imageLabel.setPreferredSize(new Dimension(40,100));
        imageLabel.setIcon(s[2]);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            Integer value = (Integer) portSpinner.getValue();
            System.out.println(value);
            String user = userField.getText();
            char[] passwordChar = passwordField.getPassword();
            String password = new String(passwordChar);
            prefsListener.preferencesSet(user, password,value);
            System.out.println(user+"; "+password);
            System.out.println(d.getDayOfWeek());
            System.out.println(d.plusHours(26));
            setVisible(false);
        });




        cancelButton.addActionListener(e -> setVisible(false));

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //portSpinner.setValue(slider.getValue());
                int index = slider.getValue()/5;
                imageLabel.setIcon(s[index]);
            }
        });

        layOutControl();



    }

    public void setPreferences(String user, String pass, int port){
        userField.setText(user);
        passwordField.setText(pass);
        portSpinner.setValue(port);
    }

    public void layOutControl(){
        JPanel controlsPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        int space = 10;
        Border spaceBorder = BorderFactory.createEmptyBorder(space,space,space,space);
        Border titleBorder = BorderFactory.createTitledBorder("Settings");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder,titleBorder));
        //buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        //Controls panel
        controlsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //Row 1
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 2;

        gc.anchor = GridBagConstraints.FIRST_LINE_END;

        controlsPanel.add(new Label("Parameter 1: "),gc);

        gc.gridx++;

        gc.fill = GridBagConstraints.VERTICAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        controlsPanel.add(slider,gc);

        //Next row
        gc.gridy++;
        gc.weighty = 0.25;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.NONE;
        controlsPanel.add(new Label("Username: "),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        controlsPanel.add(userField,gc);


        //Next row
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.NONE;
        controlsPanel.add(new Label("Password: "),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.fill = GridBagConstraints.HORIZONTAL;
        controlsPanel.add(passwordField,gc);




        //Next row
        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.fill = GridBagConstraints.NONE;
        controlsPanel.add(new Label("Port: "),gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.LINE_START;
        controlsPanel.add(portSpinner,gc);



        //Next Row

        gc.gridy++;

        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        controlsPanel.add(new Label("Month"),gc);

        gc.gridx=1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        controlsPanel.add(daySpinner,gc);

        //Next Row

        gc.gridy++;
        gc.weighty = 2;

        gc.gridx=0;
        gc.gridwidth = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        controlsPanel.add(imageLabel,gc);





        //Buttons panel

        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        buttonPanel.add(okButton,gc);
        buttonPanel.add(cancelButton,gc);

        //Dialog
        setLayout(new BorderLayout());
        add(controlsPanel,BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
