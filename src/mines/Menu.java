package mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author joshua
 */
public class Menu extends JPanel
{
    private JButton m1, m2, m3;
    private int width, height, widthOne, heightOne, choice;

    public Menu()
    {
        choice = 0;
        widthOne = 250;
        heightOne = 150;
        width = widthOne * 2;
        height = heightOne * 2;
        m1 = new JButton("Easy: 8X8 10 mines");
        m2 = new JButton("Medium: 16X16 40 mines");
        m3 = new JButton("Hard: 32X16 99 mines");
        m1.setPreferredSize(new Dimension(widthOne, heightOne));
        m2.setPreferredSize(new Dimension(widthOne, heightOne));
        m3.setPreferredSize(new Dimension(widthOne, heightOne));
        setLayout(new GridLayout(2, 2));
        add(m1);
        add(m2);
        add(m3);

        m1.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                choice = 1;
            }
        }); 
        m2.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                choice = 2;
            }
        }); 
        m3.addActionListener(new ActionListener(){
            @Override 
            public void actionPerformed(ActionEvent e)
            {
                choice = 3;
            }
        }); 
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }

    public int getChoice()
    {
        return choice;
    }

    public void resetChoice()
    {
        choice = 0;
    }
}