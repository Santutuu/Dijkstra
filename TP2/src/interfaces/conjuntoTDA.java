package interfaces;

public interface conjuntoTDA {

    void inicializarConjunto(); 
    void agregar(int x);
    void sacar(int x); // Remueve el valor especificado
    int elegir(); // Elige un valor azarosamente
    boolean pertenece(int x); // Dado un valor, retorna si pertenece o no al conjunto
    boolean conjuntoVacio(); // Retorna si el conjunto esta vacio
}



