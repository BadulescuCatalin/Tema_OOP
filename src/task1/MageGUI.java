package task1;

public class MageGUI extends Mage{
    public MageGUI(String nume, int experienta, int nivel) {
        super(nume, experienta, nivel);
    }

    @Override
    public String toString() {
        return numePersonaj;
    }
}
