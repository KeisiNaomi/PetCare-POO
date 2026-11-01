package modelo.mascotas;
import modelo.personas.Dueño;

public class AveExotica extends Mascota {
    private double longitudAlas;

    public AveExotica(String nombre, int edad, double peso, Dueño dueño, double longitudAlas) {
        super(nombre, edad, peso, dueño);
        setLongitudAlas(longitudAlas);
    }

    public void setLongitudAlas(double longitudAlas) {
        if (longitudAlas <= 0) {
            throw new IllegalArgumentException("La longitud de las alas no puede ser menor o igual a 0 >:c");
        }
        this.longitudAlas = longitudAlas;
    }

    public double getLongitudAlas() {
        return longitudAlas;
    }

    @Override
    public void hacerSonido() {
        System.out.println("Pio");
    }
}
