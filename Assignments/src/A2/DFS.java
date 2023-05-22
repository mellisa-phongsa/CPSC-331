//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//Depth first search of a maze using a doubly linked implementation of a Stack

package A2;
import java.io.FileNotFoundException;
<<<<<<< HEAD
=======
import java.io.FileWriter;
import java.io.IOException;

>>>>>>> 35abcbd (Fixed stuff with trailqueue)
public class DFS {
    //declaring variables
    private static final char MOUSE = 'm';
    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private DLLStack<Cell> structure;
    private DLLQueue<Cell> trailQueue;
    private MazeLoader ml;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start constructor
    public DFS(String filePath) {
        //try to find file
        try {
            //instantiate mazeloader
            ml = new MazeLoader();

            //call function to load maze from file located at "filePath"
            MazeData mazeData = ml.loadMazeFromFile(filePath);

            //get maze
            maze = mazeData.getMaze();

            //get the number of rows
            numRows = mazeData.getNumRows();

            //get number of columns
            numCols = mazeData.getNumCols();
<<<<<<< HEAD

            //instantiate structure as a stack using a doubly linked list 
            structure = new DLLStack<>();

            //instantiate trailqueue as a cicular queue using a doubly linked list
            trailQueue = new DLLQueue<>();
        //catch if file isnt found    
=======
            //instantiate structure as a circular queue using a doubly linked list 
            structure = new DLLStack<Cell>();
            //instantiate trailqueue as a cicular queue using a doubly linked list
            trailQueue = new DLLQueue<Cell>();
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        } catch (FileNotFoundException e) {
            //print to terminal
            System.out.println("File not found: " + filePath);
            System.exit(1);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start solveMaze
    public void solveMaze() {
        //set startign cell to null
        Cell startCell = null;
<<<<<<< HEAD
=======
        boolean moreToSearch = true;

        // Finding the starting cell
        for (int row = 0; row < numRows; row++) { //start
>>>>>>> 35abcbd (Fixed stuff with trailqueue)

        //set boolean moreToSEarch to true
        boolean moreToSearch = true;

        //count number of steps taken by mouse
        int counter = 0;

        //iterate through each row
        for (int row = 0; row < numRows; row++) {
            //iterater through each column
            for (int col = 0; col < numCols; col++){
                //get cell at coordinates
                Cell cell = maze[row][col];

                //if cell is the mouse
                if (cell.type == MOUSE) {
                    //set this cell to be the starting cell
                    startCell = cell;

                    //break out of loop
                    break;
                }
            }
            //if starting cell is no longer null break out of the loop
            if (startCell != null) {
                break;
            }
<<<<<<< HEAD
        }
        //if startign cell is still null, the mouse wasnt found
=======

        } //end
        // if statement for if there is no starting cell (m)
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        if (startCell == null) {
            //print to terminal
            System.out.println("start cell (m) not found!");
            return;
        }
        //add the starting cell to the TOP of the stack
        structure.push(startCell);

        //instantiate cellchecker
        CellChecker cc = new CellChecker(maze, numRows, numCols, trailQueue);

<<<<<<< HEAD
        //loop while the stack is not empty and moreToSearch is true
        while(!structure.isEmpty() && moreToSearch) {
            //remove current cell from stack
=======
        // Search algorithm for stack
        while(!structure.isEmpty() && moreToSearch) { //start
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
            Cell currentCell = structure.pop();

            //mark current cell as visited
            cc.markVisited(currentCell);

            //add current cell to trail queue
            trailQueue.enqueue(currentCell);
<<<<<<< HEAD

            //print the maze
            ml.printMaze(maze, numRows, numCols,trailQueue);

            //if the current cell is cheese
=======
            ml.printMaze(maze, numRows, numCols, trailQueue);
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
            if (cc.isCheese(currentCell)) {
                //print to terminal
                System.out.println("Cheese (c) found!");

                //set boolean to false, no need to search
                moreToSearch = false;

                //break out of the loop
                break;
            }
<<<<<<< HEAD
            //check neighboring cells
            cc.checkNeighborCellStack(currentCell, -1, 0, '^', structure); //up
            cc.checkNeighborCellStack(currentCell, 1, 0, 'v', structure); //down
            cc.checkNeighborCellStack(currentCell, 0, -1, '<', structure); //left
            cc.checkNeighborCellStack(currentCell, 0, 1, '>', structure); //right

            //increment counter (number of steps)
            counter++;
        }

        //if cheese is not found
=======
            cc.checkNeighborCellStack(currentCell, -1, 0, '^', structure);
            cc.checkNeighborCellStack(currentCell, 1, 0, 'v', structure);
            cc.checkNeighborCellStack(currentCell, 0, -1, '<', structure);
            cc.checkNeighborCellStack(currentCell, 0, 1, '>', structure);
        } //end
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        if (moreToSearch) {
            //print to terminal
            System.out.println("Cheese (c) not reachable!");
        }
<<<<<<< HEAD
        //print to terminal how many steps it took for the mouse to find the cheese
        System.out.println("The mouse moved " + (counter) + " number of times");
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start main()
    public static void main(String[] args) {
        //pass in file name
        DFS dfs = new DFS("Maze.txt");

        //call solveMaze function
        dfs.solveMaze();

        //instantiate maze Loader
        MazeLoader ml = new MazeLoader();

        //save trail to output file
        ml.saveTrailToFile("trail.txt", dfs.trailQueue);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end
=======
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void main(String[] args) {
    DFS dfs = new DFS("Maze.txt");
    dfs.solveMaze();
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
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
}
    
