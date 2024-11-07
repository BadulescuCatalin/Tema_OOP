package task1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Rogue extends Character{ // sablon factory

    public Rogue(String nume, int experienta, int nivel) {
        // de pus viata curenta, maxima, mana curenta, mana maxima

        abilitati.add(new Earth());
        abilitati.add(new Ice());
        abilitati.add(new Fire());
        inventar = new Inventory(15,2, new ArrayList<Potion>());
        viataCurenta = 100;
        viataMaxima = 100;
        manaCurenta = 50;
        manaMaxima = 50;
        rezistentaEarth = true;
        numePersonaj = nume;
        experientaCurenta = experienta;
        for(int i=0; i<nivel; ++i) {
            levelUp();

        }
        //inventar.greutateMaxima = 15;
    }

    public void levelUp() {
        charisma += 1;
        strength += 1;
        dexterity += 2;
        nivelCurentPersonaj++;
        viataCurenta += 50;
        viataMaxima += 50;
        manaMaxima += 50;
        manaCurenta += 50;
    }
    // damage atac normal = 7
    // sansa sa se fereasca de jumatate din damage= (secundar1 + secundar2)/2
    // sansa sa dea dublu = % atributul principal
    @Override
    void receiveDamage(int damage) {
        Random random = new Random();
        int dmgPrimit;
        int evita = random.nextInt((100) ) + 1;
        if(evita <= (charisma+strength)/2) {
            System.out.println("Jumatate evitat");
            dmgPrimit = damage/2;
        } else {
            dmgPrimit = damage;
        }
        viataCurenta -= dmgPrimit;
    }

    @Override
    void getDamage(Entity entity, int damage) {
        Random random = new Random();
        int dublu = random.nextInt(101);
        if(dublu <= dexterity) {
            System.out.println("Damage dublu");
            entity.receiveDamage(2 * damage);
        }
        else entity.receiveDamage(damage);
    }

    void receiveDamageGUI(int damage, FightGUI fightGUI) {
        Random random = new Random();
        int dmgPrimit;
        int evita = random.nextInt((100) ) + 1;
        if(evita <= (charisma+strength)/2) {
            JOptionPane.showMessageDialog(fightGUI,"Damage-ul a fost pe jumatate evitat");
            //System.out.println("Jumatate evitat");
            dmgPrimit = damage/2;
        } else {
            dmgPrimit = damage;
        }
        viataCurenta -= dmgPrimit;
    }

    void getDamageGUI(Entity entity, int damage, FightGUI fightGUI) {
        Random random = new Random();
        int hp = entity.viataCurenta;
        int dublu = random.nextInt(101);
        if(dublu <= dexterity){
            JOptionPane.showMessageDialog(fightGUI,"Damage dublu");
            entity.receiveDamageGUI(2 * damage, fightGUI);
        }
        else entity.receiveDamageGUI(damage, fightGUI);
       // if(entity.viataCurenta == hp) {
       //     JOptionPane.showMessageDialog(fightGUI,"Inamicul a evitat damageul");
      //  }
    }

    @Override
    public String toString() {
        return   "\nnume: " + numePersonaj + "\ntip erou: Rogue " +
                "\nviata curenta \\ viata maxima : " + viataCurenta + " \\ " + viataMaxima + "  " + "\nmana curenta  \\ mana maxima: " + manaCurenta
                + " \\ " + manaMaxima + "\n" + "nivel: " + nivelCurentPersonaj +
                " experienta: " + experientaCurenta + "\n"+ inventar.toString() + "\n" +
                "Abilitati:" + abilitati.toString() ;
    }


}
