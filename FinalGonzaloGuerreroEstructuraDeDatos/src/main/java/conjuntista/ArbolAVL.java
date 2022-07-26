/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntista;

import lineales.dinamicas.Lista;

/**
 *
 * @author Gonzalo
 */
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean insertar(Comparable e) {
        boolean res = false;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(e, null, null);
            res = true;//si no hay raiz se coloca como raiz y devuelve true
        } else {
            if (!this.raiz.getElemento().equals(e)) {
                if (this.raiz.getElemento().compareTo(e) > 0) {
                    //colocamos en el lado izq
                    res = insertarHijosAux(this.raiz, raiz.getIzquierdo(), 'I', e);
                } else {
                    //ahora derecho
                    res = insertarHijosAux(this.raiz, raiz.getDerecho(), 'D', e);
                }
            }
        }
        if (res) {
            this.raiz.recalcularAltura();
            balancearRaiz(this.raiz);
        }
        return res;
    }

    private boolean insertarHijosAux(NodoAVL n, NodoAVL nh, char hijo, Comparable e) {
        boolean res = false;
        if (nh != null) {
            if (nh.getElemento().compareTo(e) != 0) {
                if (nh.getElemento().compareTo(e) > 0) {
                    //nos colocamos a la izq de la subR
                    res = insertarHijosAux(nh, nh.getIzquierdo(), 'I', e);
                } else {
                    res = insertarHijosAux(nh, nh.getDerecho(), 'D', e);
                }
            }
        } else {
            NodoAVL h = new NodoAVL(e, null, null);
            h.recalcularAltura();
            nh = h;
            if (hijo == 'I') {
                n.setIzquierdo(h);
            } else {
                n.setDerecho(h);
            }
            res = true;
        }
        if(res){
            nh.recalcularAltura();
            balancear(n,nh,hijo);
        }
        return res;
    }

    public boolean balancearRaiz(NodoAVL n) {
        int b = balance(n);
        if (b < -1 || b > 1) {
            this.raiz = aplicarRotaciones(n);
        }
        this.raiz.recalcularAltura();
        return true;
    }

    public int balance(NodoAVL n) {
        int altDer = -1, altIzq = -1;
        if (n.getIzquierdo() != null) {
            altIzq = n.getIzquierdo().getAltura();
        }
        if (n.getDerecho() != null) {
            altDer = n.getDerecho().getAltura();
        }
        return altIzq - altDer;
    }

    //370
    private NodoAVL aplicarRotaciones(NodoAVL n) {
        NodoAVL balanc = null;
        int nB = balance(n);
        if (nB == -2 && n.getDerecho() != null) {
            //desbalanceado hacia la derecha
            int balanceHiDer = balance(n.getDerecho());
            if (balanceHiDer == -1) {
                balanc = giroIzq(n);//giro simple izquierda
            } else {
                balanc = dobleDerIzq(n);//doble izq-der
            }
        } else {
            if (nB == 2 && n.getIzquierdo() != null) {
                //desbalanceado a izquierda
                int balanceHijIzq = balance(n.getIzquierdo());
                if (balanceHijIzq == 1) {
                    //derecha simple
                    balanc = giroDer(n);
                } else {
                    balanc = dobleIzqDer(n);//doble der-izq
                }
            }
        }
        return balanc;
    }

    private NodoAVL giroIzq(NodoAVL n) {
        //hd hijo derecho, p padre
        NodoAVL hd = n.getDerecho(), aux = null;
        if (hd.getIzquierdo() != null) {
            aux = hd.getIzquierdo();
        }
        hd.setIzquierdo(n);//padre ahora es hIzq d hd 
        n.setDerecho(aux);//hijo der de p ahora es HI de hd
        n.recalcularAltura();
        hd.recalcularAltura();
        return hd;
    }

    private NodoAVL giroDer(NodoAVL n) {
        NodoAVL hI = n.getIzquierdo(), aux = null;
        if (hI.getDerecho() != null) {
            aux = hI.getDerecho();
        }
        hI.setDerecho(n);//hI tiene como hijo derecho a p(padre)
        n.setIzquierdo(aux);//aux es hijo izquierdo de p
        n.recalcularAltura();
        hI.recalcularAltura();
        return hI;
    }

    private NodoAVL dobleDerIzq(NodoAVL n) {
        NodoAVL hd = giroDer(n.getDerecho());
        n.setDerecho(hd);
        NodoAVL nuevoP = giroIzq(n);
        n.recalcularAltura();
        hd.recalcularAltura();
        return nuevoP;
        //se gira el hijo a la derecha despues al padre a la izq y se recalculan alturas
    }

    private NodoAVL dobleIzqDer(NodoAVL p) {
        NodoAVL hi = giroIzq(p.getIzquierdo());
        p.setIzquierdo(hi);
        NodoAVL np = giroDer(p);
        p.recalcularAltura();
        hi.recalcularAltura();
        return np;
    }

    public boolean eliminar(Comparable e) {
        boolean res = false;
        if (this.raiz != null) {
            if (this.raiz.getElemento().equals(e)) {
                //elimino la raiz
                this.raiz = eliminarRaiz(this.raiz);
                res = true;
            } else {
                if (this.raiz.getElemento().compareTo(e) > 0) {
                    res = eliminarAux(this.raiz, this.raiz.getIzquierdo(), e, 'I');
                } else {
                    res = eliminarAux(this.raiz, this.raiz.getDerecho(), e, 'D');
                }
            }
        }
        if (res) {
            this.raiz.recalcularAltura();
            balancearRaiz(this.raiz);
        }
        return res;
    }

    //136
    private NodoAVL eliminarRaiz(NodoAVL n) {
        NodoAVL res = null;
        switch (condicion(n)) {
            case 0:
                res = uno();
                break;
            case 1:
                res = dos(n);
                break;
            case 2:
                res = tres(n);
                break;
            default:
                break;
        }
        balance(n);
        return res;
    }

    private NodoAVL uno() {
        return null;
    }

    private NodoAVL dos(NodoAVL n) {
        NodoAVL res = null;
        if (n.getIzquierdo() != null) {
            res = n.getIzquierdo();
        } else {
            res = n.getDerecho();
        }
        return res;
    }

    private NodoAVL tres(NodoAVL n) {
        NodoAVL res = null;
        boolean aux;
        NodoAVL posible = candidato(n.getIzquierdo());
        n.setElemento(posible.getElemento());
        aux = eliminarAux(n, n.getIzquierdo(), posible.getElemento(), 'I');
        if (aux) {
            res = n;
        }
        return res;
    }

    private NodoAVL candidato(NodoAVL n) {
        NodoAVL aux = n;
        while (aux.getDerecho() != null) {
            aux = aux.getDerecho();
        }
        return aux;
    }

    private int condicion(NodoAVL n) {
        int res = 0;
        if (n.getIzquierdo() != null && n.getDerecho() != null) {
            res = 2;
        } else {
            if (this.raiz.getIzquierdo() != null || this.raiz.getDerecho() != null) {
                res = 1;
            }
        }
        return res;
    }

    //164
    private boolean eliminarAux(NodoAVL n, NodoAVL nhijo, Comparable e, char h) {
        boolean res;
        if (nhijo.getElemento().equals(e)) {
            NodoAVL aux = null;
            switch (condicion(nhijo)) {
                case 0:
                    aux = uno();
                    break;
                case 1:
                    aux = dos(n);
                    break;
                case 2:
                    aux = tres(n);
                    break;
                default:
                    break;
            }
            if (h == 'I') {
                n.setIzquierdo(aux);
                res = true;
            } else {
                n.setDerecho(aux);
                res = true;
            }
        } else {
            if (nhijo.getElemento().compareTo(e) > 0) {
                res = eliminarAux(nhijo, nhijo.getIzquierdo(), e, 'I');
            } else {
                res = eliminarAux(nhijo, nhijo.getDerecho(), e, 'D');
            }
        }
        if (res && nhijo != null) {
            nhijo.recalcularAltura();
            balancear(n, nhijo, h);
        }
        return res;
    }

    public boolean balancear(NodoAVL n, NodoAVL nh, char h) {
        int b = balance(nh);
        if (b < -1 || b > 1) {
            NodoAVL balanceado = aplicarRotaciones(n);
            if (h == 'I') {
                n.setIzquierdo(balanceado);
            } else {
                n.setDerecho(balanceado);
            }
            n.recalcularAltura();
        }
        return true;
    }

    //498
    public boolean pertenece(Comparable aeropuerto) {
        boolean res = false;
        if (this.raiz == null) {
            res = perteneceAux(this.raiz, aeropuerto);
        }
        return res;
    }

    private boolean perteneceAux(NodoAVL n, Comparable e) {
        boolean res = false;
        if (n != null) {
            if (n.getElemento().compareTo(e) == 0) {
                res = true;
            } else {
                if (n.getElemento().compareTo(e) > 0) {
                    res = perteneceAux(n.getIzquierdo(), e);
                } else {
                    res = perteneceAux(n.getDerecho(), e);
                }
            }
        }
        return res;
    }

    public Object recuperar(Comparable e) {
        Object res = null;
        if (this.raiz != null) {
            res = this.recuperarAux(this.raiz, null);
        }
        return res;
    }

    public Object recuperarAux(NodoAVL n, Object e) {
        Object res = null;
        if (n != null) {
            if (n.getElemento().compareTo(e) == 0) {
                res = n.getElemento();
            } else {
                if (n.getElemento().compareTo(e) > 0) {
                    //busco si es menor a n yendo por el lado izq
                    res = recuperarAux(n.getIzquierdo(), e);
                } else {
                    res = recuperarAux(n.getDerecho(), e);
                }
            }
        }
        return res;
    }

    public boolean esVacio() {
        boolean res = true;
        if (this.raiz != null) {
            res = false;
        }
        return res;
    }

    //576
    public Lista lista() {
        Lista res = new Lista();
        if (this.raiz != null) {
            listaAux(this.raiz, res);
        }
        return res;
    }

    private void listaAux(NodoAVL n, Lista res) {
        //recorrido en preorden
        if (n != null) {
            listaAux(n.getIzquierdo(), res);
            res.insertar(n.getElemento(), res.longitud() + 1);
            listaAux(n.getDerecho(), res);
        }
    }

    public Lista listaRango(Comparable min, Comparable max) {
        Lista l = new Lista();
        if (min.compareTo(max) <= 0) {
            //si min es menror o igual a max se ejecuta el metodo
            if (this.raiz != null) {
                listaRangoAux(this.raiz, l, min, max);
            }
        }
        return l;
    }

    private void listaRangoAux(NodoAVL n, Lista l, Comparable min, Comparable max) {
        if (n.getElemento().compareTo(min) >= 0 && n.getElemento().compareTo(max) <= 0) {
            if (n.getIzquierdo() != null) {
                listaRangoAux(n.getIzquierdo(), l, min, max);
            }
            l.insertar(n.getElemento(), l.longitud() + 1);
            if (n.getDerecho() != null) {
                listaRangoAux(n.getDerecho(), l, min, max);
            }
        } else {
            if (n.getDerecho() != null && n.getElemento().compareTo(min) < 0) {
                listaRangoAux(n.getDerecho(), l, min, max);
            } else {
                if (n.getIzquierdo() != null && n.getElemento().compareTo(max) > 0) {
                    listaRangoAux(n.getIzquierdo(), l, min, max);

                }
            }
        }
    }

    public Comparable minimoElem() {
        Comparable min = null;
        if (this.raiz != null) {
            min = minElemAux(this.raiz).getElemento();
        }
        return min;
    }

    private NodoAVL minElemAux(NodoAVL n) {
        NodoAVL aux = n;
        while (aux.getIzquierdo() != null) {
            aux = aux.getIzquierdo();
        }
        return aux;
    }

    //674
    public Comparable maxElem() {
        Comparable max = null;
        if (this.raiz != null) {
            max = maxElemAux(this.raiz).getElemento();
        }
        return max;
    }

    private NodoAVL maxElemAux(NodoAVL n) {
        NodoAVL res = n;
        while (res.getDerecho() != null) {
            res = res.getDerecho();
        }
        return res;
    }

    public ArbolAVL clone() {
        ArbolAVL a = new ArbolAVL();
        if (this.raiz != null) {
            a.raiz = new NodoAVL(this.raiz.getElemento(), null, null);
            clonAux(this.raiz, a.raiz);
        }
        return a;
    }

    private void clonAux(NodoAVL n, NodoAVL a) {
        if (n != null) {
            if (n.getIzquierdo() != null) {
                a.setIzquierdo(new NodoAVL(n.getIzquierdo().getElemento(), null, null));
                clonAux(n.getIzquierdo(), a.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                a.setDerecho(new NodoAVL(n.getDerecho().getElemento(), null, null));
                clonAux(n.getDerecho(), a.getDerecho());
            }
        }
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        String res = "árbol vacio";
        if (this.raiz != null) {
            res = toStringAux(this.raiz);
        }
        return res;
    }

    private String toStringAux(NodoAVL n) {
        String s = "";
        NodoAVL d = n.getDerecho(), i = n.getIzquierdo();
        if (n != null) {
            s = n.getElemento().toString() + " Al: " + n.getAltura() + " :";
            if (i != null) {
                s = s + " HI:" + i.getElemento().toString();
            } else {
                s = s + " HI:-";
            }
            if (d != null) {
                s = s + " HD:" + d.getElemento().toString();
            } else {
                s = s + " HI:-";
            }
            s = s + "\n";
            if (i != null) {
                s = s + toStringAux(i);
            }
            if (d != null) {
                s = s + toStringAux(d);
            }
        }
        return s;
    }
}
