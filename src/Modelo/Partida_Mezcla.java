package Modelo;

public class Partida_Mezcla {
    private int codCia;
    private String ingEgr;
    private int codPartida;
    private int corr;
    private int padCodPartida;
    private String tUnitMed;
    private String eUnitMed;
    private float costoUnit;
    private int nivel;
    private int orden;
    private char vigente;

    public Partida_Mezcla() {
    }

    public Partida_Mezcla(int codCia, String ingEgr, int codPartida, int corr, int padCodPartida, String tUnitMed, String eUnitMed, float costoUnit, int nivel, int orden, char vigente) {
        this.codCia = codCia;
        this.ingEgr = ingEgr;
        this.codPartida = codPartida;
        this.corr = corr;
        this.padCodPartida = padCodPartida;
        this.tUnitMed = tUnitMed;
        this.eUnitMed = eUnitMed;
        this.costoUnit = costoUnit;
        this.nivel = nivel;
        this.orden = orden;
        this.vigente = vigente;
    }

    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public String getIngEgr() {
        return ingEgr;
    }

    public void setIngEgr(String ingEgr) {
        this.ingEgr = ingEgr;
    }

    public int getCodPartida() {
        return codPartida;
    }

    public void setCodPartida(int codPartida) {
        this.codPartida = codPartida;
    }

    public int getCorr() {
        return corr;
    }

    public void setCorr(int corr) {
        this.corr = corr;
    }

    public int getPadCodPartida() {
        return padCodPartida;
    }

    public void setPadCodPartida(int padCodPartida) {
        this.padCodPartida = padCodPartida;
    }

    public String gettUnitMed() {
        return tUnitMed;
    }

    public void settUnitMed(String tUnitMed) {
        this.tUnitMed = tUnitMed;
    }

    public String geteUnitMed() {
        return eUnitMed;
    }

    public void seteUnitMed(String eUnitMed) {
        this.eUnitMed = eUnitMed;
    }

    public float getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(float costoUnit) {
        this.costoUnit = costoUnit;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }
}
