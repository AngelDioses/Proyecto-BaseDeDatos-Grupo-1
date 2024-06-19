package Modelo;

import java.sql.Date;

public class Empresa_Vta extends Persona {
    
    //Atributos
    private String nroRuc;
    private char flgEmpConsorcio;
    private Date fecIni;
    private Date fecFin;
    private int codEmpresa = -1; //Mantener en NULL
    private String observac;
    
    //Constructores
    public Empresa_Vta(){
    }
    
    public Empresa_Vta(Empresa_Vta fuente) {
        super(fuente);
        this.nroRuc = fuente.nroRuc;
        this.flgEmpConsorcio = fuente.flgEmpConsorcio;
        this.fecIni = fuente.fecIni;
        this.fecFin = fuente.fecFin;
        this.codEmpresa = fuente.codEmpresa;
        this.observac = fuente.observac;
    }
    
    //Getters and Setters
    public String getNroRuc() {
        return nroRuc;
    }

    public void setNroRuc(String nroRuc) {
        this.nroRuc = nroRuc;
    }

    public char getFlgEmpConsorcio() {
        return flgEmpConsorcio;
    }

    public void setFlgEmpConsorcio(char flgEmpConsorcio) {
        this.flgEmpConsorcio = flgEmpConsorcio;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public int getCodEmpresa() {
        return codEmpresa;
    }

    public void setCodEmpresa(int codEmpresa) {
        this.codEmpresa = codEmpresa;
    }

    public String getObservac() {
        return observac;
    }

    public void setObservac(String observac) {
        this.observac = observac;
    }

    @Override
    public Persona clone() {
        return new Empresa_Vta(this);
    }
}
