import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner; // Import the Scanner class to read text files

public class Main {
    public static void main(String[] args) {


        ArrayList<String> lines = new ArrayList<>();
        try {
            File myObj = new File("src/input");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String[] firstLine = lines.getFirst().split("");
        int numColumns = firstLine.length;

        String[][] maze = new String[lines.size()][numColumns];

        for (int i = 0; i < maze.length; i++){
            String[] line = lines.get(i).split("");
            maze[i] = line;
        }

        MazeSolver mazeSolver = new MazeSolver(maze);
        mazeSolver.play();
    }
}