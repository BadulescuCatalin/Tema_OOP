package task1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class MapaGUI extends  JFrame implements ActionListener {
    Character erou;
    Enemy enemy;
    Game game = Game.getInstace();
    Grid grid;
    JPanel map = new JPanel();
    JPanel infoErou = new JPanel();
    JPanel infoInamic = new JPanel();
    JPanel poveste = new JPanel();
    JPanel optiuni = new JPanel();
    JButton mergiSud = new JButton("MergiSud");
    JButton mergiNord = new JButton("MergiNord");
    JButton mergiEst = new JButton("MergiEst");
    JButton mergiVest = new JButton("MergiVest");
    TextArea povesteTA;
    TextArea infoErouTA;
    TextArea infoInamicTA;
    public String povesteCelula(Cell cell) {
        List <Account> acc = game.getAccountsList();
       // System.out.println(acc);
        Map<String , ArrayList<String>> map = game.getMap();
        //System.out.println(map);
        Random random = new Random();
        String poveste = "";
        if(!cell.vizitat && cell.cellElement instanceof Shop) {
            java.util.List<String> storiesList = map.get("SHOP");
            poveste = storiesList.get(random.nextInt(storiesList.size()));
        } else if(!cell.vizitat && cell.cellElement instanceof Enemy) {
            java.util.List<String> storiesList = map.get("ENEMY");
            poveste = storiesList.get(random.nextInt(storiesList.size()));
        } else if(!cell.vizitat && cell.cellElement instanceof EmptyCell) {
            List<String> storiesList = map.get("EMPTY");
            poveste = storiesList.get(random.nextInt(storiesList.size()));
        } else if(!cell.vizitat && cell.cellElement instanceof StartPoint) {
            poveste = "Aici incepe aventura ta!";
        } else if(!cell.vizitat && cell.cellElement instanceof StopPoint){
            poveste = "Felicitari, ai terminat mapa!";
        }
        return poveste;
    }

    public MapaGUI(int lungime, int latime, Character erou1) {
        super("Joc");
        if(erou1 instanceof WarriorGUI) {
            erou = new Warrior(erou1.numePersonaj,erou1.experientaCurenta, erou1.nivelCurentPersonaj);
        } else if(erou1 instanceof MageGUI) {
            erou = new Mage(erou1.numePersonaj,erou1.experientaCurenta, erou1.nivelCurentPersonaj);
        } else {
            erou = new Rogue(erou1.numePersonaj,erou1.experientaCurenta, erou1.nivelCurentPersonaj);
        }
        mergiEst.addActionListener(this);
        mergiSud.addActionListener(this);
        mergiNord.addActionListener(this);
        mergiVest.addActionListener(this);
        erou.coordonateCurente = new Cell(0,0,new StartPoint(), true);
        JPanel flowJpanel = new JPanel();

        flowJpanel.setLayout(new FlowLayout());//
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout( new FlowLayout());
        infoErouTA = new TextArea("Erou:" + erou.toString());
        infoErouTA.setEditable(false);
        infoErou.add( infoErouTA);
        infoInamicTA = new TextArea("Inamic: ");
        infoInamicTA.setEditable(false);
        infoInamic.add(infoInamicTA);
        map.setLayout(new GridLayout(latime, lungime));
        map.setPreferredSize(new Dimension(500,500));
        grid = Grid.generareHarta(latime, lungime, erou, erou.coordonateCurente);
        grid.pozitieCurenta.vizitat = false;
        povesteTA = new TextArea("Poveste: " + povesteCelula(grid.pozitieCurenta));
        povesteTA.setEditable(false);
        poveste.add(povesteTA);
        grid.get(0).get(0).vizitat = true;
        grid.pozitieCurenta.vizitat = true;
        grid.pozitieCurenta = grid.get(0).get(0);

        JPanel jp = new JPanel();

        jp.setLayout(new GridLayout(2,1));
        jp.add(infoErou);
        //jp.add(infoInamic);
        jp.add(poveste);

        optiuni.setLayout(new GridLayout(10,2));

        optiuni.add(new JLabel("Optiuni: "));
        optiuni.add(mergiSud);
        optiuni.add(mergiNord);
        optiuni.add(mergiEst);
        optiuni.add(mergiVest);

        flowJpanel.add(optiuni);
        flowJpanel.add(jp);

        for(int i=0; i<latime; ++i) {
            for (int j=0; j<lungime; ++j) {
                JLabel testImg = new JLabel();
                if(grid.get(i).get(j).vizitat) {
                    testImg.setText("" + grid.get(i).get(j).cellElement.getValue());

                } else {
                    testImg.setText("?");
                }
                testImg.setHorizontalAlignment( SwingConstants.CENTER);
                testImg.setVerticalAlignment( SwingConstants.CENTER);
                testImg.setBorder(BorderFactory.createLineBorder(Color.black));
                testImg.setBackground(Color.black);
                testImg.validate();
                //testImg.setPreferredSize(new Dimension(100,100));
                //testImg.setIcon(new ImageIcon("shop_img.jfif"));
                map.add(testImg);
            }
        }

        add(flowJpanel);
        add(map);
        setVisible(true);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == mergiEst) {
            if(grid.pozitieCurenta.y + 1 < grid.lungime) {
                grid.pozitieCurenta = grid.get(grid.pozitieCurenta.x).get(grid.pozitieCurenta.y+1);
                erou.coordonateCurente = grid.pozitieCurenta;
                Random random = new Random();
                if(!grid.pozitieCurenta.vizitat) {
                    if(grid.pozitieCurenta.cellElement.getValue() == 'N') {
                        int random1 = random.nextInt(101);
                        if(random1 <= 20) {
                            erou.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                        }
                    }
                }// de completat pt inamic si restul cazurilor
                if(!grid.pozitieCurenta.vizitat) {
                    povesteTA.setText("Poveste: " + povesteCelula(grid.pozitieCurenta));
                    //game.afisPovesteCasuta(grid.pozitieCurenta);
                    grid.pozitieCurenta.vizitat = true;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Nu se poate merge in Est. Incearca alta varianta!");
            }
            int i = 0;
            int j = 0;
            for(Component jc : map.getComponents()) {
                JLabel label = (JLabel) jc;
                if(grid.get(i).get(j).vizitat) {
                    if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        ShopGUI shopGUI = new ShopGUI((Shop) grid.pozitieCurenta.cellElement, erou, this);
                        this.setVisible(false);
                        label.setText("PS");

                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("S");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PE");
                        enemy = (Enemy) grid.pozitieCurenta.cellElement;
                        if(enemy.viataCurenta <= 0) {
                            JOptionPane.showMessageDialog(this,"Acest inamic a fost deja infrant!");
                        } else {
                            // de facut batalia
                            this.setVisible(false);
                            FightGUI fightGUI = new FightGUI(erou, enemy,this);
                        }
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("E");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PF");
                        JOptionPane.showMessageDialog(this, "Ai terminat mapa!!!");
                        System.exit(0);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("F");
                    }
                }
                j++;
                if(j == grid.lungime) {
                    j=0;
                    i++;
                }
                infoErouTA.setText("Erou: " + erou.toString());
            }
        } else if (e.getSource() == mergiNord) {
            if(grid.pozitieCurenta.x - 1 >= 0) {
                grid.pozitieCurenta = grid.get(grid.pozitieCurenta.x-1).get(grid.pozitieCurenta.y);
                erou.coordonateCurente = grid.pozitieCurenta;
                Random random = new Random();
                if(!grid.pozitieCurenta.vizitat) {
                    if(grid.pozitieCurenta.cellElement.getValue() == 'N') {
                        int random1 = random.nextInt(101);
                        if(random1 <= 20) {
                            erou.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                        }
                    }
                }// de completat pt inamic si restul cazurilor
                if(!grid.pozitieCurenta.vizitat) {
                    povesteTA.setText("Poveste: " + povesteCelula(grid.pozitieCurenta));
                    //game.afisPovesteCasuta(grid.pozitieCurenta);
                    grid.pozitieCurenta.vizitat = true;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nu se poate merge in Nord. Incearca alta varianta!");
            }
            int i = 0;
            int j = 0;
            for(Component jc : map.getComponents()) {
                JLabel label = (JLabel) jc;
                if(grid.get(i).get(j).vizitat) {
                    if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PS");
                        ShopGUI shopGUI = new ShopGUI((Shop) grid.pozitieCurenta.cellElement, erou, this);
                        this.setVisible(false);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("S");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PE");
                        enemy = (Enemy) grid.pozitieCurenta.cellElement;
                        if(enemy.viataCurenta <= 0) {
                            JOptionPane.showMessageDialog(this,"Acest inamic a fost deja infrant!");
                        } else {
                            // de facut batalia
                            this.setVisible(false);
                            FightGUI fightGUI = new FightGUI(erou, enemy,this);
                        }
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("E");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PF");
                        JOptionPane.showMessageDialog(this, "Ai terminat mapa!!!");
                        System.exit(0);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("F");
                    }
                }
                j++;
                if(j == grid.lungime) {
                    j=0;
                    i++;
                }
            }
            infoErouTA.setText("Erou: " + erou.toString());
        } else if(e.getSource() == mergiSud) {
            if(grid.pozitieCurenta.x + 1 < grid.latime) {
                grid.pozitieCurenta = grid.get(grid.pozitieCurenta.x + 1).get(grid.pozitieCurenta.y);
                erou.coordonateCurente = grid.pozitieCurenta;
                Random random = new Random();
                if(!grid.pozitieCurenta.vizitat) {
                    if(grid.pozitieCurenta.cellElement.getValue() == 'N') {
                        int random1 = random.nextInt(101);
                        if(random1 <= 20) {
                            erou.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                        }
                    }
                }// de completat pt inamic si restul cazurilor
                if(!grid.pozitieCurenta.vizitat) {
                    povesteTA.setText("Poveste: " + povesteCelula(grid.pozitieCurenta));
                    //game.afisPovesteCasuta(grid.pozitieCurenta);
                    grid.pozitieCurenta.vizitat = true;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nu se poate merge in Sud. Incearca alta varianta!");
            }
            int i = 0;
            int j = 0;
            for(Component jc : map.getComponents()) {
                JLabel label = (JLabel) jc;
                if(grid.get(i).get(j).vizitat) {
                    if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PS");
                        ShopGUI shopGUI = new ShopGUI((Shop) grid.pozitieCurenta.cellElement, erou, this);
                        this.setVisible(false);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("S");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PE");
                        enemy = (Enemy) grid.pozitieCurenta.cellElement;
                        if(enemy.viataCurenta <= 0) {
                            JOptionPane.showMessageDialog(this,"Acest inamic a fost deja infrant!");
                        } else {
                            // de facut batalia
                            this.setVisible(false);
                            FightGUI fightGUI = new FightGUI(erou, enemy,this);

                        }
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("E");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PF");
                        JOptionPane.showMessageDialog(this, "Ai terminat mapa!!!");
                        System.exit(0);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("F");
                    }
                }
                j++;
                if(j == grid.lungime) {
                    j=0;
                    i++;
                }
            }
            infoErouTA.setText("Erou: " + erou.toString());
        } else if(e.getSource() == mergiVest) {
            if(grid.pozitieCurenta.y - 1 >= 0) {
                grid.pozitieCurenta = grid.get(grid.pozitieCurenta.x).get(grid.pozitieCurenta.y-1);
                erou.coordonateCurente = grid.pozitieCurenta;
                Random random = new Random();
                if(!grid.pozitieCurenta.vizitat) {
                    if(grid.pozitieCurenta.cellElement.getValue() == 'N') {
                        int random1 = random.nextInt(101);
                        if(random1 <= 20) {
                            erou.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                        }
                    }
                }// de completat pt inamic si restul cazurilor
                if(!grid.pozitieCurenta.vizitat) {
                    povesteTA.setText("Poveste: " + povesteCelula(grid.pozitieCurenta));
                    //game.afisPovesteCasuta(grid.pozitieCurenta);
                    grid.pozitieCurenta.vizitat = true;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nu se poate merge in Vest. Incearca alta varianta!");
            }
            int i = 0;
            int j = 0;
            for(Component jc : map.getComponents()) {
                JLabel label = (JLabel) jc;
                if(grid.get(i).get(j).vizitat) {
                    if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'P' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("P");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'N' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("N");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PS");
                        ShopGUI shopGUI = new ShopGUI((Shop) grid.pozitieCurenta.cellElement, erou, this);
                        this.setVisible(false);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'S' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("S");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PE");
                        enemy = (Enemy) grid.pozitieCurenta.cellElement;
                        if(enemy.viataCurenta <= 0) {
                            JOptionPane.showMessageDialog(this,"Acest inamic a fost deja infrant!");
                        } else {
                            // de facut batalia
                            this.setVisible(false);
                            FightGUI fightGUI = new FightGUI(erou, enemy,this);
                        }
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'E' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("E");
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) == grid.pozitieCurenta) {
                        label.setText("PF");
                        JOptionPane.showMessageDialog(this, "Ai terminat mapa!!!");
                        System.exit(0);
                    } else if (grid.get(i).get(j).cellElement.getValue() == 'F' && grid.get(i).get(j) != grid.pozitieCurenta) {
                        label.setText("F");
                    }
                }
                j++;
                if(j == grid.lungime) {
                    j=0;
                    i++;
                }
            }
            infoErouTA.setText("Erou: " + erou.toString());
        }
        infoErouTA.setText("Erou:" + erou.toString());
    }
}
