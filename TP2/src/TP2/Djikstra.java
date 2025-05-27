package TP2;

import interfaces.GrafoTDA;
import interfaces.conjuntoTDA;
import implementaciones.GrafoMA;

import implementaciones.ConjuntoLD;



  /*
  GrafoTDA g1 = Grafo();  // grafo con vertices y aristas ya incluidos
  conjuntoTDA nodos = g1.vertices(); // Conjunto de los nodos que contiene el grafo
  conjuntoTDA vecinos = new ConjuntoLD(); // conjuntos de todos los vecinos de un nodo x
  conjuntoTDA AUX = new ConjuntoLD(); // auxiliar utilizado para acomodar los nodos
  int nodo; // Representa el valor de un nodo
  int vecino; // Representa el valor del vecino de un nodo
  int peso; // Representa el peso de la arista entre dos nodos
*/

public class Djikstra {

  static final int MAX = 100;
  static final int INF = Integer.MAX_VALUE;



  public static class Matriz {
    int distanciaOrigen;
    int nodoAnterior;

    Matriz() {
      this.distanciaOrigen = INF;
      this.nodoAnterior = -1;
    }
  }

  public static GrafoTDA Grafo() {
    GrafoTDA grafo = new GrafoMA();
    grafo.inicializarGrafo();

    grafo.agregarVertice(1);
    grafo.agregarVertice(2);
    grafo.agregarVertice(3);
    grafo.agregarVertice(4);
    grafo.agregarVertice(5);
    grafo.agregarVertice(6);
    grafo.agregarVertice(7);

    grafo.agregarArista(1, 2, 2);
    grafo.agregarArista(1, 3, 6);
    grafo.agregarArista(2, 3, 5);
    grafo.agregarArista(3, 4, 8);
    grafo.agregarArista(4, 5, 15);
    grafo.agregarArista(4, 6, 10);
    grafo.agregarArista(5, 6, 6);
    grafo.agregarArista(6, 7, 2);

    return grafo;
  }

  public static void main(String[] args) {
    GrafoTDA g1 = Grafo();
    int origen = 1;

    Matriz[] tabla = new Matriz[MAX];
    boolean[] visitado = new boolean[MAX];

    for (int i = 0; i < MAX; i++) {
      tabla[i] = new Matriz();
    }

    tabla[origen].distanciaOrigen = 0;

    conjuntoTDA nodos = g1.vertices();
    conjuntoTDA AUX = new ConjuntoLD();
    AUX.inicializarConjunto();

    while (!nodos.conjuntoVacio()) {
      int nodo = nodos.elegir();
      AUX.agregar(nodo);
      nodos.sacar(nodo);
    }

    while (!AUX.conjuntoVacio()) {
      int u = nodoMinimo(tabla, visitado, AUX);

      if (u == -1) break;

      visitado[u] = true;

      conjuntoTDA vecinos = g1.vecinos(u);

      while (!vecinos.conjuntoVacio()) {
        int v = vecinos.elegir();
        vecinos.sacar(v);

        int peso = g1.pesoArista(u, v);

        if (!visitado[v] && tabla[u].distanciaOrigen != INF &&
                tabla[u].distanciaOrigen + peso < tabla[v].distanciaOrigen) {
          tabla[v].distanciaOrigen = tabla[u].distanciaOrigen + peso;
          tabla[v].nodoAnterior = u;
        }
      }

      AUX.sacar(u); // ¡Importante! Sacar el nodo que ya fue visitado
    }

    for (int i = 0; i < MAX; i++) {
      if (tabla[i].distanciaOrigen != INF) {
        System.out.println("Nodo: " + i +
                ", Distancia: " + tabla[i].distanciaOrigen +
                ", Anterior: " + tabla[i].nodoAnterior);
      }
    }
  }

  public static int nodoMinimo(Matriz[] tabla, boolean[] visitado, conjuntoTDA nodos) {
    int minNodo = -1;
    int minDist = INF;
    conjuntoTDA aux = new ConjuntoLD();
    aux.inicializarConjunto();

    while (!nodos.conjuntoVacio()) {
      int n = nodos.elegir();
      nodos.sacar(n);
      aux.agregar(n);

      if (!visitado[n] && tabla[n].distanciaOrigen < minDist) {
        minDist = tabla[n].distanciaOrigen;
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
/* 
     while(!AUX.conjuntoVacio()) {
      nodo = AUX.elegir();
      nodos.agregar(nodo);
      AUX.sacar(nodo);
    }

    
    vecinos = g1.vecinos(1);
    while(!vecinos.conjuntoVacio()) {
      vecino = vecinos.elegir();
      vecinos.sacar(vecino);
    }
   

    while (!AUX.conjuntoVacio()) {
      nodo = AUX.elegir();

      vecinos = g1.vecinos(nodo);
      while(!vecinos.conjuntoVacio()) {

        vecino = vecinos.elegir();

        peso = g1.pesoArista(nodo, vecino);

        Matriz m1= new Matriz();
        
        m1.distanciaOrigen = peso;
        m1.nodoAnterior = nodo;
        
 
        
        System.out.println(m1.distanciaOrigen);
        System.out.println(m1.nodoAnterior);

        
        
        vecinos.sacar(vecino);
      }
      AUX.sacar(nodo);
    }
}}
     /* 
      vecinos = g1.vecinos(vertice);
     
     conjuntoTDA vecino = new ConjuntoLD();
      
      
      for (int i=0 ;i<2 ;i++) {

        vecino = g1.vecinos(nodos.elegir());
        System.out.println(vecino.elegir());
        vecino.sacar(vecino.elegir());
        
      }
      

}}

        /*for (int i=0; i<100 ; i++) {
  
          vertices = g1.vertices();
  
          try {
          vertice = vertices.elegir();
  
          } catch (NullPointerException e) {
            break;
          }
  
          if (vertice != 0) { 
            nodos.agregar(vertice);
            System.out.println(vertice);
            
          } else {
            break;
          } 
        }*/