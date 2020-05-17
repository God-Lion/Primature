package com.Model;

public class Login {
    private String idUser;
    private String username;
    private String password;
    private String MODE_CONNECTION;
    private boolean rightSave;
    private boolean rightModify;
    private boolean rightDelete;
    private boolean rightSearch;


    public Login() {}
    
    public Login( String username, String password ) {
        this.username = username;
        this.password = password;
    }

    public Login(String idUser, String username, String password, String MODE_CONNECTION) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
    }

    public Login(String idUser, String username, String password, boolean rightSave, boolean rightModify, boolean rightDelete, boolean rightSearch) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.rightSave = rightSave;
        this.rightModify = rightModify;
        this.rightDelete = rightDelete;
        this.rightSearch = rightSearch;
    }

    public Login(String username, String MODE_CONNECTION, boolean rightSave, boolean rightModify, boolean rightDelete, boolean rightSearch) {
        this.username = username;
        this.MODE_CONNECTION = MODE_CONNECTION;
        this.rightSave = rightSave;
        this.rightModify = rightModify;
        this.rightDelete = rightDelete;
        this.rightSearch = rightSearch;
    }

    public Login(String idUser, String username, String password, String MODE_CONNECTION, boolean rightSave, boolean rightModify, boolean rightDelete, boolean rightSearch) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.MODE_CONNECTION = MODE_CONNECTION;
        this.rightSave = rightSave;
        this.rightModify = rightModify;
        this.rightDelete = rightDelete;
        this.rightSearch = rightSearch;
    }
    
    public String getIdUser() {
        return idUser;
    }public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }public void setPassword(String password) {
        this.password = password;
    }

    public String getMODE_CONNECTION() {
        return MODE_CONNECTION;
    }

    public void setMODE_CONNECTION(String MODE_CONNECTION) {
        this.MODE_CONNECTION = MODE_CONNECTION;
    }
    
    public boolean isRightSave() {
        return rightSave;
    }

    public void setRightSave(boolean rightSave) {
        this.rightSave = rightSave;
    }

    public boolean isRightModify() {
        return rightModify;
    }

    public void setRightModify(boolean rightModify) {
        this.rightModify = rightModify;
    }

    public boolean isRightDelete() {
        return rightDelete;
    }

    public void setRightDelete(boolean rightDelete) {
        this.rightDelete = rightDelete;
    }

    public boolean isRightSearch() {
        return rightSearch;
    }

    public void setRightSearch(boolean rightSearch) {
        this.rightSearch = rightSearch;
    }
}
