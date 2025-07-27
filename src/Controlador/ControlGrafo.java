/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Grafo;
import java.util.ArrayList;

/**
 *
 * @author carlosmamut1
 */
public class ControlGrafo {
    private ControlPrincipal cPrincipal;
    private Grafo grafo;

    public void crearGrafo(char[] vertices, ArrayList<char[]> aristas, ArrayList<Integer> pesosDeLosNodos, boolean dirigido) {
        this.grafo = new Grafo(vertices,aristas,pesosDeLosNodos,dirigido);
    }
    
    public ControlGrafo(ControlPrincipal cPrincipal) {
        this.cPrincipal = cPrincipal;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }
    
   
    
    
}
