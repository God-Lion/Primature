package com.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DataBase {
    private Connection connexion;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public int id;
    
    public boolean connecter(){
        boolean connecter = false;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            this.connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/primature", "root", "");
            connecter = true;
        }catch( java.lang.ClassNotFoundException e ){
            System.err.println("Pilote de la base de donnee non-trouvee");
        }catch(java.sql.SQLException e){
            throw new Exception(e.toString()); 
        }finally{ return connecter; }
    }
    
    public boolean deconnecter(){
        boolean deconnecter = false;
        try{
            this.connexion.close();
            deconnecter = true;
        }
        catch(java.sql.SQLException e){
            throw new Exception(e.toString());
        }finally{ return deconnecter; }
    }
    
    public List<Map<String, Object>> find(HashMap<String, Object> req) {
        List<Map<String, Object>> rows = new ArrayList<>();
        try{
            if( connecter() ){
                List condValue = new LinkedList();
                String sql = "SELECT ";
                if(req.containsKey("fields")) sql += req.get("fields");
                else sql += " * ";
                if(req.containsKey("table")) sql += " FROM " + req.get("table");
                else return null;
                if(req.containsKey("conditions")){
                    sql += " WHERE ";
                    Map<String, Object> conditions;
                    conditions = (Map<String, Object>) req.get("conditions");
                    if (conditions.containsKey("like")) {
                        List<String> list = (List<String>) conditions.get("like");
                        List<String> list2= new LinkedList<>();
                        for (int i = 0; i < list.size(); i++) {
                            list2.add(list.get(i) + " LIKE ?");
                            condValue.add(conditions.get("occurence"));
                        }
                        sql += String.join(" OR ", list2);
                    }
                    else {
                        List cond = new LinkedList();
                        Set<Entry<String, Object>> setCond = conditions.entrySet();
                        Iterator<Entry<String, Object>> iterator = setCond.iterator();
                        while (iterator.hasNext()) {
                            Entry<String, Object> e = iterator.next();
                            condValue.add( e.getValue() );
                            cond.add( e.getKey() + " = ? " );
                        }
                        sql += String.join(" AND ", cond);
                    }
                }
                this.preparedStatement = this.connexion.prepareStatement( sql );
                for (int i = 0; i < condValue.size(); i++)
                    this.preparedStatement.setString(i+1, (String) condValue.get(i));
                
                System.out.println(this.preparedStatement);
                
                this.resultSet = this.preparedStatement.executeQuery();
                ResultSetMetaData metaData = this.resultSet.getMetaData();
                int nbColumn = metaData.getColumnCount();
                while(this.resultSet.next()){
                    Map<String, Object> row = new HashMap<>(nbColumn);
                    for (int i = 1; i <= nbColumn; i++)
                        row.put(metaData.getColumnName(i), this.resultSet.getObject(i) );
                    rows.add(row);
                }
            }       
        } catch (SQLException ex) {
           throw new Exception(ex.toString());
        } finally{
            deconnecter();
            return rows;
        }
    }
    
    public boolean delete(HashMap<String, Object> req) {
        byte delete = -1;
        List condValue = new LinkedList();
        try{
            if( connecter() ){
                String sql = "DELETE ";
                if(req.containsKey("table")) sql += "FROM " + req.get("table");
                else return false;
                if(req.containsKey("conditions")){
                    sql += " WHERE ";
                    Map<String, String> conditions;
                    conditions = (Map<String, String>) req.get("conditions");
                    List cond = new LinkedList();
                    Set<Entry<String, String>> setCond = conditions.entrySet();
                    Iterator<Entry<String, String>> iterator = setCond.iterator();
                    while (iterator.hasNext()) {
                        Entry<String, String> e = iterator.next();
                        condValue.add( e.getValue() );
                        cond.add( e.getKey() + " = ? " );
                    }
                    sql += String.join(" AND ", cond);
                }
                this.preparedStatement = this.connexion.prepareStatement( sql );
                for (int i = 0; i < condValue.size(); i++)
                    this.preparedStatement.setString(i+1, (String) condValue.get(i));
                delete = (byte) this.preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
        } finally{
            deconnecter();
            return delete == 1;
        }
    }
    
    public boolean save(HashMap<String, Object> req) {
        byte save = -1;
        List<Map<String, Object>> rows = new ArrayList<>();
        try{
            if( connecter() ){
                String sql = new String();
                String actions;
                List fieldsKey = new LinkedList();
                List fieldsValue = new LinkedList();
                if(req.containsKey("fields")){
                    Map<String, String> fields;
                    fields = (Map<String, String>) req.get("fields");
                    Set<Entry<String, String>> setfields = fields.entrySet();
                    Iterator<Entry<String, String>> iterator = setfields.iterator();
                    while (iterator.hasNext()) {
                        Entry<String, String> e = iterator.next();
                        fieldsValue.add( e.getValue() );
                        fieldsKey.add( e.getKey() + " = ?" );
                    }
                }
                if(req.containsKey("conditions")){
                   sql="UPDATE " + req.get("table") + " SET ";
                   sql += String.join(",", fieldsKey);
                   sql += " WHERE ";
                    Map<String, String> conditions;
                    conditions = (Map<String, String>) req.get("conditions");
                    List cond = new LinkedList();
                    Set<Entry<String, String>> setCond = conditions.entrySet();
                    Iterator<Entry<String, String>> iterator = setCond.iterator();
                    while (iterator.hasNext()) {
                        Entry<String, String> e = iterator.next();
                        fieldsValue.add( e.getValue() );
                        cond.add( e.getKey() + " = ?" );
                    }
                    sql += String.join(" AND ", cond); 
                    actions = "update";
                }
                else {
                   sql="INSERT INTO " + req.get("table") + " SET ";
                   if(req.containsKey("fields")) sql += String.join(",", fieldsKey);
                   actions = "insert";
                }
                
                System.out.println(sql);
                
                this.preparedStatement = this.connexion.prepareStatement( sql );
                for (int i = 0; i < fieldsValue.size(); i++) {
                    System.out.println(fieldsValue.get(i));
                    this.preparedStatement.setString(i+1, (String) fieldsValue.get(i));
                }
                save = (byte) this.preparedStatement.executeUpdate();
                System.out.println(this.preparedStatement);
//                if (actions.equals("insert")) {
//                    this.resultSet = this.preparedStatement.getGeneratedKeys();
//                  
//                }
            }       
        } catch (SQLException ex) {
           throw new Exception(ex.toString());
        } finally{
            deconnecter();
            return save == 1;
        }
    }
}