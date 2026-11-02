package app;
import java.util.Scanner;
import modelo.clinica.Clinica;
import modelo.mascotas.Mascota;
import modelo.mascotas.Perro;
import modelo.personas.Dueño;

public class PetCareApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Clinica clinica = new Clinica("PetCare");

        do {
            mostrarMenu();
            opcion = leerEntero(scanner, "Seleccione una opción: ");

            switch (opcion) {
                case 1 -> registrarDueñoYMascota(scanner, clinica);
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
                int numero = Integer.parseInt(scanner.nextLine().strip());
                if (numero > 0) {
                    return numero;
                }
                System.out.println("Ingrese un múmero mayor a 0 >:P");
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido >:P");
            }
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
        Mascota mascota = registrarMascota(scanner, clinica, dueño);
    }

    public static Dueño obtenerDueño(Scanner scanner, Clinica clinica) {
        System.out.println("¿El dueño ya está registrado?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        switch (opcion) {
            case 1 -> {
                System.out.println("-----------------------------------------------");
                int id = leerEntero(scanner, "ID del dueño: ");
                if (id <= 0) {
                    System.out.println("El ID deber ser mayor a 0 >:P");
                    return null;
                }
                Dueño dueño = clinica.buscarDueño(id);
                if (dueño == null) {
                    System.out.println("No se encontró al dueño :c");
                }
                return dueño;
            }
            case 2 -> {
                return registrarNuevoDueño(scanner, clinica);
            }
            default -> {
                System.out.println("Opción no válida >:P");
                return null;
            }
        }
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
        int edad = leerEntero(scanner, "Edad: ");
        double peso = leerDouble(scanner, "Peso (kg): ");
        System.out.println("-----------------------------------------------");
        System.out.println("Seleccione el tipo de mascota:");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.println("3. Ave");
        int opcion = leerEntero(scanner, "Seleccione una opción: ");
        Mascota mascota = null;
        System.out.println("-----------------------------------------------");
        switch (opcion) {
            case 1 -> {}
            case 2 -> {}
            case 3 -> {}
            default -> {}
        }
    }
}