package A2;
import java.io.FileNotFoundException;

//Breadth First Search Using a Doubly Linked List Implementation of a Circular Queue
public class BFS {

    private static final char MOUSE = 'm';

    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private DLLQueue<Cell> structure;
    private DLLQueue<Cell> trailQueue;
    private MazeLoader ml;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public BFS(String filePath) {
        try {
            ml = new MazeLoader();
            //call function to load maze from file located at "filePath"
            MazeData mazeData = ml.loadMazeFromFile(filePath);
            maze = mazeData.getMaze();
            numRows = mazeData.getNumRows();
            numCols = mazeData.getNumCols();
            //instantiate structure as a circular queue using a doubly linked list 
            structure = new DLLQueue<>();
            //instantiate trailqueue as a cicular queue using a doubly linked list
            trailQueue = new DLLQueue<>();
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
        if (startCell == null) {
            System.out.println("start cell (m) not found!");
            return;
        }
        structure.enqueue(startCell);

        CellChecker cc = new CellChecker(maze, numRows, numCols, trailQueue);

        while(!structure.isEmpty() && moreToSearch) { //start
            Cell currentCell = structure.dequeue();
            cc.markVisited(currentCell);
            ml.printMaze(maze, numRows, numCols);
            if (cc.isCheese(currentCell)) {
                System.out.println("Cheese (c) found!");
                moreToSearch = false;
                break;
            }
            cc.checkNeighborCell(currentCell, -1, 0, '^', structure);
            cc.checkNeighborCell(currentCell, 1, 0, 'v', structure);
            cc.checkNeighborCell(currentCell, 0, -1, '<', structure);
            cc.checkNeighborCell(currentCell, 0, 1, '>', structure);
        } //end
        if (moreToSearch) {
            System.out.println("Cheese (c) not reachable!");
        }
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        BFS bfs = new BFS("Maze.txt");
        bfs.solveMaze();
        MazeLoader ml = new MazeLoader();
        ml.saveTrailToFile("trail.txt", bfs.trailQueue);
    }
}
