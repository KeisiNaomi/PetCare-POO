package modelo.servicios;

public class Vacunacion extends Servicio {
    private double tarifaVacunacion;
    private double costoVacuna;

    public Vacunacion(double tarifaVacunacion, double costoVacuna) {
        super("Vacunación", "Aplicación de vacunas para la prevención de enfermedades");
        
        if (tarifaVacunacion < 0) {
            throw new IllegalArgumentException("La tarifa de vacunación no puede ser negativa");
        }
        if (costoVacuna < 0) {
            throw new IllegalArgumentException("El costo de la vacuna no puede ser negativo");
        }
        this.tarifaVacunacion = tarifaVacunacion;
        this.costoVacuna = costoVacuna;
    }

    public double getTarifaVacunacion() {
        return tarifaVacunacion;
    }

    public double getCostoVacuna() {
        return costoVacuna;
    }

    @Override
    public double calcularCosto() {
        return tarifaVacunacion + costoVacuna;
    }
}