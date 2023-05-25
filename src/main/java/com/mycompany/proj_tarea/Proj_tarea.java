/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proj_tarea;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;
/**
 *
 * @author Alfredo
 */
public class Proj_tarea {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {
        OpcionesGrupos opGrups = new OpcionesGrupos();

        opGrups.probarConexion();
        //opGrups.insertar();
        //opGrups.modificar();
        //opGrups.consultar();
        //opGrups.eliminar();
        //opGrups.listar();

        /*        Articulo a = new Articulo();
        Grupo b = new Grupo();

        System.out.println("G E S T I O N    D E     I N V E N T A R I O");
        System.out.println("INGRESO DE GRUPO");
        System.out.println("===================");
        b.leer();
        System.out.println("Estado del objeto");
        System.out.println(b.toString());

        System.out.println("INGRESO DE ARTICULO");
        System.out.println("===================");
        a.leer();
        System.out.println("Estado del objeto");
        System.out.println(a.toString());
         */
    }
}
