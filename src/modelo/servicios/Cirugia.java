package modelo.servicios;

public class Cirugia extends Servivio {
    private Complejidad complejidad;
    private double horasQuirofano;

    public Cirugia(Complejidad complejidad, double horasQuirofano) {
        if (complejidad == null) {
            throw new IllegalArgumentException("Especifíque la complejidad de la cirugía >:c");
        }
        if (horasQuirofano < 0) {
            throw new IllegalArgumentException("Las horas de quirófano no pueden ser menores a 0 >:c");
        }
        this.complejidad = complejidad;
        this.horasQuirofano = horasQuirofano;
    }

    @Override
    public double calcularCosto() {
        return complejidad.getTarifaHora() * horasQuirofano;
    }
}