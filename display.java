/*
	This class displays the mines board.
	It is a applet which uses a mouselistener to interact.
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class display extends Applet
		     implements MouseListener, MouseMotionListener
{
	int[][] bottom, top;
	int width, height;
	int length, textOffsetX, textOffsetY, startx, starty, mx, my, rows, cols;
	boolean isFirst;
	gridGeneration grid;
	Image backBuffer;
	Graphics backg;
	Font gridFont;

	public void init()
	{
		width = getSize().width;
		height = getSize().height;
		isFirst = true;
		grid = new gridGeneration(3);
		length = 40;
		textOffsetX = 10;
		textOffsetY = 30;
		startx = starty = 20;
		rows = grid.getRows();
		cols = grid.getCols();
		top = new int[rows][cols];
		gridFont = new Font("Monospaced", Font.PLAIN, 30);

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				top[k][a] = 1;
			}
		}

		setBackground(Color.blue);
		addMouseListener(this);
		addMouseMotionListener(this);

		backBuffer = createImage(width, height);
		backg = backBuffer.getGraphics();
		backg.setColor(Color.white);

		updateBackBuffer();
		repaint();
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		int row = (mx - 20) / 40;
		int col = (my - 20) / 40;
		int button = e.getButton();
		if(isFirst)
		{
			if(row < rows && row >= 0 && col < cols && col >= 0)
			{
				grid.generateMines(row, col);
				bottom = grid.getGrid();
				top[row][col] = 0;
				isFirst = false;
				expandZeros();
			}
		}
		else
		{
			if(row < rows && row >= 0 && col < cols && col >= 0)
			{
				if(button == 1)
				{
					if(top[row][col] == 1)
					{
						top[row][col] = 0;
						if(bottom[row][col] == 0)
						{
							expandZeros();
						}
						if(bottom[row][col] == -1)
						{
							displayEndGame();
						}
					}
					if(top[row][col] == 0)
					{
						expandConstant();
					}
				}
	
				if(button == 3)
				{
					if(top[row][col] == 1)
					{
						top[row][col] = 2;
					}
					else if(top[row][col] == 2)
					{
						top[row][col] = 1;
					}
				}
			}
		}
	
		updateBackBuffer();
		repaint();
		e.consume();
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseMoved(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		int row = (mx - 20) / 40;
		int col = (my - 20) / 40;

		if(row < rows && row >= 0 && col < cols && col >= 0)
		{
	 		showStatus("Mouse at (" + row + "," + col + ")");
		}
		else
		{
			showStatus("Mouse off grid");
		}
		e.consume();	
	}

	public void mouseDragged(MouseEvent e){}

	public void displayEndGame()
	{
		System.exit(0);
	}

	private void expandZeros()
	{
		int count = 0;

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				if(top[k][a] == 0 && bottom[k][a] == 0)
				{
					for(int t = -1; t < 2; t++)
					{
						for(int i = -1; i < 2; i++)
						{
							if(k + t >= 0 && k + t < rows && a + i >= 0 && a + i < cols && top[k + t][a + i] != 0)
							{
								top[k + t][a + i] = 0;
								if(bottom[k + t][a + i] == 0)
								{
									count++;
								}
							}
						}
					}
				}
			}
		}

		if(count != 0)
		{
			expandZeros();
		}
	}

	private void expandConstants(int row, int col)
	{
		int count = bottom[row][col];

		for(int k = -1; k < 2; k++)
		{
			for(int a = -1; a < 2; a++)
			{
				if(row + k >= 0 && row + k < rows && col + a >= 0 && col + a < cols && bottom[row + k][col + a] == -1)
				{
					count--;
				}
			}
		}
	}

	private void updateBackBuffer()
	{
		for(int k = startx; k - startx < rows * length; k += length)
		{
			for(int a = starty; a - starty < cols * length; a += length)
			{
				if(top[(k - startx) / length][(a - starty) / length] == 0)
				{
					backg.setColor(new Color(200, 200, 200));
					backg.fillRect(k, a, length, length);
					backg.setColor(Color.white);
					backg.drawRect(k, a, length - 1, length - 1);
					backg.setFont(gridFont);
					if(bottom[(k - startx) / length][(a - starty) / length] == -1)
					{
						backg.setColor(new Color(0, 0, 0));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 1)
					{
						backg.setColor(new Color(0, 0, 255));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 2)
					{
						backg.setColor(new Color(0, 255, 0));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 3)
					{
						backg.setColor(new Color(255, 0, 0));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 4)
					{
						backg.setColor(new Color(150, 150, 255));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 5)
					{
						backg.setColor(new Color(100, 0, 0));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 6)
					{
						backg.setColor(new Color(150, 150, 150));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 7)
					{
						backg.setColor(new Color(0, 50, 0));
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == 8)
					{
						backg.setColor(new Color(255, 0, 255));
					}
					if(bottom[(k - startx) / length][(a - starty) / length] > 0)
					{
						backg.drawString("" + bottom[(k - startx) / length][(a - starty) / length], k + textOffsetX, a + textOffsetY);
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == -1)
					{
						backg.drawString("!", k + textOffsetX, a + textOffsetY);
					}
				}
				if(top[(k - startx) / length][(a - starty) / length] == 1)
				{
					backg.setColor(new Color(150, 150, 150));
					backg.fillRect(k, a, length, length);
					backg.setColor(Color.black);
					backg.drawRect(k, a, length - 1, length - 1);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 2)
				{
					backg.setColor(new Color(0, 0, 0));
					backg.fillRect(k, a, length, length);
					backg.setFont(gridFont);
					backg.setColor(Color.white);
					backg.drawString("!", k + textOffsetX, a + textOffsetY);
				}
			}
		}
	}

	public void update(Graphics g)
	{
		g.drawImage(backBuffer, 0, 0, this);
	}

	public void paint(Graphics g)
	{
		update(g);
	}
}
