package task1;
// aici se porneste jocul
public class TestareMapaRandomCuOptiuni {

    public static void main(String[] args) throws InvalidCommandException {
        Game game = Game.getInstace();
        game.run();
        /*Character pers = game.getAccountsList().get(1).personaje.get(1);
        pers.coordonateCurente = new Cell(0,0,new StartPoint(), true);
        Grid map = Grid.generareHarta(7,7,pers, pers.coordonateCurente);
        map.get(0).get(0).vizitat = true;
        map.pozitieCurenta = map.get(0).get(0);
        game.optiuniPosibile(map);*/
    }
}
