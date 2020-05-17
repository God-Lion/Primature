package com.Controleur;

import com.Model.DataBase;
import com.Model.GestionFonds;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LUX
 */
public class GestionFonds_controleur {
    
    private final DataBase db = new DataBase();
    private final String table = "fonds";

    public boolean modification(GestionFonds gestionFonds, boolean modify) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Exercicefiscale", gestionFonds.getExerciceFiscale());
        fields.put("Ministere", gestionFonds.getMinistere());
        fields.put("Montant", String.valueOf(gestionFonds.getMontantAlloue()));
        fields.put("MontantBRH", String.valueOf(gestionFonds.getMontantBRH()));
        fields.put("MontantBNC", String.valueOf(gestionFonds.getMontantBNC()));
        fields.put("Signataires", gestionFonds.getSignataires());
        req.put("fields", fields);
        if (modify) {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("id", gestionFonds.getId());
            req.put("conditions", conditions);
        }
        return db.save(req);
    }
    
    public java.util.List<GestionFonds> recherche(String occurrence, boolean search){
        List<GestionFonds> listgestionFonds = new LinkedList<>();
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        if (search) {
            HashMap<String, Object> conditions = new HashMap<>();
            List<String> serch = new LinkedList<>();
            serch.add("id");
            serch.add("Exercicefiscale");
            serch.add("Ministere");        
            serch.add("Signataires");
            conditions.put("like", serch);
            conditions.put("occurence", occurrence);
            req.put("conditions", conditions);
        }
        List<Map<String, Object>> list = db.find(req);
        for (int i = 0; i < list.size(); i++) {
            GestionFonds gestionFonds = new GestionFonds(
                list.get(i).get("id").toString(),
                list.get(i).get("Exercicefiscale").toString(),
                list.get(i).get("Ministere").toString(),
                Double.parseDouble( list.get(i).get("Montant").toString() ),
                Double.parseDouble( list.get(i).get("MontantBRH").toString() ),
                Double.parseDouble( list.get(i).get("MontantBNC").toString() ),
                list.get(i).get("Signataires").toString()
            );
            listgestionFonds.add( gestionFonds );
        }
        return listgestionFonds;
    }
}