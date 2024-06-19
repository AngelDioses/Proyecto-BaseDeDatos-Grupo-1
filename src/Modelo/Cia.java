package Modelo;

public class Cia {
    //Atributos
    private int codCia;
    private String desCia;
    private String desCorta;
    private char vigente;
    
    //Constructores
//    public Cia() {
//    }

//    public Cia(int codCia, String desCia, String desCorta, char vigente) {
//        this.codCia = codCia;
//        this.desCia = desCia;
//        this.desCorta = desCorta;
//        this.vigente = vigente;
//    }
    
    private Cia(Builder builder) {
        this.codCia = builder.codCia;
        this.desCia = builder.desCia;
        this.desCorta = builder.desCorta;
        this.vigente = builder.vigente;
    }
    
    public static class Builder {
        private int codCia;
        private String desCia;
        private String desCorta;
        private char vigente;

        public Builder() {
            
        }

        public Builder codCia(int codCia) {
            this.codCia = codCia;
            return this;
        }

        public Builder desCia(String desCia) {
            this.desCia = desCia;
            return this;
        }

        public Builder desCorta(String desCorta) {
            this.desCorta = desCorta;
            return this;
        }
        
        public Builder vigente(char vigente) {
            this.vigente = vigente;
            return this;
        }

        public Cia build() {
            return new Cia(this);
        }
    }
    
    //Getters and Setters

    public int getCodCia() {
        return codCia;
    }

//    public void setCodCia(int codCia) {
//        this.codCia = codCia;
//    }

    public String getDesCia() {
        return desCia;
    }

//    public void setDesCia(String desCia) {
//        this.desCia = desCia;
//    }

    public String getDesCorta() {
        return desCorta;
    }

//    public void setDesCorta(String desCorta) {
//        this.desCorta = desCorta;
//    }

    public char getVigente() {
        return vigente;
    }

//    public void setVigente(char vigente) {
//        this.vigente = vigente;
//    }
    
}
