/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Objetos;
import java.util.Objects;
/**
 *
 * @author Gonzalo
 */
public class Aeropuerto {
    
    private String nombreAeronautico;
    private String ciudad;
    private String numeroTelefono;
    
    public Aeropuerto(String n){//nombre aeronau
    this.nombreAeronautico=n;
    }
    
    public Aeropuerto(String n, String c, String t){//n aero, c ciudad, t telefono
    this.nombreAeronautico=n;
    this.ciudad=c;
    this.numeroTelefono=t;
    }
    
    public String getNombreAeronautico(){
        return this.nombreAeronautico;
    }
    
    public String getCiudad(){
        return this.ciudad;
    }
    
    public String getTelefono(){
        return this.numeroTelefono;
    }
    
    public void setCiudad(String c){
        this.ciudad=c;
    }
    
    public void setTelefono(String t){
        this.numeroTelefono=t;
    }
    
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o==null){
            return false;
        }
        if(getClass()!= o.getClass()){
            return false;
        }
        final Aeropuerto other = (Aeropuerto)o;
        if(!Objects.equals(this.nombreAeronautico, other.nombreAeronautico)){
            return false;
        }
        return true;
    }
    
    public String toString(){
        return "Informacion del aeropuerto: \n"
                + "Nombre Aeronautico: "+this.nombreAeronautico+"\n"
                + "Ciudad: "+this.ciudad+"\n"
                + "Numero de telefono: "+this.numeroTelefono;
    }
}
