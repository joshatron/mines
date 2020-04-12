package mines;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Log
{
    private LinkedList<Player> players;
    String file;
    int people;

    public Log()
    {
        file = "stats.txt";
        players = new LinkedList<Player>();
        readFile();
    }

    public Log(String file)
    {
        this.file = file;
        players = new LinkedList<Player>();
        readFile();
    }

    public void addGame(String name, int game, int time, boolean won)
    {
        boolean found = false;
        for(int k = 0; k < people; k++)
        {
            if(players.get(k).getName().equals(name))
            {
                found = true;
                players.get(k).addGame(game, time, won);
                break;
            }
        }

        if(!found)
        {
            Player temp = new Player(name);
            temp.addGame(game, time, won);
            players.add(temp);
            people++;
        }

        writeFile();
    }

    public void clearLog()
    {
        try
        {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.write("0");
            out.close();
        }
        catch(Exception e){}
    }

    public void refresh()
    {
        players.clear();
        readFile();
    }

    public String firstName()
    {
        if(people > 0)
        {
            return players.get(0).getName();
        }
        return "";
    }

    private void readFile()
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));

            try
            {
                people = Integer.parseInt(in.readLine());
            }
            catch(NumberFormatException e)
            {
                people = 0;
            }

            for(int k = 0; k < people; k++)
            {
                StringTokenizer token = new StringTokenizer(in.readLine(), ",");
                String name = token.nextToken();
                int[] played = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
                                Integer.parseInt(token.nextToken())};
                int[] won = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
                             Integer.parseInt(token.nextToken())};
                int[] best = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
                              Integer.parseInt(token.nextToken())};
                int[] average = {Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()),
                                 Integer.parseInt(token.nextToken())};
                players.add(new Player(name, played, won, best, average));
            }
        }
        catch(FileNotFoundException e)
        {
            File file = new File(this.file);
            try
            {
                file.createNewFile();
            }
            catch(IOException e2){}
        }
        catch(IOException e){}
    }

    private void writeFile()
    {
        try
        {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.write("" + people + "\n");

            for(int k = 0; k < people; k++)
            {
                Player temp = players.get(k);
                out.write(temp.toString());
            }

            out.close();
        }
        catch(Exception e){}
    }

    public String toStringPretty()
    {
        String str = "";

        for(int k = 0; k < people; k++)
        {
            str += players.get(k).toStringPretty();
        }

        return str;
    }
}