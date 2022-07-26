/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

/**
 *
 * @author Gonzalo
 */
public class Heap {

    private static final int tam = 50;
    private Comparable[] ar;
    private int ultimo;

    public Heap() {
        this.ar = new Comparable[tam];
        this.ultimo = 0;
    }

    public boolean insertar(Comparable elem){
        boolean res=false;
        if(this.ultimo<this.tam){
            this.ultimo++;
            this.ar[this.ultimo]=elem;
            hacerSubir(this.ultimo);
            res=true;
        }
        return res;
    }

    public void hacerBajar(int pos) {
        int hMenor, hIz, hDer;
        boolean res = true;
        while (res) {
            if (pos * 2 + 1 <= this.ultimo) {
                hIz = pos * 2;
                hDer = pos * 2 + 1;
                hMenor = obtenerMenor(hIz, hDer);//si los hijos son iguales devuelve el de la derecha
                if (this.ar[pos].compareTo(this.ar[hMenor]) > 0) {
                    Comparable aux = this.ar[pos];
                    this.ar[pos] = this.ar[hMenor];
                    this.ar[hMenor] = aux;
                    pos = hMenor;
                    pos = hMenor;
                } else {
                    res = false;
                }
            } else {
                if (pos * 2 <= this.ultimo) {
                    hMenor = pos * 2;
                    if (this.ar[pos].compareTo(this.ar[hMenor]) > 0) {
                        Comparable aux = this.ar[pos];
                        this.ar[pos] = this.ar[hMenor];
                        this.ar[hMenor] = aux;
                        pos = hMenor;
                    } else {
                        res = false;
                    }
                } else {
                    res = false;
                }
            }
        }
    }

    public int obtenerMenor(int pos1, int pos2) {
        Comparable elem1 = this.ar[pos1];
        Comparable elem2 = this.ar[pos2];
        int res;
        if (elem1.compareTo(elem2) < 0) {
            res = pos1;
        } else {
            res = pos2;
        }
        return res;
    }

    public boolean eliminarCima() {
        boolean exito;
        if (this.ultimo == 0) {
            exito = false;
        } else {
            //saca la raiz y pone la ultima hoja en su lugar
            this.ar[1] = this.ar[ultimo];
            this.ultimo--;
            //reordena el arbol nuevamente
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }

    public void hacerSubir(int pos) {
        int padre = pos / 2;
        while (pos > 1 && (this.ar[padre].compareTo(this.ar[pos]) > 0)) {
            //si el elem no es raiz, y el padre de elem es menor, repetir
            Comparable aux = this.ar[pos];
            this.ar[pos] = this.ar[padre];
            this.ar[padre] = aux;
            pos = padre;
            padre = pos / 2;
        }
    }

    public Heap clone() {
        Heap h = new Heap();
        if (this.ultimo != 0) {
            for (int i = 1; i <= this.ultimo; i++) {
                h.ar[i] = ar[i];
            }
            h.ultimo = this.ultimo;
        }
        return h;
    }

    public Object recuperarCima() {
        Object elem;
        if (this.ultimo == 0) {
            elem = null;
        } else {
            elem = this.ar[1];
        }
        return elem;
    }

    public boolean esVacio() {
        return (this.ultimo == 0);
    }

    public void vaciar() {
        this.ultimo = 0;
    }

    public String toString() {
        String men = "";
        int aux = 1;
        if (esVacio()) {
            men = "arbol vacio";
        } else {
            while (aux <= this.ultimo) {
                men += "padre: " + this.ar[aux] + " ";

                if (2 * aux <= this.ultimo) {
                    men += "hijo izquierdo: " + this.ar[2 * aux] + " ";
                } else {
                    men += "hijo izquierdo vacio";
                }

                if ((2 * aux) + 1 <= this.ultimo) {
                    men += "hijo derecho: " + this.ar[(2 * aux) + 1] + "\n";;
                } else {
                    men += "hijo derecho vacio\n";
                }

                aux++;
            }
        }
        return men;
    }
}
