/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Grupo {

//    private int id;
    private String codigo;
    private String descripcion;

    public Grupo() {
    }

//    public Grupo(int id, String codigo, String descripcion) {
//        this.id = id;
//        this.codigo = codigo;
//        this.descripcion = descripcion;
//    }

//    public int getId() {
//        return id;
//    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
//        return "Grupo{" + "id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + '}';
        return "Grupo{" + "codigo=" + codigo + ", descripcion=" + descripcion + '}';
    }



    public void leer() {
        Scanner rs = new Scanner(System.in);

//        System.out.println("Id: ");
//        this.setId(rs.nextInt());
//        rs.nextLine();
        System.out.println("Código:");
        this.setCodigo(rs.nextLine());
        System.out.println("Descripcion:");
        this.setDescripcion(rs.nextLine());
    }

}