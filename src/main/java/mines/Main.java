package mines;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Main
{
    JFrame frame;
    Grid grid;
    Menu menu;
    LogDisplay logDisplay;

    public static void main(String[] args)
    {
        new Main().go();
    }

    public void go()
    {
        frame = new JFrame();
        grid = new Grid(1);
        menu = new Menu();
        logDisplay = new LogDisplay();
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
                int choice = Integer.parseInt(e.getNewValue().toString());
                if(choice != 4)
                {
                    grid.resetGrid(choice);
                    frame.remove(menu);
                    frame.add(grid);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                }
                else
                {
                    logDisplay.refresh();
                    frame.remove(menu);
                    frame.add(logDisplay);
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                }
            }
        });

        grid.addPropertyChangeListener(Grid.EXIT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent e)
            {
                frame.remove(grid);
                frame.add(menu);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });

        logDisplay.addPropertyChangeListener(LogDisplay.EXIT, new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent)
            {
                frame.remove(logDisplay);
                frame.add(menu);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
    }
}