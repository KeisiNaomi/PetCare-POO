package modelo.clinica;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private LocalDateTime fechaHora;
    private String diagnostico;
    private String tratamiento;

    public Consulta(LocalDateTime fechaHora, String diagnostico, String tratamiento) {
        if (fechaHora == null) {
            throw new IllegalArgumentException("Indique la fecha y hora de la consulta >:c");
        }
        if (diagnostico == null || diagnostico.isBlank()) {
            throw new IllegalArgumentException("El diagnóstico no puede ser nulo ni estar vacío >:c");
        }
        if (tratamiento == null || tratamiento.isBlank()) {
            throw new IllegalArgumentException("El tratamiento no puede ser nulo ni estar vacío >:c");
        }
        this.fechaHora = fechaHora;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFechaFormateada() {
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return fechaHora.format(fechaFormateada);
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    @Override
    public String toString() {
        return "Fecha:" + getFechaFormateada() +
            "\nDiagnostico: " + diagnostico +
            "\nTratamiento: " + tratamiento;
    }
}
