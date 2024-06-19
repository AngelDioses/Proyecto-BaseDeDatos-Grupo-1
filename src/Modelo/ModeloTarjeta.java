package Modelo;

import javax.swing.Icon;

public class ModeloTarjeta {
    String titulo;
    double valor;
    Icon icon;
    
    public ModeloTarjeta(){
    }

    public ModeloTarjeta(String titulo, double valor, Icon icon) {
        this.titulo = titulo;
        this.valor = valor;
        this.icon = icon;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
