package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;

public class Proyecto {
    //Atributos
    private int codCia;
    private int codPyto;
    private String nomPyto;
    private int emplJefeProy;
    //private int codCia1;    
    private int ciaContrata;
    //private int codCC;
    private int codCliente;
    //private char flgEmpConsorcio;
    //private int codSNIP;
    private Date fecReg;
    //private int codFase;
    //private int codNivel;
    //private int codFuncion;
    //private int codSituacion;
    //private int numInform; //Es un 'char', no num de cel.
    private int estPyto;
    private Date fecEstado;
    private double valRefer;
    //private double costoDirecto;
    //private double costoGGen;
    //private double costoFinan;
    //private double impUtilidad;
    private double costoTotSinIGV;
    private double impIGV;
    private double costoTotal;
    //private double costoPenalid;
    //private String codDpto;
    //private String codProv;
    //private String codDist;
    //private Date fecViab;
    private String observac;
    //private String rutaDoc;
    private int annoIni;
    private int annoFin;
    //private int codObjC;
    private byte[] logoProy;
    private char vigente;
    
    //Constructores
//    public Proyecto() {
//        
//    }

//    public Proyecto(int codCia, int codPyto, String nomPyto, int emplJefeProy, int ciaContrata, int codCliente, Date fecReg, int estPyto, Date fecEstado, double valRefer, double costoTotSinIGV, double impIGV, double costoTotal, String observac, int annoIni, int annoFin, byte[] logoProy, char vigente) {
//        this.codCia = codCia;
//        this.codPyto = codPyto;
//        this.nomPyto = nomPyto;
//        this.emplJefeProy = emplJefeProy;
//        this.ciaContrata = ciaContrata;
//        this.codCliente = codCliente;
//        this.fecReg = fecReg;
//        this.estPyto = estPyto;
//        this.fecEstado = fecEstado;
//        this.valRefer = valRefer;
//        this.costoTotSinIGV = costoTotSinIGV;
//        this.impIGV = impIGV;
//        this.costoTotal = costoTotal;
//        this.observac = observac;
//        this.annoIni = annoIni;
//        this.annoFin = annoFin;
//        this.logoProy = logoProy;
//        this.vigente = vigente;
//    }
    
    private Proyecto(Builder builder) {
        this.codCia = builder.codCia;
        this.codPyto = builder.codPyto;
        this.nomPyto = builder.nomPyto;
        this.emplJefeProy = builder.emplJefeProy;
        this.ciaContrata = builder.ciaContrata;
        this.codCliente = builder.codCliente;
        this.fecReg = builder.fecReg;
        this.estPyto = builder.estPyto;
        this.fecEstado = builder.fecEstado;
        this.valRefer = builder.valRefer;
        this.costoTotSinIGV = builder.costoTotSinIGV;
        this.impIGV = builder.impIGV;
        this.costoTotal = builder.costoTotal;
        this.observac = builder.observac;
        this.annoIni = builder.annoIni;
        this.annoFin = builder.annoFin;
        this.logoProy = builder.logoProy;
        this.vigente = builder.vigente;
    }
    
    public static class Builder {
        private int codCia;
        private int codPyto;
        private String nomPyto;
        private int emplJefeProy; 
        private int ciaContrata;
        private int codCliente;
        private Date fecReg;
        private int estPyto;
        private Date fecEstado;
        private double valRefer;
        private double costoTotSinIGV;
        private double impIGV;
        private double costoTotal;
        private String observac;
        private int annoIni;
        private int annoFin;
        private byte[] logoProy;
        private char vigente;

        public Builder() {
            
        }

        public Builder codCia(int codCia) {
            this.codCia = codCia;
            return this;
        }

        public Builder codPyto(int codPyto) {
            this.codPyto = codPyto;
            return this;
        }
        
        public Builder nomPyto(String nomPyto) {
            this.nomPyto = nomPyto;
            return this;
        }
        
        public Builder emplJefeProy(int emplJefeProy) {
            this.emplJefeProy = emplJefeProy;
            return this;
        }
        
        public Builder ciaContrata(int ciaContrata) {
            this.ciaContrata = ciaContrata;
            return this;
        }
        
        public Builder codCliente(int codCliente) {
            this.codCliente = codCliente;
            return this;
        }
        
        public Builder fecReg(Date fecReg) {
            this.fecReg = fecReg;
            return this;
        }
        
        public Builder estPyto(int estPyto) {
            this.estPyto = estPyto;
            return this;
        }
        
        public Builder fecEstado(Date fecEstado) {
            this.fecEstado = fecEstado;
            return this;
        }
        
        public Builder valRefer(double valRefer) {
            this.valRefer = valRefer;
            return this;
        }
        
        public Builder costoTotSinIGV(double costoTotSinIGV) {
            this.costoTotSinIGV = costoTotSinIGV;
            return this;
        }
        
        public Builder impIGV(double impIGV) {
            this.impIGV = impIGV;
            return this;
        }
        
        public Builder costoTotal(double costoTotal) {
            this.costoTotal = costoTotal;
            return this;
        }
        
        public Builder observac(String observac) {
            this.observac = observac;
            return this;
        }
         
        public Builder annoIni(int annoIni) {
            this.annoIni = annoIni;
            return this;
        }
         
        public Builder annoFin(int annoFin) {
            this.annoFin = annoFin;
            return this;
        }
        
        public Builder logoProy(byte[] logoProy) {
            System.out.println("****Modo bytes");
            this.logoProy = logoProy;
            return this;
        }
        
        public Builder logoProy(String txtRuta) {
            System.out.println("****MODO RUTA "+txtRuta);
            File ruta = new File(txtRuta);
            try{
                byte[] icono = new byte[(int) ruta.length()];
                InputStream input = new FileInputStream(ruta);
                input.read(icono);
                this.logoProy = icono;
            }catch(Exception ex){
                this.logoProy = null;
            }
            return this;
        }
        
        public Builder vigente(char vigente) {
            this.vigente = vigente;
            return this;
        }        

        public Proyecto build() {
            return new Proyecto(this);
        }
    }
    
    //Getters and Setters

    public int getCodCia() {
        return codCia;
    }

//    public void setCodCia(int codCia) {
//        this.codCia = codCia;
//    }

    public int getCodPyto() {
        return codPyto;
    }

//    public void setCodPyto(int codPyto) {
//        this.codPyto = codPyto;
//    }

    public String getNomPyto() {
        return nomPyto;
    }

//    public void setNomPyto(String nomPyto) {
//        this.nomPyto = nomPyto;
//    }

    public int getEmplJefeProy() {
        return emplJefeProy;
    }

//    public void setEmplJefeProy(int emplJefeProy) {
//        this.emplJefeProy = emplJefeProy;
//    }

    public int getCiaContrata() {
        return ciaContrata;
    }

//    public void setCiaContrata(int ciaContrata) {
//        this.ciaContrata = ciaContrata;
//    }
    
    public int getCodCliente() {
        return codCliente;
    }

//    public void setCodCliente(int codCliente) {
//        this.codCliente = codCliente;
//    }

    public Date getFecReg() {
        return fecReg;
    }

//    public void setFecReg(Date fecReg) {
//        this.fecReg = fecReg;
//    }

    public int getEstPyto() {
        return estPyto;
    }

//    public void setEstPyto(int estPyto) {
//        this.estPyto = estPyto;
//    }

    public Date getFecEstado() {
        return fecEstado;
    }

//    public void setFecEstado(Date fecEstado) {
//        this.fecEstado = fecEstado;
//    }

    public double getValRefer() {
        return valRefer;
    }

//    public void setValRefer(double valRefer) {
//        this.valRefer = valRefer;
//    }

    public double getCostoTotSinIGV() {
        return costoTotSinIGV;
    }

//    public void setCostoTotSinIGV(double costoTotSinIGV) {
//        this.costoTotSinIGV = costoTotSinIGV;
//    }

    public double getImpIGV() {
        return impIGV;
    }

//    public void setImpIGV(double impIGV) {
//        this.impIGV = impIGV;
//    }

    public double getCostoTotal() {
        return costoTotal;
    }

//    public void setCostoTotal(double costoTotal) {
//        this.costoTotal = costoTotal;
//    }

    public String getObservac() {
        return observac;
    }

//    public void setObservac(String observac) {
//        this.observac = observac;
//    }

    public int getAnnoIni() {
        return annoIni;
    }

//    public void setAnnoIni(int annoIni) {
//        this.annoIni = annoIni;
//    }

    public int getAnnoFin() {
        return annoFin;
    }

//    public void setAnnoFin(int annoFin) {
//        this.annoFin = annoFin;
//    }

    public byte[] getLogoProy() {
        return logoProy;
    }

//    public void setLogoProy(byte[] logoProy) {
//        this.logoProy = logoProy;
//    }

    public char getVigente() {
        return vigente;
    }

//    public void setVigente(char vigente) {
//        this.vigente = vigente;
//    }
    
    
}
