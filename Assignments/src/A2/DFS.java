//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//Depth First Search Using a Doubly Linked List Implementation of a stack
package A2;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DFS {
    private static final char WALL = '1';
    private static final char SPACE = '0';
    private static final char MOUSE = 'm';
    private static final char CHEESE = 'c';

    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private DLLStack<Cell> structure;
    private DLLQueue<Cell> trailQueue;
    private MazeLoader ml;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DFS(String filePath) {
        try {
            ml = new MazeLoader();
            //call function to load maze from file located at "filePath"
            MazeData mazeData = ml.loadMazeFromFile(filePath);
            maze = mazeData.getMaze();
            numRows = mazeData.getNumRows();
            numCols = mazeData.getNumCols();
            //instantiate structure as a circular queue using a doubly linked list 
            structure = new DLLStack<Cell>();
            //instantiate trailqueue as a cicular queue using a doubly linked list
            trailQueue = new DLLQueue<Cell>();
        } catch (FileNotFoundException e) {
            //print to terminal
            System.out.println("File not found: " + filePath);
            System.exit(1);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void solveMaze() {
        Cell startCell = null;
        boolean moreToSearch = true;

        // Finding the starting cell
        for (int row = 0; row < numRows; row++) { //start

            for (int col = 0; col < numCols; col++){ //start
                Cell cell = maze[row][col];

                if (cell.type == MOUSE) {
                    startCell = cell;
                    break;
                }

            } //end

            if (startCell != null) {
                break;
            }

        } //end
        // if statement for if there is no starting cell (m)
        if (startCell == null) {
            System.out.println("start cell (m) not found!");
            return;
        }
        structure.push(startCell);

        CellChecker cc = new CellChecker(maze, numRows, numCols, trailQueue);

        // Search algorithm for stack
        while(!structure.isEmpty() && moreToSearch) { //start
            Cell currentCell = structure.pop();
            cc.markVisited(currentCell);
            trailQueue.enqueue(currentCell);
            ml.printMaze(maze, numRows, numCols, trailQueue);
            if (cc.isCheese(currentCell)) {
                System.out.println("Cheese (c) found!");
                moreToSearch = false;
                break;
            }
            cc.checkNeighborCellStack(currentCell, -1, 0, '^', structure);
            cc.checkNeighborCellStack(currentCell, 1, 0, 'v', structure);
            cc.checkNeighborCellStack(currentCell, 0, -1, '<', structure);
            cc.checkNeighborCellStack(currentCell, 0, 1, '>', structure);
        } //end
        if (moreToSearch) {
            System.out.println("Cheese (c) not reachable!");
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void main(String[] args) {
    // Enter your specific file path to the maze file here
    DFS dfs = new DFS("/Users/hassan/Desktop/CPSC-331/Assignments/src/A2/Maze.txt");
    // Using the solve maze function on the DFS class
    dfs.solveMaze();
    // Printing the cell coordinates from the trailqueue and the lenght of the trailqueue
    int length = 0;
    System.out.println("Depth First Search");
    while (!dfs.trailQueue.isEmpty()){
        Cell cell = dfs.trailQueue.dequeue();
        length++;
        System.out.print("(" + cell.row + "," + cell.col + ") ");
    }
    System.out.println();
    System.out.println("The mouse moved " + length + " Times.");
}
}


    
