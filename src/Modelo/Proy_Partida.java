
package Modelo;

public class Proy_Partida{
    
    private int CodCia;
    private int codPyto;
    private int nroVersion;
    private String ingEgr;
    private int CodPartida;
    private String CodPartidas;
    private String FlgCC;
    private int Nivel;
    private String uUniMed;
    private String tabEstado;
    private String codEstado;
    private char Vigente;
    
    public Proy_Partida() {
    }

    public Proy_Partida(int CodCia, int codPyto, int nroVersion, String ingEgr, int CodPartida, String CodPartidas, String FlgCC, int Nivel, String uUniMed, String tabEstado, String codEstado, char Vigente) {
        this.CodCia = CodCia;
        this.codPyto = codPyto;
        this.nroVersion = nroVersion;
        this.ingEgr = ingEgr;
        this.CodPartida = CodPartida;
        this.CodPartidas = CodPartidas;
        this.FlgCC = FlgCC;
        this.Nivel = Nivel;
        this.uUniMed = uUniMed;
        this.tabEstado = tabEstado;
        this.codEstado = codEstado;
        this.Vigente = Vigente;
    }

    public int getCodCia() {
        return CodCia;
    }

    public void setCodCia(int CodCia) {
        this.CodCia = CodCia;
    }

    public int getCodPyto() {
        return codPyto;
    }

    public void setCodPyto(int codPyto) {
        this.codPyto = codPyto;
    }

    public int getNroVersion() {
        return nroVersion;
    }

    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
    }

    public String getIngEgr() {
        return ingEgr;
    }

    public void setIngEgr(String ingEgr) {
        this.ingEgr = ingEgr;
    }

    public int getCodPartida() {
        return CodPartida;
    }

    public void setCodPartida(int CodPartida) {
        this.CodPartida = CodPartida;
    }

    public String getCodPartidas() {
        return CodPartidas;
    }

    public void setCodPartidas(String CodPartidas) {
        this.CodPartidas = CodPartidas;
    }

    public String getFlgCC() {
        return FlgCC;
    }

    public void setFlgCC(String FlgCC) {
        this.FlgCC = FlgCC;
    }

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int Nivel) {
        this.Nivel = Nivel;
    }

    public String getuUniMed() {
        return uUniMed;
    }

    public void setuUniMed(String uUniMed) {
        this.uUniMed = uUniMed;
    }

    public String getTabEstado() {
        return tabEstado;
    }

    public void setTabEstado(String tabEstado) {
        this.tabEstado = tabEstado;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public char getVigente() {
        return Vigente;
    }

    public void setVigente(char Vigente) {
        this.Vigente = Vigente;
    }

    
}
