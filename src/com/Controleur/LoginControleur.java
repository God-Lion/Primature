package com.Controleur;

import com.Model.DataBase;
import com.Model.Login;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LoginControleur {
    private final DataBase db = new DataBase();
    private final String table = "login";
    

    public boolean login(Login login) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        req.put("fields", "username, password");
        HashMap<String, String> conditions = new HashMap<>();
        conditions.put("username", login.getUsername());
        conditions.put("password", login.getPassword());
        req.put("conditions", conditions);
        List<Map<String, Object>> rows = this.db.find(req);
        return !rows.isEmpty();
    }
    
    public boolean save( Login login, boolean modify ) {
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        HashMap<String, String> fields = new HashMap<>();
        fields.put("USERNAME", login.getUsername() );
        if ( modify ) {
            fields.put("RIGHTSAVE", (login.isRightSave()) ? "1" : "0" );
            fields.put("RIGHTNODIFY", (login.isRightModify()) ? "1" : "0" );
            fields.put("RIGHTDELETE", (login.isRightDelete()) ? "1" : "0" );
            fields.put("RIGHTSEARCH", (login.isRightSearch()) ? "1" : "0" );
            HashMap<String, String> conditions = new HashMap<>();
            conditions.put("USERNAME", login.getUsername() );
            req.put("conditions", conditions);
        } else {
            fields.put("PASSWORD", login.getPassword() );
        }
        req.put("fields", fields);
        return db.save(req);
    }
    
    public java.util.List<Login> recherche(String occurrence, boolean search){
        List<Login> listLogin = new LinkedList<>();
        HashMap<String, Object> req = new HashMap<>();
        req.put("table", this.table );
        if (search) {
            HashMap<String, Object> conditions = new HashMap<>();
            List<String> serch = new LinkedList<>();
            serch.add("ID_USER");
            serch.add("USERNAME");
            serch.add("PASSWORD");        
            conditions.put("like", serch);
            conditions.put("occurence", occurrence);
            req.put("conditions", conditions);
        }
        List<Map<String, Object>> list = db.find(req);
        for (int i = 0; i < list.size(); i++) {
            Login login = new Login(
                list.get(i).get("ID_USER").toString(),
                list.get(i).get("USERNAME").toString(),
                list.get(i).get("PASSWORD").toString(),
                list.get(i).get("RIGHTS").toString(),
                Boolean.valueOf( list.get(i).get("RIGHTSAVE").toString() ),
                Boolean.valueOf( list.get(i).get("RIGHTNODIFY").toString() ),
                Boolean.valueOf( list.get(i).get("RIGHTDELETE").toString() ),
                Boolean.valueOf( list.get(i).get("RIGHTSEARCH").toString() )
            );
            listLogin.add( login );
        }
        return listLogin;
    }
}
