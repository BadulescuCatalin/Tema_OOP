package task1;



import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import org.json.*;


public class Game {
    private List <Account> accountsList ; // de implementat accounts
    private Map <String, ArrayList <String>> map ; // cellEnum e tipul celulei , lista e povestea
    private static Game instace = null;

    private Game() {
        accountsList = new ArrayList<>();
        map = new HashMap<>();
    }

    public static Game getInstace() {
        if(instace == null) {
            instace = new Game();
        }
        return instace;
    }

    public List<Account> getAccountsList() {
        return accountsList;
    }

    public Map<String, ArrayList<String>> getMap() {
        return map;
    }

    public void run() throws InvalidCommandException { // porneste jocu, fisiere json
        // de facut -> alege tipul de joc : CLI / GUI
        instace = getInstace();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Apasa 1 pentru jocul in CLI si 2 pentru jocul cu interfata grafica sau orice altceva pt jocul hardcodat");
        String path  = Paths.get("")
                .toAbsolutePath()
                .toString();
        path = path+"\\accounts.json";

            try {
                String content = Files.readString(Paths.get(path));
                JSONObject jsonObject = new JSONObject((content));
                JSONArray accounts = jsonObject.getJSONArray("accounts");
                for (int i = 0; i < accounts.length(); ++i) {
                    JSONObject obj = accounts.getJSONObject(i);
                    String email = obj.getJSONObject("credentials").getString("email");
                    String password = obj.getJSONObject("credentials").getString("password");
                    String name = obj.getString("name");
                    String tara = obj.getString("country");
                    JSONArray jocuriPref = obj.getJSONArray("favorite_games");
                    AlphabeticList jocuri = new AlphabeticList();
                    for (int j = 0; j < jocuriPref.length(); ++j) {
                        jocuri.add(jocuriPref.getString(j));

                    }
                    int mapsCompleted = Integer.parseInt(obj.getString("maps_completed"));
                    JSONArray characters = obj.getJSONArray("characters");
                    List<Character> caractere = new ArrayList<>();
                    for (int j = 0; j < characters.length(); ++j) {
                        JSONObject chObj = characters.getJSONObject(j);
                        String chNume = chObj.getString("name");
                        String chProfession = chObj.getString("profession");
                        int chLevel = Integer.parseInt(chObj.getString("level"));
                        int chExperience = chObj.getInt("experience");
                        CharacterFactory characterFactory = new CharacterFactory();
                        caractere.add(characterFactory.createUser(chProfession, chNume, chExperience, chLevel));

                    }
                    Account.Information.Builder builder = new Account.Information.Builder(new Credentials(email, password), name);
                    builder.tara(tara);
                    builder.jocuriPreferate(jocuri);
                    Account.Information info = builder.build();
                    accountsList.add(new Account(info, caractere, mapsCompleted));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String path2 = Paths.get("")
                    .toAbsolutePath()
                    .toString();
            path2 = path2 + "\\stories.json";
            try {
                String content = Files.readString(Paths.get(path2));
                JSONObject jsonObject = new JSONObject((content));
                JSONArray array = jsonObject.getJSONArray("stories");
                for (int i = 0; i < array.length(); ++i) {
                    JSONObject obj = array.getJSONObject(i);
                    String cellType = obj.getString("type");
                    String story = obj.getString("value");
                    if (!map.containsKey(cellType)) {
                        map.put(cellType, new ArrayList<>());
                    }
                    ArrayList<String> l = map.get(cellType);
                    l.add(story);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String s = scanner.next();
            if(s.equals("1")){ // ruleaza test
                // de facut autentificare + in clasele de testare
                // poate trebuie apelat aici si optiuni posibile
                String email;
                String password;
                List<Character> listaPersonaje = null;
                System.out.println("Introduce emailul si parola pentru a te loga in cont");
                while(listaPersonaje == null) {
                System.out.println("Email: ");
                email = scanner.next();
                System.out.println("Parola: ");
                password = scanner.next();

                    for (Account account : accountsList) {
                        if (account.information.getCredentials().getEmail().equals(email)
                                && account.information.getCredentials().getParola().equals(password)) {
                            listaPersonaje = account.personaje;
                        }
                    }
                    if (listaPersonaje == null) {
                        System.out.println("Nu exista acest cont, incerca din nou");
                    }
                }
                System.out.println("Alege un persoanj din lista");
                System.out.println();
                Character erou = null;
                int nr = 0;
                for(Character c: listaPersonaje) {
                    System.out.println("Apasa " + nr  + " pentru a selecta personajul:\n" + c.toString());
                    System.out.println();
                    nr++;
                }
                while(erou == null) {
                    String nrPers = scanner.next();
                    for (int i = 0; i < nr; ++i) {
                        if (nrPers.equals(Integer.toString(i))) {
                            erou = listaPersonaje.get(i);
                        }
                    }
                }
                erou.coordonateCurente = new Cell(0,0,new StartPoint(), true);
                Grid map = Grid.generareHarta(7,7,erou, erou.coordonateCurente);
                map.get(0).get(0).vizitat = true;
                map.pozitieCurenta = map.get(0).get(0);
                optiuniPosibile(map);

        } else if(s.equals("2")){
                Login login = new Login();

            //de facut GUI
        } else {
                String email;
                String password;
                List<Character> listaPersonaje = null;
                System.out.println("Introduce emailul si parola pentru a te loga in cont");
                while(listaPersonaje == null) {
                System.out.println("Email: ");
                email = scanner.next();
                System.out.println("Parola: ");
                password = scanner.next();

                    for (Account account : accountsList) {
                        if (account.information.getCredentials().getEmail().equals(email)
                                && account.information.getCredentials().getParola().equals(password)) {
                            listaPersonaje = account.personaje;
                        }
                    }
                    if (listaPersonaje == null) {
                        System.out.println("Nu exista acest cont, incercati din nou");
                    }
                }
                System.out.println("Alege un persoanj din lista");
                System.out.println();
                Character erou = null;
                int nr = 0;
                for(Character c: listaPersonaje) {
                    System.out.println("Apasa " + nr  + " pentru a selecta personajul:\n" + c.toString());
                    System.out.println();
                    nr++;
                }
                while(erou == null) {
                    String nrPers = scanner.next();
                    for (int i = 0; i < nr; ++i) {
                        if (nrPers.equals(Integer.toString(i))) {
                            erou = listaPersonaje.get(i);
                        }
                    }
                }
                erou.coordonateCurente = new Cell(0,0,new StartPoint(), true);
                Grid map = Grid.generareHartaGoala(5,5,erou, erou.coordonateCurente);
                System.out.println(erou);
                System.out.println();
                System.out.println("Apasa P pentru a trece la urmatoare actiune");
                map.pozitieCurenta = map.get(0).get(0);
                map.get(0).get(0).vizitat = true;
                map.get(0).get(3).cellElement = new Shop( new HealthPotion(1,1,50), new ManaPotion(1,1,15));
                map.get(1).get(3).cellElement = new Shop();
                map.get(2).get(0).cellElement = new Shop();
                map.get(4).get(4).cellElement = new StopPoint();
                map.get(3).get(4).cellElement = new Enemy();

                if(scanner.next().equals("P")) {
                    System.out.println("Aici incepe aventura ta!");
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goEast(this);
                    System.out.println();
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goEast(this);
                    System.out.println();
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goEast(this);
                    System.out.println();
                    showMap(map);
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
                    map.goEast(this);
                    System.out.println();
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goSouth(this);
                    System.out.println();
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goSouth(this);
                    System.out.println();
                    showMap(map);
                } else {
                    throw new InvalidCommandException();
                }

                if(scanner.next().equals("P")) {
                    map.goSouth(this);
                    System.out.println();
                    showMap(map);
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
                    map.goSouth(this);
                    showMap(map);
                    System.exit(0);
                } else {
                    throw new InvalidCommandException();
                }

            }
    }

    public void optiuniPosibile(Grid grid) throws InvalidCommandException {
        Scanner scanner = new Scanner(System.in);
        while( grid.pozitieCurenta.cellElement.getValue() != 'F') {
            if (grid.pozitieCurenta.cellElement instanceof Shop) {
               // afisPovesteCasuta(grid.pozitieCurenta);
                showMap(grid);
                System.out.println();
                System.out.println("Ai ajuns la un magazin");
                System.out.println();
                Shop shop = (Shop) grid.pozitieCurenta.cellElement;
                int shopSize = shop.listaPotiuni.size();
                System.out.println("Apasa 1 pentru a deschide magazinul");
                System.out.println();
                String decizia1 = scanner.next();
                if (decizia1.equals("1")) {
                    System.out.println(shop);
                    System.out.println(grid.personajCurent.inventar);
                    System.out.println();
                    while (shopSize > 0) {
                        System.out.println(shop);
                        System.out.println(grid.personajCurent.inventar);
                        for (int i = 0; i < shopSize; ++i) {
                            System.out.println("Apasa tasta " + i + " pentru a cumpara poriunea " + shop.listaPotiuni.get(i));
                        }
                        System.out.println("Apasa P pentru a iesi din magazi si a alege unde mergi mai departe");
                        System.out.println();
                        String s = scanner.next();
                        if (s.equals("P")) {
                            break;
                        }
                        for (int i = 0; i < shopSize; ++i) {
                            if (s.equals(Integer.toString(i))) {
                                grid.personajCurent.inventar.adaugaPotiuneInLista(shop.selectarePoriune(i));
                                shopSize--;
                                break;
                            }
                        }
                    }
                }
                showMap(grid);
                System.out.println();
                System.out.println("Apasa W pentru a merge in Vest");
                System.out.println("Apasa E pentru a merge in Est");
                System.out.println("Apasa N pentru a merge in Nord");
                System.out.println("Apasa S pentru a merge in Sud");
                System.out.println();
                String s = scanner.next();
                if (s.equals("W")) {
                    grid.goWest(this);
                } else if (s.equals("E")) {
                    grid.goEast(this);
                } else if (s.equals("N")) {
                    grid.goNorth(this);
                } else if (s.equals("S")) {
                    grid.goSouth(this);
                } else {
                    throw new InvalidCommandException();
                }
            } else if (grid.pozitieCurenta.cellElement instanceof Enemy) {
               // afisPovesteCasuta(grid.pozitieCurenta);
                showMap(grid);
                System.out.println();
                Enemy enemy = (Enemy) grid.pozitieCurenta.cellElement;
                Character erou = grid.personajCurent;
                if(enemy.viataCurenta <= 0) {
                    System.out.println("Acest inamic a fost deja infrant");
                }
                while(enemy.viataCurenta > 0 && erou.viataCurenta > 0) {
                    System.out.println(erou);
                    System.out.println(enemy);

                    System.out.println();

                    System.out.println("Apasa A pentru atac normal");
                    System.out.println("Apasa 1 pentru a folosi prima abilitate " + erou.abilitati.get(0).toString());
                    System.out.println("Apasa 2 pentru a folosi a doua abilitate " + erou.abilitati.get(1).toString());
                    System.out.println("Apasa 3 pentru a folosi a treia abilitate " + erou.abilitati.get(2).toString());
                    for(int i=0; i<erou.inventar.potiuni.size(); ++i) {
                        System.out.println("Apasa " + (4+i) + "pentru a folosi a " + (4+i) + "-a potiune:" + erou.inventar.potiuni.get(i).toString());
                    }
                    String optiune = scanner.next();
                    if(optiune.equals("A")) {
                        erou.getDamage(enemy,erou.atacNormal());
                    } else if(optiune.equals("1")) {
                        if(erou.folosesteAbilitatea(erou.abilitati.get(0),enemy) == -1) {
                            System.out.println("Alege alta optiune");
                        } else {
                            erou.getDamage(enemy, erou.folosesteAbilitatea(erou.abilitati.get(0), enemy));
                        }
                    } else if(optiune.equals("2")) {
                        if(erou.folosesteAbilitatea(erou.abilitati.get(1),enemy) == -1) {
                            System.out.println("Alege alta optiune");
                        } else {
                            erou.getDamage(enemy, erou.folosesteAbilitatea(erou.abilitati.get(1), enemy));
                        }
                    } else if(optiune.equals("3")) {
                        if(erou.folosesteAbilitatea(erou.abilitati.get(2),enemy) == -1) {
                            System.out.println("Alege alta optiune");
                        } else {
                            erou.getDamage(enemy, erou.folosesteAbilitatea(erou.abilitati.get(2), enemy));
                        }
                    } else {
                        boolean ok = false;
                        for(int i=0; i<erou.inventar.potiuni.size(); ++i) {
                            Potion p = erou.inventar.potiuni.get(i);
                            if(optiune.equals(Integer.toString(i+4))) {
                                p.folosestePotiunea(erou);
                                ok =true;
                            }
                        }
                        if(!ok) {
                            throw new InvalidCommandException();
                        }
                    }
                    System.out.println();
                    if(enemy.viataCurenta > 0) {
                        System.out.println("Inamicul a atacat");
                        enemy.getDamage(erou, 0);
                       // System.out.println(erou);
                        //System.out.println(enemy);
                        System.out.println();
                    }
                }
                if(erou.viataCurenta <= 0) {
                    System.out.println("Ai pierdut");
                    System.exit(0);
                } else {
                    Random random = new Random();
                    int sansa = random.nextInt(101);
                    if(sansa <= 80) {
                        erou.inventar.numarMonede += 4;
                    }
                    System.out.println("Ai invins inamicul, acum iti poti continua drumul");
                    System.out.println();
                    showMap(grid);
                    //grid.pozitieCurenta.cellElement = new EmptyCell();
                    System.out.println("Apasa W pentru a merge in Vest");
                    System.out.println("Apasa E pentru a merge in Est");
                    System.out.println("Apasa N pentru a merge in Nord");
                    System.out.println("Apasa S pentru a merge in Sud");
                    System.out.println();
                    String s = scanner.next();
                    if (s.equals("W")) {
                        grid.goWest(this);
                    } else if (s.equals("E")) {
                        grid.goEast(this);
                    } else if (s.equals("N")) {
                        grid.goNorth(this);
                    } else if (s.equals("S")) {
                        grid.goSouth(this);
                    } else {
                        throw new InvalidCommandException();
                    }
                }
            } else if(grid.pozitieCurenta.cellElement instanceof EmptyCell || grid.pozitieCurenta.cellElement instanceof StartPoint) {
                //afisPovesteCasuta(grid.pozitieCurenta);
                showMap(grid);
                System.out.println();
                System.out.println("Apasa W pentru a merge in Vest");
                System.out.println("Apasa E pentru a merge in Est");
                System.out.println("Apasa N pentru a merge in Nord");
                System.out.println("Apasa S pentru a merge in Sud");
                System.out.println();
                String s = scanner.next();
                if (s.equals("W")) {
                    grid.goWest(this);
                } else if (s.equals("E")) {
                    grid.goEast(this);
                } else if (s.equals("N")) {
                    grid.goNorth(this);
                } else if (s.equals("S")) {
                    grid.goSouth(this);
                } else {
                    throw new InvalidCommandException();
                }
            }
        }
        afisPovesteCasuta(grid.pozitieCurenta);
        showMap(grid);
        System.out.println();
    }

    public void afisPovesteCasuta(Cell cell) {
        Random random = new Random();
        if(!cell.vizitat && cell.cellElement instanceof Shop) {
            List<String> storiesList = map.get("SHOP");
            System.out.println(storiesList.get(random.nextInt(storiesList.size())));
        } else if(!cell.vizitat && cell.cellElement instanceof Enemy) {
            List<String> storiesList = map.get("ENEMY");
            System.out.println(storiesList.get(random.nextInt(storiesList.size())));
        } else if(!cell.vizitat && cell.cellElement instanceof EmptyCell) {
            List<String> storiesList = map.get("EMPTY");
            System.out.println(storiesList.get(random.nextInt(storiesList.size())));
        } else if(!cell.vizitat && cell.cellElement instanceof StartPoint) {
            System.out.println("Aici incepe aventura ta!");
        } else if(!cell.vizitat && cell.cellElement instanceof StopPoint){
            System.out.println("Felicitari, ai terminat mapa!");
        }
    }

     public void showMap(Grid map) {
        for(int i=0; i<map.latime; ++i) {
            for (int j=0; j< map.lungime; ++j) {
                if(map.get(i).get(j).vizitat ) {
                 if(map.get(i).get(j) == map.pozitieCurenta) {
                     if(map.get(i).get(j).cellElement.getValue() == 'N' || map.get(i).get(j).cellElement.getValue() == 'P') {
                         System.out.print("P ");
                     } else {
                         System.out.print("P" + map.get(i).get(j).cellElement.getValue() + " ");
                     }
                 } else {
                     if(map.get(i).get(j).cellElement.getValue() != 'P') {
                         System.out.print(map.get(i).get(j).cellElement.getValue() + " ");
                     } else {
                         System.out.print("N ");
                     }
                 }
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}
