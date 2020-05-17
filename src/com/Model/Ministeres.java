package com.Model;

public class Ministeres {
    // variables d'instances
    private String code;
    private String nom; 
    private String sigle;
    private String adresse;
    private String telephone;
    private String ministreActuel;
    private String noBRH;
    private double soldeBRH;
    private String noBNC;
    private double soldeBNC;
    
    public Ministeres(){}

    public Ministeres(String code, String nom, String sigle, String adresse, String telephone, String ministreActuel, String noBRH, String noBNC) {
        this.code = code;
        this.nom = nom;
        this.sigle = sigle;
        this.adresse = adresse;
        this.telephone = telephone;
        this.ministreActuel = ministreActuel;
        this.noBRH = noBRH;
        this.noBNC = noBNC;
        this.soldeBRH = 0.0;
        this.soldeBNC = 0.0;
    }

    // Accesseurs
    public String getCode() { return this.code; }

    public String getNom() { return this.nom; }

    public String getSigle(){return this.sigle; }

    public String getAdresse(){ return this.adresse; }

    public String getTelephone(){ return this.telephone; }

    public String getMinistreActuel(){ return this.ministreActuel; }

    public String getNoBRH(){ return this.noBRH; }

    public String getNoBNC(){ return this.noBNC; }

    // Mutateurs
    public void setCode(String code) { this.code = code;}

    public void setNom(String nom) { this.nom = nom; }

    public void setSigle(String sigle) { this.sigle = sigle; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public void setTelephone(String telephone) { this.telephone = telephone; }

    public void setMinistreActuel(String ministreActuel) { this.ministreActuel = ministreActuel; }

    public void setNoBRH(String noBRH){ this.noBRH = noBRH; }

    public void setNoBNC(String noBNC){ this.noBNC = noBNC; }

    public double getSoldeBRH() { return soldeBRH; }

    public void setSoldeBRH(double soldeBRH) { this.soldeBRH = soldeBRH; }

    public double getSoldeBNC() { return soldeBNC; }

    public void setSoldeBNC(double soldeBNC) { this.soldeBNC = soldeBNC; }
    
    // Opérations
    // méthode générée code
    public String genererCode(){ return "MN-" + (int)(1000 *(Math.random())); }

    @Override
    public String toString() { return "Minist\u00e8res{" + "code=" + code + ", nom=" + nom + ", sigle=" + sigle + ", adresse=" + adresse + ", phone=" + telephone + ", nomMinistere=" + ministreActuel + ", noCompteBRH=" + noBRH + ", noCompteBNC=" + noBNC + '}'; }
}