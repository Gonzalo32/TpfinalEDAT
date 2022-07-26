/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;

import lineales.dinamicas.Lista;

/**
 *
 * @author Gonzalo
 */
public class ArbolBB {

    private Nodo raiz;
    
    public boolean eliminarMinimo(){
        boolean exito=true;
        if(!esVacio()){
            if(this.raiz.getIz()==null){
                this.raiz=this.raiz.getDer();
            }else{
            elMinAux(this.raiz, this.raiz.getIz());
        }
        }
        return exito;
    }
    private void elMinAux(Nodo n, Nodo r){
        while(r.getIz()!=null){
            n=r;
            r=r.getIz();
        }
        if(r.getDer()!=null){
            n.setIz(r.getDer());
        }else{
            n.setIz(null);
        }
    }
    public boolean esVacio(){
        return this.raiz==null;
    }
    public boolean eliminarMaximo(){
         boolean exito=true;
        if(!esVacio()){
            if(this.raiz.getIz()==null){
                this.raiz=this.raiz.getDer();
            }else{
            elMaxAux(this.raiz, this.raiz.getDer());
        }
        }
        return exito;
    }
    private void elMaxAux(Nodo n, Nodo r){
        while(r.getDer()!=null){
            n=r;
            r=r.getDer();
        }
        if(r.getIz()!=null){
            n.setDer(r.getIz());
        }else{
            n.setDer(null);
        }
    }
    public boolean insertar(Comparable e) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new Nodo(e);
        } else {
            exito = insertarAux(this.raiz, e);
        }
        return exito;
    }
    
    private boolean insertarAux(Nodo n, Comparable e) {
        boolean exito = true;
        if ((n.getElem().compareTo(e)) == 0) {
            exito = false;
        } else {
            if ((n.getElem().compareTo(e)) > 0) {//si donde estoy parado es mayor que el elemento e voy a la izquierda
                if (n.getIz() != null) {
                    exito = insertarAux(n.getIz(), e);
                } else {//si donde estoy es menor que el que busco voy a la derecha
                    n.setIz(new Nodo(e));
                }
            } else {
                if (n.getDer() != null) {
                    exito = insertarAux(n.getDer(), e);
                } else {
                    n.setDer((new Nodo(e)));
                }
            }
        }
        return exito;
    }

    public boolean pertenece(Comparable e) {
        boolean exito = true;
        if (this.raiz != null) {
            exito = perteneceAux(this.raiz, e);
        } else {
            exito = false;
        }
        return exito;
    }

    private boolean perteneceAux(Nodo n, Comparable e) {
        boolean res = false;
        if (n != null) {
            if (n.getElem().compareTo(e) == 0) {
                res = true;
            } else {
                if (n.getElem().compareTo(e) > 0) {
                    res = perteneceAux(n.getIz(), e);
                }
                if (n.getElem().compareTo(e) < 0) {
                    res = perteneceAux(n.getDer(), e);
                }
            }
        }
        return res;
    }

    public boolean eliminar(Comparable e) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, null, e);
        }
        return exito;
    }

    private boolean eliminarAux(Nodo n, Nodo r, Comparable e) {
        boolean exito = false;
        if (n != null) {
            if (n.getElem().compareTo(e) == 0) {
                int x = cantHijos(n);
                if (x == 2) {
                    casoTres(n);//dos hijos
                } else {
                    if (x == 1) {
                        casoDos(n, r);//un hijo
                    } else {
                        //casoUno  sin hijos
                        if(this.raiz.getElem().compareTo(n.getElem())==0){
                            this.raiz=null;
                        }else{
                            casoUno(n,r);
                        }
                    }
                }
                exito=true;
            } else {
                if (n.getElem().compareTo(e) > 0) {//si n es mayor a e me voy a la izquierda de n
                    eliminarAux(n.getIz(), n, e);
                } else {
                    eliminarAux(n.getDer(), n, e);// si n es menor a e me voy a la derecha de n
                }
            }
        }
        return exito;
    }
    
    private void casoUno(Nodo n, Nodo r){
        if(r.getDer().getElem().compareTo(n.getElem())==0){
            r.setDer(null);
        }else{
            r.setIz(null);
        }
    }

    private void casoDos(Nodo n, Nodo r) {
        if(this.raiz.getElem().compareTo(n.getElem())==0){
            if(n.getDer()!=null){
                this.raiz=n.getDer();
            }else{
                this.raiz=n.getIz();
            }
        }else{
            if(r.getDer().getElem().compareTo(n.getElem())==0){
                if(n.getDer()!=null){
                    r.setDer(n.getDer());
                }else{
                    r.setDer(n.getIz());
                }
            }else{
                if(n.getDer()!=null){
                    r.setIz(n.getDer());
                }else{
                    r.setIz(n.getIz());
                }
            }
        }
    }

    private void casoTres(Nodo n) {
        Nodo aux = n.getIz(), auxP = null;
        while (aux.getDer() != null) {
            auxP = aux;
            aux = aux.getDer();
        }
        n.setElem(aux.getElem());
        if (aux.getIz() != null) {
            auxP.setDer(aux.getIz());
        }else{
           auxP.setDer(null);
        }
    }

    private int cantHijos(Nodo n) {
        int x;
        if (n.getDer() != null && n.getIz() != null) {
            x = 2;
        } else {
            if ((n.getDer() != null) || (n.getIz() != null)) {
                x = 1;
            } else {
                x = 0;
            }
        }
        return x;
    }

    public Lista listar() {
        Lista l = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, l);
        }
        return l;
    }
public boolean vaciar(){
    this.raiz=null;
    return true; 
}
    private void listarAux(Nodo n, Lista l) {
        if (n != null) {
            listarAux(n.getIz(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
            listarAux(n.getDer(), l);
        }

    }
    
    public Lista listarPorRango(Comparable min, Comparable max) {
        Lista l = new Lista();

        if (raiz != null) {
          listarRangoAux(min,max,this.raiz,  l);
        }

        return l;
    }

    private void listarRangoAux(Comparable min, Comparable max, Nodo n, Lista l) {
        if (n != null) {
            if (n.getElem().compareTo(min) >= 0) {
                listarRangoAux(min, max, n.getIz(), l);
            }
            if (n.getElem().compareTo(min) >= 0 && n.getElem().compareTo(max) <= 0) {
                l.insertar(n.getElem(), l.longitud() + 1);
            }
            if (n.getElem().compareTo(max) <= 0) {
                listarRangoAux(min, max, n.getDer(), l);
            }

        }
    }
    public Comparable minimoElem(){
        Comparable e=null;
        if(!esVacio()){
            if(this.raiz.getIz()!=null){
            e=minE(this.raiz);
        }else{
                e=this.raiz.getElem();
            }
    }
        return e;
    }
    
    private Comparable minE(Nodo n){
        while(n.getIz()!=null){
            n=n.getIz();
        }
        return n.getElem();
    }

    public Comparable maximoElem(){
        Comparable e=null;
        if(!esVacio()){
            if(this.raiz.getDer()!=null){
            e=maxE(this.raiz);
        }else{
                e=this.raiz.getElem();
            }
    }
        return e;
    }
    
    private Comparable maxE(Nodo n){
        while(n.getDer()!=null){
            n=n.getDer();
        }
        return n.getElem();
    }
    
    public String toString() {
        String s = "";
        if (this.raiz != null) {
            s = toStringAux(this.raiz);
        }
        return s;
    }

    private String toStringAux(Nodo n) {
        String s = "";
        if (n != null) {
            s = "" + n.getElem();
            if (n.getIz() != null) {
                s = s + "    HI:" + n.getIz().getElem();
            } else {
                s = s + "    HI --";
            }
            if (n.getDer() != null) {
                s = s + "    HD:" + n.getDer().getElem() + "\n";
            } else {
                s = s + "    HD --\n";
            }
            s = s + "" + toStringAux(n.getIz());
            s = s + "" + toStringAux(n.getDer());
        }
        return s;
    }
 
}
