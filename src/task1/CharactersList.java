package task1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CharactersList extends JFrame implements ListSelectionListener, ActionListener {
    JList<Character> listaCharacters ;
    DefaultListModel <Character> model = new DefaultListModel<Character>();
    JButton selectButton = new JButton("SelectHero");
    JLabel numeErouLable = new JLabel("Nume erou: ");
    JLabel tipErouLable = new JLabel("Tip erou: ");
    JLabel nivelErouLable = new JLabel("Nivel erou: ");
    JLabel experientaErouLable = new JLabel("Experienta erou: ");
    JLabel latimeJL = new JLabel("Latime harta: ");
    JLabel lungimeJL = new JLabel("Lungime harta: ");
    JTextField latime = new JTextField("5");
    JTextField lungime = new JTextField("5");
    public CharactersList(List <Character> characterList) {
        super("Lista caractere");
        for(Character character : characterList) {
            Character character1;
            if(character instanceof Warrior) {
                character1 = new WarriorGUI(character.numePersonaj, character.experientaCurenta, character.nivelCurentPersonaj);
            } else if(character instanceof Mage) {
                character1 = new MageGUI(character.numePersonaj, character.experientaCurenta, character.nivelCurentPersonaj);
            } else {
                character1 = new RogueGUI(character.numePersonaj, character.experientaCurenta, character.nivelCurentPersonaj);
            }
           model.addElement(character1);
        }
        listaCharacters = new JList<Character>(model);
        listaCharacters.addListSelectionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new FlowLayout());
        setPreferredSize(new Dimension(600,200));
        JScrollPane sp = new JScrollPane(listaCharacters);
        add(sp);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(5,1));
        selectButton.setEnabled(false);
        selectButton.addActionListener(this);

        jp.add(numeErouLable);
        jp.add(tipErouLable);
        jp.add(nivelErouLable);
        jp.add(experientaErouLable);

        add(jp);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(5,1));
        jp2.add(latimeJL);
        jp2.add(latime);
        jp2.add(lungimeJL);
        jp2.add(lungime);
        jp2.add(selectButton);
        add(jp2);

        setVisible(true);
        pack();

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (listaCharacters.isSelectionEmpty()) {
            return ;
        }
        int index = listaCharacters.getSelectedIndex();
        Character c = listaCharacters.getModel().getElementAt(index);
        //numeErou.setText(c.numePersonaj);
        numeErouLable.setText("Nume erou: " + c.numePersonaj);
        if(c instanceof Warrior) {
            tipErouLable.setText("Tip erou: Warrior");
        } else if(c instanceof Mage) {
            tipErouLable.setText("Tip erou: Mage");
        } else {
            tipErouLable.setText("Tip erou: Rogue");
        }
        nivelErouLable.setText("Nivel erou: " + Integer.toString(c.nivelCurentPersonaj));
        experientaErouLable.setText("Experienta erou: " + Integer.toString(c.experientaCurenta));
        selectButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(selectButton.isEnabled()) {
            int index = listaCharacters.getSelectedIndex();
            Character c = listaCharacters.getModel().getElementAt(index);
            Character character;
            if(c instanceof WarriorGUI) {
                //character = new Warrior(c.numePersonaj, c.experientaCurenta, c.nivelCurentPersonaj);
                character = (Warrior) c;
            } else if(c instanceof MageGUI) {
                character = (Mage) c;
                //character = new Mage(c.numePersonaj, c.experientaCurenta, c.nivelCurentPersonaj);
            } else {
                character = (Rogue) c;
                //character = new Rogue(c.numePersonaj, c.experientaCurenta, c.nivelCurentPersonaj);
            }
            this.setVisible(false);
            int lung=Integer.parseInt(lungime.getText());
            int lat=Integer.parseInt(latime.getText());
            JOptionPane.showMessageDialog(this,"Ai selectat un erou!");
            MapaGUI mapaGUI = new MapaGUI(lung, lat, character);
        }
    }
}
