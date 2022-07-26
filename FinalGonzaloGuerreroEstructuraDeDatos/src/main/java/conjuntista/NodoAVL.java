/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntista;

/**
 *
 * @author Gonzalo
 */
public class NodoAVL {
    private Comparable elemento;
    private NodoAVL izq;
    private NodoAVL der;
    private int altura;
    
    public NodoAVL(Comparable e,NodoAVL i,NodoAVL d){
        this.elemento=e;
        this.izq=i;
        this.der=d;
        this.altura=0;
    }
    public void setElemento(Comparable e){
        this.elemento=e;
    }
    public Comparable getElemento(){
        return this.elemento;
    }
    public int getAltura(){
        return this.altura;
    }
    
    public void recalcularAltura(){
        // (condicion)? si es true asigna -1 a altDer si es false le asigna 
        //la altura de der.getAltura
        int altDer = (this.der==null)? -1:this.der.getAltura();
        int altIzq = (this.izq==null)? -1:this.izq.getAltura();
        this.altura = Math.max(altIzq, altDer) + 1;//devuelvo el mayor de los dos +1
    }
    
    public void setIzquierdo(NodoAVL i){
        this.izq=i;
    }
    
    public NodoAVL getIzquierdo(){
        return this.izq;
    }
    
    public void setDerecho(NodoAVL d){
        this.der=d;
    }
    
    public NodoAVL getDerecho(){
        return this.der;
    }
}
