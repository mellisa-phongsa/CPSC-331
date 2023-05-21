package A2;

public class MazeData {
    public Cell[][] maze;
    public int numRows;
    public int numCols;

    public MazeData(Cell[][] maze, int numRows, int numCols) {
        this.maze = maze;
        this.numRows = numRows;
        this.numCols = numCols;
    }
    public Cell[][] getMaze() {
        return maze;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }
}
