package modelo.personas;

public class Recepcionista extends Persona {
    private Turno turno;

    public Recepcionista(String nombre, String apellido, String telefono, String correo, Turno turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
    }

    public void setTurno(Turno turno) {
        if (turno == null) {
            throw new IllegalArgumentException("Especifíque el turno del recepcionista >:c");
        }
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return super.toString() +
            "\nTurno: " + turno.name();
    }
}
