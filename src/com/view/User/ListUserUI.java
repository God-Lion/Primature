package com.view.User;

import com.Controleur.LoginControleur;
import com.Model.Login;
import com.view.Primature.Listener.Listener;
import com.view.Primature.PrimatureUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class ListUserUI extends JInternalFrame  {
    
    public ListUserUI() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle("List Utilisateur >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        Initcomposants();
        refreshList();
        listUserUI = this;
    }
    
    public final void Initcomposants() {
        panelPrincipal.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        tfSrecherche = new JTextField();
        tfSrecherche.setText("Recherche");
        tfSrecherche.setFont(new Font("Arial", 2, 14)); 
        tfSrecherche.setForeground(new Color(153, 153, 153));
        tfSrecherche.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(FocusEvent e) { tfrechercheFocusGained(e); }
            public void focusLost(FocusEvent e) { tfrechercheFocusLost(e); }
        });
        tfSrecherche.addKeyListener(new java.awt.event.KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                list = controleur.recherche( "%" + tfSrecherche.getText() + "%", true );
                if ( list != null ) listLogin(list);
            }
        });
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(40, 10, 0, 0);
        c.weightx = 5;
        c.gridx = 0;
        c.gridy = 0;
        panelPrincipal.add(tfSrecherche, c);
        JButton btnRecherche = new JButton("Rafraichir");
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(40, 0, 0, 10);
        c.weightx = 0;
        c.gridx = 1;
        c.gridy = 0;
        btnRecherche.setIcon( new ImageIcon("icons/search.png") );
        btnRecherche.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { refreshList(); }
        });
        panelPrincipal.add(btnRecherche, c);
        this.tableListe  = new JTable();
        this.tableListe.setAutoCreateRowSorter(true);
        this.tableListe.setModel(new DefaultTableModelImpl( new Object [][] {}, TitileListUser ));
        JScrollPane scrollListe = new JScrollPane();
        scrollListe.setViewportView(tableListe);
        this.tableListe.setColumnSelectionAllowed( false );
        this.tableListe.setRowSelectionAllowed( true );
        this.tableListe.addMouseListener(  new MouseAdapter(){
            public void mouseReleased(MouseEvent event){
                if( event.getButton() == MouseEvent.BUTTON3 ){
                    if(tableListe.columnAtPoint( new Point(event.getX(), event.getY() ) ) != -1 ){
                        JPopupMenu menu = new JPopupMenu();
                        JMenuItem supprimerLine = new JMenuItem("Supprimer");
                        supprimerLine.setIcon( new ImageIcon("icons/cancelform.png") );
                        supprimerLine.setForeground( new Color( 201, 15, 15 ) );
                        supprimerLine.addActionListener( new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) { 
                                int row = tableListe.getSelectedRow(); 
                                if( controleur.delete((String)tableListe.getValueAt(row, 0))) {
                                    listener.supprimer(e);
                                    refreshList();
                                } else listener.deleteError(e, "ListUserUI"); 
                            }
                        });
                        menu.add( supprimerLine );
                        JMenuItem modifierLine = new JMenuItem("Droit d'access");
                        modifierLine.setIcon( new ImageIcon("icons/sort_by_modified_date.png") );
                        modifierLine.addActionListener( new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                primatureUI.addRigthAccessUserUI();
                                remplissageChamp();
                            }
                        });
                        menu.add( modifierLine );
                        menu.show( tableListe, event.getX(), event.getY());
                    }    
                }   
            }  
        });
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 0, 10);
        c.gridwidth = 2;
        c.ipady = 280;
        c.gridx = 0;
        c.gridy = 1;
        panelPrincipal.add( scrollListe, c);
        lblnbRow = new JLabel();
        Nrow = "Nonbre de ligne : ";
        this.lblnbRow.setText(Nrow);
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 10, 0, 10);
        c.gridwidth = 2;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 2;
        panelPrincipal.add( lblnbRow, c);
    }
    
    private void tfrechercheFocusGained( FocusEvent e) {                                     
        if( this.tfSrecherche.getText().equals("Recherche"))
            this.tfSrecherche.setText("");
        this.tfSrecherche.setFont(new Font("Tahoma", 0, 14));
        this.tfSrecherche.setForeground(new Color(0, 0, 0));
    } 
     
    private void tfrechercheFocusLost(java.awt.event.FocusEvent e) {                                   
        if( this.tfSrecherche.getText().equals("")){
            this.tfSrecherche.setFont(new Font("Arial", 2, 14));
            this.tfSrecherche.setForeground(new Color(153, 153, 153));
            this.tfSrecherche.setText("Recherche");
        }
    }    
    
    private void listLogin(  List<Login> listLogin ){
        if( listLogin != null){
            table = new Object[listLogin.size()][10];
            for(int i = 0; i < listLogin.size(); i++){
                table[i][0] = listLogin.get(i).getUsername();
                table[i][1] = listLogin.get(i).isRightSave();
                table[i][2] = listLogin.get(i).isRightModify();
                table[i][3] = listLogin.get(i).isRightDelete();
                table[i][4] = listLogin.get(i).isRightSearch();
            }
            Nrow = "Nonbre de ligne : " + listLogin.size();
            this.lblnbRow.setText(Nrow);
            tableListe.setModel(new DefaultTableModel(table, TitileListUser ){});
        } else JOptionPane.showMessageDialog(null, "Pas trouv\u00E9", "Not Found", JOptionPane.WARNING_MESSAGE, new ImageIcon("icons/warning_shield_64px.png") );
    }

    private class DefaultTableModelImpl extends DefaultTableModel {
        public DefaultTableModelImpl(Object[][] data, Object[] columnNames) { super(data, columnNames); }
        Class[] types = new Class [] { String.class, String.class, String.class, String.class, String.class, String.class , String.class, String.class, String.class, String.class };
        @Override
        public Class getColumnClass(int columnIndex) { return types [columnIndex]; }
    }    
    
    private void remplissageChamp() {
        int row = tableListe.getSelectedRow(); 
        RigthAccessUserUI rigthAccessUserUI = (RigthAccessUserUI) RigthAccessUserUI.rigthAccessUserUI;
        rigthAccessUserUI.remplir(tableListe.getValueAt(row, 0).toString());
    }
    
    public final void refreshList() {
        try {
            this.list = controleur.recherche(null, false);
            if ( this.list != null ) listLogin( this.list );
        } catch (IndexOutOfBoundsException e) {}
    }
    
    private final String[] TitileListUser = { "Username", "Enregistrer", "Modifier", "Supprimer", "Rechercher"};
    private final JPanel panelPrincipal = new JPanel( new GridBagLayout() );
    private final GridBagConstraints c = new GridBagConstraints();
    private final LoginControleur controleur = new LoginControleur();
    private final Listener listener = new Listener();
    private final PrimatureUI primatureUI = (PrimatureUI) PrimatureUI.primatureUI;
    private JTable tableListe;
    private Object table[][] = null;
    private JTextField tfSrecherche;
    private JLabel lblnbRow;
    private String Nrow;
    private List<Login> list = new LinkedList<>();
    public static JInternalFrame listUserUI = null;
}