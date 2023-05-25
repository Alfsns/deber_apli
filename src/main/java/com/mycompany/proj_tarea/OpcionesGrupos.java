/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proj_tarea;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.xml.sax.SAXException;

import accesoDatos.OpGrupo;
import Entidades.Grupo;

/**
 *
 * @author Admin
 */
public class OpcionesGrupos {

    private final OpGrupo oper;

    public OpcionesGrupos() throws ParserConfigurationException, SAXException, IOException {
        this.oper = new OpGrupo();
    }

    public void probarConexion() {
        System.out.println(oper.existeXml() ? "Conexion Valida...." : "Conexion Fallida....");
    }

    public void insertar() {      //funcion que realiza la insercion de una nueva cuenta de ahorros
        Grupo grup = new Grupo();
        grup.leer();

        System.out.println("INSERCION DE NUEVO GRUPO EN XML");
        if (oper.insertar(grup)) {
            System.out.println("Grupo registrado exitosamente.....");
        } else {
            System.out.println("Error al registrar Grupo....");
        }
    }

    public void consultar() {     //funcion que realiza la consulta de una cuenta de ahorros
        System.out.println("CONSULTA DE GRUPO DESDE XML");
        System.out.println("Grupo: " + oper.consultar(2).toString());
    }

    public void modificar() throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {     //funcion que realiza la modificación de una cuenta de ahorros
        Grupo grup = new Grupo();
        grup.leer();

        System.out.println("MODIFICACION DE GRUPO EN XML");
        if (oper.modificar(grup)) {
            System.out.println("Grupo modificado exitosamente.....");
        } else {
            System.out.println("Error al modificar Grupo....");
        }
    }

    public void eliminar() throws ParserConfigurationException, SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {       //funcion que realiza la eliminación de una cuenta de ahorros
        System.out.println("ELIMINACION DE GRUPO EN XML");
        if (oper.eliminar(2)) {
            System.out.println("Grupo eliminado exitosamente.....");
        } else {
            System.out.println("Error al eliminar Grupo....");
        }
    }

    public void listar() {   //implementa el listado de cuentas de ahorro
        ArrayList<Grupo> lisGrups = oper.listar();
        System.out.println("LISTA DE GRUPOS EN XML");

        lisGrups.forEach(cA -> {
            System.out.println("Grupo: " + cA.toString());
        });
    }
}
