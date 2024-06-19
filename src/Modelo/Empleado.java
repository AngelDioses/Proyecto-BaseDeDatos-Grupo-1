package Modelo;

import java.sql.Date;

public class Empleado extends Persona {
    //Atributos
    private String direcc;
    private String celular;
    private String hobby;
    private byte[] foto;
    private Date fecNac;
    private String dni;
    private String nroCIP;
    private Date fecCIPVig;
    private char licCond;
    private char flgEmplIEA;
    private String observac;
    private int codCargo;
    private String email;

    //Constructores
    public Empleado(){
        
    }
    
    public Empleado(Empleado fuente){
        super(fuente);
        this.direcc = fuente.direcc;
        this.celular = fuente.celular;
        this.hobby = fuente.hobby;
        this.foto = fuente.foto;
        this.fecNac = fuente.fecNac;
        this.dni = fuente.dni;
        this.nroCIP = fuente.nroCIP;
        this.fecCIPVig = fuente.fecCIPVig;
        this.licCond = fuente.licCond;
        this.flgEmplIEA = fuente.flgEmplIEA;
        this.observac = fuente.observac;
        this.codCargo = fuente.codCargo;
        this.email = fuente.email;
    }
    
    //Getters and Setters
    public String getDirecc() {
        return direcc;
    }

    public void setDirecc(String direcc) {
        this.direcc = direcc;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNroCIP() {
        return nroCIP;
    }

    public void setNroCIP(String nroCIP) {
        this.nroCIP = nroCIP;
    }

    public Date getFecCIPVig() {
        return fecCIPVig;
    }

    public void setFecCIPVig(Date fecCIPVig) {
        this.fecCIPVig = fecCIPVig;
    }

    public char getLicCond() {
        return licCond;
    }

    public void setLicCond(char licCond) {
        this.licCond = licCond;
    }

    public char getFlgEmplIEA() {
        return flgEmplIEA;
    }

    public void setFlgEmplIEA(char flgEmplIEA) {
        this.flgEmplIEA = flgEmplIEA;
    }

    public String getObservac() {
        return observac;
    }

    public void setObservac(String observac) {
        this.observac = observac;
    }

    public int getCodCargo() {
        return codCargo;
    }

    public void setCodCargo(int codCargo) {
        this.codCargo = codCargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    @Override
    public Persona clone() {
        return new Empleado(this);
    }
}
