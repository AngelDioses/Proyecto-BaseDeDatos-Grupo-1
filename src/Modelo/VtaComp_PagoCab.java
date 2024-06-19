package Modelo;

import java.sql.Date;

public class VtaComp_PagoCab {
    //Atributos
    private int codCia;
    private String nroCP;
    private int codPyto;
    private int codCliente;
    private int nroPago;
    private int tCompPago;
    private int eCompPago;
    private Date fecCP;
    private int tMoneda;
    private int eMoneda;
    private double tipCambio;
    private double impMO;
    private double impNetoMN;
    private double impIGVMN;
    private double impTotalMN;
    private String fotoCP;
    private String fotoAbono;
    private Date fecAbono;
    private String desAbono;
    private int semilla;
    private String tabEstado;
    private String codEstado;
    
    //Constructores
    public VtaComp_PagoCab() {
    }
    
    public VtaComp_PagoCab(int codCia, String nroCP, int codPyto, int codCliente, int nroPago, int tCompPago, int eCompPago, Date fecCP, int tMoneda, int eMoneda, double tipCambio, double impMO, double impNetoMN, double impIGVMN, double impTotalMN, String fotoCP, String fotoAbono, Date fecAbono, String desAbono, int semilla, String tabEstado, String codEstado) {
        this.codCia = codCia;
        this.nroCP = nroCP;
        this.codPyto = codPyto;
        this.codCliente = codCliente;
        this.nroPago = nroPago;
        this.tCompPago = tCompPago;
        this.eCompPago = eCompPago;
        this.fecCP = fecCP;
        this.tMoneda = tMoneda;
        this.eMoneda = eMoneda;
        this.tipCambio = tipCambio;
        this.impMO = impMO;
        this.impNetoMN = impNetoMN;
        this.impIGVMN = impIGVMN;
        this.impTotalMN = impTotalMN;
        this.fotoCP = fotoCP;
        this.fotoAbono = fotoAbono;
        this.fecAbono = fecAbono;
        this.desAbono = desAbono;
        this.semilla = semilla;
        this.tabEstado = tabEstado;
        this.codEstado = codEstado;
    }

    //Getters and Setters
    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public String getNroCP() {
        return nroCP;
    }

    public void setNroCP(String nroCP) {
        this.nroCP = nroCP;
    }

    public int getCodPyto() {
        return codPyto;
    }

    public void setCodPyto(int codPyto) {
        this.codPyto = codPyto;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
    
    public int getNroPago() {
        return nroPago;
    }

    public void setNroPago(int nroPago) {
        this.nroPago = nroPago;
    }

    public int gettCompPago() {
        return tCompPago;
    }

    public void settCompPago(int tCompPago) {
        this.tCompPago = tCompPago;
    }

    public int geteCompPago() {
        return eCompPago;
    }

    public void seteCompPago(int eCompPago) {
        this.eCompPago = eCompPago;
    }

    public Date getFecCP() {
        return fecCP;
    }

    public void setFecCP(Date fecCP) {
        this.fecCP = fecCP;
    }

    public int gettMoneda() {
        return tMoneda;
    }

    public void settMoneda(int tMoneda) {
        this.tMoneda = tMoneda;
    }

    public int geteMoneda() {
        return eMoneda;
    }

    public void seteMoneda(int eMoneda) {
        this.eMoneda = eMoneda;
    }

    public double getTipCambio() {
        return tipCambio;
    }

    public void setTipCambio(double tipCambio) {
        this.tipCambio = tipCambio;
    }

    public double getImpMO() {
        return impMO;
    }

    public void setImpMO(double impMO) {
        this.impMO = impMO;
    }

    public double getImpNetoMN() {
        return impNetoMN;
    }

    public void setImpNetoMN(double impNetoMN) {
        this.impNetoMN = impNetoMN;
    }

    public double getImpIGVMN() {
        return impIGVMN;
    }

    public void setImpIGVMN(double impIGVMN) {
        this.impIGVMN = impIGVMN;
    }

    public double getImpTotalMN() {
        return impTotalMN;
    }

    public void setImpTotalMN(double impTotalMN) {
        this.impTotalMN = impTotalMN;
    }

    public String getFotoCP() {
        return fotoCP;
    }

    public void setFotoCP(String fotoCP) {
        this.fotoCP = fotoCP;
    }

    public String getFotoAbono() {
        return fotoAbono;
    }

    public void setFotoAbono(String fotoAbono) {
        this.fotoAbono = fotoAbono;
    }

    public Date getFecAbono() {
        return fecAbono;
    }

    public void setFecAbono(Date fecAbono) {
        this.fecAbono = fecAbono;
    }

    public String getDesAbono() {
        return desAbono;
    }

    public void setDesAbono(String desAbono) {
        this.desAbono = desAbono;
    }

    public int getSemilla() {
        return semilla;
    }

    public void setSemilla(int semilla) {
        this.semilla = semilla;
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
}
