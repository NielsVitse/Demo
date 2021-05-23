package Hoorcollege.Gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {
    private JLabel naamLabel, occupationLabel;
    private JTextField naamField,occupationField;
    private JButton okKnop,knop1,knop2;
    private FormListener formListener;
    private JList ageList;
    private JComboBox emplCombo;
    private JCheckBox citizenCheck;
    private JLabel taxLabel;
    private JTextField taxID;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel() {
        //aanmaak nieuwe elementen

        naamLabel = new JLabel("Naam: ");
        occupationLabel = new JLabel("Occupation: ");
        naamField = new JTextField(20);
        occupationField = new JTextField(20);
        naamLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        naamLabel.setLabelFor(naamField);

        okKnop = new JButton("OK");
        okKnop.setMnemonic(KeyEvent.VK_O);
        //knop1 = new JButton("Knop1");
        //knop2 = new JButton("Knop2");

        //Opmaak labels

        Font labelFont = new Font("Times New Roman",Font.ITALIC,20);
        naamLabel.setFont(labelFont);


        //JListBox aanmaken

        ageList = new JList();
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategorie(0,"Under 18"));
        ageModel.addElement(new AgeCategorie(1,"18 up to 65"));
        ageModel.addElement(new AgeCategorie(2,"Over 65"));
        ageList.setModel(ageModel);
        ageList.setSize(new Dimension(100,70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(0);

        //JComboBox aanmaken

        emplCombo = new JComboBox();
        DefaultComboBoxModel emplModel = new DefaultComboBoxModel();
        emplModel.addElement("Employed");
        emplModel.addElement("Self-Employed");
        emplModel.addElement("Unemployed");
        emplCombo.setModel(emplModel);
        emplCombo.setSelectedIndex(0);

        //JCheckBox en gelinkte JLabel/JTextField

        citizenCheck = new JCheckBox();
        taxLabel = new JLabel("TaxID: ");
        taxID = new JTextField(10);
        taxLabel.setEnabled(false);
        taxID.setEnabled(false);

        taxID.setText("XXXX");

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isChecked = citizenCheck.isSelected();
                taxLabel.setEnabled(isChecked);
                taxID.setEnabled(isChecked);
            }
        });

        //RadioButtons configureren

        femaleRadio = new JRadioButton("Female");
        maleRadio = new JRadioButton("Male");
        genderGroup = new ButtonGroup();

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        //maleRadio.setSelected(true);
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");


        //layout van dimensie en randen

        Dimension dim = getPreferredSize();
        dim.setSize(300, 300);
        setPreferredSize(dim);
        setMinimumSize(dim);

        Border outerBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border innerBorder = BorderFactory.createTitledBorder("Naam");
        this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));



        //GridBagLayout vanaf hier

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        Insets i1 = new Insets(0, 0, 0, 10);
        Insets i2 = new Insets(0, 0, 0, 0);

        //Row 1
        gc.gridy = 0;

        gc.gridx = 0;
        gc.weightx = 0.2;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = i1;

        add(naamLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = i2;

        add(naamField, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 0.2;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = i1;

        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = i2;

        add(occupationField, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets=i1;

        add(new JLabel("Age: "), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(ageList, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets=i1;

        add(new JLabel("Employment: "), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(emplCombo, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets=i1;

        add(new JLabel("US Citizen: "), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(citizenCheck, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets=i1;

        add(taxLabel, gc);

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(taxID, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 0;
        gc.weightx = 1;
        gc.weighty = 0.01;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets=i1;

        add(new JLabel("Gender: "), gc);

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(maleRadio, gc);

        //Next Row
        gc.gridy++;

        gc.gridx = 1;
        gc.weightx = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets=i2;

        add(femaleRadio, gc);





        //Next Row
        gc.gridy++;

        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 1;
        //gc.gridwidth=2;
        //gc.gridheight=2;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;

        add(okKnop, gc);



        okKnop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naam = naamField.getText();
                String occupation = occupationField.getText();
                AgeCategorie ageCat = (AgeCategorie) ageList.getSelectedValue();
                String emplCat = (String) emplCombo.getSelectedItem();
                boolean check = citizenCheck.isSelected();
                String taxId = taxID.getText();
                String gender = genderGroup.getSelection().getActionCommand();
                FormEvent fe = new FormEvent(this, naam, occupation, ageCat, emplCat, check,taxId,gender);
                if (formListener != null) formListener.formEventPerformed(fe);
            }
        });
    }
    public void setFormListener(FormListener fl) {
        formListener = fl;
    }


    public JTextField getNaamField(){
        return naamField;
    }

}
