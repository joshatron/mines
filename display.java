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
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		int row = (mx - 20) / 40;
		int col = (my - 20) / 40;
		int button = e.getButton();

		if(row < rows && col < cols)
		{
			if(button == 1)
			{
				if(top[row][col] == 1)
				{
					top[row][col] = 0;
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
			repaint();
		}

		e.consume();
	}

	public void mousePressed(MouseEvent e)
	{
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseMoved(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		int row = (mx - 20) / 40;
		int col = (my - 20) / 40;

	 	showStatus("Mouse at (" + row + "," + col + ")");
		e.consume();	
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
					g.setColor(new Color(200, 200, 200));
					g.fillRect(k, a, length, length);
					g.setColor(Color.white);
					g.drawRect(k, a, length - 1, length - 1);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 1)
				{
					g.setColor(new Color(150, 150, 150));
					g.fillRect(k, a, length, length);
					g.setColor(Color.black);
					g.drawRect(k, a, length - 1, length - 1);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 2)
				{
					g.setColor(new Color(0, 0, 0));
					g.fillRect(k, a, length, length);
				}
			}
		}
	}
}
