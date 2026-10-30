package modelo.personas;

public class Veterinario extends Persona {
    private String cedulaProfesional;
    private String especialidad;
    
    public Veterinario(String nombre, String apellido, String telefono, String correo, String cedulaProfesonal, String especialidad) {
        super(nombre, apellido, telefono, correo);
        setCedulaProfesional(cedulaProfesonal);
        setEspecialidad(especialidad);
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        if (cedulaProfesional == null) {
            throw new IllegalArgumentException("La cédula no puede ser nula >:c");
        }
        cedulaProfesional = cedulaProfesional.strip();

        if (cedulaProfesional.isBlank() || !cedulaProfesional.matches("\\d+")) {
            throw new IllegalArgumentException("La cédula debe contener solamente dígitos >:c");
        }
        this.cedulaProfesional = cedulaProfesional;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.isBlank()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía >:c");
        }
        this.especialidad = especialidad.strip();
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public String toString() {
        return super.toString() +
            "\nCedula Profesional: " + cedulaProfesional +
            "\nEspecialidad: " + especialidad;
    }
}
