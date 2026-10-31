package modelo.personas;

public class Dueño extends Persona {
    private String direccion;
    
    public Dueño(String nombre, String apellido, String telefono, String correo, String direccion) {
        super(nombre, apellido, telefono, correo);
        setDireccion(direccion);
    }
    
    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede estar vacía >:c");
        }
        this.direccion = direccion.strip();
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return super.toString() +
            "\nDireccion: " + direccion;
    }
}
