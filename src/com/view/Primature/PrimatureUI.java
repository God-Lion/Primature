package com.view.Primature;

import com.Model.DataBase;
import com.view.Primature.GFonds.FondUI;
import com.view.Primature.GFonds.PrintFondUI;
import com.view.Primature.GMinistère.MinistereUI;
import com.view.Primature.GMinistère.PrintMinistereUI;
import com.view.Primature.GProjets.PrintProjets;
import com.view.Primature.GProjets.ProjetsUI;
import com.view.Primature.PayrollEmployes.PayrollEmployesUI;
import com.view.Primature.PayrollEmployes.PrintPayrollEmployes;
import com.view.User.AdminUI;
import com.view.User.ListUserUI;
import com.view.User.LoginUI;
import com.view.User.NewUserUI;
import com.view.User.RigthAccessUserUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class PrimatureUI extends JFrame {
    public JDesktopPane gMinistèresUI = new JDesktopPane();
    private JPanel panelPrincipal;
    public static JFrame primatureUI = null;
    public boolean isLogged = false;

    public PrimatureUI(){
        super("Primature");
        ImageIcon icon = new ImageIcon( "Icons/Coat_of_arms_of_Haiti.png" );
        this.setIconImage( icon.getImage() );
        this.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        if ( new DataBase().connecter() ) {
            connect();
            this.setSize(1020, 550);
            this.setLocationRelativeTo( null );
            this.setJMenuBar( this.createMenuBar() );
            Initcomposants();
        } else {
            JOptionPane.showMessageDialog(null, "Pas trouv\u00E9 la base de donn\u00E9e", "Not Found", JOptionPane.WARNING_MESSAGE, new ImageIcon("icons/warning_shield_64px.png") );
            System.exit(0);
        }
        primatureUI = this;
    }
    
    private void connect() {
        LoginUI loginUI = new LoginUI(this, true);
        loginUI.setVisible( true );
        if( LoginUI.trouver.equalsIgnoreCase("simple") ){
            loginUI.setVisible(false);
            this.setVisible( true );
        } if( LoginUI.trouver.equalsIgnoreCase("admin") ){
           loginUI.setVisible(false);
           this.setVisible( true );
        } if( LoginUI.trouver.equals("false") ) System.exit(0);
    }
    
    private void deconnect() {
        this.setVisible(false);
        LoginUI.trouver = "false";
        LoginUI.save = false;
        LoginUI.modify = false;
        LoginUI.delete = false;
        LoginUI.search = false;
        connect();
    }
    
    private void Initcomposants(){
        panelPrincipal = ( JPanel ) this.getContentPane();
        panelPrincipal.setLayout( new BorderLayout() );
        panelPrincipal.add( createToolBar(), BorderLayout.NORTH );
        panelPrincipal.add( gMinistèresUI, BorderLayout.CENTER );
    }
    
    private JMenuBar createMenuBar() {
        // La barre de menu à proprement parler
        JMenuBar menuBar = new JMenuBar();

        // Définition du menu déroulant "File" et de son contenu
        JMenu mnuOption = new JMenu( "Options" );
        mnuOption.setMnemonic( 'O' );

        JMenuItem mnuDeconnet = new JMenuItem( "Deconnecter" );
        mnuDeconnet.setIcon( new ImageIcon( "icons/logout.png" ) );
        mnuDeconnet.setMnemonic( 'D' );
        mnuDeconnet.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK) );
        mnuDeconnet.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {  deconnect(); }
        });
        mnuOption.add( mnuDeconnet );
        mnuOption.addSeparator();
        JMenuItem mnuExit = new JMenuItem( "Exit" );
        mnuExit.setIcon( new ImageIcon( "icons/exit.png" ) );
        mnuExit.setMnemonic( 'x' );
        mnuExit.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_DOWN_MASK) );
        mnuExit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {  System.exit(0); }
        });
        mnuOption.add(mnuExit);
        
        menuBar.add(mnuOption);
        
        // Définition du menu déroulant "Edit" et de son contenu
        JMenu mnuRacour = new JMenu( "Racourcis" );
        mnuRacour.setMnemonic( 'R' );
        
        JMenuItem mnuMinistere = new JMenuItem( "Ministere" );
        mnuMinistere.setIcon( new ImageIcon( "icons/parliament.png" ) );
        mnuMinistere.setMnemonic( 'M' );
        mnuMinistere.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK) );
        mnuMinistere.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addMinistereUI();
                addPrintMinistereUI();
            }
        });
        mnuRacour.add(mnuMinistere);
        mnuRacour.addSeparator();
        JMenuItem mnuFonds = new JMenuItem( "Fonds" );
        mnuFonds.setIcon( new ImageIcon( "icons/money_bag.png" ) );
        mnuFonds.setMnemonic( 'F' );
        mnuFonds.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK) );
        mnuFonds.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addFondUI();
                addPrintFondUI();
            }
        });
        mnuRacour.add(mnuFonds);
        JMenuItem mnuPayroll = new JMenuItem( "Payroll Employer" );
        mnuPayroll.setIcon( new ImageIcon( "icons/payroll.png" ) );
        mnuPayroll.setMnemonic( 'E' );
        mnuPayroll.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK) );
        mnuPayroll.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addPayrollEmployees();
                addPrintPayrollEmployees();
            }
        });
        mnuRacour.add(mnuPayroll);
        mnuRacour.addSeparator();
        JMenuItem mnuProjet = new JMenuItem( "Projets" );
        mnuProjet.setIcon( new ImageIcon( "icons/project.png" ) );
        mnuProjet.setMnemonic( 'P' );
        mnuProjet.setAccelerator( KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK) );
        mnuProjet.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addGProjets();
                addPrintGProjets();
            }
        });
        mnuRacour.add(mnuProjet);
        menuBar.add(mnuRacour);

        // Définition du menu déroulant "Help" et de son contenu
        JMenu mnuHelp = new JMenu( "Help" );
        mnuHelp.setMnemonic( 'H' );
        menuBar.add( mnuHelp );
        return menuBar;
    }
    
    private JToolBar createToolBar(){
        JToolBar toolBar = new JToolBar();
        toolBar.setRollover(true);
        JLabel lblPrimature = new JLabel("PRIMATURE");
        toolBar.add(lblPrimature);

        JButton btnGMinistere = new JButton();
        btnGMinistere.setToolTipText("Gestion des Ministères");
        btnGMinistere.setIcon( new ImageIcon( "icons/parliament.png" )  );
        btnGMinistere.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addMinistereUI();
                addPrintMinistereUI();
            }
        });
        toolBar.add(btnGMinistere);
        JButton btnGFondFiscale = new JButton();
        btnGFondFiscale.setToolTipText("Gestion des Fonds");
        btnGFondFiscale.setIcon( new ImageIcon( "icons/money_bag.png" )  );
        btnGFondFiscale.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addFondUI();
                addPrintFondUI();
            }
        });
        toolBar.add( btnGFondFiscale );
        JButton btnGPayroollEmployeews = new JButton();
        btnGPayroollEmployeews.setToolTipText("Payroll Employees");
        btnGPayroollEmployeews.setIcon( new ImageIcon( "icons/payroll.png" )  );
        btnGPayroollEmployeews.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addPayrollEmployees();
                addPrintPayrollEmployees();
            }
        });
        toolBar.add( btnGPayroollEmployeews );
        JButton btnGProjet = new JButton();
        btnGProjet.setToolTipText("Gestion des Prijets");
        btnGProjet.setIcon( new ImageIcon( "icons/project.png" )  );
        btnGProjet.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addGProjets();
                addPrintGProjets();
            }
        });
        toolBar.add( btnGProjet );
        JButton btnPanelAdmin = new JButton();
        btnPanelAdmin.setToolTipText("Administration Utilisateur");
        btnPanelAdmin.setIcon( new ImageIcon( "icons/administrative.png" )  );
        btnPanelAdmin.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gMinistèresUI.removeAll();
                gMinistèresUI.repaint();
                addAdminUI();
            }
        });
        if( LoginUI.trouver.equals("admin") ) toolBar.add( btnPanelAdmin );
       return toolBar;
    }
    
    public void addAdminUI() {
        AdminUI adminUI = new AdminUI();
        adminUI.setBounds(0, 0, 200, 460);
        adminUI.setVisible(true);
        gMinistèresUI.add( adminUI );
    }
    
    public void addANewUserUI() {
        NewUserUI newUserUI = new NewUserUI();
        newUserUI.setBounds(200, 0, 350, 460);
        newUserUI.setVisible(true);
        gMinistèresUI.add( newUserUI );
    }
    
    public void addListUserUI() {
        ListUserUI listUserUI = new ListUserUI();
        listUserUI.setBounds(540, 0, 465, 460);
        listUserUI.setVisible(true);
        gMinistèresUI.add( listUserUI );
    }
    
    public void addRigthAccessUserUI() {
        RigthAccessUserUI rigthAccessUserUI = new RigthAccessUserUI();
        rigthAccessUserUI.setBounds(200, 0, 350, 460);
        rigthAccessUserUI.setVisible(true);
        gMinistèresUI.add( rigthAccessUserUI );
    }
    
    public void addMinistereUI() {
        MinistereUI operation = new MinistereUI();
        operation.setBounds(0, 0, 550, 460);
        operation.setVisible(true);
        gMinistèresUI.add(operation);
    }
   
    public void addPrintMinistereUI() {
        PrintMinistereUI print = new PrintMinistereUI();
        print.setBounds(550, 0, 455, 460);
        print.setVisible(true);
        gMinistèresUI.add(print);
    }
    
    public void addFondUI() {
        FondUI fondUI = new FondUI();
        fondUI.setBounds(0, 0, 550, 460);
        fondUI.setVisible(true);
        gMinistèresUI.add(fondUI);
    }
    
    public void addPrintFondUI() {
        PrintFondUI printFondUI = new PrintFondUI();
        printFondUI.setBounds(550, 0, 455, 460);
        printFondUI.setVisible(true);
        gMinistèresUI.add(printFondUI);
    }
    
    public void addPayrollEmployees() {
        PayrollEmployesUI payrollEmployees = new PayrollEmployesUI();
        payrollEmployees.setBounds(0, 0, 550, 460);
        payrollEmployees.setVisible(true);
        gMinistèresUI.add(payrollEmployees);
    }
     
    public void addPrintPayrollEmployees() {
        PrintPayrollEmployes printPayrollEmployees = new PrintPayrollEmployes();
        printPayrollEmployees.setBounds(550, 0, 455, 460);
        printPayrollEmployees.setVisible(true);
        gMinistèresUI.add(printPayrollEmployees);
    }
    
    public void addGProjets() {
        ProjetsUI projets = new ProjetsUI();
        projets.setBounds(0, 0, 670, 460);
        projets.setVisible(true);
        gMinistèresUI.add(projets);
    }
     
    public void addPrintGProjets() {
        PrintProjets printProjets = new PrintProjets();
        printProjets.setBounds(670, 0, 338, 460);
        printProjets.setVisible(true);
        gMinistèresUI.add(printProjets);
    }
}
