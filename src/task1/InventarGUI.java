package task1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarGUI extends JFrame implements ActionListener, ListSelectionListener {

    Character erou;
    FightGUI fightGUI;
    JButton foloseste = new JButton("Foloseste");
    JButton parasesteInventar = new JButton("Paraseste");
    JList<Potion> listaPotiuni ;
    DefaultListModel <Potion> model = new DefaultListModel<Potion>();
    public InventarGUI(Character character, FightGUI fightGUI1) {
        super("Inventar");
        erou = character;
        fightGUI = fightGUI1;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        for(Potion p: erou.inventar.potiuni) {
            model.addElement(p);
        }
        listaPotiuni = new JList<Potion>(model);
        listaPotiuni.addListSelectionListener(this);
        foloseste.addActionListener(this);
        foloseste.setEnabled(false);
        parasesteInventar.addActionListener(this);
        add(listaPotiuni);
        add(foloseste);
        add(parasesteInventar);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == parasesteInventar) {
            setVisible(false);
            fightGUI.setVisible(true);
        } else if(e.getSource() == foloseste) {
            int index = listaPotiuni.getSelectedIndex();
            Potion p = listaPotiuni.getModel().getElementAt(index);
            p.folosestePotiunea(erou);
            if (index >= 0 ) {
                model.remove(index);
            }
            fightGUI.infoErou.setText("Erou: " + erou.toString());
            fightGUI.atacNormal.setEnabled(false);
            fightGUI.vraja1.setEnabled(false);
            fightGUI.vraja2.setEnabled(false);
            fightGUI.vraja3.setEnabled(false);
            fightGUI.inventar.setEnabled(false);
            fightGUI.inamicAtaca.setEnabled(true);
            setVisible(false);
            fightGUI.setVisible(true);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (listaPotiuni.isSelectionEmpty()) {
            return ;
        }
        int index = listaPotiuni.getSelectedIndex();
        Potion p = listaPotiuni.getModel().getElementAt(index);
        foloseste.setEnabled(true);
    }
}
