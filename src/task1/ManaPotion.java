package task1;

public class ManaPotion implements Potion{
    private int pret;
    private int greutate;
    private int valoareRegenerare;
    @Override
    public void folosestePotiunea(Character character) {
        character.regenerareMana(valoareRegenerare);
        character.inventar.potiuni.remove(this);
    }

    @Override
    public int preiaPret() {
        return pret;
    }

    @Override
    public int preiaValoareRegenerare() {
        return valoareRegenerare;
    }

    @Override
    public int preiaGreutate() {
        return greutate;
    }

    public ManaPotion(int pret, int greutate, int valoareRegenerare) {
        this.greutate = greutate;
        this.pret = pret;
        this.valoareRegenerare = valoareRegenerare;
    }

    @Override
    public String toString() {
        return "ManaPotion{" +
                "pret=" + pret +
                ", greutate=" + greutate +
                ", valoareRegenerare=" + valoareRegenerare +
                '}';
    }
}

