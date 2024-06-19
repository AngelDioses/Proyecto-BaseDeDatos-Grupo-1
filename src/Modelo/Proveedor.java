package Modelo;

public class Proveedor extends Persona{
    //Atributos
    private String nroRuc;
    
    //Constructores
    public Proveedor(){
        
    }

    public Proveedor(Proveedor fuente){
        super(fuente);
        this.nroRuc = fuente.nroRuc;
    }
    
    //Getters and Setters
    public String getNroRuc() {
        return nroRuc;
    }

    public void setNroRuc(String nroRuc) {
        this.nroRuc = nroRuc;
    }

    @Override
    public Persona clone() {
        return new Proveedor(this);
    }
    
}
