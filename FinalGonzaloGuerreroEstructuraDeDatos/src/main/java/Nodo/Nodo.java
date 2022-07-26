/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodo;

/**
 *
 * @author Gonzalo
 */
public class Nodo {
    private Object elem;
    private Nodo enlace;
    
    public Nodo(Object e, Nodo a){
        this.elem=e;
        this.enlace=a;
    }
    public Nodo(){
        this.elem=null;
        this.enlace=null;
    }
    public Nodo(Object e){
        this.elem=e;
        this.enlace=null;
    }
    public Object getElem(){
        return this.elem;
    }
    public Nodo getEnlace(){
        return this.enlace;
    }
    public void setElem(Object e){
        this.elem=e;
    }
    public void setEnlace(Nodo a){
        this.enlace=a;
    }
}
