package task1;

import javax.swing.*;

abstract class Character extends Entity {
    String numePersonaj;
    Cell coordonateCurente ; // = new Cell si in metoda goNorth si celelalte de revazut
    Inventory inventar;
    int experientaCurenta;
    int nivelCurentPersonaj;
    int strength; // le calculez in functie de nivel cu o formula aleasa de mine
    int dexterity;
    int charisma;

    // toti incep cu 1 la fiecare stat, iar warr priemste 2 la strength si 1 la celelalte pe lvl up mage si rogue +2 la statusul
    // lor principal, iar inamicii au 1 1 1 si primesc +1 pe lvl up
    public boolean cumparaPotiune(Potion p) { // verifica daca am bani si spatiu
        if (inventar.calculeazaGreutateaRamasa() - p.preiaGreutate() < 0) {
            return false;
        }
        if (inventar.numarMonede - p.preiaPret() < 0) {
            return false;
        }
        inventar.adaugaPotiuneInLista(p);
        return true;
    }
    public int atacNormal() {
        return 7 + strength + dexterity/2;
    }

    @Override
    public int folosesteAbilitatea(Spell spell, Entity entity) {
        int damage = -1;
        if(manaCurenta - spell.costMana >= 0) {
            manaCurenta -= spell.costMana;
            boolean primeste = entity.accept(spell);
            if(primeste) {
                damage = spell.damage + charisma + dexterity/2;
            } else {
                damage = 0;
            }
        } else {
            System.out.println("Nu ai mana pentru abilitate");
            //damage = atacNormal();// dau atac normal sau cand o sa fac gui o sa ii dau mesaj sa aleaga altceva
            //entity.receiveDamage(damage);
        }
        return damage;

    }

    public int folosesteAbilitateaGUI(Spell spell, Entity entity, FightGUI fightGUI) {
        int damage = -1;
        if(manaCurenta - spell.costMana >= 0) {
            manaCurenta -= spell.costMana;
            boolean primeste = entity.accept(spell);
            if(primeste) {
                damage = spell.damage + charisma + dexterity/2;
            } else {
                damage = 0;
            }
        } else {
            JOptionPane.showMessageDialog(fightGUI,"Nu ai suficienta mana!");
            //System.out.println("Nu ai mana pentru abilitate");
            //damage = atacNormal();// dau atac normal sau cand o sa fac gui o sa ii dau mesaj sa aleaga altceva
            //entity.receiveDamage(damage);
        }
        return damage;

    }

    @Override
    public String toString() {
        return "" +
                "numePersonaj='" + numePersonaj + '\'' +
                ", coordonateCurente=" + coordonateCurente +
                ", inventar=" + inventar +
                ", experientaCurenta=" + experientaCurenta +
                ", nivelCurentPersonaj=" + nivelCurentPersonaj +
                ", strength=" + strength +
                ", dexterity=" + dexterity +
                ", charisma=" + charisma +
                ", abilitati=" + abilitati +
                ", viataCurenta=" + viataCurenta +
                ", viataMaxima=" + viataMaxima +
                ", manaCurenta=" + manaCurenta +
                ", manaMaxima=" + manaMaxima +
                ", rezistentaFire=" + rezistentaFire +
                ", rezistentaIce=" + rezistentaIce +
                ", rezistentaEarth=" + rezistentaEarth +
                '}';
    }
}
    class CharacterFactory {

    public static Character createUser(String characterType, String nume, int experienta, int nivel) throws InvalidCommandException {
        switch (characterType) {
            case "Warrior":
                return new Warrior(nume, experienta, nivel);
            case "Mage":
                return new Mage(nume, experienta, nivel);
            case "Rogue":
                return new Rogue(nume, experienta, nivel);
        }
        throw new InvalidCommandException();
    }

    }