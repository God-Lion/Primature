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
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class RigthAccessUserUI  extends JInternalFrame  {
    
    public RigthAccessUserUI () {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("Nouveau Utilisateur >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        form();
        rigthAccessUserUI  = this;
    }
    
    private void form() {
        JLabel lblRightAccessUser = new JLabel("Droit d'access utilisateur");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 50, 0);
        c.gridx = 0;
        panelPrincipal.add(lblRightAccessUser, c);
        JLabel lblUsername = new JLabel("Nom d'utilisateur");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 10, 10, 0);
        c.gridx = 0;
        c.gridy = 1;
        panelPrincipal.add(lblUsername, c);
        this.tfUsername = new JTextField();
        this.tfUsername.setEditable(false);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 10, 50, 0);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        panelPrincipal.add(this.tfUsername, c);
        this.chkSave = new JCheckBox();
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 10, 0, 0);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        panelPrincipal.add(this.chkSave, c);
        JLabel lblSave = new JLabel("Droit d'enregistrer");
        c.gridx = 1;
        c.gridy = 3;
        panelPrincipal.add(lblSave, c);
        this.chkModify = new JCheckBox();
        c.gridx = 0;
        c.gridy = 4;
        panelPrincipal.add(this.chkModify, c);
        JLabel lblModify = new JLabel("Droit de modifier");
        c.gridx = 1;
        c.gridy = 4;
        panelPrincipal.add(lblModify, c);
        this.chkDelete = new JCheckBox();
        c.gridx = 0;
        c.gridy = 5;
        panelPrincipal.add(this.chkDelete, c);
        JLabel lblDelete = new JLabel("Droit de supprimer");
        c.gridx = 1;
        c.gridy = 5;
        panelPrincipal.add(lblDelete, c);
        this.chkSearch = new JCheckBox();
        c.gridx = 0;
        c.gridy = 6;
        panelPrincipal.add(this.chkSearch, c);
        JLabel lblSearch = new JLabel("Droit de rechercher");
        c.gridx = 1;
        c.gridy = 6;
        panelPrincipal.add(lblSearch, c);
        btnEnregistrer = new JButton("Enregistrer");
        btnEnregistrer.setIcon( new ImageIcon("icons/Save.png") );
        btnEnregistrer.setBackground( new Color( 63, 72, 204 ) );
        btnEnregistrer.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.FIRST_LINE_END;
        c.insets = new Insets(40, 20, 0, 20);
        c.gridx = 1;
        c.gridy = 8;
        this.btnEnregistrer.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login(
                    tfUsername.getText().trim(),
                    chkSave.isSelected(),
                    chkModify.isSelected(),
                    chkDelete.isSelected(),
                    chkSearch.isSelected()
                );
                if (controleur.save(login, true)) {
                   listener.enregistrer(e);
                   ListUserUI listUserUI = (ListUserUI) ListUserUI.listUserUI;
                   listUserUI.refreshList();
                   clean();
                }else listener.modifyError(e, "RigthAccessUserUI");
           }
        });
        panelPrincipal.add(btnEnregistrer, c);
    }
    
    public void remplir(String code) {
        clean();
        try {
            List<Login> list = null;
            list = controleur.recherche(code, true);
            if ( list != null ) {
                this.tfUsername.setText( list.get(0).getUsername() );
                this.chkSave.setSelected( list.get(0).isRightSave()  );
                this.chkModify.setSelected( list.get(0).isRightModify()  );
                this.chkDelete.setSelected( list.get(0).isRightDelete()  );
                this.chkSearch.setSelected( list.get(0).isRightSearch()  );
            }
        } catch ( IndexOutOfBoundsException e ){}
    }
    
    private void clean() {
        this.id = "";
        this.tfUsername.setText("");
        this.chkSave.setSelected(false);
        this.chkModify.setSelected(false);
        this.chkDelete.setSelected(false);
        this.chkSearch.setSelected(false);
    }
    
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final LoginControleur controleur = new LoginControleur();
    private final GridBagConstraints c = new GridBagConstraints();
    private final Listener listener = new Listener();
    private JTextField tfUsername;
    private JCheckBox chkSave, chkModify, chkDelete, chkSearch;
    private JButton btnEnregistrer;
    private String id;
    public static JInternalFrame rigthAccessUserUI  = null;
}