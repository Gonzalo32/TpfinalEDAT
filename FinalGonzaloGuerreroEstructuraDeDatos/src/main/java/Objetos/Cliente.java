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
public class Cliente {
    private ID id;
    private String numeroTelefono;
    private String domicilio;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    
    public Cliente(ID i, String num, String d, String n, String a, String f){
        this.id=i;
        this.numeroTelefono=num;
        this.domicilio=d;
        this.nombre=n;
        this.apellido=a;
        this.fechaNacimiento=f;
    }
}
