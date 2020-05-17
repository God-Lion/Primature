package com.view.Primature.GProjets;

import com.Controleur.GestionProjets_controleur;
import com.Model.GestionProjets;
import com.Model.Ministeres;
import com.view.Primature.Listener.Listener;
import com.view.Primature.PrimatureUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class ProjetsUI extends JInternalFrame  {

    public ProjetsUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Gestion de Projets >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        codeProjet = new GestionProjets().genererCode();
        form();
        button();
        projets = this;
    }
    
    public void form() {
        lblCode = new JLabel("");
        this.lblCode.setBorder( BorderFactory.createLineBorder( Color.black ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        lblCode.setText("           " + codeProjet); 
        panelPrincipal.add(lblCode, c);
        c.weighty = 0;
        JButton btnList = new JButton();
        btnList.setIcon( new ImageIcon("icons/list.png") );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 0, 10, 15);
        c.gridx = 6;
        c.gridy = 0;               
        btnList.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimatureUI PrimatureUI = null;
                PrimatureUI = (PrimatureUI) PrimatureUI.primatureUI;
                if (PrintProjets.printProjets.isClosed())
                    PrimatureUI.addPrintFondUI();
                else {
                    PrintProjets.printProjets.setBounds(0, 0, 1005 , 460);
                    projets.moveToBack();
                }
            }
        });               
        panelPrincipal.add(btnList, c);
        c.ipadx = 0;
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
        c.weightx = 0.0124;
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
        JLabel lblNumeroProjet = new JLabel("Numéro de Projet ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 3;
        panelPrincipal.add(lblNumeroProjet, c);
        tfNuméroProjet = new JTextField();
        noProjet();
        tfNuméroProjet.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 60);
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(tfNuméroProjet, c);
        JLabel lblTypeProjet = new JLabel("Type de projet");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 2;
        c.gridy = 3;
        panelPrincipal.add(lblTypeProjet, c);
        comboBoxTypeProjet = new JComboBox<>();
        comboBoxTypeProjet.setModel(new DefaultComboBoxModel<>( TypeProjet ));
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 2;
        c.gridy = 4;
        panelPrincipal.add(comboBoxTypeProjet, c);
        JLabel lblZoneConcerne = new JLabel("Zone(s) concernées par le projet ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 0);
        c.gridx = 4;
        c.gridy = 3;
        panelPrincipal.add(lblZoneConcerne, c);
        tfZoneConcerne = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 4;
        c.gridy = 4;
        panelPrincipal.add(tfZoneConcerne, c);
        lblIconZoneConcerne = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridx = 5;
        c.gridy = 4;
        panelPrincipal.add(lblIconZoneConcerne, c);
        JLabel lblNomFirme = new JLabel("Nom Firme ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 5;
        panelPrincipal.add(lblNomFirme, c);
        tfNomFirme = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 0;
        c.gridy = 6;
        panelPrincipal.add(tfNomFirme, c);
        lblIconNomFirme = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 6;
        panelPrincipal.add(lblIconNomFirme, c);
        JLabel lblMaitreOuvrage = new JLabel("Maitre d’ouvrage ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 25, 0, 0);
        c.gridwidth = 1;
        c.gridx = 2;
        c.gridy = 5;
        panelPrincipal.add(lblMaitreOuvrage, c);
        tfMaitreOuvrage = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 2;
        c.gridy = 6;
        panelPrincipal.add(tfMaitreOuvrage, c);
        lblIconMaitreOuvrage = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 3;
        c.gridy = 6;
        panelPrincipal.add(lblIconMaitreOuvrage, c);
        JLabel lblMontant = new JLabel("Coût du projet ");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 4;
        c.gridy = 5;
        panelPrincipal.add(lblMontant, c);
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
        c.gridy = 6;
        panelPrincipal.add(tfMontant, c);
        JLabel lblgdes = new JLabel("gdes");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 5;
        c.gridy = 6;
        panelPrincipal.add(lblgdes, c);
        lblIconMontant = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 6;
        c.gridy = 6;
        panelPrincipal.add(lblIconMontant, c);
        JLabel lblDescription = new JLabel("Description");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 7;
        panelPrincipal.add(lblDescription, c);
        tTADescription = new JTextArea();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 15, 0);
        c.ipady = 20;
        c.weighty = 10;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 8;
        panelPrincipal.add(tTADescription, c);
        lblIconDescription = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 5, 0, 0);
        c.gridwidth = 1;
        c.gridx = 4;
        c.gridy = 8;
        panelPrincipal.add(lblIconDescription, c);
    }
    
    public void button() {
        c.insets = new Insets(0, 20, 20, 1);
        JButton btnNettoyer = new JButton("Nettoyer");
        btnNettoyer.setIcon( new ImageIcon("icons/clear_symbol.png") );
        btnNettoyer.setBackground( new Color( 115, 145, 248 ) );
        btnNettoyer.setForeground( new Color( 255, 255, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;
        c.weighty = 0;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 9;               
        btnNettoyer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {clean();}
        });               
        panelPrincipal.add(btnNettoyer, c);
        JButton btnSupprimer = new JButton("Supprimer");
        btnSupprimer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !testBefore() ) {
                    if(controleur.delete( lblCode.getText() ) ) {
                        clean();
                        listener.supprimer( e );
                        PrintProjets printProjets = (PrintProjets) PrintProjets.printProjets;
                        printProjets.refreshList();
                    }
                }
            }
        });
        btnSupprimer.setIcon( new ImageIcon("icons/trash.png") );
        btnSupprimer.setBackground( new Color( 206, 88, 112 ) );
        btnSupprimer.setForeground( new Color( 255, 255, 255 ) );
        c.insets = new Insets(0, 80, 20, 10);
        c.gridx = 4;
        c.gridy = 9;
        panelPrincipal.add(btnSupprimer, c);
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        btnEnregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( testBefore() ) {
                    GestionProjets projets = new GestionProjets(
                        lblCode.getText().trim(),
                        tfExerciceFiscale.getText().trim(),
                        String.valueOf( comboBoxMinistere.getSelectedItem() ),
                        Integer.valueOf( tfNuméroProjet.getText().trim() ),
                        String.valueOf( comboBoxTypeProjet.getSelectedItem() ),
                        tfZoneConcerne.getText().trim(),
                        tfNomFirme.getText().trim(),
                        tfMaitreOuvrage.getText().trim(),
                        Double.parseDouble( tfMontant.getText().trim() ),
                        tTADescription.getText().trim()
                    );
                    if( btnEnregistrer.getText().equals("Enregistrer") ){
                        if( controleur.modification(projets, false)) {
                            listener.enregistrer(e);
                            clean();
                        } else listener.saveError(e, "ProjetsUI");
                    }
                    else if( btnEnregistrer.getText().equals("Modifier") ){
                        if( controleur.modification(projets, true) ){
                            listener.modifier(e);
                            clean();
                        } else listener.modifyError(e, "ProjetsUI");
                    }
                    PrintProjets printProjets = (PrintProjets) PrintProjets.printProjets;
                    printProjets.refreshList();
                }
            }
        });
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(0, 5, 20, 10);
        c.gridx = 5;
        c.gridy = 9;
        panelPrincipal.add( btnEnregistrer, c);
    }
    
    private boolean testBefore(){
        if( this.tfNomFirme.getText().trim().isEmpty() ) {
            lblIconNomFirme.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconNomFirme.setIcon( null );
        if( this.tfZoneConcerne.getText().trim().isEmpty() ) {
            lblIconZoneConcerne.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconZoneConcerne.setIcon( null );
        if( this.tfMaitreOuvrage.getText().trim().isEmpty() ) {
            lblIconMaitreOuvrage.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconMaitreOuvrage.setIcon( null );
        Pattern pattern = Pattern.compile("(\\d*\\.)?\\d+");  
        Matcher matcher = pattern.matcher( this.tfMontant.getText().trim() );
        if(matcher.matches())  lblIconMontant.setIcon( null );
        else {
            lblIconMontant.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        }
        if( this.tTADescription.getText().trim().isEmpty() ) {
            lblIconDescription.setIcon( new ImageIcon("icons/cancelform.png") );
            return false;
        } else lblIconDescription.setIcon( null );
        return true;
    }
    
    private void clean() {
        comboBoxMinistere.setSelectedIndex(0);
        noProjet();
        comboBoxTypeProjet.setSelectedIndex(0);
        this.tfZoneConcerne.setText("");
        lblIconZoneConcerne.setIcon( null );
        this.tfNomFirme.setText("");
        lblIconNomFirme.setIcon( null );
        this.tfMaitreOuvrage.setText("");
        lblIconMaitreOuvrage.setIcon( null );
        this.tfMontant.setText("");
        lblIconMontant.setIcon( null );
        this.tTADescription.setText("");
        lblIconDescription.setIcon( null );
        btnEnregistrer.setText("Enregistrer");
        btnEnregistrer.setIcon(new ImageIcon("icons/Save.png")); 
    }
    
    public void remplir( String code ) {
        List<GestionProjets> list = null;
        list = controleur.recherche(code, true);
        if ( list != null ) {
            this.lblCode.setText( list.get(0).getCode() );
            this.tfExerciceFiscale.setText( list.get(0).getExerciceFiscale() );
            this.comboBoxMinistere.setSelectedItem( list.get(0).getMinistere() ); 
            this.tfNuméroProjet.setText( String.valueOf( list.get(0).getNumeroProjet() ) ); 
            this.comboBoxTypeProjet.setSelectedItem( list.get(0).getTypeProjet() );        
            this.tfZoneConcerne.setText( list.get(0).getZoneConcernee() );
            this.tfNomFirme.setText( list.get(0).getNomFirme() );
            this.tfMaitreOuvrage.setText( list.get(0).getMaitreOuvrage() ); 
            this.tfMontant.setText( String.valueOf( list.get(0).getCoutProjet() ) );
            this.tTADescription.setText( list.get(0).getDescriptionProjet() );
            btnEnregistrer.setText("Modifier");
            btnEnregistrer.setIcon( new ImageIcon("icons/sort_by_modified_date.png") );
        }
    }
    
    private void comboBoxAddMinistere(){
        com.Controleur.GestionMinisteres_controleur controlMinistere = new com.Controleur.GestionMinisteres_controleur();
        List<Ministeres> listeMinistere = controlMinistere.recherche(null, false);
            if( listeMinistere != null )
                for(int i = 0; i < listeMinistere.size(); i++)
                    comboBoxMinistere.addItem(listeMinistere.get(i).getSigle());
    }

    private void noProjet() { tfNuméroProjet.setText( String.valueOf( controleur.numeroProjet( this.tfExerciceFiscale.getText().trim() ) ) ); }
    
    private final String[] TypeProjet = { "Assainissement", "Construction", "Infrastructure", "Santé", "Education", "Formation", "Culturel"};
    private final GestionProjets_controleur controleur = new GestionProjets_controleur();
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final Listener listener = new Listener();
    private final GridBagConstraints c = new GridBagConstraints();
    private JLabel lblCode;
    private JTextField tfExerciceFiscale;
    private JComboBox<String> comboBoxMinistere;
    private JTextField tfNuméroProjet;
    private JComboBox<String> comboBoxTypeProjet;
    private JTextField tfMontant ;
    private JLabel lblIconMontant ;
    private JTextField tfNomFirme;
    private JLabel lblIconNomFirme;
    private JTextField tfZoneConcerne;
    private JLabel lblIconZoneConcerne;
    private JTextField tfMaitreOuvrage;
    private JLabel lblIconMaitreOuvrage;
    private JTextArea tTADescription;
    private JLabel lblIconDescription;
    private JButton btnEnregistrer;
    private String codeProjet = new String();
    public static JInternalFrame projets = null;
}