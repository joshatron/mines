package mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JLabel implements MouseListener
{
    private int rows, cols, length, width, height, mines;
    private int startx, starty, textOffsetx, textOffsety, gFontSize;
    private boolean isFirst, isEnd, didWin;
    private int[][] top, bottom;
    private Font gridFont;
    private GridGeneration grid;

    public Grid(int type)
    {
        if(type == 1)
        {
            rows = 8;
            cols = 8;
        }
        else if(type == 2)
        {
            rows = 16;
            cols = 16;
        }
        else
        {
            rows = 32;
            cols = 16;
        }

        bottom = new int[rows][cols];
        top = new int[rows][cols];
        for(int k = 0; k < rows; k++)
        {
            for(int a = 0; a < cols; a++)
            {
                bottom[k][a] = 1;
                top[k][a] = 1;
            }
        }

        isFirst = true;
        isEnd = false;
        didWin = false;
        mines = 100;

        length = 40;
        startx = 0;
        starty = 0;
        textOffsetx = 10;
        textOffsety = 30;
        gFontSize = 30;
        width = rows * length;
        height = cols * length;
        gridFont = new Font("Monospaced", Font.PLAIN, gFontSize); 
        grid = new GridGeneration(type);
        addMouseListener(this);
    }

    public boolean atEnd()
    {
        if(isEnd)
        {
            if(didWin)
            {
                JOptionPane.showMessageDialog(null, "Congrats! You won.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Sorry, you lost.");
            }
            return true;
        }
        return false;
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics backg)
    {
        super.paintComponent(backg);

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
            didWin = true;
            isEnd = true;
        }
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
                            top[row][col] = 3;
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

    private void displayEndGame()
    {
        for(int k = 0; k < rows; k++)
        {
            for(int a = 0; a < cols; a++)
            {
                if(top[k][a] == 1)
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
        }
        isEnd = true;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();
        int row = (mx - startx) / length;
        int col = (my - starty) / length;
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
                        else if(bottom[row][col] == -1)
                        {
                            top[row][col] = 3;
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
    
        repaint();
        e.consume(); 
    }

    @Override
    public void mousePressed(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){}
}