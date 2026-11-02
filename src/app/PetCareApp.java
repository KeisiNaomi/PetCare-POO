package app;
import java.util.Scanner;
import modelo.clinica.Clinica;
import modelo.personas.Dueño;

public class PetCareApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
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
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n---------- Registrar dueño y mascota ----------");
                    System.out.println("¿El dueño ya está registrado?");
                    System.out.println("1. Sí");
                    System.out.println("2. No");
                    System.out.print("Seleccione una opción: ");
                    int opcionDueño = scanner.nextInt();
                    Dueño dueño = null;
                    if (opcionDueño == 1) {
                        clinica.buscarDueño(idDueño);
                    } else if (opcionDueño == 2) {

                    } else {

                    }
                }
                case 10 -> {
                    System.out.println("Saliendo.... :c");
                    System.out.println("Hasta luego :3");
                }
                default -> System.out.println("Opción inválida, intente de nuevo :x");
            }
        } while (opcion != 10);
    }
}
