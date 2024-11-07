package task1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopGUI extends JFrame implements ListSelectionListener, ActionListener {
    MapaGUI mapaGUI;
    Shop shop;
    Character erou;
    TextArea inventar = new TextArea();
    TextArea spatiuLiberInventar = new TextArea();
    JList<Potion> listaPotiuni ;
    JButton cumpara = new JButton("Cumpara");
    JButton parasesteMagazinul = new JButton("Paraseste");

    DefaultListModel <Potion> model = new DefaultListModel<Potion>();
    public ShopGUI(Shop shop1, Character erou1, MapaGUI mapaGUI1) {
        super("Magazin");
        mapaGUI = mapaGUI1;
        shop = shop1;
        erou = erou1;
       // JPanel jp2 = new J
        inventar.setText("Inventar: " + erou.inventar.toString());
        inventar.setEditable(false);
        spatiuLiberInventar.setText("Spatiu disponibil in inventar: " + erou.inventar.calculeazaGreutateaRamasa());
        spatiuLiberInventar.setEditable(false);
        JPanel infoInventar = new JPanel();
        infoInventar.add(inventar);
        JPanel infoSpatiu = new JPanel();
        infoSpatiu.add(spatiuLiberInventar);
        for(Potion p: shop.listaPotiuni) {
            model.addElement(p);
        }
        listaPotiuni = new JList<Potion>(model);
        listaPotiuni.addListSelectionListener(this);
        cumpara.setEnabled(false);
        parasesteMagazinul.addActionListener(this);
        cumpara.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new GridLayout(2,1));
        //setPreferredSize(new Dimension(600,300));
        JScrollPane sp = new JScrollPane(listaPotiuni);
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(sp);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(2,1));

        inventar.setEditable(false);
        spatiuLiberInventar.setEditable(false);
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(2,1));
        jPanel.add(infoInventar);
        jPanel.add(infoSpatiu);
        add(flowPanel);
        add(jPanel);
        //cumpara.setPreferredSize(new Dimension(20,20));
        jp2.add(cumpara);
        jp2.add(parasesteMagazinul);
        flowPanel.add(jp2);
        setVisible(true);
        pack();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == cumpara) {
            if(shop.listaPotiuni.size() <= 0 || model.size() <= 0) {
                cumpara.setEnabled(false);
            }
            int index = listaPotiuni.getSelectedIndex();
            boolean cumpara = erou.cumparaPotiune(shop.selectarePoriune(index));
            ListSelectionModel selmodel = listaPotiuni.getSelectionModel();
            inventar.setText("Inventar: " + erou.inventar.toString());
            spatiuLiberInventar.setText("Spatiu disponibil in inventar: " + erou.inventar.calculeazaGreutateaRamasa());
            if (index >= 0 && cumpara) {
                model.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Nu poti cumpara potiunea");
            }
        }
        if(e.getSource() == parasesteMagazinul) {
            mapaGUI.setVisible(true);
            setVisible(false);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (listaPotiuni.isSelectionEmpty()) {
            return ;
        }
        int index = listaPotiuni.getSelectedIndex();
        Potion p = listaPotiuni.getModel().getElementAt(index);
        cumpara.setEnabled(true);
    }
}
