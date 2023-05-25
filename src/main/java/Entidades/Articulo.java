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
public class Articulo {

    private String codigo;
    private String descripcion;
    private String grupo;
    private float valor;
    private float stock;

    public Articulo() {
    }

    public Articulo(String codigo, String descripcion, String grupo, float valor, float stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.grupo = grupo;
        this.valor = valor;
        this.stock = stock;
    }

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getStock() {
        return stock;
    }

    public void setStock(float stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Articulo{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", grupo=" + grupo + ", valor=" + valor + ", stock=" + stock + '}';
    }

    public void leer() {
        Scanner rs = new Scanner(System.in);
        System.out.println("Código: ");
        this.setCodigo(rs.nextLine());
        System.out.println("Descripción");
        this.setDescripcion(rs.nextLine());
        System.out.println("Grupo: ");
        this.setGrupo(rs.nextLine());
        System.out.println("Valor Unitario: ");
        this.setValor(rs.nextFloat());
        System.out.println("Stock actual: ");
        this.setStock(rs.nextFloat());
    }

}
