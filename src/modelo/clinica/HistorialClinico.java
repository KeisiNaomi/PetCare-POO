package modelo.clinica;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class HistorialClinico {
    private ArrayList<Consulta> consultas;

    public HistorialClinico() {
        this.consultas = new ArrayList<>();
    }

    public void agregarConsulta(Consulta consulta) {
        if (consulta == null) {
            throw new IllegalArgumentException("La consulta no puede estar vacía >:c");
        }
        this.consultas.add(consulta);
    }

    public ArrayList<Consulta> getConsultas() {
        return consultas;
    }

    public void mostrarHistorial() {
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }

    public void mostrarHistorial(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        for (Consulta consulta : consultas) {
            LocalDateTime fechaHora = consulta.getFechaHora();
            if (!fechaHora.isBefore(fechaInicio) && !fechaHora.isAfter(fechaFin)) {
                System.out.println(consulta);
            }
        }
    }

    @Override
    public String toString() {
        if (consultas.isEmpty()) {
            return "Esta mascota todavía no tiene consultas registradas :c";
        }
        StringBuilder historial = new StringBuilder();
        for (Consulta consulta : consultas) {
            historial.append(consulta).append("\n");
        }
        return historial.toString();
    }
}
