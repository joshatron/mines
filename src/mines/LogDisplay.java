package mines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by joshua on 15/02/14.
 */
public class LogDisplay extends JPanel
{
    public static final String EXIT = "exit";
    private JTextArea text;
    private JScrollPane pane;
    private JButton exit, clear;
    private Log log;
    private int width, height, widthButton, heightButton;

    public LogDisplay()
    {
        width = 500;
        height = 600;
        widthButton = 200;
        heightButton = 40;

        log = new Log();
        text = new JTextArea(log.toStringPretty());
        pane = new JScrollPane(text);
        exit = new JButton("Back to Menu");
        clear = new JButton("Clear Log");

        text.setEditable(false);
        pane.setPreferredSize(new Dimension(width - 5, height - 5 - heightButton));
        exit.setPreferredSize(new Dimension(widthButton, heightButton));
        clear.setPreferredSize(new Dimension(widthButton, heightButton));
        setLayout(new BorderLayout());
        add(pane, BorderLayout.NORTH);
        add(clear, BorderLayout.WEST);
        add(exit, BorderLayout.EAST);

        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                firePropertyChange(EXIT, false, true);
            }
        });
        clear.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                log.clearLog();
                refresh();
            }
        });
    }

    public void refresh()
    {
        log.refresh();
        text.setText(log.toStringPretty());
        repaint();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width, height);
    }
}
