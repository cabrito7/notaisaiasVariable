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
    private static final int INFINITY = Integer.MAX_VALUE;

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
                } else {
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

    public int[][] crearMatrizDeAdyacencia() {
        int[][] matrizDeAdyacencia = new int[this.cGrafo.getGrafo().getVertices().length][this.cGrafo.getGrafo().getVertices().length];
        for (int i = 0; i < this.cGrafo.getGrafo().getAristas().size(); i++) {
            char[] aristaAComparar = this.cGrafo.getGrafo().getAristas().get(i);
            matrizDeAdyacencia[buscarEnElAbecedario(aristaAComparar[0])][buscarEnElAbecedario(aristaAComparar[1])] = this.cGrafo.getGrafo().getPesosDeLosNodos().get(i);
        }
        return matrizDeAdyacencia;
    }

    public String dijkstra(String verticeInicial, int[][] matrizDeAdyacencia) {
        if (encontrarVertice(verticeInicial.charAt(0)) && verticeInicial.length() == 1) {
            int numNodes = matrizDeAdyacencia.length;
            int[] distances = new int[numNodes];
            boolean[] visited = new boolean[numNodes];

            for (int i = 0; i < numNodes; i++) {
                distances[i] = INFINITY;
                visited[i] = false;
            }
            distances[buscarEnElAbecedario(verticeInicial.charAt(0))] = 0;
            for (int i = 0; i < numNodes - 1; i++) {
                int minDistanceNode = findMinDistanceNode(distances, visited);
                visited[minDistanceNode] = true;

                for (int j = 0; j < numNodes; j++) {
                    if (!visited[j] && matrizDeAdyacencia[minDistanceNode][j] != 0
                            && distances[minDistanceNode] != INFINITY
                            && distances[minDistanceNode] + matrizDeAdyacencia[minDistanceNode][j] < distances[j]) {
                        distances[j] = distances[minDistanceNode] + matrizDeAdyacencia[minDistanceNode][j];
                    }
                }
            }
            String text = "<html><h3>Resultados del Algoritmo de Dijkstra:</h3>";
            for (int i = 0; i < numNodes; i++) {
                if (distances[i] == INFINITY) {
                    text += "Hasta el nodo " + this.cGrafo.getGrafo().getVertices()[i] + ": ∞ (inaccesible)<br>";
                } else {
                    text += "Hasta el nodo " + this.cGrafo.getGrafo().getVertices()[i] + ": " + distances[i] + "<br>";
                }
            }
            text += "</html>";
            return text;
        }
        return "<html>Error: Vértice inicial no válido</html>";
    }

    public static int findMinDistanceNode(int[] distances, boolean[] visited) {
        int minDistance = INFINITY;
        int minDistanceNode = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] <= minDistance) {
                minDistance = distances[i];
                minDistanceNode = i;
            }
        }
        return minDistanceNode;
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
