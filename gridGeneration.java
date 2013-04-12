/*
	gridGeneration.java
	by Joshua Leger
	This class will create the grid to play mines from.
	The output is a 2d array containing integers ranging from -1 to 8.
	-1 represents a mine.
	0 through 8 are constants representing number of mines surrounding;
	The two types of input are a single input mode. The different modes are:
		1- 8x8 with 10 mines
		2- 16x16 with 40 mines
		3- 30x16 with 99 mines
	The other input contains 3 perameters:
		the number of coloums
		the number of rows
		the number of mines
	This second mode will return an empty array if too many mines are possible.
	This should be created at the beginning of the game.
	Call generateMines at the beginning of each match.
*/
import java.util.Random;

public class gridGeneration
{
	private int[][] grid;
	private int cols, rows, mines;

	public gridGeneration(int gameMode)
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

	public gridGeneration(int c, int r, int m)
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
		//center
		for(int k = 1; k + 1 < rows; k++)
		{
			for(int a = 1; a + 1 < cols; a++)
			{
				if(grid[k][a] != -1)
				{
					int counter = 0;

					for(int t = -1; t < 2; t++)
					{
						for(int i = -1; i < 2; i++)
						{
							if(t == 0 && i == 0)
							{
							}
							else
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

		//horizontal border
		for(int k = 1; k + 1 < cols; k++)
		{
			int counter1 = 0;
			int counter2 = 0;

			for(int a = 0; a < 2; a++)
			{
				for(int t = -1; t < 2; t++)
				{
					if(a != 0 || t != 0)
					{
						if(grid[a][k + t] == -1)
						{
							counter1++;
						}
						if(grid[rows - 1 - a][k + t] == -1)
						{
							counter2++;
						}
					}
				}
			}

			if(grid[0][k] == 0)
			{
				grid[0][k] = counter1;
			}
			if(grid[rows - 1][k] == 0)
			{
				grid[rows - 1][k] = counter2;
			}
		}

		//vertical border
		for(int k = 1; k + 1 < rows; k++)
		{
			int counter1 = 0;
			int counter2 = 0;

			for(int a = 0; a < 2; a++)
			{
				for(int t = -1; t < 2; t++)
				{
					if(a != 0 || t != 0)
					{
						if(grid[k + t][a] == -1)
						{
							counter1++;
						}
						if(grid[k + t][cols - 1 - a] == -1)
						{
							counter2++;
						}
					}
				}
			}

			if(grid[k][0] == 0)
			{
				grid[k][0] = counter1;
			}
			if(grid[k][cols - 1] == 0)
			{
				grid[k][cols - 1] = counter2;
			}
		}

		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
		int counter4 = 0;

		//corners
		for(int a = 0; a < 2; a++)
		{
			for(int t = 0; t < 2; t++)
			{
				if(a != 0 || t != 0)
				{
					if(grid[a][t] == -1)
					{
						counter1++;
					}
					if(grid[a][cols - 1 - t] == -1)
					{
						counter2++;
					}
					if(grid[rows - 1 - a][t] == -1)
					{
						counter3++;
					}
					if(grid[rows - 1 - a][cols - 1 - t] == -1)
					{
						counter4++;
					}
				}
			}
		}

		if(grid[0][0] == 0)
		{
			grid[0][0] = counter1;
		}
		if(grid[0][cols - 1] == 0)
		{
			grid[0][cols - 1] = counter2;
		}
		if(grid[rows - 1][0] == 0)
		{
			grid[rows - 1][0] = counter3;
		}
		if(grid[rows - 1][cols - 1] == 0)
		{
			grid[rows - 1][cols - 1] = counter4;
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
