/*
	This class displays the choices for play and allows the game to end.
	It is displayed when the game is initalized and after every individual game.
	Initial it will give 4 options:
	Easy game with a game type of 1.
	Medium game with a game type of 2.
	Hard game with a game type of 3.
	Quit the game.
*/

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class menuDisplay extends Applet
			 implements MouseListener
{
	int gameMode, width, height, optionWidth, optionHeight, startX, startY, textOffsetX, textOffsetY, textHeight;
	Image backBuffer;
	Graphics backg;
	Font font;

	public void init()
	{
		width = getSize().width;
		height = getSize().height;
		startX = 50;
		startY = 50;
		optionWidth = 300;
		optionHeight = 200;
		textOffsetX = 30;
		textOffsetY = 80;
		textHeight = 50;
		
		setBackground(Color.white);
		font = new Font("Monospaced", Font.PLAIN, 30);
		addMouseListener(this);

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
		int mx = e.getX();
		int my = e.getY();
		int button = e.getButton();

		if(button == 1)
		{
			if(mx > startX && mx < startX + optionWidth && my > startY && my < startY + optionHeight)
			{
				gameMode = 1;
			}
			if(mx > startX + optionWidth && mx < startX + optionWidth * 2 && my > startY && my < startY + optionHeight)
			{
				gameMode = 2;
			}
			if(mx > startX && mx < startX + optionWidth && my > startY + optionHeight && my < startY + optionHeight * 2)
			{
				gameMode = 3;
			}
			if(mx > startX + optionWidth && mx < startX + optionWidth * 2 && my > startY + optionHeight && my < startY + optionHeight * 2)
			{
				gameMode = 0;
			}

			goToGame();
		}
	}

	public void mousePressed(MouseEvent e){}

	public void mouseReleased(MouseEvent e){}

	public void goToGame()
	{
	}

	public void updateBackBuffer()
	{
		backg.setColor(Color.lightGray);
		backg.fillRect(startX, startY, optionWidth * 2, optionHeight * 2);
		backg.setColor(Color.black);
		backg.drawRect(startX, startY, optionWidth - 1, optionHeight - 1);
		backg.drawRect(startX + optionWidth, startY, optionWidth - 1, optionHeight - 1);
		backg.drawRect(startX, startY + optionHeight, optionWidth - 1, optionHeight - 1);
		backg.drawRect(startX + optionWidth, startY + optionHeight, optionWidth - 1, optionHeight - 1);
		backg.setFont(font);
		backg.setColor(Color.blue);
		backg.drawString("Easy", startX + textOffsetX, startY + textOffsetY);
		backg.drawString("8x8 10 mines", startX + textOffsetX, startY + textOffsetY + textHeight);
		backg.setColor(Color.green);
		backg.drawString("Medium", startX + textOffsetX + optionWidth, startY + textOffsetY);
		backg.drawString("16x16 40 mines", startX + textOffsetX + optionWidth, startY + textOffsetY + textHeight);
		backg.setColor(Color.red);
		backg.drawString("Hard", startX + textOffsetX, startY + textOffsetY + optionHeight);
		backg.drawString("30x16 99 mines", startX + textOffsetX, startY + textOffsetY + textHeight + optionHeight);
		backg.setColor(Color.black);
		backg.drawString("quit", startX + textOffsetX + optionWidth, startY + textOffsetY + textHeight / 2 + optionHeight);
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
