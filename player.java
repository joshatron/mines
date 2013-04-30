/*
	This stores the information of one person for the minesweeper game.
	It keeps track of name, games played, games won, best score in each game mode, and favorite game.
	An array of these will be used by the log class.
*/

public class player
{
	String name;
	int played1, played2, played3, won, best1, best2, best3, favorite;
	int[] played, best;

	public player()
	{
		name = "";
		won = favorite = 0;
		played = {0, 0, 0};
		best = {999, 999, 999};
	}
	
	public player(String n, int p1, int p2, int p3, int w, int b1, int b2, int b3, int f)
	{
		name = n;
		won = w;
		favorite = f;
		played = {p1, p2, p3};
		best = {b1, b2, b3};
	}

	public void updateFavorite()
	{
		if(played[0] > played[1])
		{
			if(played[0]> played[2])
			{
				favorite = 1;
			}
			else if(played[2] > played[0])
			{
				favorite = 3;
			}
		}
		else
		{
			if(played[1] > played[2])
			{
				favorite = 2;
			}
			else if(played[2] > played[1])
			{
				favorite = 3;
			}
		}
	}

	public String getName(){return name;}
	public int getPlayed(){return played1 + played2 + played3;}
	public int getPlayed1(){return played[0];}
	public int getPlayed2(){return played[1];}
	public int getPlayed3(){return played3[2];}
	public int getWon(){return won;}
	public double getPercentWin(){return (double)won / (double)getPlayed() * 100.;}
	public int getBest1(){return best[0];}
	public int getBest2(){return best[1];}
	public int getBest3(){return best[2];}
	public int getFavorite(){return favorite;}

	public String toString()
	{
		return name + "," + played1 + "," + played2 + "," + played3 + "," + won + "," + best1 + "," + best2 + "," + best3 + "," + favorite;
	}
	public String toStringPretty()
	{
		String str = new String();

		str += name + ":\n\n";
		str += "Game 1-\n\tGames Played: " + played[0] + "\n\tFastest Time: " + best[0] + "\n";
		str += "Game 2-\n\tGames Played: " + played[1] + "\n\tFastest Time: " + best[1] + "\n";
		str += "Game 3-\n\tGames Played: " + played[2] + "\n\tFastest Time: " + best[2] + "\n";
		str += "Totals:\n\tTotal Played: " + getPlayed() + "\n\tGames Won: " + won;

		return str;
	}

	public void setName(String n){name = n;}
	public void setBest1(int g, int b){best[g - 1] = b;}
	
	public void incP(int game)
	{
		played[game - 1]++;
		updateFavorite();
	}

	public void incWon()
	{
		won++;
	}
}
