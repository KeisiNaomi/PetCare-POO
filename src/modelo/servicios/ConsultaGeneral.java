package modelo.servicios;

public class ConsultaGeneral extends Servicio {
    private double tarifaBase;

    public ConsultaGeneral(double tarifaBase) {
        super("Consulta General", "Evaluación del estado de salud, diagnóstico y recomendaciones médicas");
        this.tarifaBase = tarifaBase;
    }

    @Override
    public double calcularCosto() {
        return tarifaBase;
    }
}