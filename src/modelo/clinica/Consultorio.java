package modelo.clinica;

public class Consultorio {
    private int numero;

    public Consultorio(int numero) {
        setNumero(numero);
    }

    public void setNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("Indique el número del consultorio >:c");
        }
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Consultorio " + numero;
    }
}
