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
public class Lista {

    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object e, int pos) {
        boolean res = true;
        if (pos < 1 || pos > this.longitud() + 1) {
            res = false;
        } else {
            if (pos == 1) {
                this.cabecera = new Nodo(e, this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(e, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return res;
    }//

    public int longitud() {
        int i = 0;
        if (this.cabecera != null) {
            i = 1;
            Nodo aux = this.cabecera;
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                i++;
            }
        }
        return i;
    }//

    public boolean eliminar(int pos) {
        boolean res = false;
        if (pos <= this.longitud() && pos >= 1) {
            res = true;
            Nodo aux = this.cabecera;
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                int i = 1;
                while (i + 1 < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
            }

        }
        return res;
    }//

    public Object recuperar(int pos) {
        //devuelve el elemento en la pos
        Object o = null;
        if (pos <= this.longitud() && pos > 0) {
            int i = 1;
            Nodo aux = this.cabecera;
            while (i < pos) {
                i++;
                aux = aux.getEnlace();
            }
            o = aux.getElem();
        }
        return o;
    }//

    public int localizar(Object o) {
        int i = 1, pos = -1;
        boolean r = false;
        if (!esVacia()) {
            Nodo aux = this.cabecera;
            while (!r && i <= this.longitud()) {
                if (o.equals(aux.getElem())) {
                    pos = i;
                    r = true;
                }

                aux = aux.getEnlace();
                i++;
            }
        }
        return pos;
    }

    public boolean esVacia() {
        boolean res = true;
        if (this.cabecera != null) {
            res = false;
        }
        return res;
    }//

    public void vaciar() {
        this.cabecera = null;
    }//

    public Lista clone() {
        Lista clon = new Lista();
        Nodo a1, a2, a3;
        //con a1 me muevo sobre la lista ya armada
        if (!esVacia()) {
            a1 = this.cabecera;
            clon.cabecera = new Nodo(this.cabecera.getElem());
            a2 = clon.cabecera;
            while (a1.getEnlace() != null) {
                a1 = a1.getEnlace();//me desplazo un nodo en la lista
                a3 = new Nodo(a1.getElem());//copio el elemento de a1 en un nuevo nodo
                a2.setEnlace(a3);//aca enlazo al nuevo nodo al ultimo del clon
                a2 = a2.getEnlace();//me desplazo un lugar para enlazar el siguiente nodo
            }
        }
        return clon;
    }//

    public String toString() {
        String s = "la lista esta vacia";
        if (!esVacia()) {
            Nodo a1 = this.cabecera;
            s = a1.getElem() + ", ";
            while (a1.getEnlace() != null) {
                a1 = a1.getEnlace();
                if (a1.getEnlace() == null) {
                    s += a1.getElem();
                } else {
                    s += a1.getElem() + ", ";
                }
            }
        }
        return s;
    }//

    public void agregarElem(Object nuevo, int x) {
        if (this.cabecera != null) {
            this.cabecera = new Nodo(nuevo, this.cabecera);
            Nodo aux2 = this.cabecera;
            int i = 1;

            while (aux2.getEnlace() != null) {

                aux2 = aux2.getEnlace();
                if (i == x) {
                    Nodo aux = new Nodo(nuevo);
                    aux.setEnlace(aux2.getEnlace());
                    aux2.setEnlace(aux);
                    aux2 = aux2.getEnlace();
                    i = 0;
                }
                i++;
            }
        }
    }

    public static boolean revisarLista(Lista l, int i) {
        boolean res = false;

        if (!l.esVacia()) {
            Lista listaClon = l.clone();
            Cola c = new Cola();
            Pila p = new Pila(), clon = new Pila();
            res = revisar(listaClon, c, p, clon, i);
        }
        return res;
    }

    public static boolean revisar(Lista l, Cola c, Pila p, Pila clon, int i) {
        boolean r = true;
        Nodo aux = l.cabecera;
        int x = 1, j = 1;
        char var;
        while (aux.getEnlace() != null && r == true) {
            var = (char) aux.getElem();
            c.poner(var);
            p.apilar(var);
            clon.apilar(var);
            if (x == i) {
                r = revisar2(l, c, p, clon);
                x = 1;
                System.out.println(r);
            }
            aux=aux.getEnlace();
            x++;
        }
        System.out.println(r);
        return r;
    }

    public static boolean revisar2(Lista l, Cola c, Pila p, Pila clon) {
        boolean res = true;
 
        Nodo aux=l.cabecera;
        while (!c.esVacia()) {
            if (c.obtenerFrente() ==aux.getElem()) {
                c.sacar();
                aux=aux.getEnlace();
                System.out.println(c.obtenerFrente());
                System.out.println(aux.getElem());
            } else {
                res = false;
            }
        }
        System.out.println(p.obtenerTope());
        while (!p.esVacia()) {
            if (p.obtenerTope() == aux.getElem()) {
                p.desapilar();
                aux=aux.getEnlace();
               System.out.println(aux.getElem());
            } else {
                res = false;
            }
        }
            while (!clon.esVacia()) {
                if (clon.obtenerTope() == aux.getElem()) {
                    clon.desapilar();
                    aux.getEnlace();
                    
                } else {
                    res = false;
                }
            }
            if ('$' !=(char)aux.getElem()) {
                res = false;
            } else {
                aux=aux.getEnlace();
                System.out.println(res);
            }
        
        
        return res;
    }
}
