package com.Model;

/**
 *
 * @author LUX Group
 */
public class GestionProjets {
    private String code;
    private String exerciceFiscale;
    private String ministere;
    private int numeroProjet;
    private String typeProjet;
    private String zoneConcernee;
    private String nomFirme;
    private String maitreOuvrage;
    private double coutProjet;
    private String descriptionProjet;
    
    public GestionProjets(){}

    public GestionProjets(String code, String exerciceFiscale, String ministere, int numeroProjet, String typeProjet, String zoneConcernee, String nomFirme, String maitreOuvrage, double coutProjet, String descriptionProjet) {
        this.code = code;
        this.exerciceFiscale = exerciceFiscale;
        this.ministere = ministere;
        this.numeroProjet = numeroProjet;
        this.typeProjet = typeProjet;
        this.zoneConcernee = zoneConcernee;
        this.nomFirme = nomFirme;
        this.maitreOuvrage = maitreOuvrage;
        this.coutProjet = coutProjet;
        this.descriptionProjet = descriptionProjet;
    }
    
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getExerciceFiscale() { return exerciceFiscale; }

    public void setExerciceFiscale(String exerciceFiscale) { this.exerciceFiscale = exerciceFiscale; }

    public String getMinistere() { return ministere; }

    public void setMinistere(String ministere) { this.ministere = ministere; }

    public int getNumeroProjet() { return numeroProjet; }

    public void setNumeroProjet(int numeroProjet) { this.numeroProjet = numeroProjet; }

    public String getTypeProjet() { return typeProjet; }

    public void setTypeProjet(String typeProjet) { this.typeProjet = typeProjet; }

    public String getDescriptionProjet() { return descriptionProjet; }

    public void setDescriptionProjet(String descriptionProjet) { this.descriptionProjet = descriptionProjet; }

    public String getNomFirme() { return nomFirme; }

    public void setNomFirme(String nomFirme) { this.nomFirme = nomFirme; }

    public String getMaitreOuvrage() { return maitreOuvrage; }

    public void setMaitreOuvrage(String maitreOuvrage) { this.maitreOuvrage = maitreOuvrage; }

    public double getCoutProjet() { return coutProjet; }

    public void setCoutProjet(double coutProjet) { this.coutProjet = coutProjet; }

    public String getZoneConcernee() { return zoneConcernee; }

    public void setZoneConcernee(String zoneConcernee) { this.zoneConcernee = zoneConcernee; }
    
    public String genererCode() {
        return "PR-" + (int)(1000 *(Math.random()));
    }
}
