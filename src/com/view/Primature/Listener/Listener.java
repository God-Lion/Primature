package com.view.Primature.Listener;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Listener {
    
    public void enregistrer( ActionEvent e ){
        JOptionPane.showMessageDialog( null, "Enregistrement réussie !" );
    }
    
    public void modifier( ActionEvent e ){
        JOptionPane.showMessageDialog( null, "Modification effectuée !" );
    }
    
    public void supprimer( ActionEvent e ){
        JOptionPane.showMessageDialog( null, "Suppression réussie !" );
    }
    
    public void listeVide( ActionEvent e ){
        JOptionPane.showMessageDialog(null, "Pas trouv\u00E9", "Not Found", JOptionPane.WARNING_MESSAGE, new ImageIcon("icons/warning_shield_64px.png") );
    }
    
    public void window( WindowEvent e ){
        JOptionPane.showMessageDialog( null, "Button clicked !" );
//        System.exit(0);
    }
    
    public void deleteError( ActionEvent e, String str ){
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Veuillez verifiez les informations saisies \n\t\t OU\n\n Veuillez verifier le solde du ministere choisi.",
            "Impossible d'enregistrer",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
    
    public void saveError( ActionEvent e, String str ){
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Veuillez verifiez les informations saisies \n\t\t OU\n\n Veuillez verifier le solde du ministere choisi.",
            "Impossible d'enregistrer",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
    
    public void modifyError( ActionEvent e, String str ){
        switch (str) {
            case "ministere":
                break;
            case "payrollEmploye":
                break;
            case "fondUI":
                break;
            case "ProjetsUI":
                break;
            case "NewUserUI":
                break;
            case "RigthAccessUserUI":
                break;
            case "ListUserUI":
                break;
            default:
                break;
        }
        javax.swing.JOptionPane.showMessageDialog(
            null,
            "Veuillez verifiez les informations saisies \n\t\t OU\n\n Veuillez verifier le solde du ministere choisi.",
            "Impossible de modifier",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }
}
