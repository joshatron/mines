/*
    This class monitors game play and keeps statistics of games played.
    It will keep track of games played, games won, best 3 times, and favorite configuration.
    Each person will type their name for games and stats will individually be tracked.
    All information will be stored in a file called stats.
    format of file is:

    number of players
    name1,games played1,games played2,games played3,games won,best score1,best score2,best score3,number of favorite configuration
    name2,games played1,games played2,games played3,games won,best score1,best score2,best score3,number of favorite configuration
    name3,games played1,games played2,games played3,games won,best score1,best score2,best score3,number of favorite configuration
    ...

    The three groups of scores are each of the different game types in ascending order.
    The worst time limit is 999 seconds.
*/

import java.io.*;
import java.util.*;

public class log
{
    BufferedReader in;
    PrintWriter out;
    String file;
    String[] names;
    int p;
    personNode first, current, last;

    public log() throws IOException
    {
        file = "stats.txt";
        in = new BufferedReader(new FileReader(file));
        p = Integer.parseInt(in.readLine());
        first = new personNode();
        current = first;
        names = new String[p];
        readFile();
    }

    public log(String f) throws IOException
    {
        file = f;
        in = new BufferedReader(new FileReader(file));
        p = Integer.parseInt(in.readLine());
        first = new personNode();
        current = first;
        names = new String[p];
        readFile();
    }

    public void readFile() throws IOException
    {
        for(int k = 0; k < p; k++)
        {
            String per = in.readLine();
            StringTokenizer token = new StringTokenizer(per, ",");
            String name = token.nextToken();
            player p1 = new player(name,Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),
                           Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),
                           Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),
                           Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
            names[k] = name;
            current.setPerson(p1);
            current.next = new personNode();
            current = current.next;
        }

        last = current;
    }

    public void addStats(String name, int game, boolean won, int time)
    {
        boolean hasName = false;
        current = first;

        System.out.println(p);
        for(int k = 0; k < p && !hasName; k++)
        {
            current = current.next;

            if(names[k].equals(name))
            {
                hasName = true;
            }
        }

        if(hasName)
        {
            player p1 = current.getPerson();
            p1.incP(game);
            if(won)
            {
                p1.incWon();
                p1.setBest(game, time);
            }

            current.setPerson(p1);
        }
        else
        {
            player p1 = new player();
            p1.setName(name);
            p1.incP(game);
            if(won)
            {
                p1.incWon();
                p1.setBest(game, time);
            }
            last.setPerson(p1);
            last.next = new personNode();
            last = last.next;
            p++;
        }
    }

    public String toString()
    {
        String str = new String();
        current = first;

        for(int k = 0; k < p; k++)
        {
            str += current.getPerson().toStringPretty() + "\n\n\n";
        }

        return str;
    }

    public void toFile() throws IOException
    {
        out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        current = first;
        out.write("" + p);

        for(int k = 0; k < p; k++)
        {
            System.out.println(current.getPerson().toString());
            out.write(current.getPerson().toString());
        }
    }

    private class personNode
    {
        public personNode next;
        player person;

        public personNode()
        {
            next = null;
            person = new player();
        }

        public personNode(player p)
        {
            person = p;
            next = null;
        }

        public void setPerson(player p){person = p;}

        public player getPerson(){return person;}
    }
}
