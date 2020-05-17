package com.view.User;

import com.view.Primature.PrimatureUI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class AdminUI extends JInternalFrame {

    public AdminUI() {
        this.setClosable(false);
        this.setResizable(false);
        this.setMaximizable(false);
        this.setIconifiable(false);
        this.setTitle("Panel >");
        this.toFront();
        this.setResizable(false);
        this.add( panelPrincipal );
        this.setVisible(true);
        Initcomposants();
        adminUI = this;
    }
    
    private void Initcomposants() {
        btnUser = new JButton("Nouveau Utilisateur");
        btnUser.setIcon( new ImageIcon("icons/Save.png") );
        btnUser.setBackground( new Color( 57, 92, 118 ) );
        btnUser.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 0;
        btnUser.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
               primatureUI.addANewUserUI();
           }
        });
        panelPrincipal.add(btnUser, c);
        
        btnListUser = new JButton("Liste d'Utilisateur");
        btnListUser.setIcon( new ImageIcon("icons/Save.png") );
        btnListUser.setBackground( new Color( 57, 92, 118 ) );
        btnListUser.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 1;
        btnListUser.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
             primatureUI.addListUserUI();
           }
        });
        panelPrincipal.add(btnListUser, c);
        
        btnRightAccess = new JButton("Droit d'access");
        btnRightAccess.setIcon( new ImageIcon("icons/Save.png") );
        btnRightAccess.setBackground( new Color( 57, 92, 118 ) );
        btnRightAccess.setForeground( new Color( 227, 229, 255 ) );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 2;
        btnRightAccess.addActionListener( new ActionListener() {
           @Override
            public void actionPerformed(ActionEvent e) {
             primatureUI.addRigthAccessUserUI();
           }
        });
//        panelPrincipal.add(btnRightAccess, c);
    }
    private final PrimatureUI primatureUI = (PrimatureUI) PrimatureUI.primatureUI;
    private final GridBagConstraints c = new GridBagConstraints();
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private JButton btnUser, btnRightAccess, btnListUser;
    public static JInternalFrame adminUI = null;
}
