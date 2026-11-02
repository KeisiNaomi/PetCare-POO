package modelo.clinica;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comprobante {
    private static int contadorComprobantes = 0;
    private final int folio;
    private LocalDateTime fechaEmision;
    private double costoTotal;
    private Cita cita;

    public Comprobante(double costoTotal, Cita cita) {
        this.folio = ++contadorComprobantes;
        this.fechaEmision = LocalDateTime.now();
    }

    public String getFechaFormateada() {
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, hh:mm a");
        return fechaEmision.format(fechaFormateada);
    }

    @Override
    public String toString() {
        return "Folio #" + folio +
            "\nFecha de emisión:\n" + getFechaFormateada() +
            "\nTotal: " + costoTotal;
    }
}
