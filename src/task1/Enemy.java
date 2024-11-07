package task1;

import javax.swing.*;
import java.util.Random;

public class Enemy extends  Entity implements CellElement{// viata si mana intr-un interval ales de noi,
    // cele 3 protectii au valori aleatoare si primeste 2-4 abilitati alese aleator dintre cele 3 tipuri
    public Enemy() {
        Random random = new Random();
        int radnom1 = random.nextInt(2);
        if(radnom1 == 1) {
            rezistentaEarth = true;
        }
        radnom1 = random.nextInt(2);
        if(radnom1 == 1) {
            rezistentaFire = true;
        }
        radnom1 = random.nextInt(2);
        if(radnom1 == 1) {
            rezistentaIce = true;
        }

        int nrAbilitati = random.nextInt(3) + 2;
        for(int i=0; i<nrAbilitati; ++i) {
            radnom1 = random.nextInt(3) + 1;
            if(radnom1 == 1) abilitati.add(new Ice());
            else if(radnom1 == 2) abilitati.add(new Fire());
            else abilitati.add(new Earth());
        }
        viataMaxima = random.nextInt(200) + 100;
        viataCurenta = viataMaxima;
        manaMaxima = random.nextInt(100) + 50;
        manaCurenta = manaMaxima;
    }
    @Override
    void receiveDamage(int damage) {// 50% sa evite dmg
        Random random = new Random();
        int evita = random.nextInt(2) + 1;
        if(evita == 1) {
            System.out.println("Damage evitat");
        } else {
            viataCurenta -= damage;
        }
    }

    void receiveDamageGUI(int damage, FightGUI fightGUI) {// 50% sa evite dmg
        Random random = new Random();
        int evita = random.nextInt(2) + 1;
        if(evita == 1) {
            JOptionPane.showMessageDialog(fightGUI,"Damage evitat");
            //System.out.println("Damage evitat");
        } else {
            viataCurenta -= damage;
        }
    }

    @Override
    void getDamage(Entity entity, int dmg) { // 50% sa dea dublu
        Random random = new Random();
        int damage = dmg;
        int dublu = random.nextInt(2) + 1;
        int normalSauSpell = random.nextInt(101);// 75% atac normal
        if(normalSauSpell <=75) {
            damage = 4;
            if(dublu == 1) entity.receiveDamage( damage);
            else {
                System.out.println("Damage dublu!");
                entity.receiveDamage(2 * damage);
            }
        } else {
            int size = abilitati.size();
            Spell abilitate = abilitati.get(random.nextInt(size));
            damage = folosesteAbilitatea(abilitate,entity);
            if(dublu == 1) entity.receiveDamage(damage);
            else {
                System.out.println("Damage dublu!");
                entity.receiveDamage(2*damage);
            }
        }
    }

    void getDamageGUI(Entity entity, int dmg, FightGUI fightGUI) { // 50% sa dea dublu
        Random random = new Random();
        int damage = dmg;
        int dublu = random.nextInt(2) + 1;
        int normalSauSpell = random.nextInt(101);// 75% atac normal
        if(normalSauSpell <=75) {
            damage = 4;
            if(dublu == 1) entity.receiveDamageGUI(damage, fightGUI);
            else {
                JOptionPane.showMessageDialog(fightGUI,"Damage dublu");
                //System.out.println("Damage dublu!");
                entity.receiveDamageGUI(2 * damage,fightGUI);
            }
        } else {
            int size = abilitati.size();
            Spell abilitate = abilitati.get(random.nextInt(size));
            damage = folosesteAbilitatea(abilitate,entity);
            if(dublu == 1) entity.receiveDamageGUI( damage,fightGUI);
            else {
                //System.out.println("Damage dublu!");
                JOptionPane.showMessageDialog(fightGUI,"Damage dublu");
                entity.receiveDamageGUI(2* damage,fightGUI);
            }
        }
    }

    @Override
    public String toString() {
        return "Enemy{" + super.toString();
    }

    @Override
    public char getValue() {
        return 'E';
    }
}
