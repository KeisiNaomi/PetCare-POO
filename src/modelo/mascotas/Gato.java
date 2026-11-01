package modelo.mascotas;
import modelo.personas.Dueño;

public class Gato extends Mascota {
    private EsDomestico esDomestico;

    public Gato(String nombre, int edad, double peso, Dueño dueño) {
        super(nombre, edad, peso, dueño);
        setEsDomestico(esDomestico);
    }

    public void setEsDomestico(EsDomestico esDomestico) {
        if (esDomestico == null) {
            throw new IllegalArgumentException("Especifique si el gato es doméstico o no >:c");
        }
    }

    @Override
    public void hacerSonido() {
        System.out.println("Meow");
    }
}
