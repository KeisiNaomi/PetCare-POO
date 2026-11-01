package modelo.servicios;

public enum Complejidad {
    ALTA(1500),
    MEDIA(1000),
    BAJA(500);
    private final double tarifaHora;

    Complejidad(double tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }
}