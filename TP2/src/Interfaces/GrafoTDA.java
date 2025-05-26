package interfaces;

public interface GrafoTDA {
    void inicializarGrafo();
    void agregarVertice(int v);
    void eliminarVertice(int v);
    conjuntoTDA vertices();
    void agregarArista(int origen, int destino, int peso);
    void eliminarArista (int origen, int destino);
    boolean existeArista(int origen, int destino);
    int pesoArista(int origen, int destino);
    conjuntoTDA vecinos(int v);

}
