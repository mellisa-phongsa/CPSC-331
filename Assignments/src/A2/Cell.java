//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//cell within the maze

package A2;
public class Cell {
    //initializing variables
    int row;
    int col;
    char type;
    boolean visited;

    //constructor
    public Cell(int row, int col, char type) {
        //coordinates of the cell
        this.row = row;
        this.col = col;

        //character variable for the type or content of the cell
        this.type = type;

        //boolean to check if cell was visited or not
        this.visited = false;
    }
}
