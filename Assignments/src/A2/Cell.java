package A2;

public class Cell {
    int row;
    int col;
    char type;
    boolean visited;

    public Cell(int row, int col, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.visited = false;
    }
}
