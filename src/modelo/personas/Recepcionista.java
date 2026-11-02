package modelo.personas;

public class Recepcionista extends Persona {
    private static int contadorRecepcionista = 0;
    private final int idRecepcionista;
    private Turno turno;

    public Recepcionista(String nombre, String apellido, String telefono, String correo, Turno turno) {
        super(nombre, apellido, telefono, correo);
        setTurno(turno);
        this.idRecepcionista = ++contadorRecepcionista;
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

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    @Override
    public String toString() {
        return super.toString() +
            "\nTurno: " + turno.name();
    }
}
