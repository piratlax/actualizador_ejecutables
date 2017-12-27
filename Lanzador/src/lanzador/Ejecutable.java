
package lanzador;

import java.io.IOException;

public class Ejecutable {
    public void lanzar(){
        try 
{ 
   /* directorio/ejecutable es el path del ejecutable y un nombre */ 
   Process p = Runtime.getRuntime().exec ("prueba.exe"); 
   System.out.println ("Ejecutamos la calculadora");
} 
catch (IOException e) 
{ 
   /* Se lanza una excepción si no se encuentra en ejecutable o el fichero no es ejecutable. */ 
    System.out.println ("no se encontre el ejecutable");
}
    }
}
