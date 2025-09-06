package View;

import Model.Animal;
import java.util.List;

public class AnimalView {

    public void mostrarAnimais(List<Animal> animais) {
        System.out.println("=== Lista de Animais ===");
        for (Animal u : animais) {
            System.out.println(u); 
        }
    }
}
