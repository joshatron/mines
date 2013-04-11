/*
	This class tests the gridGeneration class.
	It is a simple loop calling generateMines.
*/

public class testGenerate
{
	public static void main(String[] args)
	{
		gridGeneration g = new gridGeneration(1);
		g.generateMines(3, 3);
		g.printGrid();
	}
}
