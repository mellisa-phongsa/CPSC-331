//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//data of a maze

package A2;
public class MazeData {
    //2d array of Cell objects, this is the maze
    public Cell[][] maze;

    //number of rows in the maze
    public int numRows;

    //number of columns in the maze
    public int numCols;

    //constructor
    public MazeData(Cell[][] maze, int numRows, int numCols) {
        //initializes variables with values given in the parameters
        this.maze = maze;
        this.numRows = numRows;
        this.numCols = numCols;
    }

    //returns the maze
    public Cell[][] getMaze() {
        return maze;
    }

    //returns the number of rows
    public int getNumRows() {
        return numRows;
    }

    //returns the number of columns
    public int getNumCols() {
        return numCols;
    }
}
