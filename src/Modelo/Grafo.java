/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author carlosmamut1
 */
public class Grafo {
    private char[] vertices;
    private ArrayList<char[]> aristas;
    private ArrayList<Integer> pesosDeLosNodos;
    private boolean dirigido;

    public char[] getVertices() {
        return vertices;
    }

    public void setVertices(char[] vertices) {
        this.vertices = vertices;
    }



    public boolean isDirigido() {
        return dirigido;
    }

    public void setDirigido(boolean dirigido) {
        this.dirigido = dirigido;
    }

    public ArrayList<char[]> getAristas() {
        return aristas;
    }

    public void setAristas(ArrayList<char[]> aristas) {
        this.aristas = aristas;
    }

    public ArrayList<Integer> getPesosDeLosNodos() {
        return pesosDeLosNodos;
    }

    public void setPesosDeLosNodos(ArrayList<Integer> pesosDeLosNodos) {
        this.pesosDeLosNodos = pesosDeLosNodos;
    }

    public Grafo(char[] vertices, ArrayList<char[]> aristas, ArrayList<Integer> pesosDeLosNodos, boolean dirigido) {
        this.vertices = vertices;
        this.aristas = aristas;
        this.pesosDeLosNodos = pesosDeLosNodos;
        this.dirigido = dirigido;
    }


    
    
}

