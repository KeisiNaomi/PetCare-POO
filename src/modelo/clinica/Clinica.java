package modelo.clinica;
import java.util.Iterator;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Objects;
import modelo.personas.Veterinario;
import modelo.mascotas.Mascota;
import modelo.personas.Dueño;

public class Clinica {
    private String nombre;
    private ArrayList<Consultorio> consultorios;
    private ArrayList<Veterinario> veterinarios;
    private ArrayList<Cita> citas;
    private ArrayList<Mascota> mascotas;

    public Clinica (String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Especifíque el nombre de la clínica");
        }
        this.nombre = nombre.strip();
    }

    public void crearConsultorio(int numero) {
        for (Consultorio consultorio : consultorios) {
            if (consultorio.getNumero() == numero) {
                throw new IllegalArgumentException("Ya existe un consultorio con ese número >:c");
            }
        }
        Consultorio nuevo = new Consultorio(numero);
        consultorios.add(nuevo);
    }

    public void agregarVeterinario(Veterinario veterinario) {
        if (veterinario == null) {
            throw new IllegalArgumentException("Asigne un nuevo veterinario >:c");
        }
        veterinarios.add(veterinario);
    }

    public void agregarMascota(Mascota mascota) {
        if (mascota == null) {
            throw new IllegalArgumentException("Asigne una nueva mascota >:c");
        }
        mascotas.add(mascota);
    }

    private void cancelarCitasPendientes(Mascota mascota) {
        for (Cita cita : citas) {
            if (cita.getMascota() == mascota && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.cancelarCita();
            }
        }
    }

    public boolean darBajaMascota(String nombre, Dueño dueño) {
        Iterator<Mascota> iterator = mascotas.iterator();
        while (iterator.hasNext()) {
            Mascota mascota = iterator.next();
            if (mascota.getNombre().equalsIgnoreCase(nombre) && mascota.getDueño() == dueño) {
                cancelarCitasPendientes(mascota);
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public Cita registrarCita(LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Consultorio consultorio) {
        for (Cita cita : citas) {
            if (cita.getEstado() == EstadoCita.PROGRAMADA && Objects.equals(cita.getFechaHora(), fechaHora)) {
                if (cita.getVeterinario() == veterinario) {
                    throw new IllegalArgumentException("El veterinario ya tiene una cita en ese horario :/");
                }
                if (cita.getConsultorio() == consultorio) {
                    throw new IllegalArgumentException("El consultorio ya está ocupado en ese horario :/");
                }
            }
        }
        Cita nuevaCita = new Cita(fechaHora, mascota, veterinario, consultorio);
        citas.add(nuevaCita);
        return nuevaCita;
    }

    public Cita buscarCita(int idCita) {
        if (idCita <= 0) {
            throw new IllegalArgumentException("Indique un ID de cita válido >:c");
        }
        for (Cita cita : citas) {
            if (cita.getIdCita() == idCita) {
                return cita;
            }
        }
        return null;
    }

    public void cancelarCita(int idCita) {
        Cita cita = buscarCita(idCita);
        if (cita == null) {
            throw new IllegalArgumentException("No se encontró una cita con ese ID :c");
        }
        cita.cancelarCita();
    }

    public void atenderCita(int idCita) {
        Cita cita = buscarCita(idCita);
        if (cita == null) {
            throw new IllegalArgumentException("No se encontró una cita con ese ID :c");
        }
        cita.atenderCita();
    }

    public void registrarConsulta(int idCita, String diagnostico, String tratamiento) {
        
    }
}
