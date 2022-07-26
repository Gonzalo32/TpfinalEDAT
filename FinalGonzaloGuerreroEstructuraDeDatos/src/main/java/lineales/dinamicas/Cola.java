/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

import Nodo.Nodo;

/**
 *
 * @author Gonzalo
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner (Object e){
        Nodo nuevo = new Nodo(e);  
        if(this.fin==null){
            this.frente=nuevo;
            this.fin=nuevo;
        }else{
            if(this.frente.getEnlace()==null){
                this.frente.setEnlace(nuevo);
            }else{
                fin.setEnlace(nuevo);
            }
        }
        this.fin=nuevo;
        return true;
    }
    public boolean esVacia() {
        return this.frente == null;
    }

    public boolean sacar() {
        boolean res = true;
        if (esVacia()) {
            res = false;
        } else {
            if (this.frente.getEnlace() == null) {
                this.frente = null;
                this.fin = null;
            } else {
                this.frente = this.frente.getEnlace();
            }
        }
        return res;
    }

    public Object obtenerFrente() {
        Object res=null;
        if(!esVacia()){
            res=this.frente.getElem();
        }
        return res;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

     public Cola clone() {
        Cola clon = new Cola();
        Nodo nuevo = this.frente, aux, enlaceClon;

        if (nuevo != null) {
            aux = new Nodo(nuevo.getElem());
            clon.frente = aux;

            while (nuevo.getEnlace()!=null) {
                nuevo = nuevo.getEnlace();
                enlaceClon = new Nodo(nuevo.getElem());
                aux.setEnlace(enlaceClon);
                aux = enlaceClon;
            }

            clon.fin = aux;
        }

        return clon;
    }

    public String toString() {
        String res = "[";
        Nodo aux = frente;
        while (aux != null) {
            res += aux.getElem().toString();
            if (aux.getEnlace() != null) {
                res += ",";
            }
            aux = aux.getEnlace();
        }
        res += "]";
        return res;
    }

}
