/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

/**
 *
 * @author Gonzalo
 */
public class NodoAdy {
	private NodoVertice NodoVert;
	private NodoAdy NodoAdy;
	private double Etiqueta;
	
	public NodoAdy(NodoVertice nodoVert, NodoAdy nodoAdy, double etiqueta) {
		this.NodoVert = nodoVert;
		this.NodoAdy = nodoAdy;
		this.Etiqueta = etiqueta;
	}
	
	public NodoVertice getVertice() {
		return this.NodoVert;
	}
	
	public void setVertice(NodoVertice nodo) {
		this.NodoVert = nodo;
	}
	
	public NodoAdy getSiguienteAdy() {
		return this.NodoAdy;
	}
	
	public void setSiguienteAdy(NodoAdy nodo) {
		this.NodoAdy = nodo;
	}
	
	public double getEtiqueta() {
		return this.Etiqueta;
	}
	
	public void setEtiqueta(double elem) {
		this.Etiqueta = elem;
	}
}
