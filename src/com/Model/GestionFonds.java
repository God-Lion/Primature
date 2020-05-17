package com.Model;

/**
 *
 * @author LUX Group
 */
public class GestionFonds {
    private String id;
    private String exerciceFiscale;
    private String ministere;
    private double montantAlloue;
    private double montantBRH;
    private double montantBNC;
    private String signataires;

    public GestionFonds(){}

    public GestionFonds(String id, String exerciceFiscale, String ministere, double montantAlloue, double montantBRH, double montantBNC, String signataires) {
        this.id = id;
        this.exerciceFiscale = exerciceFiscale;
        this.ministere = ministere;
        this.montantAlloue = montantAlloue;
        this.montantBRH = montantBRH;
        this.montantBNC = montantBNC;
        this.signataires = signataires;
    }
    
    public GestionFonds(String exerciceFiscale, String ministere, double montantAlloue, double montantBRH, double montantBNC, String signataires) {
        this.exerciceFiscale = exerciceFiscale;
        this.ministere = ministere;
        this.montantAlloue = montantAlloue;
        this.montantBRH = montantBRH;
        this.montantBNC = montantBNC;
        this.signataires = signataires;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
    
    public String getExerciceFiscale() { return exerciceFiscale; }

    public void setExerciceFiscale(String exerciceFiscale) { this.exerciceFiscale = exerciceFiscale; }

    public String getMinistere() { return ministere; }

    public void setMinistere(String ministere) { this.ministere = ministere; }

    public double getMontantAlloue() { return montantAlloue; }

    public void setMontantAlloue(double montantAlloue) { this.montantAlloue = montantAlloue; }

    public double getMontantBRH() { return montantBRH; }

    public void setMontantBRH(double montantBRH) { this.montantBRH = montantBRH; }

    public double getMontantBNC() { return montantBNC; }

    public void setMontantBNC(double montantBNC) { this.montantBNC = montantBNC; }

    public String getSignataires() { return signataires; }

    public void setSignataires(String signataires) { this.signataires = signataires; }

    @Override
    public String toString() {
        return "GestionFonds{" + "id=" + id + ", exerciceFiscale=" + exerciceFiscale + ", ministere=" + ministere + ", montantAlloue=" + montantAlloue + ", montantBRH=" + montantBRH + ", montantBNC=" + montantBNC + ", signataires=" + signataires + '}';
    }
    
}
