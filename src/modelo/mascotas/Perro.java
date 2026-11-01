package modelo.mascotas;
import modelo.personas.Dueño;

public class Perro extends Mascota {
    private String raza;
    private int nivelEnergia;

    public Perro (String nombre, int edad, double peso, Dueño dueño, String raza, int nivelEnergia) {
        super(nombre, edad, peso, dueño);
        setRaza(raza);
        setNivelEnergia(nivelEnergia);
    }

    public void setRaza(String raza) {
        if (raza == null || raza.isBlank()) {
            throw new IllegalArgumentException("La raza no puede ser nula >:c");
        }
        this.raza = raza.strip();
    }

    public void setNivelEnergia(int nivelEnergia) {
        if (nivelEnergia <= 0) {
            throw new IllegalArgumentException("El nivel de energía no puede ser igual o menor a 0 >:c");
        }
        this.nivelEnergia = nivelEnergia;
    }

    public String getRaza() {
        return raza;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    @Override
    public void hacerSonido() {
        System.out.println("Woof");
    }
}
