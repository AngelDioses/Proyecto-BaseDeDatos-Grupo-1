
package Modelo;

public class Proy_Partida_Mezcla {
    private int codCia;
    private int codPyto;
    private String ingEgr;
    private int nroVersion;
    private int codPartida;
    private int corr;
    private int padCodPartida;
    private String tUnitMed;
    private String eUnitMed;
    private int nivel;
    private int orden;
    private float costoUnit;
    private int cant;
    private float costoTot;

    public Proy_Partida_Mezcla() {
    }

    public Proy_Partida_Mezcla(int codCia, int codPyto, String ingEgr, int nroVersion, int codPartida, int corr, int padCodPartida, String tUnitMed, String eUnitMed, int nivel, int orden, float costoUnit, int cant, float costoTot) {
        this.codCia = codCia;
        this.codPyto = codPyto;
        this.ingEgr = ingEgr;
        this.nroVersion = nroVersion;
        this.codPartida = codPartida;
        this.corr = corr;
        this.padCodPartida = padCodPartida;
        this.tUnitMed = tUnitMed;
        this.eUnitMed = eUnitMed;
        this.nivel = nivel;
        this.orden = orden;
        this.costoUnit = costoUnit;
        this.cant = cant;
        this.costoTot = costoTot;
    }

    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public int getCodPyto() {
        return codPyto;
    }

    public void setCodPyto(int codPyto) {
        this.codPyto = codPyto;
    }

    public String getIngEgr() {
        return ingEgr;
    }

    public void setIngEgr(String ingEgr) {
        this.ingEgr = ingEgr;
    }

    public int getNroVersion() {
        return nroVersion;
    }

    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
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

    public float getCostoUnit() {
        return costoUnit;
    }

    public void setCostoUnit(float costoUnit) {
        this.costoUnit = costoUnit;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public float getCostoTot() {
        return costoTot;
    }

    public void setCostoTot(float costoTot) {
        this.costoTot = costoTot;
    }
}
