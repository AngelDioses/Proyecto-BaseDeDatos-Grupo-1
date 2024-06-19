package Modelo;

import java.util.Objects;

public abstract class Persona {
    
    //Atributos
    private int codCia;
    private int codPersona;
    private char tipPersona;
    private String desPersona;
    private String descorta;
    private String desAlterna;
    private String desCortaAlt;
    private char vigente;
    
    //Constructores
    public Persona(){
        
    }

    public Persona(Persona fuente) {
        this.codCia = fuente.codCia;
        this.codPersona = fuente.codPersona;
        this.tipPersona = fuente.tipPersona;
        this.desPersona = fuente.desPersona;
        this.descorta = fuente.descorta;
        this.desAlterna = fuente.desAlterna;
        this.desCortaAlt = fuente.desCortaAlt;
        this.vigente = fuente.vigente;
    }
    
    //Getters and Setters
    public int getCodCia() {
        return codCia;
    }

    public void setCodCia(int codCia) {
        this.codCia = codCia;
    }

    public int getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(int codPersona) {
        this.codPersona = codPersona;
    }

    public char getTipPersona() {
        return tipPersona;
    }

    public void setTipPersona(char tipPersona) {
        this.tipPersona = tipPersona;
    }

    public String getDesPersona() {
        return desPersona;
    }

    public void setDesPersona(String desPersona) {
        this.desPersona = desPersona;
    }

    public String getDescorta() {
        return descorta;
    }

    public void setDescorta(String descorta) {
        this.descorta = descorta;
    }

    public String getDesAlterna() {
        return desAlterna;
    }

    public void setDesAlterna(String desAlterna) {
        this.desAlterna = desAlterna;
    }

    public String getDesCortaAlt() {
        return desCortaAlt;
    }

    public void setDesCortaAlt(String desCortaAlt) {
        this.desCortaAlt = desCortaAlt;
    }

    public char getVigente() {
        return vigente;
    }

    public void setVigente(char vigente) {
        this.vigente = vigente;
    }

    @Override
    public abstract Persona clone();
    
    @Override
    public boolean equals(Object object2) {
    if (!(object2 instanceof Persona)) return false;
    Persona persona2 = (Persona) object2;
    return persona2.codCia== codCia 
        && persona2.codPersona == codPersona
        && Objects.equals(persona2.tipPersona, tipPersona)
        && Objects.equals(persona2.desPersona, desPersona)
        && Objects.equals(persona2.descorta, descorta)
        && Objects.equals(persona2.desAlterna, desAlterna)
        && Objects.equals(persona2.desCortaAlt, desCortaAlt)
        && Objects.equals(persona2.vigente, vigente);
  }

}
