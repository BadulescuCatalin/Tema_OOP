package task1;

import java.util.ArrayList;
import java.util.List;

public class Inventory { // de facut constructor
    List<Potion> potiuni;
    int greutateMaxima;
    int numarMonede; // casutele goale nevizitata -> 20% sansa sa fie monede, inamic infrant -> 80%
    public Inventory(int greutateMaxima, int numarMonede, List<Potion> potiuni) {
        this.greutateMaxima = greutateMaxima;
        this.numarMonede = numarMonede;
        this.potiuni = potiuni;
    }
    public void adaugaPotiuneInLista(Potion p) {
        if(numarMonede - p.preiaPret() >=0 && calculeazaGreutateaRamasa()  - p.preiaGreutate() >= 0) {
            potiuni.add(p);
            numarMonede -= p.preiaPret();
        } else {
            System.out.println("Nu se poate adauga potiunea in inventar");
        }

    }
    public void eliminaPoriuneDinLista(Potion p) {
        potiuni.remove(p);
    }
    public int calculeazaGreutateaRamasa() {
        int greutateCurenta = 0;
        for(Potion p : potiuni) {
            greutateCurenta += p.preiaGreutate();
        }
        return greutateMaxima - greutateCurenta;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "potiuni = [" + potiuni + "]" +
                "\n greutateMaxima=" + greutateMaxima +
                "\n numarMonede=" + numarMonede +
                '}';
    }
}
