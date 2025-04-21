import java.util.ArrayList;
import java.util.Objects;

public class MazeSolver {
    private String[][] maze;
    ArrayList<String> coordinates;
    private int currentX;
    private int currentY;
    private boolean finished = false;
    private int numRows;
    ArrayList<Integer> yCoordinates;
    private int numColumns;
    private int finalX;
    boolean moveMade;

    public MazeSolver(String[][] maze){
        this.maze = maze;
        coordinates = new ArrayList<>();
        coordinates.add("(0, 0)");
        currentX = 0;
        currentY = 0;
        moveMade = false;

        yCoordinates = new ArrayList<>();
        yCoordinates.add(0);
        finalX = 0;

        for (String[] row : maze){
            numRows++;

        }
        for (String item : maze[0]){
            numColumns++;
        }

    }

    public void printMaze(){
        int rowNumber = 0;
        int columnNumber = 0;
        for (String[] row : maze){
            for (String item : row){
                if (rowNumber == currentX && columnNumber == currentY){
                    System.out.print("X");
                }
                else{
                    System.out.print(item);
                }
                columnNumber++;
            }
            System.out.println();
            rowNumber++;
            columnNumber = 0;
        }
        System.out.println();
    }

    public void addPoint(){
        String toAdd = "(" + currentX + ", " + currentY + ")";
        coordinates.add(toAdd);
        yCoordinates.add(currentY);
    }

    public void move(){


        findEnd();


//        printMaze();

        if (currentX == numRows - 1 && currentY == finalX) {
            finished = true;
            moveMade = true;
        }
        if (!moveMade && !finished) {

             if (currentY < numColumns - 1 && Objects.equals(maze[currentX][currentY + 1], ".")) {
                maze[currentX][currentY] = "D";
                currentY++;
                addPoint();
                moveMade = true;
            }
             else if (currentX < numRows - 1 && Objects.equals(maze[currentX + 1][currentY], ".")) {
                maze[currentX][currentY] = "D";
                currentX++;
                addPoint();
                moveMade = true;
            }
             else if (currentX != 0 && Objects.equals(maze[currentX - 1][currentY], ".")) {
                maze[currentX][currentY] = "D";
                currentX--;
                addPoint();
                moveMade = true;
            }
             else if (currentY != 0 && Objects.equals(maze[currentX][currentY - 1], ".")) {
                maze[currentX][currentY] = "D";
                currentY--;
                addPoint();
                moveMade = true;
            }
             if (!moveMade){
                 if (currentY < numColumns - 1 && Objects.equals(maze[currentX][currentY + 1], "D")) {
                     maze[currentX][currentY] = "#";
                     coordinates.remove("(" + currentX + ", " + currentY + ")");
                     currentY++;
                     moveMade = true;
                 }
                 else if (currentX < numRows - 1 && Objects.equals(maze[currentX + 1][currentY], "D")) {
                     maze[currentX][currentY] = "#";
                     coordinates.remove("(" + currentX + ", " + currentY + ")");
                     currentX++;

                     moveMade = true;
                 }
                 else if (currentX != 0 && Objects.equals(maze[currentX - 1][currentY], "D")) {
                     maze[currentX][currentY] = "#";
                     coordinates.remove("(" + currentX + ", " + currentY + ")");
                     currentX--;
                     moveMade = true;
                 }
                 else if (currentY != 0 && Objects.equals(maze[currentX][currentY - 1], "D")) {
                     maze[currentX][currentY] = "#";
                     coordinates.remove("(" + currentX + ", " + currentY + ")");
                     currentY--;
                     moveMade = true;
                 }
             }
            System.out.println(coordinates);
             moveMade = false;
        }



    }

    public void findEnd(){
        int row = maze.length - 1;
        for (int i = 0; i < maze[row].length; i++){
            if (Objects.equals(maze[row][i], ".")){
                finalX = i;
            }
        }
    }

    public void play(){
        while (!finished){
            move();
        }

        System.out.println("ANSWER:");
        System.out.println(coordinates);
    }




}
