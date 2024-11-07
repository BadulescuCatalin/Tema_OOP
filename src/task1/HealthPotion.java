package task1;

public class HealthPotion implements Potion{
    private int pret;
    private int greutate;
    private int valoareRegenerare;
    @Override
    public void folosestePotiunea(Character character) {
        character.regenerareViata(valoareRegenerare);
        character.inventar.potiuni.remove(this);// folosesc si elimin potiunea din lista
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

    public HealthPotion(int pret, int greutate, int valoareRegenerare) {
        this.greutate = greutate;
        this.pret = pret;
        this.valoareRegenerare = valoareRegenerare;
    }

    @Override
    public String toString() {
        return "HealthPotion{" +
                "pret=" + pret +
                ", greutate=" + greutate +
                ", valoareRegenerare=" + valoareRegenerare +
                '}';
    }
}
