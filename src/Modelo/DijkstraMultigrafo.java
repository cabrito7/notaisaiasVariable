import java.util.*;

public class DijkstraMultigrafo {

    private static final int INFINITY = Integer.MAX_VALUE;

    // Clase que representa una arista
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Algoritmo de Dijkstra
    public static void dijkstra(List<List<Edge>> graph, int startNode) {
        int numNodes = graph.size();
        int[] distances = new int[numNodes];
        boolean[] visited = new boolean[numNodes];
        int[] previous = new int[numNodes];
        Arrays.fill(distances, INFINITY);
        Arrays.fill(previous, -1);
        distances[startNode] = 0;

        for (int i = 0; i < numNodes - 1; i++) {
            int u = findMinDistanceNode(distances, visited);
            if (u == -1) break; // Todos los nodos restantes son inaccesibles
            visited[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int w = edge.weight;

                if (!visited[v] && distances[u] != INFINITY && distances[u] + w < distances[v]) {
                    distances[v] = distances[u] + w;
                    previous[v] = u;
                }
            }
        }

        // Mostrar resultados
        System.out.println("Distancias más cortas desde el nodo " + startNode + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Hasta el nodo " + i + ": ");
            if (distances[i] == INFINITY) {
                System.out.println("∞ (inaccesible)");
            } else {
                System.out.print(distances[i] + " | Ruta: ");
                printPath(previous, i);
                System.out.println();
            }
        }
    }

    // Encuentra el nodo no visitado con menor distancia
    private static int findMinDistanceNode(int[] distances, boolean[] visited) {
        int minDistance = INFINITY;
        int minNode = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minNode = i;
            }
        }
        return minNode;
    }

    // Reconstruye el camino desde el nodo origen
    private static void printPath(int[] previous, int node) {
        if (previous[node] != -1) {
            printPath(previous, previous[node]);
        }
        System.out.print(node + " ");
    }

    // Función principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el número de nodos: ");
        int numNodes = scanner.nextInt();

        System.out.print("Ingrese el número de aristas: ");
        int numEdges = scanner.nextInt();

        // Inicializar lista de adyacencia
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Ingrese cada arista en el formato: origen destino peso");
        for (int i = 0; i < numEdges; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.get(u).add(new Edge(v, w)); // Para grafos dirigidos
        }

        System.out.print("Ingrese el nodo de inicio (entre 0 y " + (numNodes - 1) + "): ");
        int startNode = scanner.nextInt();

        dijkstra(graph, startNode);
    }
}
