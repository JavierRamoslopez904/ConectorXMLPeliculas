/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodompelis;

import java.io.File;

/**
 *
 * @author javie
 */
public class Main {
    
    public static void main(String[] args) {
        
        /** Creación de un objeto de la clase File **/
        File f = new File("src/accesodompelis/Pelis.xml");
        
        /** Creación de un objeto de la clase AccesoDOMPelis**/
        AccesoDOMPelis acc = new AccesoDOMPelis();
        
        /** Llamamos al método para abrir el fichero XML **/
        acc.abrirXmlDom(f);
        
        /** Guardamos en una variable String el método recorrerDom **/
        String datos = acc.recorrerDom();
        
        /** Mostramos la variable datos **/
        System.out.println(datos);
    }
    
}
