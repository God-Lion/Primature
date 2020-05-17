package com.Model;

/**
 *
 * @author LUX group
 */
public class PayrollEmployes {
    private String id;
    private String exerciceFiscale;
    private String ministere;
    private String mois;
    private String nomEmploye;
    private String noChequeBNC;
    private double montant;

    public PayrollEmployes(){}

    public PayrollEmployes(String id, String exerciceFiscale, String ministere, String mois, String nomEmploye, String noChequeBNC, double montant) {
        this.id = id;
        this.exerciceFiscale = exerciceFiscale;
        this.ministere = ministere;
        this.mois = mois;
        this.nomEmploye = nomEmploye;
        this.noChequeBNC = noChequeBNC;
        this.montant = montant;
    }

    public PayrollEmployes(String exerciceFiscale, String ministere, String mois, String nomEmploye, String noChequeBNC, double montant) {
        this.exerciceFiscale = exerciceFiscale;
        this.ministere = ministere;
        this.mois = mois;
        this.nomEmploye = nomEmploye;
        this.noChequeBNC = noChequeBNC;
        this.montant = montant;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }
    
    public String getExerciceFiscale() { return exerciceFiscale; }

    public void setExerciceFiscale(String exerciceFiscale) { this.exerciceFiscale = exerciceFiscale; }

    public String getMinistere() { return ministere; }

    public void setMinistere(String ministere) { this.ministere = ministere; }

    public String getNomEmploye() { return nomEmploye; }

    public void setNomEmploye(String nomEmploye) { this.nomEmploye = nomEmploye; }

    public String getNoChequeBNC() { return noChequeBNC; }

    public void setNoChequeBNC(String noChequeBRH) { this.noChequeBNC = noChequeBRH; }

    public double getMontant() { return montant; }

    public void setMontant(double montant) { this.montant = montant; }

    public String getMois() { return mois; }

    public void setMois(String mois) { this.mois = mois; }

    @Override
    public String toString() {
        return "PayrollEmployes{" + "id=" + id + ", exerciceFiscale=" + exerciceFiscale + ", ministere=" + ministere + ", mois=" + mois + ", nomEmploye=" + nomEmploye + ", noChequeBNC=" + noChequeBNC + ", montant=" + montant + '}';
    }
}
