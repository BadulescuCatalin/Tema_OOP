package task1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Mage extends Character{ // sablon factory

    public Mage(String nume, int experienta, int nivel) {

        abilitati.add(new Earth());
        abilitati.add(new Ice());
        abilitati.add(new Fire());
        viataCurenta = 100;
        viataMaxima = 100;
        manaCurenta = 50;
        manaMaxima = 50;
        inventar = new Inventory(10,2, new ArrayList<Potion>());
        rezistentaIce = true;
        numePersonaj = nume;
        experientaCurenta = experienta;
        for(int i=0; i<nivel; ++i) {
            levelUp();
        }
       // inventar.greutateMaxima = 10;
    }
    public void levelUp() {
        charisma += 2;
        strength += 1;
        dexterity += 1;
        nivelCurentPersonaj++;
        viataCurenta += 50;
        viataMaxima += 50;
        manaMaxima += 50;
        manaCurenta += 50;
    }
    @Override
    void receiveDamage(int damage) {
        Random random = new Random();
        int dmgPrimit;
        int evita = random.nextInt((100) ) + 1;
        if(evita <= (dexterity+strength)/2) {
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
        if(dublu <= charisma) {
            System.out.println("Damage dublu");
            entity.receiveDamage(2 * damage);
        }
        else entity.receiveDamage(damage);
    }

    void receiveDamageGUI(int damage, FightGUI fightGUI) {
        Random random = new Random();
        int dmgPrimit;
        int evita = random.nextInt((100) ) + 1;
        if(evita <= (strength+dexterity)/2) {
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
        if(dublu <= charisma){
            JOptionPane.showMessageDialog(fightGUI,"Damage dublu");
            entity.receiveDamageGUI(2 * damage, fightGUI);
        }
        else entity.receiveDamageGUI(damage,fightGUI);
       // if(entity.viataCurenta == hp) {
       //     JOptionPane.showMessageDialog(fightGUI,"Inamicul a evitat damageul");
     //   }
    }

    @Override
    public String toString() {
        return   "\nnume: " + numePersonaj + "\ntip erou: Mage " +
                "\nviata curenta \\ viata maxima : " + viataCurenta + " \\ " + viataMaxima + "  " + "\nmana curenta  \\ mana maxima: " + manaCurenta
                + " \\ " + manaMaxima + "\n" + "nivel: " + nivelCurentPersonaj +
                " experienta: " + experientaCurenta + "\n"+ inventar.toString() + "\n" +
                "Abilitati:" + abilitati.toString() ;
    }

}
