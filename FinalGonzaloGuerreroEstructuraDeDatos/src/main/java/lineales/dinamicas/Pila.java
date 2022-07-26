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
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object e) {
        Nodo nuevoNodo = new Nodo(e, this.tope);
        //al hacer esto no solo coloco el nuevo elemento en el nodo sino que lo
        //convierto en el nuevo tope
        this.tope = nuevoNodo;
        return true;
    }

    public boolean desapilar() {
        boolean res = false;
        if (!esVacia()) {
            //si la pila no esta vacia procede a desapilar caso contrario retorna false
            this.tope = this.tope.getEnlace();
            res = true;
        }
        return res;
    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public Object obtenerTope() {
        Object res = null;
        if (!esVacia()) {
            res = this.tope.getElem();
        }
        return res;
    }

    public void vaciar() {
        this.tope = null;
    }
/*
    public Pila clone() {
        Pila clon = new Pila();
        Nodo a1, a2, a3;
        if (!esVacia()) {
            a1 = this.tope;
            clon.tope = new Nodo(this.tope.getElem());
            a2 = clon.tope;
            //hasta aca lo que hice fue copiar en a1 el tope de la pila lo que me permite
            //moverme sobre la pila sin modificarla,lo mismo pasa con a2 que esta 
            //apuntando all tope del clon donde me voy a mover con los get enlaces
            //creo nuevos nodos y los enlazo uno a uno, de esta manera el tope
            //sigue donde esta y agrego los nodos que estaria "abajo" en la pila
            while (a1.getEnlace() != null) {
                a1 = a1.getEnlace();
                a3 = new Nodo(a1.getElem());
                a2.setEnlace(a3);
                a2.getEnlace();
                //aca lo que hago es pedir el siguiente elemento con a1 y su enlace
                //creo un nuevo nodo a3 donde almaceno el elmto de a1 y al nodo 
                //a2 lo apunto haciendo setEnlace al nodo a3 de esta manera
                //enlace el nodo actual al nuevo despues getEnlace para que apunte a null
            }
        }
        return clon;
    }
*/
    public Pila clone (){
  Pila pilaClonada =new Pila();
  Nodo aux,aux2,aux3;
  if(this.tope!=null){
      aux=this.tope;
      pilaClonada.tope=new Nodo(tope.getElem());
      aux2=pilaClonada.tope;
      while(aux.getEnlace()!=null){
          aux=aux.getEnlace();
          aux3=new Nodo(aux.getElem());
          aux2.setEnlace(aux3);
          aux2=aux2.getEnlace();

      }
  }
  return pilaClonada;
}
    public String toString() {
        String  s = "";
        if (!esVacia()) {
            s="[";
            Nodo a=this.tope;
            while(a!=null){
                s+=a.getElem();
                a=a.getEnlace();
                if(a!=null){
                    s+=",";
                }
                            }
            s+="]";
        } else {
            s = "Pila vacia";
        }
        return s;
    }

}
