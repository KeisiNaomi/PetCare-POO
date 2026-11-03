package modelo.clinica;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Comprobante {
    private static int contadorComprobantes = 0;
    private final int folio;
    private LocalDateTime fechaEmision;
    private double costoTotal;
    private Cita cita;

    public Comprobante(double costoTotal, Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula >:c");
        }
        this.cita = cita;
        this.costoTotal = costoTotal;
        this.fechaEmision = LocalDateTime.now();
        this.folio = ++contadorComprobantes;
    }

    public String getFechaFormateada() {
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy, hh:mm a", Locale.forLanguageTag("es-Mx"));
        return fechaEmision.format(fechaFormateada);
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    @Override
    public String toString() {
        return "Folio #" + folio +
            "\nFecha de emisión:\n" + getFechaFormateada() +
            "\nID Cita:\n" + cita.getIdCita() +
            "\nTotal: " + costoTotal;
    }
}
