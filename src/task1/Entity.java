package task1;

import java.util.ArrayList;
import java.util.List;

abstract class Entity implements Element<Entity>{
    List<Spell> abilitati = new ArrayList<>();
    int viataCurenta;
    int viataMaxima;
    int manaCurenta;
    int manaMaxima;
    boolean rezistentaFire;
    boolean rezistentaIce;
    boolean rezistentaEarth;

    public void regenerareViata(int viata) {
        if(viataCurenta + viata > viataMaxima) {
            viataCurenta = viataMaxima;
        } else {
            viataCurenta += viata;
        }
    }
    public void regenerareMana(int mana) {
        if(manaCurenta + mana > manaMaxima) {
            manaCurenta = manaMaxima;
        } else {
            manaCurenta += mana;
        }
    }

    public int folosesteAbilitatea(Spell spell, Entity entity) {
        int damage = 4;
        if(manaCurenta - spell.costMana >=0) {
           boolean primeste = entity.accept(spell);
           if(primeste) {
               damage = spell.damage;
           } else {
               damage = 0;
           }
           manaCurenta -= spell.costMana;
        }
        return damage;
    }

    @Override
    public String toString() {
        return "" +
                "\nabilitati=" + abilitati +
                "\n viataCurenta \\ viataMaxima =" + viataCurenta + "\\" +
                + viataMaxima +
                "\n manaCurenta \\ manaMaxima =" + manaCurenta + "\\" +
                 manaMaxima +
                "\n rezistentaFire=" + rezistentaFire +
                ", rezistentaIce=" + rezistentaIce +
                ", rezistentaEarth=" + rezistentaEarth +
                '}';
    }

    @Override
    public boolean accept(Visitor<Entity> visitor) {
        return visitor.visit(this);
    }

    abstract void receiveDamage(int damage);
    abstract void receiveDamageGUI(int damage,FightGUI fightGUI);
    abstract void getDamage(Entity entity, int damage); // se calculeaza dupa o formula aleasa de noi
    abstract void getDamageGUI(Entity entity, int damage, FightGUI fightGUI); // se calculeaza dupa o formula aleasa de noi
}
