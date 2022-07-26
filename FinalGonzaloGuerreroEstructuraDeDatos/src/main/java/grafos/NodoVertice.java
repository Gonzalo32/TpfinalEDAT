/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

/**
 *
 * @author Gonzalo
 */
public class NodoVertice {
	private Object elem;
    private NodoVertice sigVertice;
    private NodoAdy primerAdy;
    
    public NodoVertice(Object elem, NodoVertice nodoVertice, NodoAdy nodoAdy) {
    	this.elem = elem;
    	this.sigVertice = nodoVertice;
    	this.primerAdy = nodoAdy;
    }
    
    public Object getElemento() {
    	return this.elem;
    }
    
    public NodoVertice getSigVertice(){
    	return this.sigVertice;
    }
    
    public NodoAdy getAdyacente() {
    	return this.primerAdy;
    }
    
    public void setElemento(Object elem) {
    	this.elem = elem;
    }
    
    public void setSigVertice(NodoVertice nodo) {
    	this.sigVertice = nodo;
    }
    
    public void setAdyacente(NodoAdy nodo) {
    	this.primerAdy = nodo;
    }
    
    public boolean equals(NodoVertice elem) {
    	return this.elem.equals(elem.elem);
    }
}