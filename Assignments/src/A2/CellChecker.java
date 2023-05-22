//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//this checks the current and neighboring cells

package A2;
public class CellChecker {
    //declaring variables
    private Cell[][] maze;
    private int numRows;
    private int numCols;
<<<<<<< HEAD
    private DLLQueue<Cell> trialQueue;
=======
    private DLLQueue<Cell> trailQueue;

>>>>>>> 35abcbd (Fixed stuff with trailqueue)
    private static final char WALL = '1';
    private static final char SPACE = '0';
    private static final char CHEESE = 'c';

<<<<<<< HEAD
    //constructor
    public CellChecker(Cell[][] maze, int numRows, int numCols, DLLQueue<Cell> trailQueue) {
        //initializing variables
=======
    public CellChecker(Cell[][] maze, int numRows, int numCols, DLLQueue<Cell> trailQueue) {
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        this.maze = maze;
        this.numRows = numRows;
        this.numCols = numCols;
        this.trailQueue = trailQueue;
    }

<<<<<<< HEAD
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start checkNeighborCellQueue method
=======
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

<<<<<<< HEAD
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start checkNeighborCellStack
=======
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start of checking and marking cell methods
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

<<<<<<< HEAD
    //mark the mouses' trail
    public void markTrail(Cell cell, char trailSymbol, DLLQueue<Cell> trailQueue) {
        cell.type = trailSymbol;
        trailQueue.enqueue(cell);
    }

    //unmark the trail
    public void unmarkTrail(Cell cell) {
        cell.type = SPACE;
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start of dead end methods
    //check if up is a dead end
    private boolean isupDeadEnd(Cell cell) {
        //get coordinates of current cell
        int row = cell.row;
        int col = cell.col;
        //wall counter
        int numWalls = 0;
    
        //Count the number of neighboring walls
        //check if there is a wall above current cell
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            //increment number of walls
            numWalls++;
        }

        //check if there is a wall to the left of the current cell
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            //increment
            numWalls++;
        }

        //check if there is a wall to the right of current cell
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            //increment
=======
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
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
<<<<<<< HEAD
    //check if down is a dead end
    private boolean isdownDeadEnd(Cell cell) {
        //get cooredinates
=======
    //checking if the next down cell is a dead end
    private boolean isdownDeadEnd(Cell cell) {
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
<<<<<<< HEAD
        //check if down is a wall
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }

        //check if left is a wall
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }

        //check if right is a wall
=======
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
<<<<<<< HEAD

    //check if left is a dead end
    private boolean isleftDeadEnd(Cell cell) {
        //get coordinates
=======
    // checking if the next left cell is a dead end
    private boolean isleftDeadEnd(Cell cell) {
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
<<<<<<< HEAD
        //check if up is a wall
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }

        //check if down is a wall
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }

        //check if right is a wall
=======
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        if (isValidPosition(row, col - 1) && maze[row][col - 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
<<<<<<< HEAD

    //check if right is a dead end
    private boolean isrightDeadEnd(Cell cell) {
        //get coordinates
=======
    // checking if the next right cell is a dead end
    private boolean isrightDeadEnd(Cell cell) {
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        int row = cell.row;
        int col = cell.col;
        int numWalls = 0;
    
        // Count the number of neighboring walls
<<<<<<< HEAD
        //is up a wall
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }

        //is down a wall
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }

        //id left a wall
=======
        if (isValidPosition(row - 1, col) && maze[row - 1][col].type == WALL) {
            numWalls++;
        }
        if (isValidPosition(row + 1, col) && maze[row + 1][col].type == WALL) {
            numWalls++;
        }
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
        if (isValidPosition(row, col + 1) && maze[row][col + 1].type == WALL) {
            numWalls++;
        }
    
        // Cell is a dead end if it has three neighboring walls
        return numWalls == 3;
    }
<<<<<<< HEAD
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end
=======
>>>>>>> 35abcbd (Fixed stuff with trailqueue)
}
