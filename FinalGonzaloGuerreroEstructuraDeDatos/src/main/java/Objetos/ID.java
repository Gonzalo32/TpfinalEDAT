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
public class ID{
    private String tipo;
    private String dni;
    
    public ID(String t, String d){
        this.tipo=t;
        this.dni=d;
    }

    public String getDni(){
        return this.dni;
    }
    public void setDni(String d){
        this.dni=d;
    }
     public String getTipo(){
        return this.tipo;
    }
    public void settipo(String t){
        this.tipo=t;
    }
     public String toString(){
         return "Id Cliente: \n"
                 + "Tipo de documento: "+this.tipo+"\n"
                 + "Numero de documento: "+this.dni+"\n";
     }
}
