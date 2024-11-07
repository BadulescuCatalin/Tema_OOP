package task1;

public class RogueGUI extends  Rogue{

    public RogueGUI(String nume, int experienta, int nivel) {
        super(nume, experienta, nivel);
    }

    @Override
    public String toString() {
        return numePersonaj;
    }
}
