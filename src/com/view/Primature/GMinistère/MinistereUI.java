package com.view.Primature.GMinistère;

import com.Model.Ministeres;
import com.Controleur.GestionMinisteres_controleur;
import com.view.Primature.Listener.Listener;
import com.view.Primature.PrimatureUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public final class MinistereUI extends JInternalFrame  {
    
    public MinistereUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Gestion Minist\u00e8res >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        this.code = new Ministeres().genererCode();
        form();
        button();
        ministereUI = this;
    }
    
    private void form() {
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.ipadx = 10;
        c.weightx = 0.1;
        c.weighty = 0;
        lblCode = new JLabel();
        this.lblCode.setBorder( BorderFactory.createLineBorder( Color.black ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 20, 20, 20);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        lblCode.setText("    " + this.code); 
        panelPrincipal.add(lblCode, c);
        JButton btnList = new JButton();
        btnList.setIcon( new ImageIcon("icons/list.png") );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 50, 20, 20);
        c.gridx = 7;
        c.gridy = 0;               
        btnList.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimatureUI primatureUI = null;
                primatureUI = (PrimatureUI) PrimatureUI.primatureUI;
                if (PrintMinistereUI.print.isClosed()) primatureUI.addPrintMinistereUI();
                else {
                    PrintMinistereUI.print.setBounds(0, 0, 1005 , 460);
                    ministereUI.moveToBack();
                }
            }
        });               
        panelPrincipal.add(btnList, c);
        JLabel lblNom = new JLabel("Nom");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        panelPrincipal.add(lblNom, c);
        tfNom = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelPrincipal.add(tfNom, c);
        LblIconNom = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 2;
        panelPrincipal.add(LblIconNom, c);
        JLabel lblSigle = new JLabel("Sigle");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 1;
        panelPrincipal.add(lblSigle, c);
        tfSigle = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 2;
        c.gridx = 3;
        c.gridy = 2;
        panelPrincipal.add(tfSigle, c);
        LblIconSigle = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 5;
        c.gridy = 2;
        panelPrincipal.add(LblIconSigle, c);
        JLabel lblPhone = new JLabel("Phone");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 1;
        panelPrincipal.add(lblPhone, c);
        try{
            MaskFormatter tel = new MaskFormatter("####-####");
            ftfCPhone = new JFormattedTextField(tel);
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 20, 10, 0);
            c.gridwidth = 2;
            c.gridx = 6;
            c.gridy = 2;
            panelPrincipal.add(ftfCPhone, c);
        }catch( ParseException e ){}
        LblIconPhone = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 8;
        c.gridy = 2;
        panelPrincipal.add(LblIconPhone, c);
        JLabel lblMinistre = new JLabel("Ministre");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 3;
        panelPrincipal.add(lblMinistre, c);
        tfMinistre = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(tfMinistre, c);
        LblIconMinistre = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 4;
        panelPrincipal.add(LblIconMinistre, c);
        JLabel lblCompteBRH = new JLabel("Compte BRH");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 3;
        panelPrincipal.add(lblCompteBRH, c);
        try{
            MaskFormatter compte = new MaskFormatter("###-###-####");
            ftfCompteBRH = new JFormattedTextField(compte);
            c.fill = GridBagConstraints.BOTH;
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 20, 10, 0);
            c.gridwidth = 2;
            c.gridx = 3;
            c.gridy = 4;
            panelPrincipal.add(ftfCompteBRH, c);
        }catch( ParseException e ){}
        lblIconCompteBRH = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 5;
        c.gridy = 4;
        panelPrincipal.add(lblIconCompteBRH, c);
        JLabel lblCompteBNC = new JLabel("Compte BNC");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 6;
        c.gridy = 3;
        panelPrincipal.add(lblCompteBNC, c);
        try{
            MaskFormatter compte = new MaskFormatter("###-###-####");
            ftfCompteBNC = new JFormattedTextField(compte);
            c.fill = GridBagConstraints.BOTH;
            c.insets = new Insets(10, 20, 10, 0);
            c.gridwidth = 2;
            c.gridx = 6;
            c.gridy = 4;
            panelPrincipal.add(ftfCompteBNC, c);
        }catch( ParseException e ){}
        lblIconCompteBNC = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 8;
        c.gridy = 4;
        panelPrincipal.add(lblIconCompteBNC, c);
        JLabel lblAdresse = new JLabel("Adresse");
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 5;
        panelPrincipal.add(lblAdresse, c);
        JLabel lblZone = new JLabel("Zone");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 25, 0, 0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        panelPrincipal.add(lblZone, c);
        tfZone = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 0;
        c.gridy = 7;
        panelPrincipal.add(tfZone, c);
        JLabel lblNRue = new JLabel("#Rue");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 25, 0, 0);
        c.gridx = 1;
        c.gridy = 6;
        panelPrincipal.add(lblNRue, c);
        tfNRue = new JTextField();
        tfNRue.addKeyListener(new java.awt.event.KeyListener() {
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
        c.gridx = 1;
        c.gridy = 7;
        panelPrincipal.add(tfNRue, c);
        JLabel lblRue = new JLabel("Rue");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 3;
        c.gridy = 6;
        panelPrincipal.add(lblRue, c);
        tfRue = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 2;
        c.gridx = 3;
        c.gridy = 7;
        panelPrincipal.add(tfRue, c);
        JLabel lblVille = new JLabel("Ville");
        c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 1;
        c.gridx = 6;
        c.gridy = 6;
        panelPrincipal.add(lblVille, c);
        tfVille = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 2;
        c.gridx = 6;
        c.gridy = 7;
        panelPrincipal.add(tfVille, c);    
        lblIconAdresse = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 8;
        c.gridy = 7;
        panelPrincipal.add(lblIconAdresse, c);            
    }
    
    private void button() {
        c.insets = new Insets(60, 10, 20, 10);
        JButton btnNettoyer = new JButton("Nettoyer");
        btnNettoyer.setIcon( new ImageIcon("icons/clear_symbol.png") );
        btnNettoyer.setBackground( new Color( 115, 145, 248 ) );
        btnNettoyer.setForeground( new Color( 255, 255, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.0125;
        c.gridx = 0;
        c.gridy = 8;               
        btnNettoyer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { clean(); }
        });               
        panelPrincipal.add(btnNettoyer, c);
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.setIcon( new ImageIcon("icons/trash.png") );
        btnSupprimer.setBackground( new Color( 206, 88, 112 ) );
        btnSupprimer.setForeground( new Color( 255, 255, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(60, 20, 20, 10);
        c.gridx = 6;
        c.gridy = 8;
        btnSupprimer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !testBefore() ) {
                    if(controleur.delete( lblCode.getText() ) ) {
                        clean();
                        listener.supprimer( e );
                    }
                }
            }
        });
//        panelPrincipal.add(btnSupprimer, c);
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( testBefore() ) {
                    ministere = new Ministeres(
                        lblCode.getText().trim(),
                        tfNom.getText().trim(),
                        tfSigle.getText().trim(),
                        "#" + tfNRue.getText().trim() + "_" + tfRue.getText().trim() + "_" + tfZone.getText().trim() + "_" + tfVille.getText().trim(),
                        ftfCPhone.getText().trim(),
                        tfMinistre.getText().trim(),
                        ftfCompteBRH.getText().trim(),
                        ftfCompteBNC.getText().trim()
                    );
                    if ( btnEnregistrer.getText().equals("Enregistrer") ) {
                        if(controleur.modification(ministere, false)) {
                            listener.enregistrer(e);
                            clean();
                        } else listener.saveError(e, "ministere");
                    } else if ( btnEnregistrer.getText().equals("Modifier") ) {
                        if(controleur.modification(ministere, true)) {
                            clean();
                            listener.modifier(e);
                        } else listener.modifyError(e, "ministere");
                    }
                    PrintMinistereUI printMinistereUI = (PrintMinistereUI) PrintMinistereUI.print;
                    printMinistereUI.refreshList();
                }
            }
        });
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(60, 0, 20, 0);
        c.gridx = 7;
        c.gridy = 8;
        panelPrincipal.add(btnEnregistrer, c);
    }
    
    private boolean testBefore(){
        if( this.tfNom.getText().trim().isEmpty() ) {
            LblIconNom.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else LblIconNom.setIcon( null );
        if( this.tfSigle.getText().trim().isEmpty() ) {
            LblIconSigle.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else LblIconSigle.setIcon( null );
        if( this.tfMinistre.getText().trim().isEmpty() ) {
            LblIconMinistre.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else LblIconMinistre.setIcon( null );
        if ( this.ftfCPhone.getText().trim().length() != 9 ) {
            LblIconPhone.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else LblIconPhone.setIcon( null );
        if( this.ftfCompteBRH.getText().trim().length() != 12 ) {
            lblIconCompteBRH.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconCompteBRH.setIcon( null );
        if( this.ftfCompteBNC.getText().trim().length() != 12 ) {
            lblIconCompteBNC.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconCompteBNC.setIcon( null );
        if( this.tfNRue.getText().trim().isEmpty() ) {
            lblIconAdresse.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconAdresse.setIcon( null );
        if( this.tfZone.getText().trim().isEmpty() ) {
             lblIconAdresse.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconAdresse.setIcon( null );
        if( this.tfRue.getText().trim().isEmpty() ) {
             lblIconAdresse.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconAdresse.setIcon( null );
        if( this.tfVille.getText().trim().isEmpty() ){
            lblIconAdresse.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconAdresse.setIcon( null );
        return true;
    }
    
    private void switchCode(){
        this.code = new Ministeres().genererCode();
        this.lblCode.setText( this.code );
    }
    
    private void clean() {
        switchCode();
        this.code = new Ministeres().genererCode();
        this.lblCode.setText( this.code );
        this.tfNom.setText("");
        this.tfSigle.setText("");
        this.tfMinistre.setText("");
        this.ftfCPhone.setText("");
        this.ftfCompteBRH.setText("");
        this.ftfCompteBNC.setText("");
        this.tfNRue.setText("");
        this.tfZone.setText("");
        this.tfRue.setText("");
        this.tfVille.setText("");
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
    }
    
    public void remplir(String code) {
        List<Ministeres> list = null;
        list = controleur.recherche(code, true);
        if ( list != null ) {
            this.lblCode.setText(list.get(0).getCode());
            this.tfNom.setText(list.get(0).getNom());
            this.tfSigle.setText(list.get(0).getSigle());
            this.ftfCPhone.setText(list.get(0).getTelephone());
            this.tfMinistre.setText(list.get(0).getMinistreActuel());
            this.ftfCompteBRH.setText(list.get(0).getNoBRH());
            this.ftfCompteBNC.setText(list.get(0).getNoBNC());
            String[] adresse = list.get(0).getAdresse().split("_");
            tfNRue.setText( adresse[0].replace("#", "") );
            tfRue.setText( adresse[1] );
            tfZone.setText( adresse[2] );
            tfVille.setText( adresse[3] );
            btnEnregistrer.setText("Modifier");
            btnEnregistrer.setIcon(new ImageIcon("icons/sort_by_modified_date.png"));
        }
    }
    
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final Listener listener = new Listener();
    private final GridBagConstraints c = new GridBagConstraints();
    private JLabel lblCode;
    private JTextField tfNom;
    private JLabel LblIconNom;
    private JTextField tfSigle;
    private JLabel LblIconSigle;
    private JFormattedTextField ftfCPhone;
    private JLabel LblIconPhone;
    private JTextField tfMinistre;
    private JLabel LblIconMinistre;
    private JFormattedTextField ftfCompteBRH;
    private JLabel lblIconCompteBRH;
    private JFormattedTextField ftfCompteBNC;
    private JLabel lblIconCompteBNC;
    private JTextField tfNRue;
    private JTextField tfZone;
    private JTextField tfRue;
    private JTextField tfVille;
    private JLabel lblIconAdresse;
    private JButton btnEnregistrer;
    private final GestionMinisteres_controleur controleur = new GestionMinisteres_controleur();
    private Ministeres ministere;
    private String code;
    public static JInternalFrame ministereUI = null;
}