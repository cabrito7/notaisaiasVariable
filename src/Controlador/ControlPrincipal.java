/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import java.util.ArrayList;

/**
 *
 * @author carlosmamut1
 */
public class ControlPrincipal {

    private char[] abecedario;
    private Fachada fachada;
    private ControlGrafo cGrafo;
    private char[] verticesDelGrafo;
    private ArrayList<char[]> aristasDelGrafo;
    private ArrayList<Integer> pesosDeLasAristas;

    public ControlPrincipal() {
        this.fachada = new Fachada(this);
        this.cGrafo = new ControlGrafo(this);
        this.abecedario = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        this.verticesDelGrafo = new char[]{};
        this.aristasDelGrafo = new ArrayList<>();
        this.pesosDeLasAristas = new ArrayList<>();
    }
    
    public void crearGrafoEnControlPrincipal(boolean dirigido) {
        this.cGrafo.crearGrafo(verticesDelGrafo, aristasDelGrafo, pesosDeLasAristas, dirigido);
    }

    public void extraerVertices(int num) {
        this.verticesDelGrafo = new char[num];
        for (int i = 0; i < num; i++) {
            verticesDelGrafo[i] = this.abecedario[i];
        }
    }

    public boolean encontrarVertice(char verticeAEncontrar) {
        for (int i = 0; i < this.verticesDelGrafo.length; i++) {
            if (verticesDelGrafo[i] == verticeAEncontrar) {
                return true;
            }
        }
        return false;
    }

    public void agregarArista(String texto, String pesoDeLaArista, boolean dirigido) {
        try {
            if (texto.length() == 3 && texto.charAt(1) == ',' && encontrarVertice(texto.charAt(0)) && encontrarVertice(texto.charAt(2)) && encontrarArista(texto) == -1) {
                if (Integer.parseInt(pesoDeLaArista) > 0) {
                    if (!dirigido) {
                        char[] arista = {texto.charAt(0), texto.charAt(2)};
                        char[] aristaReverso = {texto.charAt(2), texto.charAt(0)};
                        aristasDelGrafo.add(arista);
                        pesosDeLasAristas.add(Integer.parseInt(pesoDeLaArista));
                        this.fachada.getvPrincipal().mostrarMensaje("La arista fue creada exitosamente");
                        aristasDelGrafo.add(aristaReverso);
                        pesosDeLasAristas.add(Integer.parseInt(pesoDeLaArista));
                        this.fachada.getvPrincipal().mostrarMensaje("La arista fue creada exitosamente");
                    } else {
                        char[] arista = {texto.charAt(0), texto.charAt(2)};
                        aristasDelGrafo.add(arista);
                        pesosDeLasAristas.add(Integer.parseInt(pesoDeLaArista));
                        this.fachada.getvPrincipal().mostrarMensaje("La arista fue creada exitosamente");
                    }
                }
                else {
                    this.fachada.getvPrincipal().mostrarMensajeError("El peso que pusiste tiene que ser mayor a 0");
                }
            } else {
                this.fachada.getvPrincipal().mostrarMensajeError("La arista que pusiste no es valida");
            }
        } catch (Exception e) {
            this.fachada.getvPrincipal().mostrarMensajeError("El peso que pusiste no es valido");
        }
    }

    public int buscarEnElAbecedario(char letra) {
        for (int i = 0; i < abecedario.length; i++) {
            if (letra == abecedario[i]) {
                return i;
            }
        }
        return -1;
    }

    public void crearMatrizDeAdyacencia() {
        int[][] matrizDeAdyacencia = new int[this.cGrafo.getGrafo().getVertices().length][this.cGrafo.getGrafo().getVertices().length];
        for (int i = 0; i < this.cGrafo.getGrafo().getAristas().size(); i++) {
            char[] aristaAComparar = this.cGrafo.getGrafo().getAristas().get(i);
            matrizDeAdyacencia[buscarEnElAbecedario(aristaAComparar[0])][buscarEnElAbecedario(aristaAComparar[1])] = this.cGrafo.getGrafo().getPesosDeLosNodos().get(i);
        }

    }

    public int encontrarArista(String texto) {
        char[] buscada = {texto.charAt(0), texto.charAt(2)};
        for (int i = 0; i < aristasDelGrafo.size(); i++) {
            char[] actual = aristasDelGrafo.get(i);
            if (actual[0] == buscada[0] && actual[1] == buscada[1]) {
                return i;
            }
        }
        return -1;
    }

    public boolean validarValorNumerico(String num) {
        try {
            int numeroDeVertices = Integer.parseInt(num);
            if (numeroDeVertices > 0) {
                this.extraerVertices(numeroDeVertices);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
