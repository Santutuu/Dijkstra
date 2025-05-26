package implementaciones;
import interfaces.GrafoTDA;
import interfaces.conjuntoTDA;
import implementaciones.ConjuntoLD;



public class GrafoMA implements GrafoTDA {
    
    
    int [][] mAdy;
    int [] etiquetas; 
    // Almacena las etiquetas o identificadores de los vertices agregados al grafo
    // se trata como un objeto que almacena tipos primitivos int (areglo de enteros)


    // La matriz de adyacencia te dice si hay una arista entre la matriz[i] y[j],
    // Pero te dice que vertices tiene sino su peso.
    int cantidad;
    int mx = 55;
    
    public void inicializarGrafo() {
        mAdy = new int[mx][mx]; // Representa la matriz de adyacencia
        etiquetas = new int[mx]; // Se crea el arreglo con 5 posiciones
        cantidad = 0;
    }

    @Override
    public void agregarVertice (int v) {
        /*
        Se origina un nuevo Vertice sin conexiones(aristas) 
        */

        etiquetas[cantidad] = v;
        for (int i=0; i<= cantidad; i++) {
            mAdy[i][cantidad] = 0;
            mAdy[cantidad][i] = 0;

        }
        cantidad++; 
    }
    

    @Override
    public void eliminarVertice(int v) {
    int inx = vertice2indice(v);

    // Mover la última etiqueta al lugar del vértice a eliminar
    etiquetas[inx] = etiquetas[cantidad - 1];

    // Reemplazar la fila y columna correspondiente en la matriz
    for (int i = 0; i < cantidad; i++) {
        mAdy[inx][i] = mAdy[cantidad - 1][i];
        mAdy[i][inx] = mAdy[i][cantidad - 1];
    }

    cantidad--;
}
   // tiene complejidad lineal porque son dos recorridos lineales
   
      
    
    @Override
    public conjuntoTDA vertices() {
      conjuntoTDA v = new ConjuntoLD();
      v.inicializarConjunto();

      for(int i = 0; i<cantidad; i++) {
         v.agregar(this.etiquetas[i]);
      }

      return v;
   }


    public void agregarArista(int origen, int destino, int peso) {
        /*
        Recibe la etiqueta o identificador de origen, destino y peso, 
        Genera una arista a partir de eso
         */
        int o = vertice2indice(origen); // Retorna el indice del nodo del que se origina la arista
        int d = vertice2indice(destino); // Retorna el indice del nodo destino de la arista
        mAdy[o][d] = peso; // Suma el peso a la matriz de adyacencia
       
    }

    
    public void eliminarArista(int origen, int destino) {
      int o = this.vertice2indice(origen);
      int d = this.vertice2indice(destino);
      this.mAdy[o][d] = 0;
   }

   public boolean existeArista(int origen, int destino) {
      int o = this.vertice2indice(origen);
      int d = this.vertice2indice(destino);
      return this.mAdy[o][d] != 0;
   }

    @Override
    public int pesoArista(int origen, int destino) {
        int o = vertice2indice(origen);
        int d = vertice2indice(destino);
        
        return mAdy[o][d];
    }


    private int vertice2indice(int v) {
      int i;
      for(i = this.cantidad - 1; i >= 0 && this.etiquetas[i] != v; --i) {
      }

      return i;
   }

   public conjuntoTDA vecinos(int v) {
    conjuntoTDA vecinos = new ConjuntoLD();

    vecinos.inicializarConjunto();
    int o = vertice2indice(v);

    for (int i=0; i< cantidad; i++){
        if (mAdy[o][i] != 0) {
            vecinos.agregar(etiquetas[i]);
        }

    }
   
    return vecinos;
}

}