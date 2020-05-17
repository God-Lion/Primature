package com;

import com.view.Primature.PrimatureUI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {
    public static void main( String ... args ){
        try {
            UIManager.setLookAndFeel( new NimbusLookAndFeel() );
            PrimatureUI gNU = new PrimatureUI();
            gNU.setVisible( true );
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

//        GestionMinisteres_controleur g = new GestionMinisteres_controleur();
//        g.recherche("o");
    }
}
