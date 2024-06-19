package Modelo;

import java.sql.Date;

public class DProyPartidaMezcla {
    private int codCia;
    private int codPyto;
    private String ingEgr;
    private int nroVersion;
    private int codPartida;
    private int corr;
    private int sec;
    private String tDesembolso;
    private String eDesembolso;
    private int nroPago;
    private String tCompPago;
    private String eCompPago;
    private Date fecDesembolso;
    private float impDesemNeto;
    private float impDesemIgv;
    private float impDesemTotal;
    private int semilla;
    private int rep;

    public DProyPartidaMezcla() {
    }

    public DProyPartidaMezcla(int codCia, int codPyto, String ingEgr, int nroVersion, int codPartida, int corr, int sec, String tDesembolso, String eDesembolso, int nroPago, String tCompPago, String eCompPago, Date fecDesembolso, float impDesemNeto, float impDesemIgv, float impDesemTotal, int semilla, int rep) {
        this.codCia = codCia;
        this.codPyto = codPyto;
        this.ingEgr = ingEgr;
        this.nroVersion = nroVersion;
        this.codPartida = codPartida;
        this.corr = corr;
        this.sec = sec;
        this.tDesembolso = tDesembolso;
        this.eDesembolso = eDesembolso;
        this.nroPago = nroPago;
        this.tCompPago = tCompPago;
        this.eCompPago = eCompPago;
        this.fecDesembolso = fecDesembolso;
        this.impDesemNeto = impDesemNeto;
        this.impDesemIgv = impDesemIgv;
        this.impDesemTotal = impDesemTotal;
        this.semilla = semilla;
        this.rep = rep;
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

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }

    public String gettDesembolso() {
        return tDesembolso;
    }

    public void settDesembolso(String tDesembolso) {
        this.tDesembolso = tDesembolso;
    }

    public String geteDesembolso() {
        return eDesembolso;
    }

    public void seteDesembolso(String eDesembolso) {
        this.eDesembolso = eDesembolso;
    }

    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public String gettCompPago() {
        return tCompPago;
    }

    public void settCompPago(String tCompPago) {
        this.tCompPago = tCompPago;
    }

    public String geteCompPago() {
        return eCompPago;
    }

    public void seteCompPago(String eCompPago) {
        this.eCompPago = eCompPago;
    }

    public Date getFecDesembolso() {
        return fecDesembolso;
    }

    public void setFecDesembolso(Date fecDesembolso) {
        this.fecDesembolso = fecDesembolso;
    }

    public float getImpDesemNeto() {
        return impDesemNeto;
    }

    public void setImpDesemNeto(float impDesemNeto) {
        this.impDesemNeto = impDesemNeto;
    }

    public float getImpDesemIgv() {
        return impDesemIgv;
    }

    public void setImpDesemIgv(float impDesemIgv) {
        this.impDesemIgv = impDesemIgv;
    }

    public float getImpDesemTotal() {
        return impDesemTotal;
    }

    public void setImpDesemTotal(float impDesemTotal) {
        this.impDesemTotal = impDesemTotal;
    }

    public int getSemilla() {
        return semilla;
    }

    public void setSemilla(int semilla) {
        this.semilla = semilla;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    
}
