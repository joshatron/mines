/*
	This class displays the mines board.
	It is a applet which uses a mouselistener to interact.
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class display extends Applet
		     implements MouseListener, MouseMotionListener, KeyListener
{
	int[][] bottom, top;
	int length, textOffsetx, textOffsety, startx, starty, mx, my, rows, cols, width, height, mines, gFontSize;
	int gameMode, optionWidth, optionHeight, startX, startY, textOffsetX, textOffsetY, textHeight, mFontSize;
	boolean isFirst, isEnd, menu, didWin;
	gridGeneration grid;
	Image gameBackBuffer, menuBackBuffer;
	Graphics backg, backg2;
	Font gridFont, menuFont, mineFont;
	log stats;

	public void init()
	{
		width = getSize().width;
		height = getSize().height;
		isFirst = true;
		isEnd = false;
		didWin = false;
		menu = true;

		length = 40;
		textOffsetx = 10;
		textOffsety = 30;
		startx = 200;
		starty = 35;
		gFontSize = 30;
		gridFont = new Font("Monospaced", Font.PLAIN, gFontSize);
		mineFont = new Font("Monospaced", Font.PLAIN, gFontSize);

		startX = 50;
		startY = 50;
		optionWidth = 300;
		optionHeight = 200;
		textOffsetX = 30;
		textOffsetY = 80;
		textHeight = 50;
		mFontSize = 30;
		menuFont = new Font("Monospaced", Font.PLAIN, mFontSize);	

		setBackground(Color.white);
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		gameBackBuffer = createImage(width, height);
		backg = gameBackBuffer.getGraphics();
		backg.setColor(Color.white);

		menuBackBuffer = createImage(width, height);
		backg2 = menuBackBuffer.getGraphics();
		backg2.setColor(Color.white);

		updateMenuBackBuffer();
		repaint();
	}

	public void mouseEntered(MouseEvent e){}

	public void mouseExited(MouseEvent e){}

	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		int row = (mx - startx) / length;
		int col = (my - starty) / length;
		int button = e.getButton();
		if(isEnd)
		{
			goToMenu();
		}
		else if(menu)
		{
			if(button == 1)
			{
				if(mx > startX && mx < startX + optionWidth && my > startY && my < startY + optionHeight)
				{
					gameMode = 1;
					goToGame();
				}
				if(mx > startX + optionWidth && mx < startX + optionWidth * 2 && my > startY && my < startY + optionHeight)
				{
					gameMode = 2;
					goToGame();
				}
				if(mx > startX && mx < startX + optionWidth && my > startY + optionHeight && my < startY + optionHeight * 2)
				{
					gameMode = 3;
					goToGame();
				}
	
			}	
		}
		else if(isFirst)
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
						else if(bottom[row][col] == -1)
						{
							displayEndGame();
						}
					}
					else if(top[row][col] == 0)
					{
						expandConstant(row, col);
					}
				}
	
				if(button == 3)
				{
					if(top[row][col] == 1)
					{
						top[row][col] = 2;
						mines--;
					}
					else if(top[row][col] == 2)
					{
						top[row][col] = 1;
						mines++;
					}
				}

				checkWin();
			}
		}
	
		if(menu)
		{
			updateMenuBackBuffer();
		}
		else
		{
			updateGameBackBuffer();
		}
		repaint();
		e.consume();
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void mouseMoved(MouseEvent e){}

	public void mouseDragged(MouseEvent e){}

	public void keyPressed(KeyEvent e){}

	public void keyReleased(KeyEvent e){}

	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();

		if(c != KeyEvent.CHAR_UNDEFINED)
		{
			if(!menu)
			{
				if(c == 'd')
				{
					if(length > 20)
					{
						length -= 10;
						gFontSize -= 10;
						textOffsetx -= 1;
						textOffsety -= 9;
						gridFont = new Font("Monospace", Font.PLAIN, gFontSize);
						backg.setColor(Color.white);
						backg.fillRect(0, 0, width, height);
						updateGameBackBuffer();
					}
				}
				else if(c == 'i')
				{
					length += 10;
					gFontSize += 10;
					textOffsetx += 1;
					textOffsety += 9;
					gridFont = new Font("Monospace", Font.PLAIN, gFontSize);
					backg.setColor(Color.white);
					backg.fillRect(0, 0, width, height);
					updateGameBackBuffer();
				}
				else if(c == 'n')
				{
					goToGame();
				}
				else if(c == 'q')
				{
					goToMenu();
				}
			}
			else
			{
				if(c == '1')
				{
					gameMode = 1;
					goToGame();
				}
				else if(c == '2')
				{
					gameMode = 2;
					goToGame();
				}
				else if(c == '3')
				{
					gameMode = 3;
					goToGame();
				}
			}
		}

		repaint();
		e.consume();
	}

	public void displayEndGame()
	{
		isEnd = true;

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				if(top[k][a] == 1 && bottom[k][a] == -1)
				{
					top[k][a] = 0;
				}
				else if(top[k][a] == 2 && bottom[k][a] != -1)
				{
					top[k][a] = 3;
				}
			}
		}

		//perform logging information
	}

	public void checkWin()
	{
		int count = 0;

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				if(top[k][a] == 1 && bottom[k][a] != -1)
				{
					count++;
				}
			}
		}

		if(count == 0)
		{
			isEnd = true;
			didWin = true;
		}
	}

	public void goToMenu()
	{
		menu = true;
		isFirst = true;
		isEnd = false;
		didWin = false;
	}

	public void goToGame()
	{
		menu = false;
		isFirst = true;
		isEnd = false;
		didWin = false;
		grid = new gridGeneration(gameMode);
		rows = grid.getRows();
		cols = grid.getCols();
		mines = grid.getMines();
		top = new int[rows][cols];
		backg.setColor(Color.white);
		backg.fillRect(0, 0, width, height);

		for(int k = 0; k < rows; k++)
		{
			for(int a = 0; a < cols; a++)
			{
				top[k][a] = 1;
			}
		}
		updateGameBackBuffer();
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

	private void expandConstant(int row, int col)
	{
		int count = bottom[row][col];

		for(int k = -1; k < 2; k++)
		{
			for(int a = -1; a < 2; a++)
			{
				if(row + k >= 0 && row + k < rows && col + a >= 0 && col + a < cols && top[row + k][col + a] == 2)
				{
					count--;
				}
			}
		}

		if(count == 0)
		{
			for(int k = -1; k < 2; k++)
			{
				for(int a = -1; a < 2; a++)
				{
					if(row + k >= 0 && row + k < rows && col + a >= 0 && col + a < cols && top[row + k][col + a] == 1)
					{
						if(bottom[row + k][col + a] == -1)
						{
							displayEndGame();
						}
						else
						{
							top[row + k][col + a] = 0;
						}
					}
				}
			}
		}

		expandZeros();
	}

	private void updateGameBackBuffer()
	{
		backg.setColor(Color.white);
		backg.fillRect(0, 0, startx, starty + length);
		backg.setColor(Color.red);
		backg.setFont(mineFont);
		backg.drawString("Mines:\n" + mines, 5, starty + 25);
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
						backg.setColor(new Color(0, 150, 0));
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
						backg.drawString("" + bottom[(k - startx) / length][(a - starty) / length], k + textOffsetx, a + textOffsety);
					}
					else if(bottom[(k - startx) / length][(a - starty) / length] == -1)
					{
						backg.drawString("!", k + textOffsetx, a + textOffsety);
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
					backg.drawString("!", k + textOffsetx + gFontSize / 10 - 1, a + textOffsety);
				}
				if(top[(k - startx) / length][(a - starty) / length] == 3)
				{
					backg.setColor(new Color(0, 0, 0));
					backg.fillRect(k, a, length, length);
					backg.setFont(gridFont);
					backg.setColor(Color.white);
					backg.drawString("F", k + textOffsetx, a + textOffsety);
				}
			}
		}

		if(isEnd)
		{
			backg.setFont(new Font("Arial", Font.PLAIN, 25));
			FontMetrics fm2 = backg.getFontMetrics();
			String go = "Click anywhere on the window to go back to the menu.";
			int x = startx;
			int y = fm2.getHeight() - 1;
			
			if(didWin)
			{
				String win = "Congratulations, you won!";

				backg.setColor(new Color(255, 69, 0));
				backg.drawString(win + " " + go, x, y);
			}
			else
			{
				String lose = "You have lost.";

				backg.setColor(new Color(255, 69, 0));
				backg.drawString(lose + " " + go, x, y);
			}
		}
	}

	public void updateMenuBackBuffer()
	{
		backg2.setColor(Color.lightGray);
		backg2.fillRect(startX, startY, optionWidth * 2, optionHeight * 2);
		backg2.setColor(Color.black);
		backg2.drawRect(startX, startY, optionWidth - 1, optionHeight - 1);
		backg2.drawRect(startX + optionWidth, startY, optionWidth - 1, optionHeight - 1);
		backg2.drawRect(startX, startY + optionHeight, optionWidth - 1, optionHeight - 1);
		backg2.drawRect(startX + optionWidth, startY + optionHeight, optionWidth - 1, optionHeight - 1);
		backg2.setFont(menuFont);

		backg2.setColor(new Color(0, 0, 255));
		backg2.drawString("Easy", startX + textOffsetX, startY + textOffsetY);
		backg2.drawString("8x8 10 mines", startX + textOffsetX, startY + textOffsetY + textHeight);

		backg2.setColor(new Color(0, 150, 0));
		backg2.drawString("Medium", startX + textOffsetX + optionWidth, startY + textOffsetY);
		backg2.drawString("16x16 40 mines", startX + textOffsetX + optionWidth, startY + textOffsetY + textHeight);

		backg2.setColor(new Color(255, 0, 0));
		backg2.drawString("Hard", startX + textOffsetX, startY + textOffsetY + optionHeight);
		backg2.drawString("30x16 99 mines", startX + textOffsetX, startY + textOffsetY + textHeight + optionHeight);
	}
		
	public void update(Graphics g)
	{
		if(menu)
		{
			g.drawImage(menuBackBuffer, 0, 0, this);
		}
		else
		{
			g.drawImage(gameBackBuffer, 0, 0, this);
		}
	}

	public void paint(Graphics g)
	{
		update(g);
	}
}
