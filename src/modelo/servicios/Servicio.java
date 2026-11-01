package modelo.servicios;

public abstract class Servicio implements Facturable {
    private String nombre;
    private String descripcion;

    public Servicio(String nombre, String descripcion) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío >:c");
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía >:c");
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public abstract double calcularCosto();
}