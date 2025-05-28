package TP2;

import Implementaciones.ConjuntoLD;
import Interfaces.GrafoTDA;
import Interfaces.conjuntoTDA;



public class costoMin {

    static final int MAX = 100;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        GrafoTDA g1 = grafo.Grafo(); // inicializa el grafo
        int origen = 1;

        int[] dist = new int[MAX];
        boolean[] visitado = new boolean[MAX];

        for (int i = 0; i < MAX; i++) {
            dist[i] = INF;
        }
        dist[origen] = 0;

        conjuntoTDA nodos = g1.vertices();
        conjuntoTDA AUX = new ConjuntoLD();
        AUX.inicializarConjunto();

        while (!nodos.conjuntoVacio()) {
            int nodo = nodos.elegir();
            AUX.agregar(nodo);
            nodos.sacar(nodo);
        }

        while (!AUX.conjuntoVacio()) {
            int u = nodoMinimo(dist, visitado, AUX);
            if (u == -1) break;
            visitado[u] = true;

            conjuntoTDA vecinos = g1.vecinos(u);
            while (!vecinos.conjuntoVacio()) {
                int v = vecinos.elegir();
                vecinos.sacar(v);
                int peso = g1.pesoArista(u, v);
                if (!visitado[v] && dist[u] + peso < dist[v]) {
                    dist[v] = dist[u] + peso;
                }
            }
        }

        // Imprimir solo los costos mínimos
        for (int i = 0; i < MAX; i++) {
            if (dist[i] != INF) {
                System.out.println("Nodo: " + i + ", Costo mínimo desde " + origen + ": " + dist[i]);
            }
        }
    }

    public static int nodoMinimo(int[] dist, boolean[] visitado, conjuntoTDA nodos) {
        int minNodo = -1;
        int minDist = INF;
        conjuntoTDA aux = new ConjuntoLD();
        aux.inicializarConjunto();

        while (!nodos.conjuntoVacio()) {
            int n = nodos.elegir();
            nodos.sacar(n);
            aux.agregar(n);

            if (!visitado[n] && dist[n] < minDist) {
                minDist = dist[n];
                minNodo = n;
            }
        }

        while (!aux.conjuntoVacio()) {
            int n = aux.elegir();
            aux.sacar(n);
            nodos.agregar(n);
        }

        return minNodo;
    }

}

















