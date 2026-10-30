package modelo.personas;

public abstract class Persona {
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    public Persona(String nombre, String apellido, String telefono, String correo) {
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
        setCorreo(correo);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio >:c");
        }
        this.nombre = nombre.strip();
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacio >:c");
        }
        this.apellido = apellido.strip();
    }

    public void setTelefono(String telefono) {
        if (telefono == null) {
            throw new IllegalArgumentException("El telefono no puede ser nulo >:c");
        }
        telefono = telefono.strip();

        if (telefono.isBlank() || !telefono.matches("\\d{10}")) {
            throw new IllegalArgumentException("El telefono debe contener solamente dígitos >:c");
        }
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        if (correo == null) {
            throw new IllegalArgumentException("El correo no puede ser nulo >:c");
        }
        correo = correo.strip();

        if (!correo.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("El correo no es valido >:c");
        }
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
            "\nApellido: " + apellido +
            "\nTeléfono: " + telefono +
            "\nCorreo: " + correo;
    }
}