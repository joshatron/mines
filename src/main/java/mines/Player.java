package mines;

import java.text.DecimalFormat;

public class Player
{
    private final int GAMES = 3;
    private final int MAX = 999999;

    private String name;
    private int[] played, won, best, average;

    public Player(String name)
    {
        this.name = name;
        played = new int[GAMES];
        won = new int[GAMES];
        best = new int[GAMES];
        average = new int[GAMES];

        for(int k = 0; k < GAMES; k++)
        {
            best[k] = MAX;
        }
    }

    public Player(String name, int[] played, int[] won, int[] best, int[] average)
    {
        this.name = name;
        this.played = played;
        this.won = won;
        this.best = best;
        this.average = average;
    }

    public void addGame(int game, int time, boolean won)
    {
        if(time > MAX)
        {
            time = MAX;
        }
        game--;
        played[game]++;
        if(won)
        {
            this.won[game]++;
            if(time < best[game])
            {
                best[game] = time;
            }
            average[game] = (average[game] + time) / this.won[game];
        }
    }

    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int[] getAverage(){return average;}
    public void setAverage(int[] average){this.average = average;}
    public int[] getBest(){return best;}
    public void setBest(int[] best){this.best = best;}
    public int[] getWon(){return won;}
    public void setWon(int[] won){this.won = won;}
    public int[] getPlayed(){return played;}
    public void setPlayed(int[] played){this.played = played;}

    public String toString()
    {
        String str = name;
        for(int k = 0; k < GAMES; k++)
        {
            str += "," + played[k];
        }
        for(int k = 0; k < GAMES; k++)
        {
            str += "," + won[k];
        }
        for(int k = 0; k < GAMES; k++)
        {
            str += "," + best[k];
        }
        for(int k = 0; k < GAMES; k++)
        {
            str += "," + average[k];
        }

        return str;
    }

    public String toStringPretty()
    {
        DecimalFormat format = new DecimalFormat("#.##");
        String str = name + "\n";
        for(int k = 0; k < GAMES; k++)
        {
            if(played[k] != 0)
            {
                str += "Type " + (k + 1) + "-\n";
                str += "\tWin Record: " + format.format(((double)won[k] / played[k] * 100)) + "%"
                       + " of " + played[k] + " played\n";
                str += "\tBest Time: " + (best[k] / 1000.) + "s\n";
                str += "\tAverage Time: " + (average[k] / 1000.) + "s\n\n";
            }
        }

        return str;
    }
}