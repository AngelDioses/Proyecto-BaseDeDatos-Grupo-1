package Modelo;

public class Tabs {
    private String codTab;
    private String denTab;
    private String denCorta;
    private char vigente;

    public Tabs() {
    }

    public Tabs(String codTab, String denTab, String denCorta, char vigente) {
        this.codTab = codTab;
        this.denTab = denTab;
        this.denCorta = denCorta;
        this.vigente = vigente;
    }

    public String getCodTab() {
        return codTab;
    }

    public void setCodTab(String codTab) {
        this.codTab = codTab;
    }

    public String getDenTab() {
        return denTab;
    }

    public void setDenTab(String denTab) {
        this.denTab = denTab;
    }

    public String getDenCorta() {
        return denCorta;
    }

    public void setDenCorta(String denCorta) {
        this.denCorta = denCorta;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }
    
}
