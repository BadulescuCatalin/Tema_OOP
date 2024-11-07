package task1;

public class Cell {
    int x;
    int y;
    enum CellEnum {
        Empty,
        Enemy,
        Shop,
        Finish
    };
    CellElement cellElement;// reprezinta shop / enemy
    boolean vizitat;
    public Cell (int x, int y,  CellElement cellElement, boolean vizitat) {
        this.x = x;
        this.y = y;
        this.cellElement = cellElement;
        this.vizitat = vizitat;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                ", cellElement=" + cellElement +
                ", vizitat=" + vizitat +
                '}';
    }
}
