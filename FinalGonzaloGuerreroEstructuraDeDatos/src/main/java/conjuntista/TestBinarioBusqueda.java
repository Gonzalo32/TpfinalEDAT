/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntista;
import Utiles.TecladoIn;
import lineales.dinamicas.Lista;
import conjuntista.ArbolBB;
/**
 *
 * @author Gonzalo
 */
public class TestBinarioBusqueda {
    public static void main(String []args){
        Lista l=new Lista();
        ArbolBB a=new ArbolBB();
        a.insertar(20);
        a.insertar(12);
        a.insertar(28);
        a.insertar(7);
        a.insertar(14);
        a.insertar(3);
        a.insertar(10);
        a.insertar(17);
        a.insertar(25);
        a.insertar(34);
        a.insertar(31);
        a.insertar(4);
        
        /*Fui probando agregando y quitando elementos en el arbol para poder probar distintos casos
        por ejemplo me di cuenta que mi eliminar no funcionaba en los tres casos (casoUno,casoDos, casoTres) si el elemento
        a eliminar era la raiz, eso quedo corregido, el problema de los maximos y los minimos (eliminar en ambos casos) era que tenia un metodo
        recursivo donde, en cada vuelta eliminaba al minimo de lo que quedaba, quedo solucionado con un while
        fui probando al eliminar elementos donde tuviera dos, un o ningun hijo en el caso de dos hijos decidi que tome
        al mayor de los menoresen caso de que ese valor tuviera un hijo izquierdo lo coloca como HD de su padre inmediato
        en el caso de eliminar un nodo que tenga un hijo primero se pregunta si este nodo es HI o HD del padre inmediato
        que tambien mando por parametro una vez localizado esto se pregunta en ambos casos si el nodo a eliminar tiene HI o HD
        se colocara como hijo (derecho si el valor a eliminar es hijo derecho de su padre inmediato o a la izquierda si el valor a eliminar
        es hijo izquierdo de su padre inmediato) en caso de que el padre enviado por parametro sea nulo significa que se quiere eliminar la raiz
        en este caso se coloca al unico hijo que tenga la raiz como nueva raiz, en caso de que no tenga hijos y sea la raiz el elemento a eliminar
        esta queda como nula caso contrario el padre inmediato del valor a eliminar setea el enlace de su hijo con nulo
        */
        
        int op=menu();
        while(op>0 && op <13){
            Comparable elem;
            switch(op){
                case 1:
                    System.out.print("ingrese el elemento que desee insertar: ");
                    elem=TecladoIn.readLineInt();
                    if(a.insertar(elem)){
                        System.out.println("elemento ingresado con exito");
                    }else{
                        System.out.println("no se puede ingresar");
                    }
                break;
                
                case 2:
                    System.out.println("ingrese el elemento");
                    elem=TecladoIn.readLineInt();    
                    if(a.pertenece(elem)){
                        System.out.println("el elemento pertenece");
                    } else{
                        System.out.println("el elemento no pertenece");
                    }
                break;
                case 3:
                    if(a.esVacio())
                        System.out.println("arbol vacio");
                    else
                        System.out.println("el arbol tiene elementos");
                break;
                case 4:
                    a.vaciar();
                    System.out.println("el arbol fue vaciado");
                    break;
                case 5: 
                    l=a.listar();
                    System.out.println("lista de elementos: "+l.toString());
                    break;
                case 6:
                    System.out.println("ingrese el elemento que desee eliminar");
                    elem=TecladoIn.readLineInt();
                    if(a.eliminar(elem))
                        System.out.println("se ha eliminado el elemento con exito");
                    else
                        System.out.println("no se ha podido eliminar el elemento");
                    break;
                case 7:
                    System.out.println("El minimo elemento del arbol es: "+a.minimoElem());
                    break;
                case 8:
                    System.out.println("El maximo elemento del arbol es: "+a.maximoElem());
                    break;
                case 9:
                    System.out.println("La estructura del arbol es: "+a.toString());
                    break;
                case 10:
                    System.out.println("ingrese dos elementos para crear un rango");
                    Comparable elem1,elem2;
                    elem1=TecladoIn.readLineInt();
                    elem2=TecladoIn.readLineInt();
                    System.out.println("listar por rango: "+a.listarPorRango(elem1, elem2).toString());
                    break;
                case 11:
                    if(a.eliminarMaximo()){
                        System.out.println("elemento maximo elminado");
                    }else{
                        System.out.println("el arbol estaba vacio");
                    }
                    break;
                    case 12:
                    if(a.eliminarMinimo()){
                        System.out.println("elemento minimo elminado");
                    }else{
                        System.out.println("el arbol estaba vacio");
                    }
                    break;
                default:
                    
            }
            op=menu();
        }

    }
    
    public static int menu() {
        int n;
        System.out.println("1- insertar");
        System.out.println("2- pertenece");
        System.out.println("3- pregunta si el arbol esta vacio");
        System.out.println("4- vaciar estructura");
        System.out.println("5- listar");
        System.out.println("6- eliminar un elemento");
        System.out.println("7- obtener elemento minimo");
        System.out.println("8- obtener elemento maximo");
        System.out.println("9- toString");
        System.out.println("10- crea una lista con el rango de dos numeros ingresados");
        System.out.println("11- elimina el maximo elemento del arbol");
        System.out.println("12- elimina el minimo elemento del arbol");
        System.out.println("cualquier numero menor o igual a 0 o mayor o igual a 13 termina el programa");
        System.out.print("elija una opcion: ");
        n = TecladoIn.readLineInt();
        return n;
    }
}
