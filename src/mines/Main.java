package mines;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        new Main().go();
    }

    public void go()
    {
        JFrame frame = new JFrame();
        Grid grid = new Grid(1);
        Menu menu = new Menu();
        frame.add(menu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        boolean done = false;
        boolean menuBool = true;

        while(!done)
        {
            if(menuBool)
            {
                if(menu.getChoice() != 0)
                {
                    System.out.println("" + menu.getChoice());
                    grid = new Grid(menu.getChoice());
                    menu.resetChoice();
                    frame.add(grid);
                }
            }
            else
            {
                if(grid.atEnd())
                {
                    frame.add(menu);
                }
            }
        }
    }
}