package modelo.mascotas;

import modelo.personas.Dueño;

public abstract class Mascota {
    private String nombre;
    private int edad;
    private double peso;
    private Dueño dueño;
    // private HistorialClinico historialClinico;

    public Mascota(String nombre, int edad, double peso, Dueño dueño) {
        setNombre(nombre);
    }

    public void setNombre(String nombre) {
        if (nombre == null || !nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío >:c");
        }
        this.nombre = nombre.strip();
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad no puede ser menor o igual a 0 >:c");
        }
        this.edad = edad;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso no puede ser menor o igual a 0 >:c");
        }
        this.peso = peso;
    }

    public void setDueño(Dueño dueño) {
        if (dueño == null) {
            throw new IllegalArgumentException("Especifíque al dueño de la mascota >:c");
        }
        this.dueño = dueño;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public Dueño getDueño() {
        return Dueño;
    }

    /*public HistorialClinico getHistorialClinico() {
        return historialClinico;
    }*/

    public abstract void hacerSonido();
}