package Modelo;
import java.util.Scanner;

public class Dijkstra {
    private static final int INFINITY = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int startNode) {
        int numNodes = graph.length;
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];

        // Inicializar distancias
        for (int i = 0; i < numNodes; i++) {
            distances[i] = INFINITY;
            visited[i] = false;
        }
        distances[startNode] = 0;

        for (int i = 0; i < numNodes - 1; i++) {
            int minDistanceNode = findMinDistanceNode(distances, visited);
            visited[minDistanceNode] = true;

            for (int j = 0; j < numNodes; j++) {
                if (!visited[j] && graph[minDistanceNode][j] != 0 &&
                    distances[minDistanceNode] != INFINITY &&
                    distances[minDistanceNode] + graph[minDistanceNode][j] < distances[j]) {
                    distances[j] = distances[minDistanceNode] + graph[minDistanceNode][j];
                }
            }
        }

        System.out.println("Distancias más cortas desde el nodo " + startNode + ":");
        for (int i = 0; i < numNodes; i++) {
            if (distances[i] == INFINITY) {
                System.out.println("Hasta el nodo " + i + ": ∞ (inaccesible)");
            } else {
                System.out.println("Hasta el nodo " + i + ": " + distances[i]);
            }
        }
    }

    private static int findMinDistanceNode(int[] distances, boolean[] visited) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de nodos: ");
        int numNodes = scanner.nextInt();

        int[][] graph = new int[numNodes][numNodes];
        System.out.println("Ingrese la matriz de adyacencia (0 si no hay conexión):");
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Ingrese el nodo de inicio (entre 0 y " + (numNodes - 1) + "): ");
        int startNode = scanner.nextInt();

        dijkstra(graph, startNode);
    }
}

