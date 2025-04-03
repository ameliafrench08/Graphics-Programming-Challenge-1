import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class DrawPanel extends JPanel implements MouseListener{
    private boolean[][] grid;
    private int numLeft = 360;
    ArrayList<String> originalFile;
    public DrawPanel() {
        grid = new boolean[30][40];
        this.addMouseListener(this);
        originalFile = new ArrayList<>();
        readFile();
        dropBricks();

    }

    public void readFile(){
        try {
            File myObj = new File("src/bricks");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                originalFile.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void dropBricks(){
        int start = 0;
        int end = 0;
        int height =  30;
        for (String string : originalFile){
            boolean noTrueFound = false;
            int commaIndex = string.indexOf(",");
            start = Integer.parseInt(string.substring(0, commaIndex));
            end = Integer.parseInt(string.substring(commaIndex + 1));

            // FINDING HEIGHT

            ArrayList<Integer> heightsPossible = new ArrayList<>();

            for (int m = 0; m < 30; m++) {
                for (int i = start - 1; i < end; i++) {

                    noTrueFound = !grid[m][i];
                }

                if (noTrueFound){
                    height = m;
                    heightsPossible.add(height);
                    for (int l = start - 1; l < end; l++){
                        if (heightsPossible.size() > 1){
                            heightsPossible.removeFirst();
                            grid[height - 1][l] = false;
                        }
                        grid[height][l] = true;
                    }
                }
            }

        }
    }

    public void thirtyPercentTrue(){

        grid = new boolean[30][40];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length;c++) {
                int chance = (int)(Math.random()*10);
                if (chance < 3) {
                    grid[r][c] = true;
                }
            }
        }

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;

        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLUE);

        for (int c = 0; c < 40; c++) {
            for (int r = 0; r < 30; r++) {
                g.drawRect(x, y, 20, 20);
                if (grid[r][c]) {
                    g2.setColor(Color.RED);
                    g2.fillRect(x, y, 20, 20);
                    g2.setColor(Color.BLACK);
                }
                y += 25;
            }
            x += 24;
            y = 12;
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {

        dropBricks();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}