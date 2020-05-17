package com.Controleur;

import com.Model.DataBase;
import com.Model.GestionFonds;
import com.Model.GestionProjets;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LUX Group
 */
public class GestionProjets_controleur {
    
    private final DataBase db = new DataBase();
    private final String table = "projets";
    
    private boolean operation(String id, String ministere, double montant, boolean modifier ){
        GestionFonds_controleur controleur = new GestionFonds_controleur();
        List<GestionFonds> listGestionFonds = null;
        listGestionFonds = controleur.recherche(ministere, true);
        try {
            if ( listGestionFonds != null ) {
                GestionFonds gFonds = null;
                if (modifier) {
                    if ( listGestionFonds.get(0).getMontantBRH()>= montant ) {
                        List<GestionProjets> listGP = null;
                        double result = 0;
                        listGP = recherche( id, true );
                        if ( montant >  listGP.get(0).getCoutProjet() ) {
                            result = montant = listGP.get(0).getCoutProjet();
                            gFonds = new GestionFonds(
                                listGestionFonds.get(0).getId(),
                                listGestionFonds.get(0).getExerciceFiscale(),
                                listGestionFonds.get(0).getMinistere(),
                                listGestionFonds.get(0).getMontantAlloue(),
                                listGestionFonds.get(0).getMontantBRH() - result,
                                listGestionFonds.get(0).getMontantBNC(),
                                listGestionFonds.get(0).getSignataires()
                            );
                        }
                        if ( montant <  listGP.get(0).getCoutProjet() ) {
                            result = listGP.get(0).getCoutProjet() - montant;
                            gFonds = new GestionFonds(
                                listGestionFonds.get(0).getId(),
                                listGestionFonds.get(0).getExerciceFiscale(),
                                listGestionFonds.get(0).getMinistere(),
                                listGestionFonds.get(0).getMontantAlloue(),
                                listGestionFonds.get(0).getMontantBRH() + result,
                                listGestionFonds.get(0).getMontantBNC(),
                                listGestionFonds.get(0).getSignataires()
                            );
                        }
                        if ( montant ==  listGP.get(0).getCoutProjet() ) {
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
                    if ( listGestionFonds.get(0).getMontantBRH() >= montant  ) {
                        gFonds = new GestionFonds(
                            listGestionFonds.get(0).getId(),
                            listGestionFonds.get(0).getExerciceFiscale(),
                            listGestionFonds.get(0).getMinistere(),
                            listGestionFonds.get(0).getMontantAlloue(),
                            listGestionFonds.get(0).getMontantBRH() - montant,
                            listGestionFonds.get(0).getMontantBNC(),
                            listGestionFonds.get(0).getSignataires()
                        );
                    } else return false;
                }
                return controleur.modification(gFonds, true);
            }
        } catch ( IndexOutOfBoundsException e ) {}
        return false;
    }
    
    public boolean modification(GestionProjets gestionProjets, boolean modify ) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table);
        HashMap<String, String> fields = new HashMap<>();
        fields.put("Code", gestionProjets.getCode());
        fields.put("Exercicefiscale", gestionProjets.getExerciceFiscale() );
        fields.put("Ministere", gestionProjets.getMinistere() );
        fields.put("noProjet", String.valueOf( gestionProjets.getNumeroProjet() ) );
        fields.put("TypeProjet", gestionProjets.getTypeProjet() );
        fields.put("ZoneConcernee", gestionProjets.getZoneConcernee() );
        fields.put("Firme", gestionProjets.getNomFirme() );
        fields.put("MaitreOuvrage", gestionProjets.getMaitreOuvrage() );
        fields.put("Cout", String.valueOf( gestionProjets.getCoutProjet() ));
        fields.put("Description", gestionProjets.getDescriptionProjet() );
        req.put("fields", fields);
        boolean result = false;
        if (modify) {
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("Code", gestionProjets.getCode());
            req.put("conditions", conditions);
            result = operation( gestionProjets.getCode(), gestionProjets.getMinistere(), gestionProjets.getCoutProjet(), true);
        } else result = operation( null, gestionProjets.getMinistere(), gestionProjets.getCoutProjet(), false);
        System.out.println("result "+ result);
        if (result) return db.save(req);
        return false;
    }  
    
    public boolean delete(String code) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table);
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("Code", code);
        req.put("conditions", conditions);
        return db.delete(req);
    }
    
    public int numeroProjet( String anneFiscale ) {
        List<GestionProjets> list = null;
        list = recherche( anneFiscale, true );
        if ( list != null )
            try{ return list.get( list.size() - 1 ).getNumeroProjet() + 1;
            } catch( IndexOutOfBoundsException e ){}
        return 1;
    }
    
    public java.util.List<GestionProjets> recherche(String occurrence, boolean search){
        List<GestionProjets> listGestionProjets = new LinkedList<>();
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table);
        if (search) {
            HashMap<String, Object> conditions = new HashMap<>();
            List<String> serchTable = new LinkedList<>();
            serchTable.add("Code");
            serchTable.add("Exercicefiscale");
            serchTable.add("Ministere");
            serchTable.add("TypeProjet");       
            serchTable.add("ZoneConcernee");        
            serchTable.add("Firme");        
            serchTable.add("MaitreOuvrage");
            serchTable.add("Cout");   
            conditions.put("like", serchTable);
            conditions.put("occurence", occurrence);
            req.put("conditions", conditions);
        }
        List<Map<String, Object>> list = db.find(req);
        for (int i = 0; i < list.size(); i++) {
            GestionProjets gestionProjets = new GestionProjets (
                list.get(i).get("Code").toString(),
                list.get(i).get("Exercicefiscale").toString(),
                list.get(i).get("Ministere").toString(),
                Integer.parseInt(list.get(i).get("noProjet").toString() ),
                list.get(i).get("TypeProjet").toString(),
                list.get(i).get("ZoneConcernee").toString(),
                list.get(i).get("Firme").toString(),
                list.get(i).get("MaitreOuvrage").toString(),
                Double.parseDouble( list.get(i).get("Cout").toString() ),
                list.get(i).get("Description").toString()
            );
            listGestionProjets.add( gestionProjets );
        }
        return listGestionProjets;
    }
}