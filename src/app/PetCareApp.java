package app;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import modelo.clinica.Clinica;
import modelo.clinica.Cita;
import modelo.clinica.Consultorio;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.mascotas.Gato;
import modelo.mascotas.EsDomestico;
import modelo.mascotas.AveExotica;
import modelo.personas.Dueño;
import modelo.personas.Veterinario;

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
}