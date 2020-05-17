package com.view.User;

import com.Controleur.LoginControleur;
import com.Model.Login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LoginUI extends JDialog{
    
    public LoginUI(Frame parent, boolean modal) {
        super(parent, modal);
        this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        this.setAlwaysOnTop(true);
        this.setLocation( new Point(417, 220) );
        this.setUndecorated(true);
        this.setSize(386, 333);
        panelPrincipal = ( JPanel ) this.getContentPane();
        panelPrincipal.setLayout( new BorderLayout());
        panelPrincipal.add( form(), BorderLayout.CENTER );
    }
    
    private JPanel form() {
        JPanel form = new JPanel(null);
        JLabel lbl = new JLabel();
        lbl.setIcon( new ImageIcon( "Icons/Coat_of_arms_of_Haiti.png" ) );
        lbl.setBounds(143, 40, 150, 100);
        form.add( lbl );
        JLabel lblUsername = new JLabel("Nom Utilisateur");
        lblUsername.setBackground( new Color(255, 255, 255) );
        lblUsername.setFont( new Font("Times New Roman", 0, 14) );
        lblUsername.setBounds(30, 180, 100, 10);
        form.add( lblUsername );
        tfUsername = new JTextField();
        tfUsername.setFont(new java.awt.Font("Times New Roman", 0, 14));
        tfUsername.setBounds(143, 173, 140, 25);
        tfUsername.addKeyListener(new KeyAdapter() {
            public void keyPressed( KeyEvent e ) {
                if( e.getKeyCode() == KeyEvent.VK_ENTER ) tfPassWord.requestFocus();
            }
        });
        form.add( tfUsername );
        lblUsernameIfError = new JLabel();
        lblUsernameIfError.setBounds(290, 179, 16, 16);
        form.add( lblUsernameIfError );
        JLabel lblPassword = new JLabel("Mot de passe");
        lblPassword.setBackground( new Color(255, 255, 255) );
        lblPassword.setFont( new Font("Times New Roman", 0, 14) );
        lblPassword.setBounds(30, 230, 100, 10);
        form.add( lblPassword );
        tfPassWord = new JPasswordField();
        tfPassWord.setFont( new Font("Times New Roman", 0, 14));
        tfPassWord.setBounds(143, 220, 140, 25);
        tfPassWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connecter();
            }
        });
        form.add( tfPassWord );
        lblPasswordIfError = new JLabel();
        lblPasswordIfError.setBounds(290, 225, 16, 16);
        form.add( lblPasswordIfError );
        btnConnect = new javax.swing.JButton("Connecter");
        btnConnect.setIcon( new javax.swing.ImageIcon("icons/login_rounded_right.png") );
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connecter();
            }
        });
        btnConnect.setBounds(90, 280, 105, 30);
        form.add( btnConnect );
        btnLoginCancel = new JButton("Annuler");
        btnLoginCancel.setIcon( new javax.swing.ImageIcon("icons/cancel.png") );
        btnLoginCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                setVisible(false);
//                System.exit(0);
            }
        });
        btnLoginCancel.setBounds(227, 280, 90, 30);
        form.add( btnLoginCancel );
        return form;
    }
    
    public void connecter(){
        Login log = new Login();
        log.setUsername( this.tfUsername.getText().trim() );
        log.setPassword( String.valueOf(this.tfPassWord.getPassword()).trim() );
        if( log.getUsername().isEmpty() && log.getPassword().isEmpty() ){
            this.lblUsernameIfError.setIcon( new ImageIcon("icons/cancelform.png") );
            this.lblPasswordIfError.setIcon( new ImageIcon("icons/cancelform.png") );
        }else if( log.getUsername().isEmpty() ){
            this.tfPassWord.setText("");
            this.lblUsernameIfError.setIcon( new ImageIcon("icons/cancelform.png") );
        }else if( log.getPassword().isEmpty() ){
            this.tfUsername.setText("");
            lblPasswordIfError.setIcon( new ImageIcon("icons/cancelform.png") );
        }else {
            
            if (controleur.login(log)) {
                trouver = "true";
                setVisible(false);
            } else{
                this.tfUsername.setText("");
                this.lblUsernameIfError.setIcon( new javax.swing.ImageIcon("icons/cancelform.png") );
                this.tfPassWord.setText("");
                this.lblPasswordIfError.setIcon( new javax.swing.ImageIcon("icons/cancelform.png") );
            }
        }  
    }
    
    public String connect(){ return this.trouver; }
    
    private final LoginControleur controleur = new LoginControleur();
    private final JPanel panelPrincipal;
    private JTextField tfUsername;
    private JLabel lblUsernameIfError;
    private JPasswordField tfPassWord;
    private JLabel lblPasswordIfError;
    private JButton btnConnect;
    private JButton btnLoginCancel;
    private String trouver = "false";
    public static String idUseur;
}
