/*
	This class monitors game play and keeps statistics of games played.
	It will keep track of games played, games won, best 3 times, and favorite configuration.
	Each person will type their name for games and stats will individually be tracked.
	All information will be stored in a file called stats.
	format of file is:

	number of players
	name1,games played,games won,best score,second score,third score,best score,second score,third score,best score,second score,third score,number of favorite configuration
	name2,games played,games won,best score,second score,third score,best score,second score,third score,best score,second score,third score,number of favorite configuration
	name3,games played,games won,best score,second score,third score,best score,second score,third score,best score,second score,third score,number of favorite configuration
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
	int p;
	player[] people;

	public log() throws IOException
	{
		file = "stats";
		in = new BufferedReader(new FileReader(file));
		out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		p = Integer.parseInt(in.readLine());
		people = new player[p];
	}

	public log(String f) throws IOException
	{
		file = f;
		in = new BufferedReader(new FileReader(file));
		out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		p = Integer.parseInt(in.readLine());
		people = new player[p];
	}

	public void readFile() throws IOException
	{
		for(int k = 0; k < p; k++)
		{
			//create player and store in people
		}
	}
}
