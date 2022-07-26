/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;

/**
 *
 * @author Gonzalo
 */
public class Nodo {
    private Comparable elem;
    private Nodo iz;
    private Nodo de;
    
    public Nodo(Comparable e){
        this.elem=e;
    }
    public Nodo(Comparable e,Nodo iz,Nodo de){
        this.elem=e;
        this.de=de;
        this.iz=iz;
    }
    public Comparable getElem(){
        return this.elem;
    }
    public Nodo getIz(){
        return this.iz;
    }
    public Nodo getDer(){
        return this.de;
    }
    public void setElem(Comparable e){
        this.elem=e;
    }
    public void setDer(Nodo d){
        this.de=d;
    }
    public void setIz(Nodo i){
        this.iz=i;
    }
}
