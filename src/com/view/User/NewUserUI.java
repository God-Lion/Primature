package com.view.User;

import com.Controleur.LoginControleur;
import com.Model.Login;
import com.view.Primature.Listener.Listener;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class NewUserUI extends JInternalFrame  {
    
    public NewUserUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Nouveau Utilisateur >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        form();
        newUserUI = this;
    }
    
    private void form() {
        JLabel lblUsername = new JLabel("Nom d'utilisateur");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 0;
        panelPrincipal.add(lblUsername, c);
        this.tfUsername = new JTextField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 1;
        panelPrincipal.add(this.tfUsername, c);
        lblUsernameIfError = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 1;
        panelPrincipal.add(lblUsernameIfError, c);
        lblUsernameIndiacation = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 20, 20);
        c.gridx = 0;
        c.gridy = 2;
        panelPrincipal.add(lblUsernameIndiacation, c);
        JLabel lblPasseword = new JLabel("Mot de Passe");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 3;
        panelPrincipal.add(lblPasseword, c);
        this.tfPassWord =  new JPasswordField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(this.tfPassWord, c);
        lblPasswordIfError = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 4;
        panelPrincipal.add(lblPasswordIfError, c);
        lblPasswordIndiacation = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 20, 20);
        c.gridx = 0;
        c.gridy = 5;
        panelPrincipal.add(lblPasswordIndiacation, c);
        JLabel lblPassewordConfirm = new JLabel("Confirmer le mot de Passe");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 6;
        panelPrincipal.add(lblPassewordConfirm, c);
        this.tfPassWordConfirm =  new JPasswordField();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 0);
        c.gridx = 0;
        c.gridy = 7;
        panelPrincipal.add(this.tfPassWordConfirm, c);
        lblPasswordConfirmIfError = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 1;
        c.gridy = 7;
        panelPrincipal.add(lblPasswordConfirmIfError, c);
        lblPassWordConfirmIndiacation = new JLabel();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 30, 20);
        c.gridx = 0;
        c.gridy = 8;
        panelPrincipal.add(lblPassWordConfirmIndiacation, c);
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 10;
        btnEnregistrer.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                if ( testBefore() ) {
                    Login login = new Login(
                        tfUsername.getText().trim(),
                        String.valueOf(tfPassWord.getPassword()).trim()
                    );
                    if ( btnEnregistrer.getText().equals("Enregistrer") ) {
                        if ( new LoginControleur().save(login, false) ) {
                            listener.enregistrer(e);
                            ListUserUI listUserUI = (ListUserUI) ListUserUI.listUserUI;
                            listUserUI.refreshList();
                            clean();
                        } else listener.saveError(e, "NewUserUI");
                    }
                }
           }
        });
        panelPrincipal.add(btnEnregistrer, c);
    }   
 
    private boolean testBefore(){
        Color color = new Color( 236, 38, 36 );
        if ( tfUsername.getText().trim().isEmpty() ){
            lblUsernameIfError.setIcon( new ImageIcon("icons/cancelform.png") );
            lblUsernameIndiacation.setForeground( color );
            lblUsernameIndiacation.setText("C'est vide ");
            return false;
        } else {
            lblUsernameIfError.setIcon( null );
            lblUsernameIndiacation.setText("");
        }
        if ( String.valueOf(tfPassWord.getPassword()).trim().length() < 6 ){
            lblPasswordIfError.setIcon( new ImageIcon("icons/cancelform.png") );
            lblPasswordIndiacation.setForeground( color );
            lblPasswordIndiacation.setText("Trop court doit-etre superieur a 6 charctere ");
            return false;
        } else {
            lblPasswordIfError.setIcon( null );
            lblPasswordIndiacation.setText("");
        }
        if ( String.valueOf(tfPassWordConfirm.getPassword()).trim().isEmpty() ){
            lblPasswordConfirmIfError.setIcon( new ImageIcon("icons/cancelform.png") );
            lblPassWordConfirmIndiacation.setForeground( color );
            lblPassWordConfirmIndiacation.setText("C'est vide ");
            return false;
        } else if ( !String.valueOf(tfPassWordConfirm.getPassword()).trim().equals( String.valueOf(tfPassWord.getPassword()).trim() ) ){
            lblPasswordConfirmIfError.setIcon( new ImageIcon("icons/cancelform.png") );
            lblPassWordConfirmIndiacation.setForeground( color );
            lblPassWordConfirmIndiacation.setText("Pas egal ");
            return false;
        } else {
            lblPasswordConfirmIfError.setIcon( null );
            lblPassWordConfirmIndiacation.setText("");
        }
        return true;
    }
    
    private void clean() {
        this.tfUsername.setText("");
        lblUsernameIfError.setIcon( null );
        this.tfPassWord.setText("");
        lblPasswordConfirmIfError.setIcon( null );
        this.tfPassWordConfirm.setText("");
        lblPasswordConfirmIfError.setIcon( null );
    }
    
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final GridBagConstraints c = new GridBagConstraints();
    private final Listener listener = new Listener();
    private JTextField tfUsername;
    private JLabel lblUsernameIfError, lblUsernameIndiacation;
    private JPasswordField tfPassWord;
    private JLabel lblPasswordIfError, lblPasswordIndiacation;
    private JPasswordField tfPassWordConfirm;
    private JLabel lblPasswordConfirmIfError, lblPassWordConfirmIndiacation;
    private JButton btnEnregistrer;
    private String id;
    public static JInternalFrame newUserUI = null;
}