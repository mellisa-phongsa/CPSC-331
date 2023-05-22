//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//Cell checker class to check the neighboring cells of the currentcell
package A2;
public class CellChecker {

    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private DLLQueue<Cell> trailQueue;

    private static final char WALL = '1';
    private static final char SPACE = '0';
    private static final char MOUSE = 'm';
    private static final char CHEESE = 'c';

    public CellChecker(Cell[][] maze, int numRows, int numCols, DLLQueue<Cell> trailQueue) {
        this.maze = maze;
        this.numRows = numRows;
        this.numCols = numCols;
        this.trailQueue = trailQueue;
    }

    public void checkNeighborCellQueue(Cell currentCell, int rowOffset, int colOffset, char trailSymbol, DLLQueue<Cell> structure) {
        int row = currentCell.row + rowOffset;
        int col = currentCell.col + colOffset;

        if (isValidPosition(row, col)) {
            Cell neighbor = maze[row][col];
            if (!isVisited(neighbor) && isSpace(neighbor)) {
                structure.enqueue(neighbor);
                currentCell.type = trailSymbol;
                
            }
            if(isrightDeadEnd(currentCell)) {
                currentCell.type = '<';
            }
            if(isleftDeadEnd(currentCell)) {
                currentCell.type = '>';
            }
            if (isupDeadEnd(currentCell)) {
                currentCell.type = 'v';
            }
            if(isdownDeadEnd(currentCell)) {
                currentCell.type = '^';
            }
        }
    }

    public void checkNeighborCellStack(Cell currentCell, int rowOffset, int colOffset, char trailSymbol, DLLStack<Cell> structure) {
        int row = currentCell.row + rowOffset;
        int col = currentCell.col + colOffset;

        if (isValidPosition(row, col)) {
            Cell neighbor = maze[row][col];
            if (!isVisited(neighbor) && isSpace(neighbor)) {
                structure.push(neighbor);
                currentCell.type = trailSymbol;
                
            }
            if(isrightDeadEnd(currentCell)) {
                currentCell.type = '<';
            }
            if(isleftDeadEnd(currentCell)) {
                currentCell.type = '>';
            }
            if (isupDeadEnd(currentCell)) {
                currentCell.type = 'v';
            }
            if(isdownDeadEnd(currentCell)) {
                currentCell.type = '^';
            }
        }
    }

    //check if given row and col values fall withint the valid range of positions in the maze
    public boolean isValidPosition(int row, int col) {
        //returns true if row is greater than or equal to 0 and less than the total number of rows
        //and if col is greater than or equal to 0 and less than the total number of columns
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    //checks if the given cell is a 0 (SPACE) or c (CHEESE)
    public boolean isSpace(Cell cell) {
        //returns true if either or are true
        return cell.type == SPACE || cell.type == CHEESE;
    }

    //checks if given cell is a c (CHEESE)
    public boolean isCheese(Cell cell) {
        //returns true
        return cell.type == CHEESE;
    }

    //marks cell as visited
    public void markVisited(Cell cell) {
        cell.visited = true;
    }

    //checks if cell is visited
    public boolean isVisited(Cell cell) {
        return cell.visited;
    }

    //checking if the next up cell is a dead end
    private boolean isupDeadEnd(Cell cell) {
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
    //checking if the next down cell is a dead end
    private boolean isdownDeadEnd(Cell cell) {
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
    // checking if the next left cell is a dead end
    private boolean isleftDeadEnd(Cell cell) {
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
    // checking if the next right cell is a dead end
    private boolean isrightDeadEnd(Cell cell) {
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
}


