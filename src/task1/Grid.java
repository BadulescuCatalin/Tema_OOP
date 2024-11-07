package task1;

import java.util.*;
import java.util.ArrayList;

public class Grid extends ArrayList<ArrayList<Cell>> {
    int lungime;
    int latime;
    Character personajCurent; // ar trebui Character
    Cell pozitieCurenta;
    private Grid(int lungime, int latime, Character personajCurent, Cell pozitieCurenta) {
        super(latime);
        this.pozitieCurenta = pozitieCurenta;
        this.lungime = lungime;
        this.latime = latime;
        this.personajCurent = personajCurent;
    }
    static Grid generareHarta(int latime, int lungime,Character personajCurent, Cell pozitieCurenta) {
        Random random = new Random();
        ArrayList<ArrayList<Cell>> harta = new ArrayList<>();// 2 magazine si 4 inamici
        Grid grid = new Grid(lungime, latime, personajCurent, pozitieCurenta);
        for(int i=0; i<latime; ++i) {
           ArrayList<Cell> arrayList = new ArrayList<>();
           for(int j=0; j<lungime; ++j) {
               arrayList.add(new Cell(i,j,null,false));
           }
           grid.add(arrayList);
        }
        grid.get(0).get(0).cellElement = new StartPoint();
        int nrMagazine = 0;
        while (nrMagazine < 2) {
            int x = random.nextInt(latime);
            int y = random.nextInt(lungime);
            if(grid.get(x).get(y).cellElement == null && x != 0 && y != 0) {
                grid.get(x).get(y).cellElement = new Shop();
                nrMagazine++;
            }
        }
        int nrInamici = 0;
        while (nrInamici < 4) {
            int x = random.nextInt(latime);
            int y = random.nextInt(lungime);
            if(grid.get(x).get(y).cellElement == null && x != 0 && y != 0) {
                grid.get(x).get(y).cellElement = new Enemy();
                nrInamici++;
            }
        }
        int stopPoint = 0;
        while (stopPoint < 1) {
            int x = random.nextInt(latime);
            int y = random.nextInt(lungime);
            if(grid.get(x).get(y).cellElement == null && x != 0 && y != 0) {
                grid.get(x).get(y).cellElement = new StopPoint();
                stopPoint++;
            }
        }

        for(int i=0; i<latime; ++i) {
            ArrayList<Cell> arrayList = new ArrayList<>();
            for(int j=0; j<lungime; ++j) {
                if(grid.get(i).get(j).cellElement == null) {
                    grid.get(i).get(j).cellElement = new EmptyCell();
                }
            }
            grid.add(arrayList);
        }
        return grid;
    }

    static Grid generareHartaGoala(int latime, int lungime,Character personajCurent, Cell pozitieCurenta) { // nu stiu daca trb sa fie arraylist sau void

        Grid grid = new Grid(lungime, latime, personajCurent, pozitieCurenta);
        for(int i=0; i<latime; ++i) {
            ArrayList<Cell> arrayList = new ArrayList<>();
            for(int j=0; j<lungime; ++j) {
                arrayList.add(new Cell(i,j,new EmptyCell(),false));
            }
            grid.add(arrayList);
        }
        grid.get(0).get(0).cellElement = new StartPoint();

        return grid;
    }

    public void goNorth(Game game) {
        if(pozitieCurenta.x - 1 >= 0) {
            pozitieCurenta = get(pozitieCurenta.x-1).get(pozitieCurenta.y);
            personajCurent.coordonateCurente = pozitieCurenta;
            Random random = new Random();
            if(!pozitieCurenta.vizitat) {
                if(pozitieCurenta.cellElement.getValue() == 'N') {
                    int random1 = random.nextInt(101);
                    if(random1 <= 20) {
                        personajCurent.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                    }
                }
            }// de completat pt inamic si restul cazurilor
            if(pozitieCurenta.vizitat == false) {
                game.afisPovesteCasuta(pozitieCurenta);
                pozitieCurenta.vizitat = true;
            }
        } else {
            System.out.println("Nu se poate merge spre Nord");
        }

    }
    public void goSouth(Game game) {
        if(pozitieCurenta.x + 1 < latime) {
            pozitieCurenta = get(pozitieCurenta.x+1).get(pozitieCurenta.y);
            personajCurent.coordonateCurente = pozitieCurenta;
            Random random = new Random();
            if(!pozitieCurenta.vizitat) {
                if(pozitieCurenta.cellElement.getValue() == 'N') {
                    int random1 = random.nextInt(101);
                    if(random1 <= 20) {
                        personajCurent.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                    }
                }
            }
            if(pozitieCurenta.vizitat == false) {
                game.afisPovesteCasuta(pozitieCurenta);
                pozitieCurenta.vizitat = true;
            }
        } else {
            System.out.println("Nu se poate merge spre Sud");
        }
    }
    public void goWest(Game game) {
        if(pozitieCurenta.y - 1 >= 0) {
            pozitieCurenta = get(pozitieCurenta.x).get(pozitieCurenta.y-1);
            personajCurent.coordonateCurente = pozitieCurenta;
            Random random = new Random();
            if(!pozitieCurenta.vizitat) {
                if(pozitieCurenta.cellElement.getValue() == 'N') {
                    int random1 = random.nextInt(101);
                    if(random1 <= 20) {
                        personajCurent.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                    }
                }
            }
            if(pozitieCurenta.vizitat == false) {
                game.afisPovesteCasuta(pozitieCurenta);
                pozitieCurenta.vizitat = true;
            }
        } else {
            System.out.println("Nu se poate merge spre Vest");
        }
        //personajCurent.inventar.numarMonede casuta goala 20% inamic 80%
    }
    public void goEast(Game game) {
        if(pozitieCurenta.y + 1 < lungime) {
            pozitieCurenta = get(pozitieCurenta.x).get(pozitieCurenta.y+1);
            personajCurent.coordonateCurente = pozitieCurenta;
            Random random = new Random();
            if(!pozitieCurenta.vizitat) {
                if(pozitieCurenta.cellElement.getValue() == 'N') {
                    int random1 = random.nextInt(101);
                    if(random1 <= 20) {
                        personajCurent.inventar.numarMonede += 1; // gaseste moneda cu sansa de 20% in celula empty
                    }
                }
            }
            if(pozitieCurenta.vizitat == false) {
                game.afisPovesteCasuta(pozitieCurenta);
                pozitieCurenta.vizitat = true;
            }
        } else {
            System.out.println("Nu se poate merge spre Est");
        }
    }

}
