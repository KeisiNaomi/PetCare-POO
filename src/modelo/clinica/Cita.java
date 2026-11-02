package modelo.clinica;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import modelo.mascotas.Mascota;
import modelo.personas.Veterinario;
import modelo.servicios.Servicio;

public class Cita {
    private static int contadorCitas = 0;
    private final int idCita;
    private LocalDateTime fechaHora;
    private EstadoCita estado;
    private Mascota mascota;
    private Veterinario veterinario;
    private Consultorio consultorio;
    private Comprobante comprobante;
    private ArrayList<Servicio> servicios;

    public Cita(LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio) {
        this.idCita = ++contadorCitas;
        if (fechaHora == null) {
            throw new IllegalArgumentException("Indique la fecha y hora de la cita >:c");
        }
        if (mascota == null) {
            throw new IllegalArgumentException("Indique la mascota a atender >:c");
        }
        if (veterinario == null) {
            throw new IllegalArgumentException("Indique el veterinario a cargo >:c");
        }
        if (consultorio == null) {
            throw new IllegalArgumentException("Indique el consultorio >:c");
        }
        this.fechaHora = fechaHora;
        this.estado = EstadoCita.PROGRAMADA;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.consultorio = consultorio;
        this.servicios = new ArrayList<>();
    }

    public int getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFechaFormateada() {
        DateTimeFormatter fechaFormateada = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
        return fechaHora.format(fechaFormateada);
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void atenderCita() {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalArgumentException("Solo se pueden atender citas programadas >:c");
        }
        this.estado = EstadoCita.ATENDIDA;
    }

    public void cancelarCita() {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalArgumentException("Solo se pueden cancelar citas programadas >:c");
        }
        this.estado = EstadoCita.CANCELADA;
    }

    public void agregarServicios(Servicio servicio) {
        if (estado != EstadoCita.PROGRAMADA) {
            throw new IllegalStateException("Solo puedes agregar servicios a citas programadas >:c");
        }
        if (servicio == null) {
            throw new IllegalArgumentException("Asigne uno o más servicios a la cita >:c");
        }
        servicios.add(servicio);
    }

    public String getNombresServicios() {
        if (servicios.isEmpty()) {
            return "Sin servicios asignados";
        }

        StringBuilder nombres = new StringBuilder();
        for (Servicio servicio : servicios) {
            if (nombres.length() > 0) {
                nombres.append(", ");
            }
            nombres.append(servicio.getNombre());
        }
        return nombres.toString();
    }

    public double calcularTotal() {
        double total = 0;
        for (Servicio servicio : servicios) {
            total += servicio.calcularCosto();
        }
        return total;
    }

    public void generarComprobante() {
        double total = calcularTotal();
        comprobante = new Comprobante(total, this);
    }

    @Override
    public String toString() {
        return "Cita #" + idCita +
            "\nFecha: " + getFechaFormateada() +
            "\nEstado: " + estado +
            "\nMascota: " + mascota +
            "\nVeterinario: " + veterinario +
            "\nConsultorio: " + consultorio +
            "\nServicios solicitados: " + getNombresServicios();
    }
}