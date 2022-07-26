/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntista;

/**
 *
 * @author Gonzalo
 */
public class testAVL {
    static String sOk = "OK!", sErr = "ERROR";
    public static void main(String[] args) {
        //uno de los arboles va a ordenar letras(1) y el otro numeros(2)
        ArbolAVL A1 = new ArbolAVL();
        //probamos los metodos de insercion
        System.out.println("USANDO DE PIBOTE LA RAIZ");
        System.out.println("-------------------------Fran!!!5------\n");
        System.out.println("Un arbol Caido a la Izquierda y un poco a la derecha");
        System.out.println("Insertamos G espera OK!:\t\t\t"+(A1.insertar('G')? sOk: sErr));
        System.out.println("Insertamos C espera OK!:\t\t\t"+(A1.insertar('C')? sOk: sErr));
        System.out.println("Insertamos D espera OK!:\t\t\t"+(A1.insertar('D')? sOk: sErr));
        
        System.out.println("-------------------------------");
        System.out.println(A1.toString());
        
        System.out.println("-------------------------------\n");
        System.out.println("Un Arbol caido a la Izquierda: ");
        ArbolAVL A2 = new ArbolAVL();
        System.out.println("Insertamos G espera OK!:\t\t\t"+(A2.insertar('G')? sOk: sErr));
        System.out.println("Insertamos D espera OK!:\t\t\t"+(A2.insertar('D')? sOk: sErr));
        System.out.println("Insertamos C espera OK!:\t\t\t"+(A2.insertar('C')? sOk: sErr));
        
        System.out.println("-------------------------------");
        System.out.println(A2.toString());
        
        System.out.println("-------------------------------\n");
        System.out.println("Un Arbol caido a la Derecha y un poco a la izquierda");
        ArbolAVL A3 = new ArbolAVL();
        System.out.println("Insertamos G espera OK!:\t\t\t"+(A3.insertar('G')? sOk: sErr));
        System.out.println("Insertamos I espera OK!:\t\t\t"+(A3.insertar('I')? sOk: sErr));
        System.out.println("Insertamos H espera OK!:\t\t\t"+(A3.insertar('H')? sOk: sErr));
        
        System.out.println("-------------------------------");
        System.out.println(A3.toString());
        
        System.out.println("-------------------------------\n");
        System.out.println("Un arbol caido a la izquierda");
        
        ArbolAVL A4 = new ArbolAVL();
        System.out.println("Insertamos G espera OK!:\t\t\t"+(A4.insertar('G')? sOk: sErr));
        System.out.println("Insertamos H espera OK!:\t\t\t"+(A4.insertar('H')? sOk: sErr));
        System.out.println("Insertamos I espera OK!:\t\t\t"+(A4.insertar('I')? sOk: sErr));
        
        System.out.println("-------------------------------");
        System.out.println(A4.toString());
        
        System.out.println("-------------------------------\n");
        System.out.println("AHORA VAMOS A INSERTAR Y USAR DE PIBOTE OTROS NODOS");
        ArbolAVL A5 = new ArbolAVL();
        System.out.println("Insertamos G espera OK!:\t\t\t"+(A5.insertar('G')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos C espera OK!:\t\t\t"+(A5.insertar('C')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos D espera OK!:\t\t\t"+(A5.insertar('D')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos I espera OK!:\t\t\t"+(A5.insertar('I')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos H espera OK!:\t\t\t"+(A5.insertar('H')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos J espera OK!:\t\t\t"+(A5.insertar('J')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Insertamos K espera OK!:\t\t\t"+(A5.insertar('K')? sOk: sErr));
        
        
        System.out.println(A5.toString());
        System.out.println("Eliminamos J: espera Ok"+(A5.eliminar('J')? sOk: sErr));
        System.out.println(A5.toString());
        System.out.println("Rango ");
    }
}
