/*
 * GridGeneration.java
 * by Joshua Leger
 * This class will create the grid to play mines from.
 * The output is a 2d array containing integers ranging from -1 to 8.
 * -1 represents a mine.
 * 0 through 8 are constants representing number of mines surrounding;
 * The two types of input are a single input mode. The different modes are:
 *    1- 8x8 with 10 mines
 *    2- 16x16 with 40 mines
 *    3- 30x16 with 99 mines
 * The other input contains 3 perameters:
 *    the number of coloums
 *    the number of rows
 *    the number of mines
 * This second mode will return an empty array if too many mines are possible.
 * This should be created at the beginning of the game.
 * Call generateMines at the beginning of each match.
 */
package mines;
import java.util.Random;

public class GridGeneration
{
    private int[][] grid;
    private int cols, rows, mines;

    public GridGeneration(int gameMode)
    {
        if(gameMode == 1)
        {
            cols = rows = 8;
            mines = 10;
            grid = new int[rows][cols];
        }
        else if(gameMode == 2)
        {
            cols = rows = 16;
            grid = new int[rows][cols];
            mines = 40;
        }
        else if(gameMode == 3)
        {
            cols = 16;
            rows = 30;
            mines = 99;
            grid = new int[rows][cols];
        }
        else
        {
            System.out.println("Invalid input");
            System.exit(1);
        }
    }

    public GridGeneration(int c, int r, int m)
    {
        int boxes = r * c;
        if(m + 9 >= boxes || c == 0 || r == 0 || m == 0)
        {
            System.out.println("invalid input");
            System.exit(1);
        }
        cols = c;
        rows = r;
        mines = m;
        grid = new int[rows][cols];
    }

    /*
        This is the simplest possible fill.
        Other code should eventually replace this to make a better game.
    */
    public void generateMines()
    {
        Random rand = new Random();
        emptyGrid();

        for(int k = 0; k < mines; k++)
        {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);

            if(grid[r][c] == 0)
            {
                grid[r][c] = -1;
            }
            else
            {
                k--;
            }
        }

        generateConstants();
    }

    public void generateMines(int ro, int co)
    {
        Random rand = new Random();
        emptyGrid();

        for(int k = 0; k < mines; k++)
        {
            int r = rand.nextInt(rows);
            int c = rand.nextInt(cols);

            if(grid[r][c] == 0 && (Math.abs(r - ro) > 1 || Math.abs(c - co) > 1))
            {
                grid[r][c] = -1;
            }
            else
            {
                k--;
            }
        }

        generateConstants();
    }

    private void emptyGrid()
    {
        for(int k = 0; k < rows; k++)
        {
            for(int a = 0; k < cols; k++)
            {
                grid[k][a] = 0;
            }
        }
    }

    private void generateConstants()
    {
        for(int k = 0; k < rows; k++)
        {
            for(int a = 0; a < cols; a++)
            {
                int counter = 0;

                for(int t = -1; t < 2; t++)
                {
                    for(int i = -1; i < 2; i++)
                    {
                        if((t != 0 || i != 0) && 
                           (k + t >= 0 && k + t < rows) &&
                           (a + i >= 0 && a + i < cols))
                        {
                            if(grid[k + t][a + i] == -1)
                            {
                                counter++;
                            }
                        }
                    }
                }

                if(grid[k][a] == 0)
                {
                    grid[k][a] = counter;
                }
            }
        }
    }

    public void printGrid()
    {
        for(int k = 0; k < rows; k++)
        {
            for(int a = 0; a < cols; a++)
            {
                if(grid[k][a] != -1)
                {
                    System.out.print(" " + grid[k][a] + " ");
                }
                else
                {
                    System.out.print(grid[k][a] + " ");
                }
            }
            System.out.println();
        }
    }

    public int[][] getGrid() {return grid;}
    public int getRows() {return rows;}
    public int getCols() {return cols;}
    public int getMines() {return mines;}
}
