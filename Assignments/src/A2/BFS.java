//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//Breadth First Search Using a Doubly Linked List Implementation of a Circular Queue

package A2;
import java.io.FileNotFoundException;
public class BFS {

    private static final char MOUSE = 'm';

    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private DLLQueue<Cell> structure;
    private DLLQueue<Cell> trailQueue;
    private MazeLoader ml;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start Constructor
    public BFS(String filePath) {
        //try to find file
        try {
            //instantiate mazeloader
            ml = new MazeLoader();
            
            //call function to load maze from file located at "filePath" and store maze data in var "mazeData"
            MazeData mazeData = ml.loadMazeFromFile(filePath);
            
            //get maze
            maze = mazeData.getMaze();
            
            //get number of rows
            numRows = mazeData.getNumRows();
            
            //get number of columns
            numCols = mazeData.getNumCols();
            
            //instantiate structure as a circular queue using a doubly linked list 
            structure = new DLLQueue<>();
            
            //instantiate trailqueue as a cicular queue using a doubly linked list
            trailQueue = new DLLQueue<>();

        //catch if file is not found
        } catch (FileNotFoundException e) {
            //print to terminal
            System.out.println("File not found: " + filePath);
            System.exit(1);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start solveMaze method
    public void solveMaze() {
        //initialize starting cell to be null and moretosearch to true
        Cell startCell = null;
        boolean moreToSearch = true;
        int counter = 0;

        //iterate through each row and column
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++){
                //get cell from specific row and col from the maze
                Cell cell = maze[row][col];

                //if cell type is the mouse
                if (cell.type == MOUSE) {
                    //set starting to cell to this cell
                    startCell = cell;

                    //break out of the loop
                    break;
                }
            }
            //if the mouse is found break out of the loop
            if (startCell != null) {
                break;
            }
        }

        //if the mouse is not found 
        if (startCell == null) {
            //print to terminal
            System.out.println("start cell (m) not found!");
            return;
        }

        //add the starting cell to the queue
        structure.enqueue(startCell);
        //instantiate CellChecker class
        CellChecker cc = new CellChecker(maze, numRows, numCols, trailQueue);

        //while the queue (structure) is not empty and moreToSearch is true
        while(!structure.isEmpty() && moreToSearch) {
            //remove currentCell from the queue
            Cell currentCell = structure.dequeue();

            //mark currentCell as visisted
            cc.markVisited(currentCell);
            ml.printMaze(maze, numRows, numCols,trailQueue);
            if (cc.isCheese(currentCell)) {
                //print to terminal
                System.out.println("Cheese (c) found!");

                //set moreToSearch to false
                moreToSearch = false;

                //break out of the loop
                break;
            }
            cc.checkNeighborCellQueue(currentCell, -1, 0, '^', structure);
            cc.checkNeighborCellQueue(currentCell, 1, 0, 'v', structure);
            cc.checkNeighborCellQueue(currentCell, 0, -1, '<', structure);
            cc.checkNeighborCellQueue(currentCell, 0, 1, '>', structure);
            counter++;
        } //end
        if (moreToSearch) {
            //print to terminal
            System.out.println("Cheese (c) not reachable!");
        }
        System.out.println("The mouse moved " + (counter) + " number of times");
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Start main method
    public static void main(String[] args) {
        //pass filename into constructor
        BFS bfs = new BFS("/Users/hassan/Desktop/CPSC-331/Assignments/src/Maze.txt");

        //call solveMaze method
        bfs.solveMaze();

        //instantiate MazeLoader class
        MazeLoader ml = new MazeLoader();

        //save trail to output file
        ml.saveTrailToFile("trail.txt", bfs.trailQueue);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

}
