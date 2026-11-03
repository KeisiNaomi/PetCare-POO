package app;
import java.util.*;
import java.time.*;
import java.time.format.*;
import modelo.clinica.*;
import modelo.mascotas.*;
import modelo.personas.*;
import modelo.servicios.*;

public class PetCareApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Clinica clinica = new Clinica("PetCare");
        clinica.crearConsultorio(1);
        clinica.crearConsultorio(2);
        clinica.crearConsultorio(3);

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarDueñoYMascota(scanner, clinica);
                case 2 -> registrarVeterinario(scanner, clinica);
                case 3 -> agendarCita(scanner, clinica);
                case 4 -> gestionarCita(scanner, clinica);
                case 5 -> generarComprobante(scanner, clinica);
                case 6 -> consultarHistorialClinico(scanner, clinica);
                case 10 -> {
                    System.out.println("Saliendo.... :c");
                    System.out.println("Hasta luego :3");
                }
                default -> System.out.println("Opción inválida, intente de nuevo :x");
            }
        } while (opcion != 10);
    }

    public static void mostrarMenu() {
        System.out.println("\n==================== Veterinaria PetCare ====================");
        System.out.println("1. Registrar dueño y mascota (perro, gato o ave)");
        System.out.println("2. Registrar veterinario y asignarlo a la clínica");
        System.out.println("3. Agendar una cita");
        System.out.println("4. Atender o cancelar una cita");
        System.out.println("5. Generar el comprobante de una cita atendida");
        System.out.println("6. Consultar el historial clínico de una mascota");
        System.out.println("7. Listar a todas las mascotas y su sonido");
        System.out.println("8. Ver el total cobrado por veterinario");
        System.out.println("9. Dar de baja a una mascota");
        System.out.println("10. Salir");
    }

    public static String leerTexto(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().strip();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede estar vacío >:P");
        }
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
    while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().strip());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido >:P");
            }
        }
    }

    public static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            int numero = leerEntero(scanner, mensaje);
            if (numero > 0) {
                return numero;
            }
            System.out.println("Ingrese un número mayor que 0 >:P");
        }
    }

    public static double leerDouble(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double numero = Double.parseDouble(scanner.nextLine().strip());
                if (Double.isFinite(numero) && numero > 0) {
                    return numero;
                }
                System.out.println("Ingrese un número mayor a 0 >:P");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número decimal válido >:P");
            }
        }
    }

    public static void registrarDueñoYMascota(Scanner scanner, Clinica clinica) {
        System.out.println("\n--------------- Registrar dueño ---------------");
        Dueño dueño = obtenerDueño(scanner, clinica);
        if (dueño ==  null) {
            System.out.println("Regresando al menú principal...");
            return;
        }
        registrarMascota(scanner, clinica, dueño);
    }

    public static Dueño obtenerDueño(Scanner scanner, Clinica clinica) {
        System.out.println("¿El dueño ya está registrado?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        return switch (opcion) {
            case 1 -> buscarDueño(scanner, clinica);
            case 2 -> registrarNuevoDueño(scanner, clinica);
            default -> {
                System.out.println("Opción no válida >:P");
                yield null;
            }
        };
    }

    public static Dueño buscarDueño(Scanner scanner, Clinica clinica) {
        System.out.println("-----------------------------------------------");
        int id = leerEnteroPositivo(scanner, "ID del dueño: ");
        Dueño dueño = clinica.buscarDueño(id);
        if (dueño == null) {
            System.out.println("No se encontró al dueño :c");
        }
        return dueño;
    }

    public static Dueño registrarNuevoDueño(Scanner scanner, Clinica clinica) {
        System.out.println("-----------------------------------------------");
        System.out.println("Indique los datos del dueño:");
        String nombre = leerTexto(scanner, "Nombre: ");
        String apellido = leerTexto(scanner, "Apellido: ");
        String telefono = leerTexto(scanner, "Teléfono: ");
        String correo = leerTexto(scanner, "Correo: ");
        String direccion = leerTexto(scanner, "Dirección: ");
        try {
            Dueño dueño = new Dueño(nombre, apellido, telefono, correo, direccion);
            clinica.registrarDueño(dueño);
            System.out.println("¡Dueño registrado correctamente! :D");
            System.out.println("ID asignado: " + dueño.getIdDueño());
            return dueño;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Mascota registrarMascota(Scanner scanner, Clinica clinica, Dueño dueño) {
        System.out.println("-------------- Registrar mascota --------------");
        System.out.println("Ingrese los datos básicos de la mascota:");
        String nombre = leerTexto(scanner, "Nombre: ");
        int edad = leerEnteroPositivo(scanner, "Edad: ");
        double peso = leerDouble(scanner, "Peso (kg): ");
        Mascota mascota = tipoMascota(scanner, nombre, edad, peso, dueño);
        if (mascota == null) {
            System.out.println("No se pudo registrar la mascota :c");
            return null;
        }
        try {
            clinica.registrarMascota(mascota);
            System.out.println("¡Mascota registrada correctamente! :D");
            return mascota;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public static Mascota tipoMascota(Scanner scanner, String nombre, int edad, double peso, Dueño dueño) {
        System.out.println("-----------------------------------------------");
        System.out.println("Seleccione el tipo de mascota:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.println("3. Ave");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        return switch (opcion) {
            case 1 -> crearPerro(scanner, nombre, edad, peso, dueño);
            case 2 -> crearGato(scanner, nombre, edad, peso, dueño);
            case 3 -> crearAve(scanner, nombre, edad, peso,dueño);
            default -> {
                System.out.println("Opción no válida >:P");
                yield null;
            }
        };
    }

    public static Mascota crearPerro(Scanner scanner, String nombre, int edad, double peso, Dueño dueño) {
        System.out.println("-----------------------------------------------");
        System.out.println("Indique el resto de datos de la mascota:");
        String raza = leerTexto(scanner, "Raza: ");
        int nivelEnergia = leerEnteroPositivo(scanner, "Nivel de energía: ");
        try {
            return new Perro(nombre, edad, peso, dueño, raza, nivelEnergia);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Mascota crearGato(Scanner scanner, String nombre, int edad, double peso, Dueño dueño) {
        System.out.println("-----------------------------------------------");
        System.out.println("¿El gato es doméstico?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        EsDomestico esDomestico = switch (opcion) {
            case 1 -> EsDomestico.SI;
            case 2 -> EsDomestico.NO;
            default -> null;
        };
        try {
            return new Gato(nombre, edad, peso, dueño, esDomestico);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static Mascota crearAve(Scanner scanner, String nombre, int edad, double peso, Dueño dueño) {
        System.out.println("-----------------------------------------------");
        System.out.println("Indique el resto de datos de la mascota:");
        double longitudAlas = leerDouble(scanner, "Longitud de las alas: ");
        try {
            return new AveExotica(nombre, edad, peso, dueño, longitudAlas);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void registrarVeterinario(Scanner scanner, Clinica clinica) {
        System.out.println("\n------------ Registrar veterinario ------------");
        String nombre = leerTexto(scanner, "Nombre: ");
        String apellido = leerTexto(scanner, "Apellido: ");
        String telefono = leerTexto(scanner, "Teléfono: ");
        String correo = leerTexto(scanner, "Correo: ");
        String cedula = leerTexto(scanner, "Cédula profesional: ");
        String especialidad = leerTexto(scanner, "Especialidad: ");
        try {
            Veterinario veterinario = new Veterinario(nombre, apellido, telefono, correo, cedula, especialidad);
            clinica.registrarVeterinario(veterinario);
            System.out.println("¡Veterinario registrado correctamente! :D");
            System.out.println("ID asignado: " + veterinario.getIdVeterinario());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    public static void agendarCita(Scanner scanner, Clinica clinica) {
        System.out.println("\n---------- Agendar cita ----------");
        Mascota mascota = obtenerMascota(scanner, clinica);
        if (mascota == null) return;
        Veterinario veterinario = obtenerVeterinario(scanner, clinica);
        if (veterinario == null) return;
        Consultorio consultorio = obtenerConsultorio(scanner, clinica);
        if (consultorio == null) return;
        LocalDateTime fechaHora = leerFechaHora(scanner);
        try {
            Cita cita = clinica.registrarCita(fechaHora, mascota, veterinario, consultorio);
            seleccionarServicios(scanner, cita);
            System.out.println("¡Cita agendada correctamente! :D");
            System.out.println("ID asignado: " + cita.getIdCita());
            System.out.println("Total estimado: $" + cita.calcularTotal());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Mascota obtenerMascota(Scanner scanner, Clinica clinica) {
        int id = leerEnteroPositivo(scanner, "ID del dueño: ");
        Dueño dueño = clinica.buscarDueño(id);
        if (dueño == null) {
            System.out.println("No se encontró al dueño :c");
            return null;
        }
        String nombre = leerTexto(scanner, "Nombre de la mascota: ");
        Mascota mascota = clinica.buscarMascota(nombre, dueño);
        if (mascota == null) {
            System.out.println("No se encontró a la mascota :c");
        }
        return mascota;
    }

    private static Veterinario obtenerVeterinario(Scanner scanner, Clinica clinica) {
        int id = leerEnteroPositivo(scanner, "ID del veterinario: ");
        Veterinario veterinario = clinica.buscarVeterinario(id);
        if (veterinario == null) {
            System.out.println("No se encontró al veterinario :c");
        }
        return veterinario;
    }

    private static Consultorio obtenerConsultorio(Scanner scanner, Clinica clinica) {
        int numero = leerEnteroPositivo(scanner, "Número de consultorio: ");
        Consultorio consultorio = clinica.buscarConsultorio(numero);
        if (consultorio == null) {
            System.out.println("No existe ese consultorio :c");
        }
        return consultorio;
    }

    private static LocalDateTime leerFechaHora(Scanner scanner) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu hh:mm a", Locale.US).withResolverStyle(ResolverStyle.STRICT);
        while (true) {
            String texto = leerTexto(scanner, "Fecha y hora (dd/MM/yyyy hh:mm AM/PM): ");
            try {
                return LocalDateTime.parse(texto.toUpperCase(), formato);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha u hora inválida >:P");
            }
        }
    }

    private static void seleccionarServicios(Scanner scanner, Cita cita) {
        int opcion;
        do {
            System.out.println("\n---------- Servicios ----------");
            System.out.println("1. Consulta general");
            System.out.println("2. Vacunación");
            System.out.println("3. Cirugía");
            System.out.println("4. Terminar");
            opcion = leerEntero(scanner, "Seleccione una opción: ");
            switch (opcion) {
                case 1 -> agregarConsultaGeneral(scanner, cita);
                case 2 -> agregarVacunacion(scanner, cita);
                case 3 -> agregarCirugia(scanner, cita);
                case 4 -> {
                    if (cita.getServicios().isEmpty()) {
                        System.out.println("Agregue al menos un servicio >:P");
                        opcion = 0;
                    }
                }
                default -> System.out.println("Opción inválida >:P");
            }
        } while (opcion != 4);
    }

    private static void agregarConsultaGeneral(Scanner scanner, Cita cita) {
        double tarifa = leerDouble(scanner, "Tarifa de consulta: $");
        try {
            cita.agregarServicios(new ConsultaGeneral(tarifa));
            System.out.println("¡Consulta general agregada! :D");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarVacunacion(Scanner scanner, Cita cita) {
        double tarifa = leerDouble(scanner, "Tarifa de vacunación: $");
        double costo = leerDouble(scanner, "Costo de la vacuna: $");
        try {
            cita.agregarServicios(new Vacunacion(tarifa, costo));
            System.out.println("¡Vacunación agregada! :D");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void agregarCirugia(Scanner scanner, Cita cita) {
        System.out.println("Seleccione la complejidad:");
        System.out.println("1. Baja");
        System.out.println("2. Media");
        System.out.println("3. Alta");
        int opcion = leerEntero(scanner, "Opción: ");
        Complejidad complejidad = switch (opcion) {
            case 1 -> Complejidad.BAJA;
            case 2 -> Complejidad.MEDIA;
            case 3 -> Complejidad.ALTA;
            default -> null;
        };
        if (complejidad == null) {
            System.out.println("Complejidad inválida >:P");
            return;
        }
        double horas = leerDouble(scanner, "Horas de quirófano: ");
        try {
            cita.agregarServicios(new Cirugia(complejidad, horas));
            System.out.println("¡Cirugía agregada! :D");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void gestionarCita(Scanner scanner, Clinica clinica) {
        System.out.println("\n---------- Gestionar cita ----------");
        int id = leerEnteroPositivo(scanner, "ID de la cita: ");
        Cita cita = clinica.buscarCita(id);
        if (cita == null) {
            System.out.println("No se encontró la cita :c");
            return;
        }
        System.out.println("1. Atender cita");
        System.out.println("2. Cancelar cita");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        switch (opcion) {
            case 1 -> atenderCita(scanner, cita);
            case 2 -> cancelarCita(cita);
            default -> System.out.println("Opción inválida >:P");
        }
    }

    public static void atenderCita(Scanner scanner, Cita cita) {
        if (cita.getEstado() != EstadoCita.PROGRAMADA) {
            System.out.println("La cita no está programada >:P");
            return;
        }
        String diagnostico = leerTexto(scanner, "Diagnóstico: ");
        String tratamiento = leerTexto(scanner, "Tratamiento: ");
        try {
            Consulta consulta = new Consulta(LocalDateTime.now(), diagnostico, tratamiento);
            cita.atenderCita();
            cita.getMascota().getHistorialClinico().agregarConsulta(consulta);
            System.out.println("¡Cita atendida correctamente! :D");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void cancelarCita(Cita cita) {
        try {
            cita.cancelarCita();
            System.out.println("¡Cita cancelada correctamente! :D");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void generarComprobante(Scanner scanner, Clinica clinica) {
        System.out.println("\n---------- Generar comprobante ----------");
        int id = leerEnteroPositivo(scanner, "ID de la cita: ");
        Cita cita = clinica.buscarCita(id);
        if (cita == null) {
            System.out.println("No se encontró la cita >:P");
            return;
        }
        try {
            Comprobante comprobante = cita.generarComprobante();
            System.out.println("¡Comprobante generado correctamente! :D");
            System.out.println(comprobante);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void consultarHistorialClinico(Scanner scanner, Clinica clinica) {
        System.out.println("\n---------- Historial clínico ----------");
        Mascota mascota = obtenerMascota(scanner, clinica);
        if (mascota == null) {
            System.out.println("Regresando al menú principal...");
            return;
        }
        System.out.println("\nMascota: " + mascota.getNombre());
        System.out.println("---------- Consultas registradas ----------");
        System.out.println(mascota.getHistorialClinico());
    }
}