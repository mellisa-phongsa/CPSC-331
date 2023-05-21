import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

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

class Stack {
    private class Node {
        Cell data;
        Node prev;
        Node next;

        public Node(Cell data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node top;

    public Stack() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(Cell data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top.prev = newNode;
            top = newNode;
        }
    }

    public Cell pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }

        Cell data = top.data;
        top = top.next;
        if (top != null) {
            top.prev = null;
        }

        return data;
    }
}

class Queue {
    private class Node {
        Cell data;
        Node next;

        public Node(Cell data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;
    private Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(Cell data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public Cell dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        Cell data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }

        return data;
    }
}

class MazeSolver {
    private static final char WALL = '1';
    private static final char SPACE = '0';
    private static final char MOUSE = 'm';
    private static final char CHEESE = 'c';

    private Cell[][] maze;
    private int numRows;
    private int numCols;
    private Stack structure;
    private Queue trailQueue;

    public MazeSolver(String filePath) {
        try {
            loadMazeFromFile(filePath);
            structure = new Stack();
            trailQueue = new Queue();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            System.exit(1);
        }
    }

    private void loadMazeFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        numRows = 0;
        numCols = 0;

        // Determine maze dimensions
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            numCols = line.length();
            numRows++;
        }

        scanner.close();

        // Create maze array
        maze = new Cell[numRows][numCols];

        // Load maze from file
        scanner = new Scanner(file);
        for (int row = 0; row < numRows; row++) {
            String line = scanner.nextLine();
            for (int col = 0; col < numCols; col++) {
                char symbol = line.charAt(col);
                maze[row][col] = new Cell(row, col, symbol);
            }
        }

        scanner.close();
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < numCols;
    }

    private boolean isSpace(Cell cell) {
        return cell.type == SPACE || cell.type == CHEESE;
    }

    private boolean isCheese(Cell cell) {
        return cell.type == CHEESE;
    }

    private void markVisited(Cell cell) {
        cell.visited = true;
    }

    private boolean isVisited(Cell cell) {
        return cell.visited;
    }

    private void markTrail(Cell cell, char trailSymbol) {
        cell.type = trailSymbol;
        trailQueue.enqueue(cell);
    }


    private void unmarkTrail(Cell cell) {
        cell.type = SPACE;
    }

    private void printMaze() {
        char[][] mazeCopy = new char[numRows][numCols];
    
        // Copy the original maze
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                mazeCopy[row][col] = maze[row][col].type;
            }
        }
    
        // Update the maze with trail movements
        while (!trailQueue.isEmpty()) {
            Cell cell = trailQueue.dequeue();
            mazeCopy[cell.row][cell.col] = cell.type;
        }
    
        // Print the updated maze
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                System.out.print(mazeCopy[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    private void saveTrailToFile(String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            while (!trailQueue.isEmpty()) {
                Cell cell = trailQueue.dequeue();
                writer.println(cell.row + "," + cell.col + "," + cell.type);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving trail to file: " + e.getMessage());
        }
    }

    public void solveMaze() {
        Cell startCell = null;
        boolean moreToSearch = true;
    
        // Find the start cell
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Cell cell = maze[row][col];
                if (cell.type == MOUSE) {
                    startCell = cell;
                    break;
                }
            }
            if (startCell != null) {
                break;
            }
        }
    
        if (startCell == null) {
            System.out.println("Start cell (m) not found.");
            return;
        }
    
        structure.push(startCell);
    
        while (!structure.isEmpty() && moreToSearch) {
            Cell currentCell = structure.pop();
            markVisited(currentCell);
            trailQueue.enqueue(currentCell);
            printMaze();
    
            if (isCheese(currentCell)) {
                System.out.println("Cheese (c) found!");
                moreToSearch = false;
                break;
            }
    
            int row = currentCell.row;
            int col = currentCell.col;
            boolean hasUnvisitedNeighbor = false;
    
            // Check all neighbor cells
            // Up
            if (isValidPosition(row - 1, col)) {
                Cell neighbor = maze[row - 1][col];
                if (!hasUnvisitedNeighbor) {
                    markTrail(currentCell, 'v');
                }
                if (!isVisited(neighbor) && isSpace(neighbor)) {
                    hasUnvisitedNeighbor = true;
                    markTrail(currentCell, '^');
                    structure.push(neighbor);
                }
            }
    
            // Down
            if (isValidPosition(row + 1, col)) {
                Cell neighbor = maze[row + 1][col];
                if (!hasUnvisitedNeighbor) {
                    markTrail(currentCell, '^');
                }
                if (!isVisited(neighbor) && isSpace(neighbor)) {
                    hasUnvisitedNeighbor = true;
                    markTrail(currentCell, 'v');
                    structure.push(neighbor);
                }
            }
    
            // Left
            if (isValidPosition(row, col - 1)) {
                Cell neighbor = maze[row][col - 1];
                if (!hasUnvisitedNeighbor) {
                    markTrail(currentCell, '>');
                }
                if (!isVisited(neighbor) && isSpace(neighbor)) {
                    hasUnvisitedNeighbor = true;
                    markTrail(currentCell, '<');
                    structure.push(neighbor);
                }
            }
    
            // Right
            if (isValidPosition(row, col + 1)) {
                Cell neighbor = maze[row][col + 1];
                if (!hasUnvisitedNeighbor) {
                    markTrail(currentCell, 'x');
                }
                if (!isVisited(neighbor) && isSpace(neighbor)) {
                    hasUnvisitedNeighbor = true;
                    markTrail(currentCell, '>');
                    structure.push(neighbor);
                }
            }
        }
    
        if (moreToSearch) {
            System.out.println("Cheese (c) not reachable.");
        }
    }
    

public static void main(String[] args) {
    MazeSolver mazeSolver = new MazeSolver("/Users/hassan/Desktop/CPSC-331-2/Assignments/src/Maze.txt");
    mazeSolver.solveMaze();
}
}