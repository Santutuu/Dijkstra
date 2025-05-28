package TP2;

import Implementaciones.GrafoMA;
import Interfaces.GrafoTDA;

public class grafo {

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
    grafo.agregarArista(2, 4, 5);
    grafo.agregarArista(3, 4, 8);
    grafo.agregarArista(4, 5, 10);
    grafo.agregarArista(4, 6, 15);
    grafo.agregarArista(5, 6, 6);
    grafo.agregarArista(6, 7, 6);
    grafo.agregarArista(5, 7, 2);

    return grafo;
  }
    
}
