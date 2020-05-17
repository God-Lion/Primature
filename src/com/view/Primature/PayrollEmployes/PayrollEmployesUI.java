package com.view.Primature.PayrollEmployes;

import com.Controleur.GestionPayrollEmployes_controleur;
import com.Model.Ministeres;
import com.Model.PayrollEmployes;
import com.view.Primature.Listener.Listener;
import com.view.Primature.PrimatureUI;
import com.view.User.LoginUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public final class PayrollEmployesUI extends JInternalFrame  {
    
    public PayrollEmployesUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Payroll Employees >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        form();
        button();
        payrollEmployesUI = this;
    }
    
    public void form() {
        c.weighty = 0;
        JButton btnList = new JButton();
        btnList.setIcon( new ImageIcon("icons/list.png") );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 20, 20, 50);
        c.gridx = 0;
        c.gridy = 0;               
        btnList.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimatureUI primatureUI = null;
                primatureUI = (PrimatureUI) PrimatureUI.primatureUI;
                if (PrintPayrollEmployes.printPayrollEmployes.isClosed() )
                    primatureUI.addPrintPayrollEmployees();
                else {
                    PrintPayrollEmployes.printPayrollEmployes.setBounds(0, 0, 1005, 460);
                    payrollEmployesUI.moveToBack();
                }
            }
        });               
        panelPrincipal.add(btnList, c);
        JLabel lblExerciceFiscale = new JLabel("Exercice fiscale");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 1;
        panelPrincipal.add(lblExerciceFiscale, c);
        java.util.Calendar calandar = java.util.Calendar.getInstance();
        int annee = calandar.get(java.util.Calendar.YEAR);
        this.tfExerciceFiscale = new JTextField(""+(annee-1)+"-"+(annee));
        tfExerciceFiscale.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 0;
        c.gridy = 2;
        panelPrincipal.add(this.tfExerciceFiscale, c);
        JLabel lblMinistere = new JLabel("Minist\u00e8res");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 2;
        c.gridy = 1;
        panelPrincipal.add(lblMinistere, c);
        comboBoxMinistere = new JComboBox<>();
        comboBoxAddMinistere();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 2;
        c.gridy = 2;
        panelPrincipal.add(comboBoxMinistere, c);
        lblIconComboMinistere = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 3;
        c.gridy = 2;
        panelPrincipal.add(lblIconComboMinistere, c);
        JLabel lblMois = new JLabel("Mois");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 0, 0);
        c.gridx = 4;
        c.gridy = 1;
        panelPrincipal.add(lblMois, c);
        comboBoxMois = new JComboBox<>();
        String [] mois= {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre"};
        comboBoxMois.setModel(new javax.swing.DefaultComboBoxModel<>( mois ));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 4;
        c.gridy = 2;
        panelPrincipal.add(comboBoxMois, c);
        JLabel lblNom = new JLabel("Nom Complet");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 0, 20);
        c.weightx = 0.0124;
        c.gridx = 0;
        c.gridy = 3;
        panelPrincipal.add(lblNom, c);
        tfNom = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(tfNom, c);
        lblIconNom = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 4;
        panelPrincipal.add(lblIconNom, c);
        JLabel lblNuméroCheque = new JLabel("Numéro chèque ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 2;
        c.gridy = 3;
        panelPrincipal.add(lblNuméroCheque, c);
        try {
            MaskFormatter noCheque = new MaskFormatter("###-####");
            tfNuméroCheque = new JFormattedTextField(noCheque);
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 20, 10, 0);
            c.gridx = 2;
            c.gridy = 4;
            panelPrincipal.add(tfNuméroCheque, c);
        } catch (ParseException ex) {}
        lblIconNuméroCheque = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 3;
        c.gridy = 4;
        panelPrincipal.add(lblIconNuméroCheque, c);
        JLabel lblBNC = new JLabel("Montant");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 4;
        c.gridy = 3;
        panelPrincipal.add(lblBNC, c);
        tfMontant = new JTextField();
        tfMontant.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if (  Character.isDigit(input) != true && input != '.' ) e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 4;
        c.gridy = 4;
        panelPrincipal.add(tfMontant, c);
        JLabel lblPercentBNC = new JLabel("gdes");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 5;
        c.gridy = 4;
        panelPrincipal.add(lblPercentBNC, c);
        lblIconMontant = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 6;
        c.gridy = 4;
        panelPrincipal.add(lblIconMontant, c);
    }
    
    public void button() {
        c.insets = new Insets(60, 10, 20, 10);
        JButton btnNettoyer = new JButton("Nettoyer");
        btnNettoyer.setIcon( new ImageIcon("icons/clear_symbol.png") );
        btnNettoyer.setBackground( new Color( 115, 145, 248 ) );
        btnNettoyer.setForeground( new Color( 255, 255, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 6;               
        btnNettoyer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {clean();}
        });               
        panelPrincipal.add(btnNettoyer, c);
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        if (btnEnregistrer.getText().equals("Enregistrer")) 
            btnEnregistrer.setEnabled( LoginUI.save );
        if (btnEnregistrer.getText().equals("Modifier"))
            btnEnregistrer.setEnabled( LoginUI.modify );
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(60, 0, 20, 0);
        c.gridx = 4;
        c.gridy = 6;
        btnEnregistrer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( testBefore() ) {
                    PayrollEmployes payroll;
                    if(btnEnregistrer.getText().equals("Enregistrer")){
                        payroll = new PayrollEmployes (
                            tfExerciceFiscale.getText(),
                            String.valueOf( comboBoxMinistere.getSelectedItem() ),
                            String.valueOf( comboBoxMois.getSelectedItem() ),
                            tfNom.getText(),
                            tfNuméroCheque.getText(),
                            Double.parseDouble(tfMontant.getText())
                        );
                        if(controleur.modification(payroll, false)) {
                            listener.enregistrer(e);
                            clean();
                        }
                        else listener.saveError(e, "payrollEmploye");
                    }
                    else if(btnEnregistrer.getText().equals("Modifier")){
                        payroll = new PayrollEmployes (
                            id,
                            tfExerciceFiscale.getText(),
                            String.valueOf( comboBoxMinistere.getSelectedItem() ),
                            String.valueOf( comboBoxMois.getSelectedItem() ),
                            tfNom.getText(),
                            tfNuméroCheque.getText(),
                            Double.parseDouble(tfMontant.getText())
                        );
                        if(controleur.modification(payroll, true)) {
                            listener.modifier(e);
                            clean();
                        } else listener.modifyError(e, "payrollEmploye");
                    }
                    PrintPayrollEmployes printPayrollEmployes = (PrintPayrollEmployes) PrintPayrollEmployes.printPayrollEmployes;
                    printPayrollEmployes.refreshList();
                }
            }
        });
        panelPrincipal.add(btnEnregistrer, c);
    }
    
    private boolean testBefore(){
        if ( String.valueOf( comboBoxMinistere.getSelectedItem()).equalsIgnoreCase("sigle") ){
            lblIconComboMinistere.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconComboMinistere.setIcon( null );
        if( this.tfNom.getText().trim().isEmpty() ) {
            lblIconNom.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconNom.setIcon( null );
        try {
            List<PayrollEmployes> list = null;
            list = controleur.recherche( this.tfNuméroCheque.getText().trim(), true );
            if(btnEnregistrer.getText().equals("Enregistrer")){
                if( !list.isEmpty() ) {
                    lblIconNuméroCheque.setIcon( new ImageIcon("icons/cancelform.png") );
                    return false;
                } else lblIconNuméroCheque.setIcon( null );
            }
            if ( btnEnregistrer.getText().equals("Modifier") ) {
                if( !list.isEmpty() ) {
                    if (list.get(0).getNoChequeBNC().equals( this.tfNuméroCheque.getText().trim() )) {
                        lblIconNuméroCheque.setIcon( null );
                    } else {
                        lblIconNuméroCheque.setIcon( new ImageIcon("icons/cancelform.png") );
                        return false;
                    }
                }
            }
            if( this.tfNuméroCheque.getText().trim().length() != 8 ) {
                lblIconNuméroCheque.setIcon( new ImageIcon("icons/cancelform.png") );
                return false;
            }
        } catch ( IndexOutOfBoundsException e ){}
        Pattern pattern = Pattern.compile("(\\d*\\.)?\\d+");  
        Matcher matcher = pattern.matcher( this.tfMontant.getText().trim() );
        if(matcher.matches())  lblIconMontant.setIcon( null );
        else {
            lblIconMontant.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        }
        return true;
    }
    
    private void clean() {
        comboBoxMinistere.setSelectedIndex(0);
        lblIconComboMinistere.setIcon( null );
        comboBoxMois.setSelectedIndex(0);
        this.tfNom.setText("");
        lblIconNom.setIcon( null );
        this.tfNuméroCheque.setText("");
        lblIconNuméroCheque.setIcon( null );
        this.tfMontant.setText("");
        lblIconMontant.setIcon( null );
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.setIcon(new ImageIcon("icons/Save.png")); 
    }
    
    private void comboBoxAddMinistere(){
        com.Controleur.GestionMinisteres_controleur controlMinistere = new com.Controleur.GestionMinisteres_controleur();
            List<Ministeres> listeMinistere = controlMinistere.recherche(null, false);
                for(int i = 0; i < listeMinistere.size(); i++)
                    comboBoxMinistere.addItem(listeMinistere.get(i).getSigle());
    }
    
    public void remplir(final String code) {
        try {
            List<PayrollEmployes> list = null;
            list = controleur.recherche(code, true);
            if ( list != null ) {
                this.id = list.get(0).getId();
                this.tfExerciceFiscale.setText(list.get(0).getExerciceFiscale());
                this.comboBoxMinistere.setSelectedItem(list.get(0).getMinistere());
                this.comboBoxMois.setSelectedItem(list.get(0).getMois());
                this.tfNom.setText(String.valueOf(list.get(0).getNomEmploye()));
                this.tfNuméroCheque.setText(String.valueOf(list.get(0).getNoChequeBNC()));
                this.tfMontant.setText(String.valueOf(list.get(0).getMontant()));
                btnEnregistrer.setText("Modifier");
                btnEnregistrer.setIcon(new ImageIcon("icons/sort_by_modified_date.png")); 
                panelPrincipal.add(btnEnregistrer, c);
            }
        } catch ( IndexOutOfBoundsException e ) {}
    }
    
    private final GestionPayrollEmployes_controleur controleur = new GestionPayrollEmployes_controleur();
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final Listener listener = new Listener();
    private final GridBagConstraints c = new GridBagConstraints();
    private JTextField tfExerciceFiscale;
    private JComboBox<String> comboBoxMinistere;
    private JComboBox<String> comboBoxMois;
    private JTextField tfNom;
    private JLabel lblIconNom;
    private JFormattedTextField tfNuméroCheque;
    private JLabel lblIconNuméroCheque;
    private JTextField tfMontant ;
    private JLabel lblIconMontant ;
    private JLabel lblIconComboMinistere;
    private JButton btnEnregistrer;
    private String id;
    public static JInternalFrame payrollEmployesUI;
}