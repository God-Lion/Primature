package com.view.Primature.GProjets;

import com.Controleur.GestionProjets_controleur;
import com.Model.GestionProjets;
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
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PrintProjets extends JInternalFrame  {

    public PrintProjets() {
        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setTitle(" Print les Projets réalisés  >");
        this.setIconifiable(true);
        this.toFront();
        this.add(panelPrincipal);
        this.setVisible(true);
        Initcomposants();
        refreshList();
        printProjets = this;
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
                try {
                    list = controleur.recherche( "%" + tfSrecherche.getText() + "%", true );
                    if ( list != null ) listProjets(list);
                } catch (IndexOutOfBoundsException iOBE) {}
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
        this.tableListe.setModel(new DefaultTableModelImpl( new Object [][] {}, TitileListProjets ));
        JScrollPane scrollListe = new JScrollPane();
        scrollListe.setViewportView(tableListe);
        ListSelectionModel cellSelectionData = this.tableListe.getSelectionModel();
        cellSelectionData.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
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
                                if(controleur.delete( String.valueOf( tableListe.getValueAt(row, 0) ) )) {
                                    listener.supprimer( e );    
                                    refreshList();
                                }else listener.deleteError(e, "ProjetsUI");
                            }
                        });
                        menu.add( supprimerLine );
                        JMenuItem modifierLine = new JMenuItem("Modifier");
                        modifierLine.setIcon( new ImageIcon("icons/sort_by_modified_date.png") );
                        modifierLine.addActionListener( new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) { remplissageChamp(); }
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
        JButton btnLink = new JButton();
        btnLink.setIcon( new ImageIcon("icons/link.png") );
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(25, 0, 20, 80);
        c.gridx = 1;
        c.gridy = 2;               
        btnLink.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrimatureUI PrimatureUI = null;
                PrimatureUI = (PrimatureUI) PrimatureUI.primatureUI;
                if ( ProjetsUI.projets.isClosed() )
                    PrimatureUI.addFondUI();
                else {
                    ProjetsUI.projets.setBounds(0, 0, 1005 , 460);
                    printProjets.moveToBack();
                }
            }
        });               
        panelPrincipal.add(btnLink, c);
    }
    
    private void tfrechercheFocusGained( FocusEvent e) {                                     
        if( this.tfSrecherche.getText().equals("Recherche")) this.tfSrecherche.setText("");
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
    
    private void listProjets(  List<GestionProjets> listProjets ){
        if( listProjets != null){
            table = new Object[listProjets.size()][10];
            for(int i = 0; i < listProjets.size(); i++){
                table[i][0] = listProjets.get(i).getCode();
                table[i][1] = listProjets.get(i).getExerciceFiscale();
                table[i][2] = listProjets.get(i).getMinistere();
                table[i][3] = listProjets.get(i).getNumeroProjet();
                table[i][4] = listProjets.get(i).getTypeProjet();
                table[i][5] = listProjets.get(i).getZoneConcernee();
                table[i][6] = listProjets.get(i).getNomFirme();
                table[i][7] = listProjets.get(i).getMaitreOuvrage();
                table[i][8] = listProjets.get(i).getCoutProjet();
                table[i][9] = listProjets.get(i).getDescriptionProjet();
            }
            Nrow = "Nonbre de ligne : " + listProjets.size();
            this.lblnbRow.setText(Nrow);
            tableListe.setModel(new DefaultTableModel(table, TitileListProjets ){});
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
        ProjetsUI projetsUI = (ProjetsUI) ProjetsUI.projets;
        projetsUI.remplir(tableListe.getValueAt(row, 0).toString());
    }
    
    public  void refreshList() {
        try {
            this.list = controleur.recherche(null, false);
            if ( this.list != null ) listProjets(this.list );
        } catch ( IndexOutOfBoundsException e ) {}
    }
    
    private final String[] TitileListProjets = { "Code", "Exercice Fiscale", "Ministères", "Numéro projet ", "Type de proje", "Zone(s) concernées par le projet", "Nom Firme (Organisme ou Entreprise)", "Maitre d’ouvrage", "Montant",  "Description du projet"};
    private final GestionProjets_controleur controleur = new GestionProjets_controleur();
    private final JPanel panelPrincipal = new JPanel();
    private final Listener listener = new Listener();
    private List<GestionProjets> list = new LinkedList<>();
    private JTable tableListe;
    private Object table[][] = null;
    private JTextField tfSrecherche;
    private JLabel lblnbRow;
    private String Nrow;
    public static JInternalFrame printProjets = null;
}