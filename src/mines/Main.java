package mines;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main
{
    JFrame frame;
    Grid grid;
    Menu menu;

    public static void main(String[] args)
    {
        new Main().go();
    }

    public void go()
    {
        frame = new JFrame();
        grid = new Grid(1);
        menu = new Menu();
        frame.add(menu);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        menu.addPropertyChangeListener(menu.CHOICE, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent e)
            {
                grid = new Grid(Integer.parseInt(e.getNewValue().toString()));
                frame.remove(menu);
                frame.add(grid);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
    }
}