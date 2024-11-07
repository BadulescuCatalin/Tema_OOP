package task1;

public class WarriorGUI extends Warrior{

    public WarriorGUI(String nume, int experienta, int nivel) {
        super(nume, experienta, nivel);
    }

    @Override
    public String toString() {
        return numePersonaj;
    }
}
