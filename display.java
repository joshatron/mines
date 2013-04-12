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
	int length, startx, starty, mx, my, rows, cols;
	gridGeneration grid;

	public void init()
	{
		grid = new gridGeneration(1);
		length = 40;
		startx = starty = 20;
		rows = grid.getRows();
		cols = grid.getCols();
		top = new int[rows][cols];

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				top[k][a] = 1;
			}
		}

		setBackground(Color.blue);
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseMoved(MouseEvent e)
	{
	}

	public void mouseDragged(MouseEvent e)
	{
	}

	public void paint(Graphics g)
	{
		for(int k = startx; k - startx < rows * length; k += length)
		{
			for(int a = starty; a - starty < cols * length; a += length)
			{
				if(top[(k - startx) / length][(a - starty) / length] == 0)
				{
					g.setColor(new Color(30, 30, 30));
					g.fillRect(k, a, length, length);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 1)
				{
					g.setColor(new Color(100, 100, 100));
					g.fillRect(k, a, length, length);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 2)
				{
					g.setColor(new Color(255, 255, 255));
					g.fillRect(k, a, length, length);
				}
			}
		}
	}
}
