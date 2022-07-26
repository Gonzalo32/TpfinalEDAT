/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafos;

import java.util.HashMap;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author Gonzalo
 */
public class Grafo {

    private NodoVertice inicio;

    public Grafo() {
        this.inicio = null;
    }

    public boolean insertarVertice(Object e) {//Nodo
        boolean res = true;
        NodoVertice nodo = this.inicio;
        if (nodo == null) {
            this.inicio = new NodoVertice(e, null, null);
            res = true;
        } else {
            while (res && nodo.getSigVertice() != null) {
                if (nodo.getElemento().equals(e)) {
                    res = false;
                }
                nodo = nodo.getSigVertice();
            }
            if (res) {
                nodo.setSigVertice(new NodoVertice(e, null, null));
                res = true;
            }
        }
        return res;
    }

    public boolean eliminarVertice(Object e) {//nodo
        //
        boolean res = false;
        NodoVertice n = this.inicio;
        if (n.getElemento().equals(e)) {
            res = this.eliminarDeAdyacente(n, n.getElemento());
            this.inicio = n.getSigVertice();
        } else {
            NodoVertice sig = n.getSigVertice();
            while (sig != null && !sig.getElemento().equals(e)) {
                sig = sig.getSigVertice();
                n = n.getSigVertice();
            }
            if (sig != null) {
                res = this.eliminarDeAdyacente(sig, sig.getElemento());
                n.setSigVertice(sig.getSigVertice());
            }
        }
        return res;
    }

    private boolean eliminarDeAdyacente(NodoVertice n, Object e) {
        boolean res = false;
        NodoAdy a = n.getAdyacente();//le asigno el arco de n a "a"
        while (a != null) {
            res = this.eliminarAdyacente(a.getVertice(), e);
            a = a.getSiguienteAdy();
        }
        return res;
    }

    private boolean eliminarAdyacente(NodoVertice n, Object e) {
        boolean res = false;
        if (n != null) {
            NodoAdy a = n.getAdyacente();
            if (a.getVertice().equals(e)) {
                n.setAdyacente(a.getSiguienteAdy());
                res = true;
            } else {
                NodoAdy sig = a.getSiguienteAdy();
                while (sig != null && !sig.getVertice().equals(e)) {
                    sig = sig.getSiguienteAdy();
                    a = a.getSiguienteAdy();
                }
                if (sig != null) {
                    a.setSiguienteAdy(sig.getSiguienteAdy());
                    res = true;
                }
            }
        }
        return res;
    }

    public boolean existeVertice(Object e) {
        boolean res = false;
        NodoVertice n = this.inicio;
        while (n != null && !res) {
            if (n.getElemento().equals(e)) {
                res = true;
            }
            n = n.getSigVertice();
        }
        return res;
    }

    //72
    public boolean insertarArco(Object a, Object b, double etiqueta) {
        boolean res = false;
        HashMap<Object, NodoVertice> nodo = this.ubicarNodoVertice(a, b);
        NodoVertice nodoA = nodo.get(a);
        NodoVertice nodoB = nodo.get(b);
        if (nodoA != null && nodoB != null) {
            this.insertarAdyacente(nodoA, nodoB, etiqueta);
            this.insertarAdyacente(nodoB, nodoA, etiqueta);
            res = true;
        }
        return res;
    }

    private boolean insertarAdyacente(NodoVertice a, NodoVertice b, double etiqueta) {
        boolean res = false;
        if (a != null) {
            NodoAdy arco = a.getAdyacente();
            if (arco != null) {
                boolean control = true;
                while (arco.getSiguienteAdy() != null && control) {
                    arco = arco.getSiguienteAdy();
                }
                arco.setSiguienteAdy(new NodoAdy(b, null, etiqueta));
                res = true;
            } else {
                a.setAdyacente(new NodoAdy(b, null, etiqueta));
                res = true;
            }
        }
        return res;
    }

    private HashMap<Object, NodoVertice> ubicarNodoVertice(Object a, Object b) {
        boolean res = false;
        HashMap<Object, NodoVertice> resp = new HashMap<Object, NodoVertice>();
        NodoVertice aux = this.inicio;
        while (aux != null && !res) {
            if (aux.getElemento().equals(a)) {
                NodoVertice nodoA = aux;
                resp.put(a, nodoA);
            } else {
                if (aux.getElemento().equals(b)) {
                    NodoVertice nodoB = aux;
                    resp.put(b, nodoB);
                }
            }
            if (resp.get(a) != null && resp.get(b) != null) {
                res = true;
                aux = aux.getSigVertice();
            }
        }
        return resp;
    }

    public boolean eliminarArco(Object a, Object b) {
        boolean res = false;
        HashMap<Object, NodoVertice> nodo = this.ubicarNodoVertice(a, b);
        NodoVertice nodoA = nodo.get(a);
        NodoVertice nodoB = nodo.get(b);
        if (nodoA != null && nodoB != null) {
            this.eliminarAdyacente(nodoA, nodoB);
            this.eliminarAdyacente(nodoB, nodoA);
            res = true;
        }
        return res;
    }

    public boolean existeArco(Object a, Object b) {
        boolean res = false;
        HashMap<Object, NodoVertice> n = this.ubicarNodoVertice(a, b);
        NodoVertice nodoA = n.get(a);
        NodoVertice nodoB = n.get(b);
        if (nodoA != null && nodoB != null) {
            NodoAdy ad = nodoA.getAdyacente();
            while (ad != null && !res) {
                if (ad.getVertice().equals(nodoB)) {
                    res = true;
                }
                ad = ad.getSiguienteAdy();
            }
        }
        return res;
    }

    public boolean esVacio() {
        boolean res = false;
        if (this.inicio == null) {
            res = true;
        }
        return res;
    }

    //170
    public boolean exiteCamino(Object a, Object b) {
        boolean res = false;
        Lista l = new Lista();
        HashMap<Object, NodoVertice> n = this.ubicarNodoVertice(a, b);
        NodoVertice nA = n.get(a);
        NodoVertice nB = n.get(b);
        if (nA != null && nB != null) {
            res = existeCaminoAux(nA, nB, l);
        }
        return res;
    }

    //689
    private boolean existeCaminoAux(NodoVertice a, NodoVertice b, Lista l) {
        boolean res = false;
        if (a != null) {
            if (a.getElemento().equals(b.getElemento())) {
                res = true;
            } else {
                //la lista l guarda los vertices visitados
                l.insertar(a.getElemento(), l.longitud() + 1);
                NodoAdy ady = a.getAdyacente();
                while (!res && ady != null) {
                    if (l.localizar(ady.getVertice().getElemento()) < 0) {
                        res = existeCaminoAux(ady.getVertice(), b, l);
                    }
                    ady = ady.getSiguienteAdy();
                }
            }
        }
        return res;
    }

    //183
    public Lista caminoMasCorto(Object a, Object b) {
        Lista listaCamino = new Lista(), compara = new Lista();
        HashMap<String, Object> visitado = new HashMap<String, Object>();
        HashMap<Object, NodoVertice> nodo = this.ubicarNodoVertice(a, b);
        NodoVertice nA = nodo.get(a);//nodo a es nA
        NodoVertice nB = nodo.get(b);//nodo a es nB
        if (nA != null && nB != null) {
            listaCamino = this.caminoMasCortoAux(nA, nB, visitado, listaCamino, compara);
        }
        return listaCamino;
    }

    //450
    private Lista caminoMasCortoAux(NodoVertice ini, NodoVertice fin, HashMap<String, Object> visitado, Lista camino, Lista compara) {
        if (ini != null) {
            visitado.put(ini.getElemento().toString(), ini);
            camino.insertar(ini.getElemento(), camino.longitud() + 1);
            if (compara.esVacia() || camino.longitud() < compara.longitud()) {
                if (ini.equals(fin)) {
                    compara = camino.clone();
                } else {
                    NodoAdy ady = ini.getAdyacente();
                    while (ady != null) {
                        if (visitado.get(ady.getVertice().getElemento().toString()) == null) {
                            compara = this.caminoMasCortoAux(ady.getVertice(), fin, visitado, camino, compara);
                        }
                        ady = ady.getSiguienteAdy();
                    }
                }
            }
            visitado.remove(ini.getElemento().toString());
            camino.eliminar(camino.localizar(ini.getElemento()));
        }
        return compara;
    }

    //196
    public Lista caminoMasLargo(Object a, Object b) {
        Lista cam = new Lista();
        Lista compara = new Lista();
        HashMap<String, Object> visitados = new HashMap<String, Object>();
        HashMap<Object, NodoVertice> n = new HashMap<Object, NodoVertice>();
        NodoVertice nA = n.get(a);
        NodoVertice nB = n.get(b);
        if (nA != null && nB != null) {
            cam = this.caminoMasLargoAux(nA, nB, visitados, cam, compara);
        }
        return cam;
    }

    //475
    private Lista caminoMasLargoAux(NodoVertice a, NodoVertice b, HashMap<String, Object> visitados, Lista cam, Lista compara) {
        Lista l = compara;
        if (a != null) {
            visitados.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            if (compara.esVacia() || cam.longitud() >= compara.longitud()) {
                if (a.equals(b)) {
                    l = cam.clone();
                } else {
                    NodoAdy ady = a.getAdyacente();
                    while (ady != null) {
                        if (visitados.get(ady.getVertice().getElemento().toString()) == null) {
                            if (compara.esVacia()) {
                                compara = this.caminoMasLargoAux(ady.getVertice(), b, visitados, cam, compara);
                            } else {
                                compara = this.caminoMasLargoAux(ady.getVertice(), b, visitados, cam, compara);
                            }
                        }
                        ady = ady.getSiguienteAdy();
                    }
                    l = compara;
                }
            }
            visitados.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        return l;
    }

    //209
    public Lista caminoConMasPeso(Object a, Object b) {
        Lista l = new Lista();
        HashMap<String, NodoVertice> visitados = new HashMap<String, NodoVertice>();
        HashMap<Object, NodoVertice> n = this.ubicarNodoVertice(a, b);
        NodoVertice nA = n.get(a);
        NodoVertice nB = n.get(b);
        if (nA != null && nB != null) {
            l = this.caminoConMasPesoAux(nA, nB, visitados, l, 0, 0);
        }
        if (!l.esVacia()) {
            l = (Lista) l.recuperar(1);
        }
        return (Lista) l;
    }

    //508
    private Lista caminoConMasPesoAux(NodoVertice a, NodoVertice b, HashMap<String, NodoVertice> visitados, Lista cam, double peso, double compara) {
        Lista l = new Lista();
        if (a != null) {
            visitados.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            if (a.equals(b)) {
                l.insertar(cam.clone(), 1);
                l.insertar(peso, 2);
            } else {
                NodoAdy ady = a.getAdyacente();
                Lista aux = new Lista();
                while (ady != null) {
                    if (visitados.get(ady.getVertice().getElemento().toString()) == null) {
                        if (l.esVacia()) {
                            l = this.caminoConMasPesoAux(ady.getVertice(), b, visitados, cam, peso + ady.getEtiqueta(), compara);
                        } else {
                            aux = this.caminoConMasPesoAux(ady.getVertice(), b, visitados, cam, peso + ady.getEtiqueta(), (double) l.recuperar(2));
                            if (!aux.esVacia() && (double) aux.recuperar(2) >= compara) {
                                l = aux;
                            }
                        }
                    }
                    ady = ady.getSiguienteAdy();
                }
            }
            visitados.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        return l;
    }

    //227
    public Lista caminoConMenosPeso(Object a, Object b) {
        Lista l = new Lista();
        Lista cam = new Lista();
        HashMap<String, NodoVertice> visitados = new HashMap<String, NodoVertice>();
        HashMap<Object, NodoVertice> n = this.ubicarNodoVertice(a, b);
        NodoVertice nA = n.get(a);
        NodoVertice nB = n.get(b);
        if (nA != null && nB != null) {
            l = this.caminoConMenosPesoAux(nA, nB, visitados, cam, l, 0, 0);
        }
        if (!l.esVacia()) {
            l = (Lista) l.recuperar(1);
        }
        return (Lista) l;
    }

    //660
    private Lista caminoConMenosPesoAux(NodoVertice a, NodoVertice b, HashMap<String, NodoVertice> visitados, Lista cam, Lista res, double peso, double compara) {
        if (a != null) {
            visitados.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            if (a.equals(b)) {
                if (compara == 0 || peso <= compara) {
                    res.insertar(cam.clone(), 1);
                    res.insertar(peso, 2);
                }
            } else {
                NodoAdy ady = a.getAdyacente();
                while (ady != null) {
                    if (visitados.get(ady.getVertice().getElemento().toString()) == null) {
                        double pesoAdy = peso + ady.getEtiqueta();
                        if (compara == 0 || pesoAdy < compara) {
                            if (res.esVacia()) {
                                res = this.caminoConMenosPesoAux(ady.getVertice(), b, visitados, cam, res, pesoAdy, compara);
                            } else {
                                res = this.caminoConMenosPesoAux(ady.getVertice(), b, visitados, cam, res, pesoAdy, (double) res.recuperar(2));
                            }
                        }
                    }
                    ady = ady.getSiguienteAdy();
                }
            }
            visitados.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        return res;
    }

    //242
    public Lista caminos(Object a, Object b) {
        Lista cam = new Lista();
        HashMap<String, Object> visitados = new HashMap<String, Object>();
        HashMap<Object, NodoVertice> n = new HashMap<Object, NodoVertice>();
        NodoVertice nA = n.get(a);
        NodoVertice nB = n.get(b);
        if (nA != null && nB != null) {
            cam = this.caminosAux(nA, nB, visitados);
        }
        return cam;
    }

    //531
    private Lista caminosAux(NodoVertice a, NodoVertice b, HashMap<String, Object> visitados) {
        Lista res = new Lista();
        if (a != null) {
            visitados.put(a.getElemento().toString(), a);
            if (a.equals(b)) {
                Lista l = new Lista();
                l.insertar(a.getElemento(), 1);
                res.insertar(l, 1);
            } else {
                NodoAdy ady = a.getAdyacente();
                while (ady != null) {
                    if (visitados.get(ady.getVertice().getElemento().toString()) == null) {
                        Lista retorno = caminosAux(ady.getVertice(), b, visitados);
                        res = this.concatenar(retorno, res);
                    }
                    ady = ady.getSiguienteAdy();
                }
                int i = 1;
                while (i <= res.longitud()) {
                    Lista lis = (Lista) res.recuperar(i);
                    res.eliminar(i);
                    lis.insertar(a.getElemento(), 1);
                    res.insertar(lis, i);
                    i++;
                }
            }
        }
        visitados.remove(a.getElemento().toString());
        return res;
    }

    // 254
    private Lista concatenar(Lista aux, Lista aux2) {
        Lista res = new Lista();
        int i = 1;
        while (1 <= aux.longitud() + aux2.longitud()) {
            if (i <= aux.longitud()) {
                res.insertar(aux.recuperar(i), i);
            } else {
                res.insertar(aux2.recuperar(i - aux.longitud()), i - aux.longitud());
            }
            i++;
        }
        return res;
    }

    //270
    public Lista menorCaminoQueTiene(Object a, Object b, Object c) {
        Lista cam = new Lista();
        Lista res = new Lista();
        HashMap<String, NodoVertice> v = new HashMap<String, NodoVertice>();
        NodoVertice n = this.inicio, nA = null, nB = null, nC = null;
        boolean parar = false;
        while (n != null && !parar) {
            if (n.getElemento().equals(a)) {
                nA = n;
            } else {
                if (n.getElemento().equals(b)) {
                    nB = n;
                } else {
                    if (n.getElemento().equals(c)) {
                        nC = n;
                    }
                }
            }
            if (nA != null && nB != null && nC != null) {
                parar = true;
            }
            n = n.getSigVertice();
        }
        if (nA != null && nB != null && nC != null) {
            res = this.menorCaminoQueTieneAux(nA, nB, nC, v, cam, res);
        }
        return res;
    }

    //573
    private Lista menorCaminoQueTieneAux(NodoVertice a, NodoVertice b, NodoVertice c, HashMap<String, NodoVertice> v, Lista cam, Lista compara) {
        //a es inicio b es fin y en c estan todos lo que incluyen en el medio entre a y b
        if (a != null) {
            v.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            System.out.println("Inicio: " + a.getElemento().toString() + " Fin: " + b.getElemento().toString() + " Camino: " + c.toString() + " Comparar: " + compara.toString());
            if (compara.esVacia() || cam.longitud() < compara.longitud()) {
                if (a.equals(b)) {
                    if (cam.localizar(c.getElemento()) != -1) {
                        compara = cam.clone();
                    }
                } else {
                    NodoAdy ady = a.getAdyacente();
                    while (ady != null) {
                        if (v.get(ady.getVertice().getElemento().toString()) == null) {
                            compara = this.menorCaminoQueTieneAux(ady.getVertice(), b, c, v, cam, compara);
                        }
                        ady = ady.getSiguienteAdy();
                    }
                }
            }
            v.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        return compara;
    }

    //295
    public Lista caminoMasRapidoQuePasa(Object a, Object b, Object c) {
        Lista cam = new Lista();
        Lista compara = new Lista();
        HashMap<String, NodoVertice> v = new HashMap<String, NodoVertice>();
        NodoVertice n = this.inicio;
        NodoVertice nA = null;
        NodoVertice nB = null;
        NodoVertice nC = null;
        boolean parar = false;
        while (n != null && !parar) {
            if (n.getElemento().equals(a)) {
                nA = n;
            } else {
                if (n.getElemento().equals(b)) {
                    nB = n;
                } else {
                    if (n.getElemento().equals(c)) {
                        nC = n;
                    }
                }
            }
        }
        if (nA != null && nB != null && nC != null) {
            parar = true;
        }
        n = n.getSigVertice();
        if (nA != null && nB != null && nC != null) {
            cam = this.caminoMasRapidoQuePasaAux(nA, nB, nC, v, cam, compara, 0, 0);
        }
        if (!cam.esVacia()) {
            cam = (Lista) cam.recuperar(1);
        }
        return cam;
    }

    //601
    private Lista caminoMasRapidoQuePasaAux(NodoVertice a, NodoVertice b, NodoVertice c, HashMap<String, NodoVertice> v, Lista cam, Lista res, double peso, double compara) {
        if (a != null) {
            v.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            System.out.println("Inicio: " + a.getElemento().toString() + ", Camino: " + cam.toString() + " Fin: " + b.getElemento().toString() + " Incluido: " + c.getElemento().toString());
            if (a.equals(b)) {
                if (compara == 0 || peso < compara && cam.localizar(c.getElemento()) != -1) {
                    res.insertar(cam.clone(), 1);
                    res.insertar(peso, 2);
                }
            } else {
                NodoAdy ady = a.getAdyacente();
                while (ady != null) {
                    if (v.get(ady.getVertice().getElemento().toString()) == null) {
                        double pesoAdyacente = peso + ady.getEtiqueta();
                        if (compara == 0 || pesoAdyacente < compara) {

                            if (res.esVacia()) {
                                res = this.caminoMasRapidoQuePasaAux(ady.getVertice(), b, c, v, cam, res, pesoAdyacente, compara);
                            } else {
                                res = this.caminoMasRapidoQuePasaAux(ady.getVertice(), b, c, v, cam, res, pesoAdyacente, (double) res.recuperar(2));
                            }
                        }
                    }
                    ady = ady.getSiguienteAdy();
                }
            }
            System.out.println(res);
            v.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        return res;
    }

    //322
    public Lista caminoConTopeDeVertices(Object a, Object b, int topeVert) {
        Lista retorno = new Lista();
        Lista res = new Lista();
        HashMap<String, NodoVertice> visi = new HashMap<String, NodoVertice>();
        HashMap<Object, NodoVertice> vertices = this.ubicarNodoVertice(a, b);
        NodoVertice nA = vertices.get(a);
        NodoVertice nB = vertices.get(b);
        if (nA != null && nB != null) {
            res = this.caminoConTopeDeVerticesAux(nA, nB, topeVert, visi, retorno, res);
        }
        return res;
    }

    //633
    private Lista caminoConTopeDeVerticesAux(NodoVertice a, NodoVertice b, int topevert, HashMap<String, NodoVertice> v, Lista cam, Lista res) {
        /*si a no es nulo lo guardo en visitados(hash) y en camino(lista)        
         */
        if (a != null) {
            v.put(a.getElemento().toString(), a);
            cam.insertar(a.getElemento(), cam.longitud() + 1);
            if (cam.longitud() <= topevert && res.esVacia() || cam.longitud() < res.longitud()) {
                //siempre que la long de cam no supere el tope de vertices
                //o que sea menor a la long de res continua
                System.out.println("camino: " + cam.toString() + " comparar: " + res.toString());
                if (a.equals(b)) {
                    res = cam.clone();
                } else {
                    NodoAdy ady = a.getAdyacente();
                    while (ady != null) {
                        if (v.get(ady.getVertice().getElemento().toString()) == null) {
                            res = this.caminoConTopeDeVerticesAux(ady.getVertice(), b, topevert, v, cam, res);
                        }
                        ady = ady.getSiguienteAdy();
                    }
                }
            }
            v.remove(a.getElemento().toString());
            cam.eliminar(cam.localizar(a.getElemento()));
        }
        System.out.println(res.toString());
        return res;
    }

    //336
    public Lista listaProfundidad() {
        Lista l = new Lista();//guarda a los listados
        HashMap<String, Object> v = new HashMap<String, Object>();
        NodoVertice n = this.inicio;
        while (n != null) {
            if (v.get(n.getElemento().toString()) == null) {
                this.listaProfundidadAux(n, l, v);
            }
            n = n.getSigVertice();
        }
        return l;
    }

    //428
    private void listaProfundidadAux(NodoVertice n, Lista l, HashMap<String, Object> v) {
        v.put(n.getElemento().toString(), this.inicio);
        l.insertar(n.getElemento(), l.longitud() + 1);
        NodoAdy ady = n.getAdyacente();
        while (ady != null) {
            if (v.get(ady.getVertice().getElemento().toString()) == null) {
                this.listaProfundidadAux(ady.getVertice(), l, v);
            }
            ady = ady.getSiguienteAdy();
        }
    }

    //348
    public Lista listaAnchura() {
        Lista l = new Lista();
        HashMap<String, Object> v = new HashMap<String, Object>();
        NodoVertice n = this.inicio;
        while (n != null) {
            if (v.get(n.getElemento().toString()) == null) {
                this.listaAnchuraAux(n, l, v);
            }
            n = n.getSigVertice();
        }
        return l;
    }

    //409
    private void listaAnchuraAux(NodoVertice n, Lista l, HashMap<String, Object> v) {
        Cola c = new Cola();
        v.put(n.getElemento().toString(), this.inicio);
        c.poner(n);
        while (!c.esVacia()) {
            NodoVertice aux = (NodoVertice) c.obtenerFrente();
            l.insertar(aux.getElemento(), l.longitud() + 1);
            c.sacar();
            NodoAdy ady = aux.getAdyacente();
            while (ady != null) {
                if (v.get(ady.getVertice().getElemento().toString()) == null) {
                    c.poner(ady.getVertice());
                    v.put(ady.getVertice().getElemento().toString(), ady.getVertice());
                }
                ady = ady.getSiguienteAdy();
            }
        }
    }

    //360
    public Grafo clone() {
        Grafo clone = new Grafo();
        NodoVertice n = this.inicio;
        HashMap<String, NodoVertice> nodos = new HashMap<String, NodoVertice>();
        if (n != null) {
            clone.inicio = new NodoVertice(n.getElemento(), null, null);
            NodoVertice nodoClone = clone.inicio;
            nodos.put(n.getElemento().toString(), nodoClone);
            n = n.getSigVertice();
            while (n != null) {
                nodoClone.setSigVertice(new NodoVertice(n.getElemento(), null, null));
                nodoClone = nodoClone.getSigVertice();
                nodos.put(nodoClone.getElemento().toString(), nodoClone);
                n = n.getSigVertice();
            }
            nodoClone = clone.inicio;
            n = this.inicio;
            while (nodoClone != null) {
                NodoAdy ady = n.getAdyacente();
                if (ady != null) {
                    nodoClone.setAdyacente(new NodoAdy(nodos.get(ady.getVertice().getElemento().toString()), null, ady.getEtiqueta()));
                    NodoAdy adyClon = nodoClone.getAdyacente();
                    ady = ady.getSiguienteAdy();
                    while (ady != null) {
                        adyClon.setSiguienteAdy(new NodoAdy(nodos.get(ady.getVertice().getElemento().toString()), null, ady.getEtiqueta()));
                        ady=ady.getSiguienteAdy();
                        adyClon=adyClon.getSiguienteAdy();
                    }
                }
                nodoClone = nodoClone.getSigVertice();
                n=n.getSigVertice();
            }
        }
        return clone;
    }

    //389
    public String toString(){
        String res ="Grafo sin entradas";
        NodoVertice n= this.inicio;
        if(n != null){
            res="";
            while(n!=null){
                NodoAdy ady = n.getAdyacente();
                res=res+n.getElemento().toString()+":";
                while(ady!=null){
                    res=res+"->("+ady.getVertice().getElemento().toString()+","+ady.getEtiqueta()+")";
                    ady=ady.getSiguienteAdy();
                }
                res=res+"\n";
                n=n.getSigVertice();
            }
        }
        return res;
    }
    
    //120
    public Object recuperarVertice(Object e){
        Object res=null;
        NodoVertice n=this.inicio;
        while(n!=null && res==null){
            if(n.getElemento().equals(e)){
                res=n.getElemento();
            }
            n=n.getSigVertice();
        }
        return res;
    }
    
    //101
    public Object getEtiquetaArco(Object a, Object b){
        Object res=null;
        HashMap<Object,NodoVertice> n=this.ubicarNodoVertice(a, b);
        NodoVertice nA=n.get(a);
        NodoVertice nB=n.get(b);
        if(nA!=null && nB!=null){
            NodoAdy ady = nA.getAdyacente();
            while(ady!=null && res==null){
                if(ady.getVertice().equals(nB)){
                    res=ady.getEtiqueta();
                }
                ady=ady.getSiguienteAdy();
            }
            return res;
        }
        return res;
    }
}
