/*
	This stores the information of one person for the minesweeper game.
	It keeps track of name, games played, games won, best score in each game mode, and favorite game.
	An array of these will be used by the log class.
*/

public class player
{
	String name;
	int played1, played2, played3, won, best1, best2, best3, favorite;

	public player()
	{
		name = "";
		played1 = played2 = played3 = won = favorite = 0;
		best1 = best2 = best3 = 999;
	}
	
	public player(String n, int p1, int p2, int p3, int w, int b1, int b2, int b3, int f)
	{
		name = n;
		played1 = p1;
		played2 = p2;
		played3 = p3;
		won = w;
		best1 = b1;
		best2 = b2;
		best3 = b3;
		favorite = f;
	}

	public void updateFavorite()
	{
		if(played1 > played2)
		{
			if(played1 > played3)
			{
				favorite = 1;
			}
			else if(played3 > played1)
			{
				favorite = 3;
			}
		}
		else
		{
			if(played2 > played3)
			{
				favorite = 2;
			}
			else if(played3 > played2)
			{
				favorite = 3;
			}
		}
	}

	public String getName(){return name;}
	public int getPlayed(){return played1 + played2 + played3;}
	public int getPlayed1(){return played1;}
	public int getPlayed2(){return played2;}
	public int getPlayed3(){return played3;}
	public int getWon(){return won;}
	public double getPercentWin(){return (double)won / (double)getPlayed() * 100.;}
	public int getBest1(){return best1;}
	public int getBest2(){return best2;}
	public int getBest3(){return best3;}
	public int getFavorite(){return favorite;}

	public void setName(String n){name = n;}
	public void setWon(int w){won = w;}
	public void setBest1(int b){best1 = b;}
	public void setBest2(int b){best2 = b;}
	public void setBest3(int b){best3 = b;}
	
	public void incP1()
	{
		played1++;
		updateFavorite();
	}
	public void incP2()
	{
		played2++;
		updateFavorite();
	}
	public void incP3()
	{
		played3++;
		updateFavorite();
	}
}
