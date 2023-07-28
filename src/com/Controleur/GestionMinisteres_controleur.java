package com.Controleur;
import com.Model.Ministeres;
import com.Model.DataBase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GestionMinisteres_controleur {
    private final DataBase db = new DataBase();
    private final String table = "ministere";

    public boolean modification(Ministeres ministeres, boolean modify) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        HashMap<String, String> fields = new HashMap<>();
        fields.put("code", ministeres.getCode());
        fields.put("nom", ministeres.getNom());
        fields.put("sigle", ministeres.getSigle());
        fields.put("adresse", ministeres.getAdresse());
        fields.put("telephone", ministeres.getTelephone());
        fields.put("ministreActuel", ministeres.getMinistreActuel());
        fields.put("noBRH", String.valueOf(ministeres.getNoBRH()));
        fields.put("noBNC", String.valueOf(ministeres.getNoBNC()));
        fields.put("soldeBRH", String.valueOf(ministeres.getSoldeBRH()));
        fields.put("soldeBNC", String.valueOf(ministeres.getSoldeBNC()));
        req.put("fields", fields);
        if (modify) {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("code", ministeres.getCode());
            req.put("conditions", conditions);
        }
        System.out.println("lastInsertID : " + db.getLastInsertId( this.table ) );
        return db.save(req);
    }
    
    public boolean delete(String code) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("code", code);
        req.put("conditions", conditions);
        return db.delete(req);
    }
  
    public java.util.List<Ministeres> recherche(String occurrence, boolean search){
        List<Ministeres> listMinisteres = new LinkedList<>();
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        if (search) {
            HashMap<String, Object> conditions = new HashMap<>();
            List<String> serchTable = new LinkedList<>();
            serchTable.add("code");
            serchTable.add("nom");
            serchTable.add("sigle");
            conditions.put("like", serchTable);
            conditions.put("occurence", occurrence);
            req.put("conditions", conditions);
        }
        List<Map<String, Object>> list = db.find(req);
        for (int i = 0; i < list.size(); i++) {
            Ministeres ministere = new Ministeres(
                list.get(i).get("code").toString(),
                list.get(i).get("nom").toString(),
                list.get(i).get("sigle").toString(),
                list.get(i).get("adresse").toString(),
                list.get(i).get("telephone").toString(),
                list.get(i).get("ministreActuel").toString(),
                list.get(i).get("noBRH").toString(),
                list.get(i).get("noBNC").toString()
            );
            listMinisteres.add(ministere);
        }
        return listMinisteres;
    }
}