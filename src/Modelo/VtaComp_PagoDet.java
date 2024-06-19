package Modelo;

public class VtaComp_PagoDet {
    //Atributos
    private int codCia;
    private String nroCP;
    private int sec;
    private String ingEgr;
    private int codPartida;
    private double impNetoMN;
    private double impIGVMN;
    private double impTotalMN;
    private int semilla;
    private int codPytoAuxiliar;
    
    //Constructores
    public VtaComp_PagoDet() {
    }

    public VtaComp_PagoDet(int codCia, String nroCP, int sec, String ingEgr, int codPartida, double impNetoMN, double impIGVMN, double impTotalMN, int semilla) {
        this.codCia = codCia;
        this.nroCP = nroCP;
        this.sec = sec;
        this.ingEgr = ingEgr;
        this.codPartida = codPartida;
        this.impNetoMN = impNetoMN;
        this.impIGVMN = impIGVMN;
        this.impTotalMN = impTotalMN;
        this.semilla = semilla;
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

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
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

    public int getSemilla() {
        return semilla;
    }

    public void setSemilla(int semilla) {
        this.semilla = semilla;
    }

    public int getCodPytoAuxiliar() {
        return codPytoAuxiliar;
    }

    public void setCodPytoAuxiliar(int codPytoAuxiliar) {
        this.codPytoAuxiliar = codPytoAuxiliar;
    }
    
    
}
