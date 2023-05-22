//CPSC 331: Assignment 2
//Names: Hassan Sohail, Mellisa Phongsa
//has functions to load the maze from a file, print the maze to terminal and write the maze to an output file

package A2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MazeLoader {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start loadMazeFromFile
    //LoadMazeFromFile function takes a string "filePath" as an argument
    public MazeData loadMazeFromFile(String filePath) throws FileNotFoundException {
        //store file from specified loaction in object "file"
        File file = new File(filePath);

        //initializes "scanner" object with "file" allows program to read data from file
        Scanner scanner = new Scanner(file);

        //initialize int vars to 0
        int numRows = 0;
        int numCols = 0;

        // Determine maze dimensions
        while (scanner.hasNextLine()) { //iterate through each line in the file
            //read next line from file and assign to "line" var
            String line = scanner.nextLine();

            //determine number of columns
            numCols = line.length();

            //increments the number of rows
            numRows++;
        }
        //finish reading from file
        scanner.close();

        // Create maze array
        Cell[][] maze = new Cell[numRows][numCols];

        // Load maze from file
        scanner = new Scanner(file);

        //iterate over each row and column in the maze
        for (int row = 0; row < numRows; row++) {
            //read nextline and store in var
            String line = scanner.nextLine();

            for (int col = 0; col < numCols; col++) {
                //retrieve character at current column position and assign it to symbol var
                char symbol = line.charAt(col);

                //create a new "Cell" object and assign symbol to corresponding postion in the maze
                maze[row][col] = new Cell(row, col, symbol);
            }
        }
        //finish reading from file
        scanner.close();
        return new MazeData(maze, numRows, numCols);
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start printMAze
    //print current state of maze
    public void printMaze(Cell[][] maze, int numRows, int numCols, DLLQueue<Cell> trailQueue) {
        //nested for loops to iterate over each row and column
        char[][] mazeCopy = new char[numRows][numCols];
        
        // Copy the original maze
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                mazeCopy[row][col] = maze[row][col].type;
            }
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
<<<<<<< HEAD
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end
=======

    public void printMazeToFile(Cell[][] maze, int numRows, int numCols, DLLQueue<Cell> trailQueue, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            char[][] mazeCopy = new char[numRows][numCols];
    
            // Copy the original maze
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    mazeCopy[row][col] = maze[row][col].type;
                }
            }
    
            // Print the updated maze with the trail path to the file
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    fileWriter.write(mazeCopy[row][col]);
                }
                fileWriter.write('\n');
            }
            fileWriter.write('\n');
            fileWriter.flush();
        }  catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
>>>>>>> 35abcbd (Fixed stuff with trailqueue)

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// start saveTRailToFile
    //saves path travelled by the mouse in queue "trailQueue"
    public void saveTrailToFile(String filePath, DLLQueue<Cell> trailQueue, Cell[][] maze, int numRows, int numCols) {
        //write to file
        try (PrintWriter writer = new PrintWriter(filePath)) {
            char[][] mazeCopy = new char[numRows][numCols];

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    mazeCopy[row][col] = maze[row][col].type;
                }
            }
            

            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    writer.println(mazeCopy[row][col]);
                }
                writer.println('\n');
            }
            writer.println('\n');
            writer.flush();
           
        } catch (FileNotFoundException e) {
            System.out.println("Error saving trail to file: " + e.getMessage());
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// end

}
