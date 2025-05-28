package TP2;

import Interfaces.GrafoTDA;
import Interfaces.conjuntoTDA;
import Implementaciones.GrafoMA;


import Implementaciones.ConjuntoLD;


public class caminoMin {

  static final int MAX = 100; // máximo valor para los arreglos
  static final int INF = Integer.MAX_VALUE; // Representa el falor más alto posible



  public static class Matriz { 
    int distanciaOrigen;
    int nodoAnterior;

    Matriz() { // Inicializamos la Matriz
      this.distanciaOrigen = INF;
      this.nodoAnterior = -1;
    }
  }


  public static void main(String[] args) {
    GrafoTDA g1 = grafo.Grafo(); // inicialoza el grafo con aristas y vértices

    int origen = 1; 

    Matriz[] rutaMinima = new Matriz[MAX];
    boolean[] visitado = new boolean[MAX];
    for (int i = 0; i < MAX; i++) { 
      rutaMinima[i] = new Matriz();
    }// completa el arreglo con matrices vacias

    rutaMinima[origen].distanciaOrigen = 0;

    conjuntoTDA nodos = g1.vertices(); // guarda los vértices del grafo en un conjunto

   conjuntoTDA AUX = new ConjuntoLD();
    
   AUX.inicializarConjunto();

    while (!nodos.conjuntoVacio()) {
      int nodo = nodos.elegir();
      AUX.agregar(nodo);
      nodos.sacar(nodo);
    }
    

    
    while (!AUX.conjuntoVacio()) { // Para cada nodo del grafo, elige
      int u = nodoMinimo(rutaMinima, visitado, AUX);

      if (u == -1) break;

      visitado[u] = true; // marca el nodo como visitado

      conjuntoTDA vecinos = g1.vecinos(u);

      while (!vecinos.conjuntoVacio()) { // Recorer todos los vecinos de ese nodo y actualiza di
        int v = vecinos.elegir(); // obtiene uno de los vecinos de u
        vecinos.sacar(v);

        int peso = g1.pesoArista(u, v); // obtiene el peso entre el predecesor y su vecino

        if (!visitado[v] && rutaMinima[u].distanciaOrigen + peso < rutaMinima[v].distanciaOrigen) {
          rutaMinima[v].distanciaOrigen = rutaMinima[u].distanciaOrigen + peso;
          rutaMinima[v].nodoAnterior = u;
        }
      }

      nodos.sacar(u); // Sacar el nodo que ya fue visitado
    }



  // Imprime el camino mas corto para ir a cada uno de los nodos

// Imprime el camino más corto desde el nodo origen a cada nodo alcanzable
for (int i = 0; i < MAX; i++) {
  if (rutaMinima[i].distanciaOrigen != INF) { // Si el nodo fue alcanzado por el algoritmo
    System.out.print("Nodo: " + i +
                     ", Distancia: " + rutaMinima[i].distanciaOrigen +
                     ", Camino: ");

    int actual = i;
    int[] camino = new int[MAX]; // Guarda el camino al revés desde el nodo destino hasta el origen
    int indice = 0;

    while (actual != -1) { // Sigue hacia atrás usando los nodos anteriores
      camino[indice] = actual;
      indice++;
      actual = rutaMinima[actual].nodoAnterior;
    }

    // Imprime el camino en orden correcto desde el origen
    for (int j = indice - 1; j >= 0; j--) {
      System.out.print(camino[j]);
      if (j > 0) {
        System.out.print(" - ");
      }
    }

    System.out.println(); // Salto de línea entre nodos
  }
}
  }
  public static int nodoMinimo(Matriz[] rutaMinima, boolean[] visitado, conjuntoTDA nodos) {
    /*
    Retorna el nodo no visitado que tiene la menor distancia conocidad desde el origen
     */
    int minNodo = -1;
    int minDist = INF;
    conjuntoTDA aux = new ConjuntoLD();
    aux.inicializarConjunto();

    while (!nodos.conjuntoVacio()) { // nodos contiene los vertices que no fueron visitados
      int n = nodos.elegir();
      nodos.sacar(n);
      aux.agregar(n);

      if (!visitado[n] && rutaMinima[n].distanciaOrigen < minDist) {
        minDist = rutaMinima[n].distanciaOrigen;
        minNodo = n;
      }
    }

    while (!aux.conjuntoVacio()) { // Se vuelve a reconstruir el nodo.
      int n = aux.elegir();
      aux.sacar(n);
      nodos.agregar(n);
    }

    return minNodo;
  }
}

