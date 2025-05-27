package implementaciones;

import interfaces.conjuntoTDA;

public class ConjuntoLD implements conjuntoTDA {

    private static class Nodo {
        int valor;
        Nodo sig;
    }

    private Nodo origen;

    public void inicializarConjunto() {
        origen = null;
    }

    public void agregar(int x) {
        if (!pertenece(x)) {
            Nodo nuevo = new Nodo();
            nuevo.valor = x;
            nuevo.sig = origen;
            origen = nuevo;
        }
    }

    public void sacar(int x) {
        if (origen != null) {
            if (origen.valor == x) {
                origen = origen.sig;
            } else {
                Nodo turista = origen;
                while (turista.sig != null && turista.sig.valor != x) {
                    turista = turista.sig;
                }
                if (turista.sig != null) {
                    turista.sig = turista.sig.sig;
                }
            }
        }
    }

    public int elegir() {
        return origen.valor;
    }

    public boolean pertenece(int x) {
        Nodo turista = origen;
        while (turista != null && turista.valor != x) {
            turista = turista.sig;
        }
        return turista != null;
    }

    public boolean conjuntoVacio() {
        return origen == null;
    }
}


