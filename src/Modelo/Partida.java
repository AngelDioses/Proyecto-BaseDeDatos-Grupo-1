
package Modelo;

public class Partida {
    private int CodCia;
    private String ingEgr;
    private int CodPartida;
    private String CodPartidas;
    private String DesPartida;
    private String FlgCC;
    private int Nivel;
    private String tUniMed;
    private String eUniMed;
    private int Semilla;
    private char Vigente;

    public Partida(){
        
    }

    public Partida(int CodCia, String ingEgr, int CodPartida, String CodPartidas, String DesPartida, String FlgCC, int Nivel, String tUniMed, String eUniMed, int Semilla, char Vigente) {
        this.CodCia = CodCia;
        this.ingEgr = ingEgr;
        this.CodPartida = CodPartida;
        this.CodPartidas = CodPartidas;
        this.DesPartida = DesPartida;
        this.FlgCC = FlgCC;
        this.Nivel = Nivel;
        this.tUniMed = tUniMed;
        this.eUniMed = eUniMed;
        this.Semilla = Semilla;
        this.Vigente = Vigente;
    }

    public int getCodCia() {
        return CodCia;
    }

    public void setCodCia(int CodCia) {
        this.CodCia = CodCia;
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

    public String getDesPartida() {
        return DesPartida;
    }

    public void setDesPartida(String DesPartida) {
        this.DesPartida = DesPartida;
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

    public String gettUniMed() {
        return tUniMed;
    }

    public void settUniMed(String tUniMed) {
        this.tUniMed = tUniMed;
    }
    
    public String geteUniMed() {
        return eUniMed;
    }

    public void seteUniMed(String eUniMed) {
        this.eUniMed = eUniMed;
    }

    public int getSemilla() {
        return Semilla;
    }

    public void setSemilla(int Semilla) {
        this.Semilla = Semilla;
    }

    public char getVigente() {
        return Vigente;
    }

    public void setVigente(char Vigente) {
        this.Vigente = Vigente;
    }
    
    
}
