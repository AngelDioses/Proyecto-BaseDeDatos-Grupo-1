package Modelo;

public class Estado {
    private int tabEstado;
    private int codEstado;
    private String desEstado;
    private String desCorta;
    private String vigente;
    
    public Estado(){};

    public int getTabEstado() {
        return tabEstado;
    }

    public void setTabEstado(int tabEstado) {
        this.tabEstado = tabEstado;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getDesEstado() {
        return desEstado;
    }

    public void setDesEstado(String desEstado) {
        this.desEstado = desEstado;
    }

    public String getDesCorta() {
        return desCorta;
    }

    public void setDesCorta(String desCorta) {
        this.desCorta = desCorta;
    }

    public String getVigente() {
        return vigente;
    }

    public void setVigente(String vigente) {
        this.vigente = vigente;
    }
   
}
