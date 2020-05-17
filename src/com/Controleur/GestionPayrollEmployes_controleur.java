package com.Controleur;

import com.Model.DataBase;
import com.Model.GestionFonds;
import com.Model.PayrollEmployes;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GestionPayrollEmployes_controleur {
    
    private final DataBase db = new DataBase();
    private final String table = "payrollemployes";
    
    private boolean operation(String id, String ministere, double montant, boolean modifier ){
        GestionFonds_controleur controleur = new GestionFonds_controleur();
        List<GestionFonds> listGestionFonds = null;
        listGestionFonds = controleur.recherche(ministere, true);
        try {
            if ( listGestionFonds != null ) {
                GestionFonds gFonds = null;
                if (modifier) {
                    if ( listGestionFonds.get(0).getMontantBNC() >= montant ) {
                        List<PayrollEmployes> listPE = null;
                        double result = 0;
                        listPE = recherche( id, true );
                        if ( montant >  listPE.get(0).getMontant() ) {
                            result = montant = listPE.get(0).getMontant();
                            gFonds = new GestionFonds(
                                listGestionFonds.get(0).getId(),
                                listGestionFonds.get(0).getExerciceFiscale(),
                                listGestionFonds.get(0).getMinistere(),
                                listGestionFonds.get(0).getMontantAlloue(),
                                listGestionFonds.get(0).getMontantBRH(),
                                listGestionFonds.get(0).getMontantBNC() - result,
                                listGestionFonds.get(0).getSignataires()
                            );
                        }
                        if ( montant <  listPE.get(0).getMontant() ) {
                            result = listPE.get(0).getMontant() - montant;
                            gFonds = new GestionFonds(
                                listGestionFonds.get(0).getId(),
                                listGestionFonds.get(0).getExerciceFiscale(),
                                listGestionFonds.get(0).getMinistere(),
                                listGestionFonds.get(0).getMontantAlloue(),
                                listGestionFonds.get(0).getMontantBRH(),
                                listGestionFonds.get(0).getMontantBNC() + result,
                                listGestionFonds.get(0).getSignataires()
                            );
                        }
                        if ( montant ==  listPE.get(0).getMontant() ) {
                            gFonds = new GestionFonds(
                                listGestionFonds.get(0).getId(),
                                listGestionFonds.get(0).getExerciceFiscale(),
                                listGestionFonds.get(0).getMinistere(),
                                listGestionFonds.get(0).getMontantAlloue(),
                                listGestionFonds.get(0).getMontantBRH(),
                                listGestionFonds.get(0).getMontantBNC(),
                                listGestionFonds.get(0).getSignataires()
                            );
                        }
                    }
                } else {
                    if ( listGestionFonds.get(0).getMontantBNC() >= montant  ) {
                        gFonds = new GestionFonds(
                            listGestionFonds.get(0).getId(),
                            listGestionFonds.get(0).getExerciceFiscale(),
                            listGestionFonds.get(0).getMinistere(),
                            listGestionFonds.get(0).getMontantAlloue(),
                            listGestionFonds.get(0).getMontantBRH(),
                            listGestionFonds.get(0).getMontantBNC() - montant,
                            listGestionFonds.get(0).getSignataires()
                        );
                    } else return false;
                }
                return controleur.modification(gFonds, true);
            }
        } catch ( IndexOutOfBoundsException e ) {}
        return false;
    }
    
    public boolean modification(PayrollEmployes payrollEmployes, boolean modify ) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Exercicefiscale", payrollEmployes.getExerciceFiscale());
        fields.put("Ministere", payrollEmployes.getMinistere());
        fields.put("Mois", String.valueOf(payrollEmployes.getMois()));
        fields.put("NomEmploye", payrollEmployes.getNomEmploye());
        fields.put("NumeroChequeBNC", payrollEmployes.getNoChequeBNC());
        fields.put("Montant", String.valueOf(payrollEmployes.getMontant()));
        req.put("fields", fields);
        boolean result = false;
        if (modify) {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("id", payrollEmployes.getId());
            req.put("conditions", conditions);
            result = operation( payrollEmployes.getId(), payrollEmployes.getMinistere(), payrollEmployes.getMontant(), true);
        } else result = operation( null, payrollEmployes.getMinistere(), payrollEmployes.getMontant(), false);
        if (result) return db.save(req);
        return false;
    }

    public java.util.List<PayrollEmployes> recherche(String occurrence, boolean search ){
        List<PayrollEmployes> listPayrollEmployes = new LinkedList<>();
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        if (search) {
            HashMap<String, Object> conditions = new HashMap<>();
            List<String> serch = new LinkedList<>();
            serch.add("id");
            serch.add("Exercicefiscale");
            serch.add("Ministere");
            serch.add("Mois");       
            serch.add("NomEmploye");        
            serch.add("NumeroChequeBNC");        
            serch.add("Montant");        
            conditions.put("like", serch);
            conditions.put("occurence", occurrence);
            req.put("conditions", conditions);
        }
        List<Map<String, Object>> list = db.find(req);
        for (int i = 0; i < list.size(); i++) {
            PayrollEmployes payrollEmployes = new PayrollEmployes (
                list.get(i).get("id").toString(),
                list.get(i).get("Exercicefiscale").toString(),
                list.get(i).get("Ministere").toString(),
                list.get(i).get("Mois").toString(),
                list.get(i).get("NomEmploye").toString(),
                list.get(i).get("NumeroChequeBNC").toString(),
                Double.parseDouble( list.get(i).get("Montant").toString() )
            );
            listPayrollEmployes.add( payrollEmployes );
        }
        return listPayrollEmployes;
    }
}