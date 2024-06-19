package Modelo;

public class Cliente extends Persona{
    //Atributos
    private String nroRuc;
    
    //Constructores
    public Cliente(){
        
    }
    
    public Cliente(Cliente fuente){
        super(fuente);
        this.nroRuc = fuente.nroRuc;
    }
   
    public String getNroRuc() {
        return nroRuc;
    }

    public void setNroRuc(String NroRuc) {
        this.nroRuc = NroRuc;
    }

    @Override
    public Persona clone() {
        return new Cliente(this);
    }
}
