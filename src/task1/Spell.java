package task1;

abstract class Spell implements Visitor<Entity> {
    public int damage;
    public int costMana;

    @Override
    public String toString() {
        return "" +
                "damage=" + damage +
                ", costMana=" + costMana +
                '}';
    }
}
