package task1;

import java.util.*;
// nu este relevant, facusem doar niste teste pt scenariul hardcodat

public class Testare {
  /*  static public void afisareMapa(Grid map) {
        for(int i=0; i<map.latime; ++i) {
            for (int j=0; j< map.lungime; ++j) {
                if(map.get(i).get(j).vizitat ) {
                    System.out.print(map.get(i).get(j).cellElement.getValue() + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws  InvalidCommandException { // de adaugat la metoda run in Game

        Game game = Game.getInstace();
        game.run();

        Character pers = game.getAccountsList().get(1).personaje.get(1);
        pers.coordonateCurente = new Cell(0,0,new StartPoint(), true);
        Grid map = Grid.generareHartaGoala(5,5,pers, pers.coordonateCurente);
        System.out.println(pers);

        map.get(0).get(0).vizitat = true;
        map.get(0).get(3).cellElement = new Shop( new HealthPotion(1,1,50), new ManaPotion(1,1,15));
        map.get(1).get(3).cellElement = new Shop();
        map.get(2).get(0).cellElement = new Shop();
        map.get(4).get(4).cellElement = new StopPoint();
        map.get(3).get(4).cellElement = new Enemy();

        Scanner scanner = new Scanner(System.in);
        if(scanner.next().equals("P")) {
            System.out.println("Aici incepe aventura ta!");
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goEast(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goEast(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goEast(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        Shop shop = (Shop) map.pozitieCurenta.cellElement;

        if(scanner.next().equals("P")) {
            System.out.println("Lista de potiuni:" + shop.listaPotiuni);
            map.personajCurent.inventar.adaugaPotiuneInLista(shop.selectarePoriune(0));
            map.personajCurent.inventar.adaugaPotiuneInLista(shop.selectarePoriune(0));
            System.out.println("Am cumparat cele 2 potiuni");
            System.out.println(map.personajCurent.inventar);
            System.out.println("Lista de potiuni dupa cumparare:" + shop.listaPotiuni);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goEast(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goSouth(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goSouth(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            map.goSouth(game);
            System.out.println();
            afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        Enemy enemy = (Enemy) map.pozitieCurenta.cellElement;
        Character character = map.personajCurent;

        if(scanner.next().equals("P")) {
            //character.folosesteAbilitatea(character.abilitati.get(0), enemy);
            character.getDamage(enemy,character.folosesteAbilitatea(character.abilitati.get(0),enemy));
            System.out.println(enemy);
            System.out.println(character);
            //afisareMapa(map);
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            enemy.getDamage(character,0);
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            //character.folosesteAbilitatea(character.abilitati.get(1), enemy);
            character.getDamage(enemy,character.folosesteAbilitatea(character.abilitati.get(1),enemy));

            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            enemy.getDamage(character,0);
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            //character.folosesteAbilitatea(character.abilitati.get(2), enemy);
            character.getDamage(enemy,character.folosesteAbilitatea(character.abilitati.get(2),enemy));

            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }
        // utilizeaza potiunile
        if(scanner.next().equals("P")) {
            enemy.getDamage(character,0);
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            //character.folosesteAbilitatea(character.abilitati.get(2), enemy);
            System.out.println("Folosesc potiune de mana"); // de modificat cu p.folosestepotiunea
            character.regenerareMana(character.inventar.potiuni.get(1).preiaValoareRegenerare());
            character.inventar.eliminaPoriuneDinLista(character.inventar.potiuni.get(1));
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }
        // foloseste potiune de viata
        if(scanner.next().equals("P")) {
            enemy.getDamage(character,0);
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }

        if(scanner.next().equals("P")) {
            //character.folosesteAbilitatea(character.abilitati.get(2), enemy);
            System.out.println("Folosesc potiune de viata");
            character.regenerareViata(character.inventar.potiuni.get(0).preiaValoareRegenerare());
            character.inventar.eliminaPoriuneDinLista(character.inventar.potiuni.get(0));
            System.out.println(enemy);
            System.out.println(character);
            System.out.println();
        } else {
            throw new InvalidCommandException();
        }


        while(character.viataCurenta > 0 && enemy.viataCurenta > 0) {
            // de apasat p
            if(scanner.next().equals("P")) {
                enemy.getDamage(character,0);
                System.out.println(enemy);
                System.out.println(character);
                System.out.println();
            } else {
                throw new InvalidCommandException();
            }
            if(character.viataCurenta > 0 && enemy.viataCurenta > 0) {
                // de apasat p
                if(scanner.next().equals("P")) {
                    character.getDamage(enemy,character.atacNormal());
                    System.out.println(enemy);
                    System.out.println(character);
                    System.out.println();
                } else {
                    throw new InvalidCommandException();
                }
            }
        }

        if(enemy.viataCurenta <= 0) {
            //map.pozitieCurenta.cellElement = new EmptyCell();
            Random random = new Random();
            int sansa = random.nextInt(101);
            if(sansa <= 80) {
                character.inventar.numarMonede += 4;
            }
        } else {
            System.out.println("Ai pierdut!");
            System.exit(0);
        }
        if(scanner.next().equals("P")) {
            map.goSouth(game);
            afisareMapa(map);
            System.exit(0);
        } else {
            throw new InvalidCommandException();
        }
    }*/
}
