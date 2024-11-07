package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FightGUI extends JFrame implements ActionListener {
    Character erou;
    Enemy enemy;
    MapaGUI mapaGUI ;
    TextArea infoErou =new TextArea();
    TextArea infoInamic = new TextArea();
    JPanel gridPanel = new JPanel();
    JPanel erouPanel = new JPanel();
    JPanel inamicPanel = new JPanel();
    JPanel optiuniPanel = new JPanel();
    JLabel optiuniJlabel = new JLabel("Optiuni: ");
    JButton atacNormal = new JButton("Atac Normal");
    JButton inamicAtaca = new JButton("Inamicul Ataca");
    JButton vraja1 = new JButton("Vraja 1");
    JButton vraja2 = new JButton("Vraja 2");
    JButton vraja3 = new JButton("Vraja 3");
    JButton inventar = new JButton("Inventar");
    public FightGUI(Character character, Enemy enemy1, MapaGUI mapaGUI1) {
        super("Batalie");
        mapaGUI = mapaGUI1;
        erou = character;
        enemy = enemy1;
        inamicAtaca.setEnabled(false);
        infoErou.setText("Erou: " + erou.toString());
        infoInamic.setText("Inamic: " + enemy.toString());
        inamicAtaca.addActionListener(this);
        atacNormal.addActionListener(this);
        vraja1.addActionListener(this);
        vraja2.addActionListener(this);
        vraja3.addActionListener(this);
        if(erou.inventar.potiuni.size() == 0) {
            inventar.setEnabled(false);
        }
        inventar.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        erouPanel.add(infoErou);
        inamicPanel.add(infoInamic);
        gridPanel.setLayout(new GridLayout(2,1));
        gridPanel.add(erouPanel);
        gridPanel.add(inamicPanel);
        optiuniPanel.setLayout(new GridLayout(7,1));
        optiuniPanel.add(optiuniJlabel);
        optiuniPanel.add(atacNormal);
        optiuniPanel.add(vraja1);
        optiuniPanel.add(vraja2);
        optiuniPanel.add(vraja3);
        optiuniPanel.add(inventar);
        optiuniPanel.add(inamicAtaca);
        add(gridPanel);
        add(optiuniPanel);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == atacNormal) {
            erou.getDamageGUI(enemy,erou.atacNormal(),this);
            infoErou.setText("Erou: " + erou.toString());
            infoInamic.setText("Inamic: " + enemy.toString());
            atacNormal.setEnabled(false);
            vraja1.setEnabled(false);
            vraja2.setEnabled(false);
            vraja3.setEnabled(false);
            inventar.setEnabled(false);
            if(enemy.viataCurenta <= 0 ) {
                JOptionPane.showMessageDialog(this,"Ai invins inamicul!");
                Random random = new Random();
                int sansa = random.nextInt(101);
                if(sansa <= 80) {
                    JOptionPane.showMessageDialog(this,"Ai primit 4 monede drept recompensa!");
                    erou.inventar.numarMonede += 4;
                }
                this.setVisible(false);
                mapaGUI.setVisible(true);

            } else {
                inamicAtaca.setEnabled(true);
            }

        } else if(e.getSource() == vraja1) {
            if(erou.folosesteAbilitateaGUI(erou.abilitati.get(0),enemy,this) == -1) {
                JOptionPane.showMessageDialog(this,"Alege alta optiune!");
            } else {
                erou.getDamageGUI(enemy, erou.folosesteAbilitateaGUI(erou.abilitati.get(0), enemy,this),this);
                infoErou.setText("Erou: " + erou.toString());
                infoInamic.setText("Inamic: " + enemy.toString());
                atacNormal.setEnabled(false);
                vraja1.setEnabled(false);
                vraja2.setEnabled(false);
                vraja3.setEnabled(false);
                inventar.setEnabled(false);
                if(enemy.viataCurenta <= 0 ) {
                    JOptionPane.showMessageDialog(this,"Ai invins inamicul!");
                    Random random = new Random();
                    int sansa = random.nextInt(101);
                    if(sansa <= 80) {
                        JOptionPane.showMessageDialog(this,"Ai primit 4 monede drept recompensa!");
                        erou.inventar.numarMonede += 4;
                    }
                    this.setVisible(false);
                    mapaGUI.setVisible(true);

                } else {
                    inamicAtaca.setEnabled(true);
                }
            }

        } else if(e.getSource() == vraja2) {
            if(erou.folosesteAbilitateaGUI(erou.abilitati.get(1),enemy,this) == -1) {
                JOptionPane.showMessageDialog(this,"Alege alta optiune!");

            } else {
                erou.getDamageGUI(enemy, erou.folosesteAbilitateaGUI(erou.abilitati.get(1), enemy,this),this);
                infoErou.setText("Erou: " + erou.toString());
                infoInamic.setText("Inamic: " + enemy.toString());
                atacNormal.setEnabled(false);
                vraja1.setEnabled(false);
                vraja2.setEnabled(false);
                vraja3.setEnabled(false);
                inventar.setEnabled(false);
                if(enemy.viataCurenta <= 0 ) {
                    JOptionPane.showMessageDialog(this,"Ai invins inamicul!");
                    Random random = new Random();
                    int sansa = random.nextInt(101);
                    if(sansa <= 80) {
                        JOptionPane.showMessageDialog(this,"Ai primit 4 monede drept recompensa!");
                        erou.inventar.numarMonede += 4;
                    }
                    this.setVisible(false);
                    mapaGUI.setVisible(true);

                } else {
                    inamicAtaca.setEnabled(true);
                }
            }
        } else if(e.getSource() == vraja3) {

            if(erou.folosesteAbilitateaGUI(erou.abilitati.get(2),enemy,this) == -1) {
                JOptionPane.showMessageDialog(this,"Alege alta optiune!");
            } else {
                erou.getDamageGUI(enemy, erou.folosesteAbilitateaGUI(erou.abilitati.get(2), enemy,this),this);
                infoErou.setText("Erou: " + erou.toString());
                infoInamic.setText("Inamic: " + enemy.toString());
                atacNormal.setEnabled(false);
                vraja1.setEnabled(false);
                vraja2.setEnabled(false);
                vraja3.setEnabled(false);
                inventar.setEnabled(false);
                if(enemy.viataCurenta <= 0 ) {
                    JOptionPane.showMessageDialog(this,"Ai invins inamicul!");
                    Random random = new Random();
                    int sansa = random.nextInt(101);
                    if(sansa <= 80) {
                        JOptionPane.showMessageDialog(this,"Ai primit 4 monede drept recompensa!");
                        erou.inventar.numarMonede += 4;
                    }
                    this.setVisible(false);
                    mapaGUI.setVisible(true);

                } else {
                    inamicAtaca.setEnabled(true);
                }
            }
        } else if(e.getSource() == inventar) {
            InventarGUI inventarGUI = new InventarGUI(erou,this);
            infoErou.setText("Erou: " + erou.toString());

        } else if(e.getSource() == inamicAtaca) {
            JOptionPane.showMessageDialog(this,"Inamicul a atacat!");
            enemy.getDamageGUI(erou,0,this);
            infoErou.setText("Erou: " + erou.toString());
            infoInamic.setText("Inamic: " + enemy.toString());
            atacNormal.setEnabled(true);
            vraja1.setEnabled(true);
            vraja2.setEnabled(true);
            vraja3.setEnabled(true);
            if(erou.inventar.potiuni.size() == 0) {
                inventar.setEnabled(false);
            } else {
                inventar.setEnabled(true);
            }
            inamicAtaca.setEnabled(false);
            if(erou.viataCurenta <= 0) {
                JOptionPane.showMessageDialog(this,"Ai pierdut!");
                System.exit(0);
            }
        }
    }
}
