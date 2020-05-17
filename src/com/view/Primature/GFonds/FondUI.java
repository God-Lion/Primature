package com.view.Primature.GFonds;

import com.Controleur.GestionFonds_controleur;
import com.Model.GestionFonds;
import com.Model.Ministeres;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public final class FondUI extends JInternalFrame  {
    
    public FondUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Gestion Fonds >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        form();
        button();
        fondUI = this;
    }
    
    private void form() {
        JButton btnList = new JButton();
        btnList.setIcon( new ImageIcon("icons/list.png") );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(30, 20, 20, 50);
        c.gridx = 0;
        c.gridy = 0;               
        btnList.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimatureUI PrimatureUI = null;
                PrimatureUI = (PrimatureUI) PrimatureUI.primatureUI;
                if (PrintFondUI.printFondUI.isClosed())
                    PrimatureUI.addPrintFondUI();
                else {
                    PrintFondUI.printFondUI.setBounds(0, 0, 1005, 460);
                    fondUI.moveToBack();
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
        JLabel lblMontant = new JLabel("Montant");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 0, 20);
        c.weightx = 0.0124;
        c.gridx = 0;
        c.gridy = 3;
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
            public void keyReleased(KeyEvent e) { operation(); }
        });
//        On l'active si on veut aue l'utiliastateur voit le resultat apres
//        tfMontant.addFocusListener(new java.awt.event.FocusAdapter() {
//            public void focusLost(FocusEvent e) { operation(); }
//        });
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(tfMontant, c);
        JLabel lblgdes = new JLabel("gdes");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 10, 0, 0);
        c.gridx = 1;
        c.gridy = 4;
        panelPrincipal.add(lblgdes, c);
        lblIconMontant = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 2;
        c.gridy = 4;
        panelPrincipal.add(lblIconMontant, c);
        JLabel lblBRH = new JLabel("BRH");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 2;
        c.gridy = 3;
        panelPrincipal.add(lblBRH, c);
        tfBRH = new JTextField();
        tfBRH.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 10);
        c.gridx = 2;
        c.gridy = 4;
        panelPrincipal.add(tfBRH, c);
        JLabel lblPercentBRH = new JLabel("gdes");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 4;
        c.gridy = 4;
        panelPrincipal.add(lblPercentBRH, c);
        lblIconBRH = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 5;
        c.gridy = 4;
        panelPrincipal.add(lblIconBRH, c);
        JLabel lblBNC = new JLabel("BNC");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 6;
        c.gridy = 3;
        panelPrincipal.add(lblBNC, c);
        tfBNC = new JTextField();
        tfBNC.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.gridx = 6;
        c.gridy = 4;
        panelPrincipal.add(tfBNC, c);
        JLabel lblPercentBNC = new JLabel("gdes");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 7;
        c.gridy = 4;
        panelPrincipal.add(lblPercentBNC, c);
        lblIconBNC = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 8;
        c.gridy = 4;
        panelPrincipal.add(lblIconBNC, c);
        JLabel lblSignatairesReceptionFonds = new JLabel("Signataires de la réception du Fonds");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 5;
        panelPrincipal.add(lblSignatairesReceptionFonds, c);
        tTASignatairesReceptionFonds = new JTextArea();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 10, 0);
        c.ipady = 2;
        c.weighty = 10;
        c.gridx = 0;
        c.gridy = 6;
        panelPrincipal.add(tTASignatairesReceptionFonds, c);
    }   
    
    private void operation(){
        double montant = 0;
        if ( !tfMontant.getText().isEmpty() ) {
            try {
                montant = Double.parseDouble(tfMontant.getText());
            } catch (java.lang.NumberFormatException e) {
                montant = 0;
            }
        }
        if( montant < 45000 ){
            tfBRH.setText("0");
            tfBNC.setText( String.valueOf( montant ));
        }  else {
            tfBRH.setText("" + montant * 0.85);
            tfBNC.setText("" + montant * 0.15);
        }
    }
    
    private void button() {
        c.insets = new Insets(60, 20, 20, 150);
        JButton btnNettoyer = new JButton("Nettoyer");
        btnNettoyer.setIcon( new ImageIcon("icons/clear_symbol.png") );
        btnNettoyer.setBackground( new Color( 115, 145, 248 ) );
        btnNettoyer.setForeground( new Color( 255, 255, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 7;               
        btnNettoyer.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {clean();}
        });               
        panelPrincipal.add(btnNettoyer, c);
        btnEnregistrer = new JButton("Allouer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(60, 0, 20, 0);
        c.gridx = 6;
        c.gridy = 7;
        if (btnEnregistrer.getText().equals("Allouer")) 
            btnEnregistrer.setEnabled( LoginUI.save );
        if (btnEnregistrer.getText().equals("Reallouer"))
            btnEnregistrer.setEnabled( LoginUI.modify );
        btnEnregistrer.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                if ( testBefore() ) {
                    GestionFonds gFonds;
                    if (btnEnregistrer.getText().equals("Allouer")) {
                        gFonds = new GestionFonds(
                            tfExerciceFiscale.getText(),
                            String.valueOf( comboBoxMinistere.getSelectedItem()),
                            Double.parseDouble(tfMontant.getText()),
                            Double.parseDouble(tfBRH.getText()),
                            Double.parseDouble(tfBNC.getText()),
                            tTASignatairesReceptionFonds.getText()
                        );
                        if(controleur.modification(gFonds, false)) {
                            listener.enregistrer(e);
                            clean();
                        } else listener.saveError(e, "fondUI");
                    }
                    if (btnEnregistrer.getText().equals("Reallouer")) {
                        gFonds = new GestionFonds(
                            id,
                            tfExerciceFiscale.getText(),
                            String.valueOf( comboBoxMinistere.getSelectedItem() ),
                            Double.parseDouble(tfMontant.getText()),
                            Double.parseDouble(tfBRH.getText()),
                            Double.parseDouble(tfBNC.getText()),
                            tTASignatairesReceptionFonds.getText()
                        );
                        if(controleur.modification(gFonds, true)) {
                            listener.modifier(e);
                            clean();
                        } else listener.modifyError(e, "fondUI");
                    }
                    PrintFondUI printFondUI = (PrintFondUI) PrintFondUI.printFondUI;
                    printFondUI.refreshList();
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
        this.tfMontant.setText("");
        lblIconMontant.setIcon( null );
        this.tfBRH.setText("");
        lblIconBRH.setIcon( null );
        this.tfBNC.setText("");
        lblIconBNC.setIcon( null );
        this.comboBoxMinistere.setSelectedIndex(0);
        this.lblIconComboMinistere.setIcon( null );
        tTASignatairesReceptionFonds.setText("");
        btnEnregistrer.setText("Allouer");
        btnEnregistrer.setIcon(new ImageIcon("icons/Save.png")); 
    }
    
    public void remplir(final String code) {
        try {
            List<GestionFonds> list = null;
            list = controleur.recherche(code, true);
            if ( list != null ) {
                this.tfExerciceFiscale.setText(list.get(0).getExerciceFiscale());
                this.comboBoxMinistere.setSelectedItem(list.get(0).getMinistere()); 
                this.tfMontant.setText(String.valueOf(list.get(0).getMontantAlloue()));
                this.tfBRH.setText(String.valueOf(list.get(0).getMontantBRH()));
                this.tfBNC.setText(String.valueOf(list.get(0).getMontantBNC()));
                this.tTASignatairesReceptionFonds.setText(list.get(0).getSignataires());
                id = list.get(0).getId();
                btnEnregistrer.setText("Reallouer");
                btnEnregistrer.setIcon(new ImageIcon("icons/sort_by_modified_date.png")); 
                panelPrincipal.add(btnEnregistrer, c);
            }
        } catch ( IndexOutOfBoundsException e ){}
    }
    
    private void comboBoxAddMinistere(){
        com.Controleur.GestionMinisteres_controleur controlMinistere = new com.Controleur.GestionMinisteres_controleur();
            List<Ministeres> listeMinistere = controlMinistere.recherche(null, false);
            if(!listeMinistere.isEmpty())
                for(int i =0; i < listeMinistere.size(); i++)
                    comboBoxMinistere.addItem(listeMinistere.get(i).getSigle());
    }
    
    private final GestionFonds_controleur controleur = new GestionFonds_controleur();
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final Listener listener = new Listener();
    private final GridBagConstraints c = new GridBagConstraints();
    private JTextField tfExerciceFiscale;
    private JComboBox<String> comboBoxMinistere;
    private JTextField tfMontant;
    private JLabel lblIconComboMinistere;
    private JLabel lblIconMontant;
    private JTextField tfBRH;
    private JLabel lblIconBRH;
    private JTextField tfBNC;
    private JLabel lblIconBNC;
    private JTextArea tTASignatairesReceptionFonds;
    private JButton btnEnregistrer;
    private String id;
    public static JInternalFrame fondUI = null;
}