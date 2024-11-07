package task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop implements CellElement{
    List<Potion> listaPotiuni = new ArrayList<>();
    public Shop(Potion p1, Potion p2) {
        listaPotiuni.add(p1);
        listaPotiuni.add(p2);
    }
    public Shop() {
        Random random = new Random();
        int low = 2;
        int high = 4;
        int result = random.nextInt(high-low+1) + low;
        for(int i=0; i<result; ++i) {
            low = 1;
            high = 2;
            int result2 = random.nextInt(high-low+1) + low;
            if(result2 == 1) {
                result2 = random.nextInt(high-low+1) + low;
                if(result2 == 1) {
                    listaPotiuni.add(new HealthPotion(1,1,50));//potiune mica de viata
                } else {
                    listaPotiuni.add(new HealthPotion(2,3,75));//potine mare de viata
                }

            } else {
                result2 = random.nextInt(high-low+1) + low;
                if(result2 == 1) {
                    listaPotiuni.add(new ManaPotion(1,1,15));// potine mica de mana
                } else {
                    listaPotiuni.add(new ManaPotion(2,3,25));// potine mare de mana
                }
            }
        }
    }
    public Potion selectarePoriune(int index) {
        if(index >= listaPotiuni.size()) {
            System.out.println("Nu exista o potiunea la pozitia " + index);
            return null;
        } else {
            Potion p = listaPotiuni.get(index);
            listaPotiuni.remove(index);
            return p;
        }
    }

    @Override
    public String toString() {
        return "Shop{" +
                "listaPotiuni=" + listaPotiuni +
                '}';
    }

    @Override
    public char getValue() {
        return 'S';
    }
}
